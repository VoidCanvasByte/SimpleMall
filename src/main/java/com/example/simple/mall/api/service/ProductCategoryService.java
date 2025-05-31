package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.product.*;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.entity.ProductCategoryEntity;

import java.util.List;

/**
 * ProductCategoryService
 *
 * @author sunny
 * @since 2025/05/09
 */
public interface ProductCategoryService extends IService<ProductCategoryEntity> {

    /**
     * 添加商品分类
     *
     * @param productCategoryInfoDTO productCategoryInfoDTO
     * @author sunny
     * @since 2025/05/25
     */
    void addCategory(ProductCategoryInfoDTO productCategoryInfoDTO);

    /**
     * 更新商品分类信息
     *
     * @param productCategoryUpdateDTO productCategoryUpdateDTO
     * @author sunny
     * @since 2025/05/25
     */
    void updateCategory(ProductCategoryUpdateDTO productCategoryUpdateDTO);

    /**
     * 查询当前用户下全部商品分类列表
     *
     * @param userBaseDTO userBaseDTO
     * @return {@code List<ProductCategoryReturnDTO> }
     * @author sunny
     * @since 2025/05/31@return
     */
    List<ProductCategoryReturnDTO> listAll(UserBaseDTO userBaseDTO);
}