package com.example.simple.mall.api.mapStruct;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsInfoDTO;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsReDTO;
import com.example.simple.mall.common.entity.OrderItemsEntity;
import com.example.simple.mall.common.entity.OrderMainLogisticsEntity;
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
     * productInfo to OrderItemsEntity
     *
     * @param productInfo productInfo
     * @return {@code OrderInfo }
     * @author sunny
     * @since 2025/05/24
     */
    OrderItemsEntity orderAddDTOToOrderInfo(OrderAddInfoDTO productInfo);

    /**
     * OrderMainLogisticsEntityToOrderMainLogisticsInfoDTO
     *
     * @param orderMainLogisticsInfoDTO orderMainLogisticsInfoDTO
     * @author sunny
     * @since 2025/06/03@return @return {@code OrderMainLogisticsEntity }
     */
    OrderMainLogisticsEntity OrderMainLogisticsEntityToOrderMainLogisticsInfoDTO(OrderMainLogisticsInfoDTO orderMainLogisticsInfoDTO);

    /**
     * OrderMainLogisticsEntityToOrderMainLogisticsReDTO
     *
     * @param orderMainLogisticsEntity orderMainLogisticsEntity
     * @author sunny
     * @since 2025/06/03@return @return {@code OrderMainLogisticsReDTO }
     */
    OrderMainLogisticsReDTO OrderMainLogisticsEntityToOrderMainLogisticsReDTO(OrderMainLogisticsEntity orderMainLogisticsEntity);

}