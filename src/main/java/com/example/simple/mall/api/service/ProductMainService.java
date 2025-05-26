package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.entity.ProductMain;

import java.util.List;

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
    List<ProductMain> queryPageList(Integer page, Integer size, ProductMain productMain);

    /**
     * 添加商品信息
     *
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/08
     */
    void addProduct(ProductDTO productDTO);

    /**
     * 更新商品信息
     *
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/09
     */
    void updateProduct(ProductDTO productDTO);

    /**
     * 删除商品信息
     *
     * @param productCode productCode
     * @author sunny
     * @since 2025/05/09
     */
    void deleteProduct(String productCode);
}