package com.example.simple.mall.common.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "购物车主表ID")
    @NotNull(message = "购物车主表ID")
    private Long cartId;

    /**
     * 商品变体ID
     */
    @Schema(description = "商品变体ID")
    @NotNull(message = "商品变体ID不能为空")
    private Long variantId;

    /**
     * 商品数量
     */
    @NotNull(message = "用户ID不能为空")
    private Integer quantity;

    /**
     * 用户ID（用于用户校验验证）
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
