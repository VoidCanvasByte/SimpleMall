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
    PENDING_PAYMENT("待付款", 1),
    PAID("已付款", 2),

    // -------------------------------------------- 发货 -------------------------------------------------------------
    PENDING_SHIPMENT("待发货", 4),
    SHIPPED("已发货", 5),

    // -------------------------------------------- 配送 -------------------------------------------------------------
    IN_TRANSIT("配送中", 6),
    COMPLETED("已完成", 7),
    CANCELLED("已取消", 8);
    private final String description;
    private final Integer code;
}
