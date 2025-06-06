package com.example.simple.mall.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductMainMapperStruct;
import com.example.simple.mall.api.mapper.ProductCategoryMapper;
import com.example.simple.mall.api.mapper.ProductImagesMapper;
import com.example.simple.mall.api.mapper.ProductMainMapper;
import com.example.simple.mall.api.service.ProductDetailsService;
import com.example.simple.mall.api.service.ProductImagesService;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.product.ProductAddInfoDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateInfoDTO;
import com.example.simple.mall.common.entity.ProductCategoryEntity;
import com.example.simple.mall.common.entity.ProductDetailsEntity;
import com.example.simple.mall.common.entity.ProductEntity;
import com.example.simple.mall.common.entity.ProductImagesEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品实现层
 *
 * @author sunny
 * @since 2025/05/08
 */
@Service
public class ProductMainServiceImpl extends ServiceImpl<ProductMainMapper, ProductEntity> implements ProductMainService {

    @Autowired
    private ProductMainMapper productMainMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductImagesService productImagesService;

    @Autowired
    private ProductDetailsService productDetailsMapperService;

    /**
     * 分页查询
     *
     * @param page          page
     * @param size          size
     * @param productEntity productEntity
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductAddInfoDTO> }
     */
    @Override
    public List<ProductEntity> queryPageList(Integer page, Integer size, ProductEntity productEntity) {
        List<ProductEntity> productEntityList = productMainMapper.selectPageList(productEntity);
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, productEntityList.size());
        return CollUtil.sub(productEntityList, fromIndex, toIndex);
    }

    /**
     * 添加商品
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @author sunny
     * @since 2025/05/08
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProduct(ProductAddInfoDTO productAddInfoDTO) {
        QueryWrapper<ProductCategoryEntity> productCategoryWrapper = new QueryWrapper<ProductCategoryEntity>();
        productCategoryWrapper.eq("id", productAddInfoDTO.getProductCategoryId());
        ProductCategoryEntity productCategoryEntity = productCategoryMapper.selectOne(productCategoryWrapper);
        if (ObjectUtils.isEmpty(productCategoryEntity)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_CATEGORY_NOT_EXIST.getMessage());
        }
        ProductEntity productEntity = ProductMainMapperStruct.INSTANCE.productDTOToProductMain(productAddInfoDTO);
        productEntity.setDescription(productAddInfoDTO.getProductDesc());
        ProductDetailsEntity productDetailsEntity = ProductMainMapperStruct.INSTANCE.productDTOToProductDetails(productAddInfoDTO);
        System.out.printf("ProductEntity: %s, ProductDetailsEntity: %s", productEntity, productDetailsEntity);
        this.save(productEntity);
        productDetailsMapperService.save(productDetailsEntity);

        //图片信息
        List<ProductAddInfoDTO.ProductImg> productImgList = productAddInfoDTO.getProductImgList();
        if (CollUtil.isNotEmpty(productImgList)) {
            List<ProductImagesEntity> ProductImagesEntityList = getProductImagesEntities(productAddInfoDTO, productImgList);
            productImagesService.saveBatch(ProductImagesEntityList);
        }
    }

    /**
     * 添加图片
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @param productImgList    productImgList
     * @return @return {@code List<ProductImagesEntity> }
     * @author sunny
     * @since 2025/06/06
     */
    private List<ProductImagesEntity> getProductImagesEntities(ProductAddInfoDTO productAddInfoDTO, List<ProductAddInfoDTO.ProductImg> productImgList) {
        List<ProductImagesEntity> ProductImagesEntityList = new ArrayList<>(productImgList.size());
        for (ProductAddInfoDTO.ProductImg item : productImgList) {
            ProductImagesEntity productImagesEntity = new ProductImagesEntity();
            productImagesEntity.setProductCode(productAddInfoDTO.getProductCode());
            productImagesEntity.setUrl(item.getUrl());
            productImagesEntity.setSortOrder(item.getSortOrder());
            ProductImagesEntityList.add(productImagesEntity);
        }
        return ProductImagesEntityList;
    }

    /**
     * 更新商品信息
     *
     * @param productUpdateInfoDTO productUpdateInfoDTO
     * @author sunny
     * @since 2025/05/09
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ProductUpdateInfoDTO productUpdateInfoDTO) {
        //每次更新商品的信息，都会在原来的商品详情中添加版本号➕1
        String productCode = productUpdateInfoDTO.getProductCode();
        ProductEntity productEntity = ProductMainMapperStruct.INSTANCE.productUpdateDTOToProductMain(productUpdateInfoDTO);
        this.updateById(productEntity);

        ProductDetailsEntity productDetailsEntityRe = ProductMainMapperStruct.INSTANCE.productUpdateDTOToProductDetail(productUpdateInfoDTO);
        if (ObjectUtils.isNotEmpty(productUpdateInfoDTO.getProductDetailsId())) {
            QueryWrapper<ProductDetailsEntity> productDetailsEntityQueryWrapper = new QueryWrapper<>();
            productDetailsEntityQueryWrapper.eq("product_code", productCode);
            ProductDetailsEntity productDetailsEntity = productDetailsMapperService.getOne(productDetailsEntityQueryWrapper);
            if (ObjectUtils.isEmpty(productDetailsEntity)) {
                productDetailsEntityRe.setVersion(1);
            } else {
                productDetailsEntity.setVersion(productDetailsEntity.getVersion() + 1);
            }
            productDetailsEntityRe.setId(productUpdateInfoDTO.getProductDetailsId());
            productDetailsEntityRe.setProductCode(productUpdateInfoDTO.getProductCode());
            productDetailsMapperService.updateById(productDetailsEntityRe);
        }


        if (CollUtil.isNotEmpty(productUpdateInfoDTO.getProductImagesList())) {
            List<ProductImagesEntity> productImagesEntityList = new ArrayList<>();
            //批量删除旧图片，批量插入新的图片
            QueryWrapper<ProductImagesEntity> productImagesEntityQueryWrapper = new QueryWrapper<>();
            productImagesEntityQueryWrapper.eq("product_code", productUpdateInfoDTO.getProductCode());
            List<ProductImagesEntity> productImagesList = productImagesService.list(productImagesEntityQueryWrapper);
            if (CollUtil.isNotEmpty(productImagesList)) {
                List<Long> listId = productImagesList.stream().map(ProductImagesEntity::getId).toList();
                productImagesService.removeByIds(listId);
            }
            for (ProductUpdateInfoDTO.ProductImages productImages : productUpdateInfoDTO.getProductImagesList()) {
                ProductImagesEntity productImagesEntity = new ProductImagesEntity();
                productImagesEntity.setProductCode(productUpdateInfoDTO.getProductCode());
                productImagesEntity.setUrl(productImages.getUrl());
                productImagesEntity.setSortOrder(productImages.getSortOrder());
                productImagesEntityList.add(productImagesEntity);
            }
            productImagesService.saveBatch(productImagesEntityList);
        } else {
            //批量删除图片
            QueryWrapper<ProductImagesEntity> productImagesEntityQueryWrapper = new QueryWrapper<>();
            productImagesEntityQueryWrapper.eq("product_code", productUpdateInfoDTO.getProductCode());
            List<ProductImagesEntity> productImagesList = productImagesService.list(productImagesEntityQueryWrapper);
            if (CollUtil.isNotEmpty(productImagesList)) {
                List<Long> listId = productImagesList.stream().map(ProductImagesEntity::getId).toList();
                productImagesService.removeByIds(listId);
            }
        }
    }

    /**
     * 删除商品
     *
     * @param productCode productCode
     * @author sunny
     * @since 2025/05/09
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(String productCode) {
        QueryWrapper<ProductEntity> productEntityQueryWrapper = new QueryWrapper<>();
        productEntityQueryWrapper.eq("product_code", productCode);
        ProductEntity productEntity = this.getOne(productEntityQueryWrapper);
        if (ObjectUtils.isEmpty(productEntity)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        QueryWrapper<ProductDetailsEntity> productDetailsEntityQueryWrapper = new QueryWrapper<>();
        productDetailsEntityQueryWrapper.eq("product_code", productCode);
        ProductDetailsEntity productDetailsEntity = productDetailsMapperService.getOne(productDetailsEntityQueryWrapper);
        this.removeById(productEntity);
        if (!ObjectUtils.isEmpty(productDetailsEntity)) {
            productDetailsMapperService.removeById(productDetailsEntity);
        }
        //批量删除图片
        QueryWrapper<ProductImagesEntity> productImagesEntityQueryWrapper = new QueryWrapper<>();
        productImagesEntityQueryWrapper.eq("product_code", productCode);
        List<ProductImagesEntity> productImagesList = productImagesService.list(productImagesEntityQueryWrapper);
        if (!CollectionUtils.isEmpty(productImagesList)) {
            productImagesService.removeByIds(productImagesList);
        }
    }

}
