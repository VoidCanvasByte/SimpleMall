package com.example.simple.mall.api.mapStruct;

import com.example.simple.mall.common.dto.OrderAddDTO;
import com.example.simple.mall.common.entity.OrderInfo;
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
     * orderAddDTO to OrderInfo
     * @param orderAddDTO orderAddDTO
     * @return @return {@code OrderInfo }
     * @author sunny
     * @since 2025/05/24
     */
    OrderInfo orderAddDTOToOrderInfo(OrderAddDTO orderAddDTO);
}