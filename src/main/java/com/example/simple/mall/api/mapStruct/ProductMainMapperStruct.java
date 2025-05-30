package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.entity.Product;
import com.example.simple.mall.common.entity.ProductDetails;
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
     * productDTO to Product
     *
     * @param productDTO productDTO
     * @return @return {@code Product }
     * @author sunny
     * @since 2025/05/27
     */
    Product productDTOToProductMain(ProductDTO productDTO);

    /**
     * productDTO to ProductDetails
     *
     * @param productDTO productDTO
     * @return @return {@code ProductDetails }
     * @author sunny
     * @since 2025/05/27
     */
    ProductDetails productDTOToProductDetails(ProductDTO productDTO);
}
