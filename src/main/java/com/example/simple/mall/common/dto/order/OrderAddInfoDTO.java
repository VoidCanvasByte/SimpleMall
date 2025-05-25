package com.example.simple.mall.common.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单创建DTO
 *
 * @author sunny
 * @since 2025/05/24
 */
@Data
public class OrderAddInfoDTO {

    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    public String userId;

    /**
     * 产品ID
     */
    @NotBlank(message = "产品ID不能为空")
    public String productId;

    /**
     * 产品数量
     */
    @NotNull(message = "商品数量不能为空")
    @DecimalMin(value = "1", message = "商品数量不能小于1")
    public BigDecimal quantity;

    /**
     * 购物车主表ID
     */
    @NotNull(message = "购物车ID不能为空")
    public Integer cartItemId;
}