package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.product.ProductCategoryReturnDTO;
import com.example.simple.mall.common.entity.ProductCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryMapperStruct {

    ProductCategoryMapperStruct INSTANCE = Mappers.getMapper(ProductCategoryMapperStruct.class);

    /**
     * 单个对象的映射
     *
     * @param entity entity
     * @return @return {@code ProductCategoryReturnDTO }
     * @author sunny
     * @since 2025/05/31
     */
    ProductCategoryReturnDTO toDto(ProductCategoryEntity entity);

    /**
     * productCategoryEntitiesToDTO
     *
     * @param productCategoryEntities productCategoryEntities
     * @return @return {@code List<ProductCategoryReturnDTO> }
     * @author sunny
     * @since 2025/05/31
     */
    ProductCategoryReturnDTO productCategoryEntitiesToDTO(ProductCategoryEntity productCategoryEntities);
}
