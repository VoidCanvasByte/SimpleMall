package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductFavoritesItemsMapperStruct;
import com.example.simple.mall.api.mapper.ProductFavoritesItemsMapper;
import com.example.simple.mall.api.mapper.ProductFavoritesMapper;
import com.example.simple.mall.api.mapper.ProductVariantsMapper;
import com.example.simple.mall.api.service.ProductFavoritesItemsService;
import com.example.simple.mall.common.dto.product.ProductFavoritesItemsInfoDTO;
import com.example.simple.mall.common.entity.ProductFavoritesEntity;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;
import com.example.simple.mall.common.entity.ProductVariantsEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductFavoritesItemsServiceImpl
 *
 * @author sunny
 * @since 2025/06/03
 */
@Service
public class ProductFavoritesItemsServiceImpl extends ServiceImpl<ProductFavoritesItemsMapper, ProductFavoritesItemsEntity> implements ProductFavoritesItemsService {


    @Autowired
    public ProductFavoritesMapper productFavoritesMapper;

    @Autowired
    public ProductVariantsMapper  productVariantsMapper;

    /**
     * 添加收藏夹
     *
     * @param productFavoritesItemsInfoDTO productFavoritesItemsInfoDTO
     * @author sunny
     * @since 2025/06/02
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProductFavorites(ProductFavoritesItemsInfoDTO productFavoritesItemsInfoDTO) {
        QueryWrapper<ProductVariantsEntity> productVariantsEntityQueryWrapper = new QueryWrapper<>();
        productVariantsEntityQueryWrapper.eq("id", productFavoritesItemsInfoDTO.getVariantId());
        ProductVariantsEntity productVariantsEntity = productVariantsMapper.selectOne(productVariantsEntityQueryWrapper);
        if (ObjectUtils.isEmpty(productVariantsEntity)){
            throw new RuntimeException(ResponseEnum.PRODUCT_VARIANTS_NOT_EXIST.getMessage());
        }
        Long userId = productFavoritesItemsInfoDTO.getUserId();
        QueryWrapper<ProductFavoritesEntity> ProductFavoritesEntityQueryWrapper = new QueryWrapper<>();
        ProductFavoritesEntityQueryWrapper.eq("user_id", userId);
        ProductFavoritesEntity productFavoritesEntity = productFavoritesMapper.selectOne(ProductFavoritesEntityQueryWrapper);
        ProductFavoritesItemsEntity productFavoritesItemsEntity = ProductFavoritesItemsMapperStruct.INSTANCE.productFavoritesItemsInfoDTOToProductFavoritesItemsEntity(productFavoritesItemsInfoDTO);
        if (ObjectUtils.isEmpty(productFavoritesEntity)) {
            //添加到收藏夹明细
            ProductFavoritesEntity productFavoritesEntityTemp = ProductFavoritesItemsMapperStruct.INSTANCE.productFavoritesItemsInfoDTOToProductFavoritesEntity(productFavoritesItemsInfoDTO);
            productFavoritesMapper.insert(productFavoritesEntityTemp);
            productFavoritesItemsEntity.setFavoritesId(productFavoritesEntityTemp.getId());
        }else{
            productFavoritesItemsEntity.setFavoritesId(productFavoritesEntity.getId());
        }
        this.save(productFavoritesItemsEntity);
    }
}
