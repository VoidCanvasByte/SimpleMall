package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.product.ProductFavoritesItemsInfoDTO;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;


/**
 * ProductFavoritesItemsService
 *
 * @author sunny
 * @since 2025/06/02
 */
public interface ProductFavoritesItemsService extends IService<ProductFavoritesItemsEntity> {

    /**
     * 添加到收藏夹
     *
     * @param productFavoritesItemsInfoDTO productFavoritesItemsInfoDTO
     * @since 2025/06/02
     */
    void addProductFavorites(ProductFavoritesItemsInfoDTO productFavoritesItemsInfoDTO);
}
