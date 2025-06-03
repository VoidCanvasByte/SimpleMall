package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductFavoritesItemsMapperStruct;
import com.example.simple.mall.api.mapper.ProductFavoritesItemsMapper;
import com.example.simple.mall.api.mapper.ProductFavoritesMapper;
import com.example.simple.mall.api.mapper.ProductVariantsMapper;
import com.example.simple.mall.api.service.ProductFavoritesItemsService;
import com.example.simple.mall.api.service.ProductFavoritesService;
import com.example.simple.mall.common.dto.favorites.ProductFavoritesDTO;
import com.example.simple.mall.common.dto.product.ProductFavoritesItemsInfoDTO;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.entity.ProductFavoritesEntity;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;
import com.example.simple.mall.common.entity.ProductVariantsEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductFavoritesItemsServiceImpl
 *
 * @author sunny
 * @since 2025/06/03
 */
@Service
public class ProductFavoritesServiceImpl extends ServiceImpl<ProductFavoritesMapper, ProductFavoritesEntity> implements ProductFavoritesService {


    @Autowired
    public ProductFavoritesMapper productFavoritesMapper;

    @Autowired
    public ProductFavoritesItemsMapper productFavoritesItemsMapper;

    @Autowired
    public ProductFavoritesItemsService productFavoritesItemsService;

    @Autowired
    public ProductVariantsMapper productVariantsMapper;

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
        if (ObjectUtils.isEmpty(productVariantsEntity)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_VARIANTS_NOT_EXIST.getMessage());
        }
        Long userId = productFavoritesItemsInfoDTO.getUserId();
        QueryWrapper<ProductFavoritesEntity> ProductFavoritesEntityQueryWrapper = new QueryWrapper<>();
        ProductFavoritesEntityQueryWrapper.eq("user_id", userId);
        ProductFavoritesEntity productFavoritesEntity = productFavoritesMapper.selectOne(ProductFavoritesEntityQueryWrapper);


        ProductFavoritesItemsEntity productFavoritesItemsEntity = ProductFavoritesItemsMapperStruct.INSTANCE.productFavoritesItemsInfoDTOToProductFavoritesItemsEntity(productFavoritesItemsInfoDTO);
        if (ObjectUtils.isEmpty(productFavoritesEntity)) {
            ProductFavoritesEntity productFavoritesEntityTemp = ProductFavoritesItemsMapperStruct.INSTANCE.productFavoritesItemsInfoDTOToProductFavoritesEntity(productFavoritesItemsInfoDTO);
            productFavoritesMapper.insert(productFavoritesEntityTemp);
            productFavoritesItemsEntity.setFavoritesId(productFavoritesEntityTemp.getId());
        } else {
            productFavoritesItemsEntity.setFavoritesId(productFavoritesEntity.getId());
        }

        productFavoritesItemsService.save(productFavoritesItemsEntity);
    }

    /**
     * 获取收藏夹全部信息
     *
     * @param userBaseDTO userBaseDTO
     * @author sunny
     * @since 2025/06/03@return @return {@code List<ProductFavoritesDTO> }
     */
    @Override
    public ProductFavoritesDTO getAllProductFavorites(UserBaseDTO userBaseDTO) {
        QueryWrapper<ProductFavoritesEntity> ProductFavoritesEntityQueryWrapper = new QueryWrapper<>();
        ProductFavoritesEntityQueryWrapper.eq("user_id", userBaseDTO.getUserId());
        ProductFavoritesEntity productFavoritesEntity = productFavoritesMapper.selectOne(ProductFavoritesEntityQueryWrapper);
        ProductFavoritesDTO userBaseDTOToProductFavoritesDTO = ProductFavoritesItemsMapperStruct.INSTANCE.userBaseDTOToProductFavoritesDTO(userBaseDTO);

        List<ProductFavoritesDTO.ProductFavoritesItemDTO> tempList = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(productFavoritesEntity)) {
            QueryWrapper<ProductFavoritesItemsEntity> productFavoritesItemsEntityQueryWrapper = new QueryWrapper<>();
            productFavoritesItemsEntityQueryWrapper.eq("favorites_id", productFavoritesEntity.getId());
            List<ProductFavoritesItemsEntity> list = productFavoritesItemsService.list(productFavoritesItemsEntityQueryWrapper);
            for (ProductFavoritesItemsEntity item : list) {
                ProductFavoritesDTO.ProductFavoritesItemDTO productFavoritesItemDTO = ProductFavoritesItemsMapperStruct.INSTANCE.productFavoritesItemsEntityToProductFavoritesDTO(item);
                tempList.add(productFavoritesItemDTO);
            }
        }
        userBaseDTOToProductFavoritesDTO.getProductFavoritesItemDTOList().addAll(tempList);
        return userBaseDTOToProductFavoritesDTO;
    }
}
