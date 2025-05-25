package com.example.simple.mall.common.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 订单更新DTO
 *
 * @author sunny
 * @since 2025/05/24
 */
@Data
public class OrderUpdateInfoDTO {
    /**
     * 订单ID
     */
    @NotEmpty(message = "订单ID不能为空")
    public String orderId;

    /**
     * 订单状态
     */
    @NotEmpty(message = "订单状态不能为空")
    public Integer status;
}
