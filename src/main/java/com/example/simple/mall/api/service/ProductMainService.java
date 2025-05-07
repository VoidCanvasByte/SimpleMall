package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.ProductDTO;
import com.example.simple.mall.common.entity.ProductMain;

/**
 * 商品Service
 *
 * @author sunny
 * @since 2025/05/08å
 */
public interface ProductMainService extends IService<ProductMain> {


    /**
     * 分页查询
     *
     * @param page        page
     * @param size        size
     * @param productMain productMain
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductDTO> }
     */
    Page<ProductMain> queryPageList(Integer page, Integer size, ProductMain productMain);

    /**
     * @param productDTO
     * @author sunny
     * @since 2025/05/08
     */
    void addProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    void deleteProduct(String productCode);
}