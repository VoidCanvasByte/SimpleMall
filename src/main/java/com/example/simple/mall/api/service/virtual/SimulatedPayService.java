package com.example.simple.mall.api.service.virtual;

/**
 * 虚拟支付服务
 *
 * @author sunny
 * @since 2025/05/25
 */
public interface SimulatedPayService {

    /**
     * 虚拟支付服务
     *
     * @param orderId 订单ID
     * @author sunny
     * @since 2025/05/25
     */
    Boolean pay(Long orderId);
}
