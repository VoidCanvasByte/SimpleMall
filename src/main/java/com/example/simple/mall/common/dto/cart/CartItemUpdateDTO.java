package com.example.simple.mall.common.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * CartItemUpdateDTO
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class CartItemUpdateDTO {

    /**
     * 购物车主表ID
     */
    @NotNull(message = "购物车主表ID")
    private Long cartId;

    /**
     * 商品变体ID
     */
    @NotNull(message = "商品变体ID不能为空")
    private Long variantId;

    /**
     * 商品数量
     */
    @NotNull(message = "用户ID不能为空")
    private Integer quantity;
}
