package com.example.simple.mall.common.dto.cart;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * CartUpdateDTO
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class CartUpdateDTO {

    /**
     * 购物车主表ID
     */
    @NotNull(message = "购物车主表ID")
    private Long id;

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

    /**
     * 1:添加商品数量
     * 2:减少商品数量
     */
    @NotNull(message = "用户ID不能为空")
    private Integer label;

    /**
     * 用户ID（用于用户校验验证）
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
