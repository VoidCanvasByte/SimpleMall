package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.OrderAddDTO;
import com.example.simple.mall.common.entity.OrderMain;

/**
 * 订单信息
 *
 * @author sunny
 * @since 2025/05/09
 */
public interface OrderService extends IService<OrderMain> {

    /**
     * 添加订单
     *
     * @param orderAddDTO orderAddDTO
     * @author sunny
     * @since 2025/05/24
     */
    void addOrder(OrderAddDTO orderAddDTO);
}