package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.ProductDTO;
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
     * @param sort sort
     * @return @return {@code List<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    List<ProductDTO> listAllBySort(QueryWrapper<ProductDTO> sort);
}