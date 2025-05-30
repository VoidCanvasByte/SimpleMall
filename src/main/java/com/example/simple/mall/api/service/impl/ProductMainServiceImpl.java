package com.example.simple.mall.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductMainMapperStruct;
import com.example.simple.mall.api.mapper.ProductCategoryMapper;
import com.example.simple.mall.api.mapper.ProductDetailsMapper;
import com.example.simple.mall.api.mapper.ProductMainMapper;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.entity.ProductEntity;
import com.example.simple.mall.common.entity.ProductCategoryEntity;
import com.example.simple.mall.common.entity.ProductDetailsEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ProductDetailsMapper productDetailsMapper;

    /**
     * 分页查询
     *
     * @param page        page
     * @param size        size
     * @param productEntity productEntity
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductDTO> }
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
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/08
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProduct(ProductDTO productDTO) {
        String productCategoryId = productDTO.getProductCategoryId();
        QueryWrapper<ProductCategoryEntity> productCategoryWrapper = new QueryWrapper<ProductCategoryEntity>();
        productCategoryWrapper.eq("id", productCategoryId);
        ProductCategoryEntity productCategoryEntity = productCategoryMapper.selectOne(productCategoryWrapper);
        if (ObjectUtils.isEmpty(productCategoryEntity)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_CATEGORY_NOT_EXIST.getMessage());
        }
        //商品主表
        ProductEntity productEntity = ProductMainMapperStruct.INSTANCE.productDTOToProductMain(productDTO);
        //商品详细表
        ProductDetailsEntity productDetailsEntity = ProductMainMapperStruct.INSTANCE.productDTOToProductDetails(productDTO);

        productMainMapper.insert(productEntity);
        productDetailsMapper.insert(productDetailsEntity);
    }

    /**
     * 更新商品信息
     *
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/09
     */
    @Override
    public void updateProduct(ProductDTO productDTO) {

    }

    /**
     * 删除商品
     *
     * @param productCode productCode
     * @author sunny
     * @since 2025/05/09
     */
    @Override
    public void deleteProduct(String productCode) {

    }
}
