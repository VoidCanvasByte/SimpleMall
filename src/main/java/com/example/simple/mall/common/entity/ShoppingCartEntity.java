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
@TableName("shopping_cart")
public class ShoppingCartEntity extends BaseEntity implements java.io.Serializable {

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
}
