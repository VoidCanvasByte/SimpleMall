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
@TableName("product_main")
public class ProductMain extends BaseEntity implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品货号
     */
    @TableField("product_code")
    public String productCode;

    /**
     * 商品名称
     */
    @TableField("product_name")
    public String productName;

    /**
     * 商品状态：1正常，2售罄
     */
    @TableField("status")
    public Integer status;

    /**
     * 商品分类id
     */
    @TableField("product_category_id")
    public String productCategoryId;
}
