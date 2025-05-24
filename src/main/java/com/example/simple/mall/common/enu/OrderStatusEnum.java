package com.example.simple.mall.common.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author sunny
 * @since 2025/05/24
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    PENDING_PAYMENT("待付款",1),
    PAID("已付款",2),
    PENDING_SHIPMENT("待发货",3),
    SHIPPED("已发货",4),
    IN_TRANSIT("配送中",5),
    COMPLETED("已完成",6),
    CANCELLED("已取消",7),
    REFUNDING("退款中",8),
    REFUNDED("已退款",9),
    EXCEPTION("异常",10);
    private final String description;
    private final Integer code;
}
