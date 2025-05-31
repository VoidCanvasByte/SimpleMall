package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 购物车表
 *
 * @author sunny
 * @since 2025/05/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("product_shopping_cart_item")
public class ShoppingCartItemEntity extends BaseEntity implements java.io.Serializable {

    /**
     * 所属购物车
     */
    @TableField("cart_id")
    private Long cartId;

    /**
     * 商品变体
     */
    @TableField("variant_id")
    private Long variantId;

    /**
     * 商品数量
     */
    @TableField("quantity")
    private Integer quantity;
}
