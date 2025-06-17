package com.example.simple.mall.common.dto.product;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductInfoDTO {

    /**
     * 商品信息分类名字
     */
    @Schema(description = "商品信息分类名字")
    @NotNull(message = "商品信息分类名字不能为空")
    private String name;
}