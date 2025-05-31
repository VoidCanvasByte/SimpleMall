package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 商品详细信息表
 *
 * @author sunny
 * @since 2025/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("product_details")
public class ProductDetailsEntity extends BaseEntity {

    /**
     * 商品货号
     */
    @TableField("product_code")
    public String productCode;

    /**
     * 商品数量
     */
    @TableField("product_quantity")
    public Integer productQuantity;

    /**
     * 商品描述
     */
    @TableField("product_desc")
    public String productDesc;

    /**
     * 商品单价
     */
    @TableField("product_price")
    public BigDecimal productPrice;

    /**
     * 商品图片
     */
    @TableField("product_img")
    public String productImg;

    /**
     * 材质
     */
    @TableField("material")
    public String material;

    /**
     * 尺寸
     */
    @TableField("size")
    public String size;

    /**
     * 版本号
     */
    @TableField("version")
    public Integer version;
}
