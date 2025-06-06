package com.example.simple.mall.common.dto.product;

import com.example.simple.mall.common.dto.user.UserBaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新商品分类DTO
 *
 * @author sunny
 * @since 2025/05/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryUpdateDTO extends UserBaseDTO {

    /**
     * 商品信息分类名字
     */
    @NotNull(message = "商品信息分类名字不能为空")
    private String name;
    /**
     * 商品信息分类id
     */
    @NotNull(message = "商品信息分类ID不能为空")
    private Long id;

    /**
     * 排序值
     */
    private Integer sortOrder;
}