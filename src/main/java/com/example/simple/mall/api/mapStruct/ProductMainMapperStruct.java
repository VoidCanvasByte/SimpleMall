package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.product.ProductAddInfoDTO;
import com.example.simple.mall.common.entity.ProductEntity;
import com.example.simple.mall.common.entity.ProductDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ProductMainMapperStruct
 *
 * @author sunny
 * @since 2025/05/27
 */
@Mapper
public interface ProductMainMapperStruct {
    ProductMainMapperStruct INSTANCE = Mappers.getMapper(ProductMainMapperStruct.class);

    /**
     * productAddInfoDTO to ProductEntity
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @return @return {@code ProductEntity }
     * @author sunny
     * @since 2025/05/27
     */
    ProductEntity productDTOToProductMain(ProductAddInfoDTO productAddInfoDTO);

    /**
     * productAddInfoDTO to ProductDetailsEntity
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @return @return {@code ProductDetailsEntity }
     * @author sunny
     * @since 2025/05/27
     */
    ProductDetailsEntity productDTOToProductDetails(ProductAddInfoDTO productAddInfoDTO);
}
