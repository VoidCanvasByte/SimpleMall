package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 购物车表
 *
 * @author sunny
 * @since 2025/05/15
 */
@Data
@TableName("product_shopping_cart")
public class ShoppingCartEntity{

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
