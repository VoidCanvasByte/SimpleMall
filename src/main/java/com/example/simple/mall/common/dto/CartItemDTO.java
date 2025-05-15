package com.example.simple.mall.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 购物车
 *
 * @author sunny
 * @since 2025/05/15
 */

@Data
public class CartItemDTO {

    /**
     * 购物车主表ID
     */
    @NotNull(message = "购物车主表ID")
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 商品ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long productMainId;

    /**
     * 商品数量
     */
    @NotNull(message = "用户ID不能为空")
    private Integer quantity;

    /**
     * 1:添加商品数量；2:减少商品数量
     */
    private Integer label;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
