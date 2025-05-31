package com.example.simple.mall.common.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品信息主表
 *
 * @author sunny
 * @since 2025/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("product")
public class ProductEntity extends BaseEntity implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
