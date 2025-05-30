package com.example.simple.mall.api.mapStruct;


import com.example.simple.mall.common.dto.CartItemDTO;
import com.example.simple.mall.common.entity.ShoppingCartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * CartItemMapperStruct
 *
 * @author sunny
 * @since 2025/05/15
 */
@Mapper
public interface CartItemMapperStruct {

    CartItemMapperStruct INSTANCE = Mappers.getMapper(CartItemMapperStruct.class);

    /**
     * dto to entity
     *
     * @param cartItemDTO cartItemDTO
     * @return @return {@code ShoppingCartItemEntity }
     * @author sunny
     * @since 2025/05/15
     */
    ShoppingCartItemEntity cartItemDtoToEntity(CartItemDTO cartItemDTO);
}
