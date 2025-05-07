package com.example.simple.mall.common.entity;

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
public class ProductDetails extends BaseEntity {

    /**
     * 商品代码
     */
    public String productCode;


    /**
     * 商品数量
     */
    public String productQuantity;


    /**
     * 商品描述
     */
    public String productDesc;

    /**
     * 商品图片
     */
    public String productImg;

    /**
     * 商品价格
     */
    public BigDecimal productPrice;
}
