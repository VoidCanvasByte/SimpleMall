package com.example.simple.mall.api.mapStruct;


import com.example.simple.mall.common.dto.cart.CartItemAddDTO;
import com.example.simple.mall.common.entity.ShoppingCartEntity;
import com.example.simple.mall.common.entity.ShoppingCartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * CartMapperStruct
 *
 * @author sunny
 * @since 2025/05/15
 */
@Mapper
public interface CartMapperStruct {

    CartMapperStruct INSTANCE = Mappers.getMapper(CartMapperStruct.class);

    /**
     * dto to entity
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @return @return {@code ShoppingCartItemEntity }
     * @author sunny
     * @since 2025/05/15
     */
    ShoppingCartItemEntity cartItemDtoToEntity(CartItemAddDTO cartItemAddDTO);

    /**
     * cartItemDTOToEntity
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @return @return {@code ShoppingCartEntity }
     * @author sunny
     * @since 2025/05/31
     */
    ShoppingCartEntity cartItemDTOToEntity(CartItemAddDTO cartItemAddDTO);
}
