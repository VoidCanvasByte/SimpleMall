package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.dto.product.ProductInfoDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateDTO;
import com.example.simple.mall.common.entity.ProductCategory;

import java.util.List;

/**
 * ProductCategoryService
 *
 * @author sunny
 * @since 2025/05/09
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询全部分类
     *
     * @param sort sort
     * @return @return {@code List<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    List<ProductDTO> listAllBySort(QueryWrapper<ProductDTO> sort);

    /**
     * 添加商品分类
     *
     * @param productInfoDTO productInfoDTO
     * @author sunny
     * @since 2025/05/25
     */
    void addCategory(ProductInfoDTO productInfoDTO);

    /**
     * 更新商品分类信息
     *
     * @param productUpdateDTO productUpdateDTO
     * @author sunny
     * @since 2025/05/25
     */
    void updateCategory(ProductUpdateDTO productUpdateDTO);

}