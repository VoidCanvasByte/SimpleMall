package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.product.ProductDTO;
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
     * productDTO to ProductEntity
     *
     * @param productDTO productDTO
     * @return @return {@code ProductEntity }
     * @author sunny
     * @since 2025/05/27
     */
    ProductEntity productDTOToProductMain(ProductDTO productDTO);

    /**
     * productDTO to ProductDetailsEntity
     *
     * @param productDTO productDTO
     * @return @return {@code ProductDetailsEntity }
     * @author sunny
     * @since 2025/05/27
     */
    ProductDetailsEntity productDTOToProductDetails(ProductDTO productDTO);
}
