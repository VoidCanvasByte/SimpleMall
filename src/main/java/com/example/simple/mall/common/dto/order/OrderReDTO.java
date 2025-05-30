package com.example.simple.mall.common.dto.order;

import jakarta.validation.constraints.NotBlank;
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
     * 支付订单ID
     */
    @NotBlank(message = "支付订单ID不能为空")
    public String payOrder;

    /**
     * 订单ID
     */
    @NotBlank(message = "订单ID不能为空")
    public Long orderId;

    /**
     * 订单状态
     */
    @NotBlank(message = "订单状态不能为空")
    public Integer status;
}
