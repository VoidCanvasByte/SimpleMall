package com.example.simple.mall.common.dto.order;

import lombok.Data;

/**
 * 订单信息添加返回信息
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
public class OrderReDTO {

    /**
     * 订单ID
     */
    public String orderId;

    /**
     * 订单状态
     */
    public Integer status;
}
