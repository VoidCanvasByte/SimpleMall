package com.example.simple.mall.common.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 更新商品分类DTO
 *
 * @author sunny
 * @since 2025/05/25
 */
@Data
public class ProductUpdateDTO {
    /**
     * 商品信息分类名字
     */
    @NotNull(message = "商品信息分类名字不能为空")
    private String name;
    /**
     * 商品信息分类id
     */
    @NotNull(message = "商品信息分类ID不能为空")
    private String id;
}