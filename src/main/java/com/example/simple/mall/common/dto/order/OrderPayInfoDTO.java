package com.example.simple.mall.common.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 订单支付DTO
 *
 * @author sunny
 * @since 2025/05/24
 */
@Data
public class OrderPayInfoDTO {

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    @NotBlank(message = "订单ID不能为空")
    public Long orderId;
}
