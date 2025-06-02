package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderReDTO;
import com.example.simple.mall.common.dto.order.OrderPayInfoDTO;
import com.example.simple.mall.common.entity.OrderMainEntity;

import java.util.List;

/**
 * 订单信息
 *
 * @author sunny
 * @since 2025/05/09
 */
public interface OrderService extends IService<OrderMainEntity> {

    /**
     * 创建订单
     *
     * @param orderAddInfoDTO orderAddInfoDTO
     * @author sunny
     * @since 2025/05/24@return {@code OrderReDTO }
     */
    void addOrder(List<OrderAddInfoDTO> orderAddInfoDTO) throws Exception;

    /**
     * 订单支付
     *
     * @param orderPayInfoDTO orderPayInfoDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductAddInfoDTO> }
     */
    void orderPay(OrderPayInfoDTO orderPayInfoDTO);

    /**
     * 订单信息更新(支付三方回调接口)
     *
     * @param orderReDTO orderReDTO
     * @author sunny
     * @since 2025/05/25@return
     */
    void orderUpdate(OrderReDTO orderReDTO);

    /**
     * 删除订单
     * @param id id
     * @author sunny
     * @since 2025/06/02
     */
    void deleteOrderById(String id);
}