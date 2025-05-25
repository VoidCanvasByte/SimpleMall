package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderReDTO;
import com.example.simple.mall.common.dto.order.OrderUpdateInfoDTO;
import com.example.simple.mall.common.entity.OrderMain;

/**
 * 订单信息
 *
 * @author sunny
 * @since 2025/05/09
 */
public interface OrderService extends IService<OrderMain> {

    /**
     * 创建订单
     *
     * @param orderAddInfoDTO orderAddInfoDTO
     * @return {@code OrderReDTO }
     * @author sunny
     * @since 2025/05/24
     */
    OrderReDTO addOrder(OrderAddInfoDTO orderAddInfoDTO);

    /**
     * 更新订单
     *
     * @param orderUpdateInfoDTO orderUpdateInfoDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    void updateOrder(OrderUpdateInfoDTO orderUpdateInfoDTO);
}