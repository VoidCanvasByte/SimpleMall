package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.product.ProductFavoritesItemsInfoDTO;
import com.example.simple.mall.common.entity.ProductFavoritesEntity;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductFavoritesItemsMapperStruct {

    ProductFavoritesItemsMapperStruct INSTANCE = Mappers.getMapper(ProductFavoritesItemsMapperStruct.class);

    /**
     * productFavoritesItemsInfoDTOToProductFavoritesItemsEntity
     *
     * @param productFavoritesItemsInfoDTO productFavoritesItemsInfoDTO
     * @return @return {@code ProductFavoritesItemsEntity }
     * @author sunny
     * @since 2025/06/02
     */
    ProductFavoritesItemsEntity productFavoritesItemsInfoDTOToProductFavoritesItemsEntity(ProductFavoritesItemsInfoDTO productFavoritesItemsInfoDTO);

    /**
     * productFavoritesItemsInfoDTOToProductFavoritesEntity
     *
     * @param productFavoritesItemsInfoDTO productFavoritesItemsInfoDTO
     * @return @return {@code ProductFavoritesEntity }
     * @author sunny
     * @since 2025/06/03
     */
    ProductFavoritesEntity productFavoritesItemsInfoDTOToProductFavoritesEntity(ProductFavoritesItemsInfoDTO productFavoritesItemsInfoDTO);
}
