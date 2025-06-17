package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.product.ProductAddInfoDTO;
import com.example.simple.mall.common.dto.product.ProductPaginationDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateInfoDTO;
import com.example.simple.mall.common.entity.ProductEntity;

import java.util.List;

/**
 * 商品Service
 *
 * @author sunny
 * @since 2025/05/08å
 */
public interface ProductMainService extends IService<ProductEntity> {


    /**
     * 分页查询
     *
     * @param page                 page
     * @param size                 size
     * @param productPaginationDTO productPaginationDTO
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductAddInfoDTO> }@return @return {@code List<ProductEntity> }
     */
    List<ProductEntity> queryPageList(Integer page, Integer size, ProductPaginationDTO productPaginationDTO);

    /**
     * 添加商品信息
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @author sunny
     * @since 2025/05/08
     */
    void addProduct(ProductAddInfoDTO productAddInfoDTO);

    /**
     * 更新商品信息
     *
     * @param productUpdateInfoDTO productUpdateInfoDTO
     * @author sunny
     * @since 2025/05/09
     */
    void updateProduct(ProductUpdateInfoDTO productUpdateInfoDTO);

    /**
     * 删除商品信息
     *
     * @param productCode productCode
     * @author sunny
     * @since 2025/05/09
     */
    void deleteProduct(String productCode);
}