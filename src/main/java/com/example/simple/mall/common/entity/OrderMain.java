package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品信息分类表
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@TableName("Order_main")
@EqualsAndHashCode(callSuper = true)
public class OrderMain extends BaseEntity {

    /**
     * 用户id
     */
    @TableField("user_id")
    public String userId;

    /**
     * 数量
     */
    @TableField("total_amount")
    public String totalAmount;

    /**
     * 订单状态
     */
    @TableField("status")
    public String status;
}