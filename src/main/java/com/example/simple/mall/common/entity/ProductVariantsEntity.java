package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * ProductVariantsEntity
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
@TableName("product_variants")
@EqualsAndHashCode(callSuper = true)
public class ProductVariantsEntity extends BaseEntity {

    /**
     * 商品ID
     */
    @TableField("product_id")
    public Long productId;

    /**
     * 库存单位唯一编码
     */
    @TableField("sku")
    public String sku;

    /**
     * 尺码（如 S/M/L/XL）
     */
    @TableField("size")
    public String size;

    /**
     * 颜色
     */
    @TableField("color")
    public String color;

    /**
     * 售价
     */
    @TableField("price")
    public BigDecimal price;

    /**
     * 成本价
     */
    @TableField("cost_price")
    public BigDecimal costPrice;

    /**
     * 库存数量
     */
    @TableField("stock_quantity")
    public Integer stockQuantity;
}
