package com.example.simple.mall.common.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 商品信息主表
 *
 * @author sunny
 * @since 2025/05/08
 */
@Data
@TableName("product")
public class ProductEntity{


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

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    /**
     * 商品分类id
     */
    @TableField("product_category_id")
    public Long productCategoryId;

    /**
     * 商品名称
     */
    @TableField("product_name")
    public String productName;

    /**
     * 商品货号
     */
    @TableField("product_code")
    public String productCode;

    /**
     * 商品描述
     */
    @TableField("description")
    public String description;

    /**
     * 品牌
     */
    @TableField("brand")
    public String brand;

    /**
     * 商品状态：1上架，2下架
     */
    @TableField("status")
    public Integer status;
}
