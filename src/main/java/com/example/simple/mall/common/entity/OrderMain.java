package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品信息分类表
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@TableName("order_main")
public class OrderMain {

    /**
     * 订单ID
     */
    public String id;

    /**
     * 用户id
     */
    @TableField("user_id")
    public String userId;

    /**
     * 订单状态(1:待付款；2:已付款；3:待发货；4:已发货；5:配送中；6:已完成；7:已取消；8:退款中；9:已退款；10:异常)
     */
    @TableField("status")
    public Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;
}