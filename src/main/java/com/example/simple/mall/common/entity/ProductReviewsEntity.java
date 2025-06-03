package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * 商品评价表
 *
 * @author sunny
 * @since 2025-06-03
 */
@Data
@TableName("product_reviews")
public class ProductReviewsEntity {

    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * 评价商品id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 评价用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 评分（1–5)
     */
    @TableField("rating")
    private Integer rating;

    /**
     * 评价内容
     */
    @TableField("comment")
    private String comment;

    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 添加时间
     */
    @TableField("update_time")
    private Date updateTime;
}
