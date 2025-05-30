package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.entity.OrderItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * OrderMapperStruct
 *
 * @author sunny
 * @since 2025/05/24
 */
@Mapper
public interface OrderMapperStruct {
    OrderMapperStruct INSTANCE = Mappers.getMapper(OrderMapperStruct.class);

    /**
     * productInfo to OrderItems
     *
     * @param productInfo productInfo
     * @return {@code OrderInfo }
     * @author sunny
     * @since 2025/05/24
     */
    OrderItems orderAddDTOToOrderInfo(OrderAddInfoDTO.ProductInfo productInfo);
}