package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品图片
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
@TableName("product_images")
public class ProductImagesEntity {

    /**
     * 主键ID
     */
    @TableField("id")
    public Long id;

    /**
     * 所属商品
     */
    @TableField("product_code")
    public String productCode;

    /**
     * 如对应变体时填写
     */
    @TableField("variant_id")
    public Long variantId;

    /**
     * 图片URL
     */
    @TableField("url")
    public String url;

    /**
     * 显示顺序
     */
    @TableField("sort_order")
    public Integer sortOrder;

}
