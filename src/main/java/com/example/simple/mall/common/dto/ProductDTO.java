package com.example.simple.mall.common.dto;

import com.example.simple.mall.common.entity.ProductDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 商品DTO
 *
 * @author sunny
 * @since 2025/05/08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends ProductDetails {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品货号
     */
    @NotNull(message ="商品货号不能为空")
    public String productCode;

    /**
     * 商品名称
     */
    @NotNull(message ="商品名称不能为空")
    public String productName;

    /**
     * 商品状态：1正常，2售罄
     */
    public Integer status;

    /**
     * 创建时间
     */
    private String creatTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
