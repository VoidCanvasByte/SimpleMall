package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.entity.ProductDetails;

public interface ProductDetailsService extends IService<ProductDetails> {


    /**
     * 根据quantity 扣减商品库存
     *
     * @param productId  productId
     * @param quantity   quantity
     * @param oldVersion oldVersion
     * @author sunny
     * @since 2025/05/30@return @return int
     */
    int updateQuantity(Integer productId, Integer quantity,Integer oldVersion);
}
