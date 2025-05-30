package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderReDTO;
import com.example.simple.mall.common.dto.order.OrderPayInfoDTO;
import com.example.simple.mall.common.entity.Order;

/**
 * 订单信息
 *
 * @author sunny
 * @since 2025/05/09
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     *
     * @param orderAddInfoDTO orderAddInfoDTO
     * @author sunny
     * @since 2025/05/24@return {@code OrderReDTO }
     */
    void addOrder(OrderAddInfoDTO orderAddInfoDTO);

    /**
     * 订单支付
     *
     * @param orderPayInfoDTO orderPayInfoDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    void orderPay(OrderPayInfoDTO orderPayInfoDTO);

    /**
     * orderReDTO
     *
     * @param orderReDTO orderReDTO
     * @author sunny
     * @since 2025/05/25@return
     */
    void orderUpdate(OrderReDTO orderReDTO);
}