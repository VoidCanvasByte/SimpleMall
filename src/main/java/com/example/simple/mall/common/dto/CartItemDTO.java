package com.example.simple.mall.common.dto;

import lombok.Data;

/**
 * 购物车
 *
 * @author sunny
 * @since 2025/05/15
 */

@Data
public class CartItemDTO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品数量
     */
    private Integer quantity;


    /**
     * 用户ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
