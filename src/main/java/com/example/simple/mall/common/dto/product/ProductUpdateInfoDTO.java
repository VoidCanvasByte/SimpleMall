package com.example.simple.mall.common.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息更新表
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class ProductUpdateInfoDTO {

    /**
     * 商品基础信息
     */
    @NotNull(message = "商品基础信息不能为空")
    public Product product = new Product();

    /**
     * 商品基础信息
     */
    public ProductDetail productDetail = new ProductDetail();

    @Data
    public static class Product {

        /**
         * 商品表ID
         */
        @NotNull(message = "商品表ID不能为空")
        public Long id;

        /**
         * 用户ID
         */
        public Long userId;

        /**
         * 商品标题
         */
        public String productName;

        /**
         * 商品货号
         */
        @NotNull(message = "商品货号不能为空")
        public String productCode;

        /**
         * 商品描述（详情）
         */
        public String description;

        /**
         * 品牌
         */
        public String brand;

        /**
         * 上下架状态（1：上架；2:下架）
         */
        public Integer status;
    }

    @Data
    public static class ProductDetail {

        /**
         * id
         */
        public Long id;

        /**
         * 商品数量
         */
        public Integer productQuantity;


        /**
         * 商品单价
         */
        public BigDecimal productPrice;

        /**
         * 商品图片
         */
        public String productImg;

        /**
         * 材质
         */
        public String material;

        /**
         * 尺寸
         */
        public String size;

        /**
         * 版本
         */
        public Integer version;
    }

}
