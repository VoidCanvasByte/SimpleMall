package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductDetailsMapper;
import com.example.simple.mall.api.service.ProductDetailsService;
import com.example.simple.mall.common.entity.ProductDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductDetailsServiceImpl extends ServiceImpl<ProductDetailsMapper, ProductDetailsEntity> implements ProductDetailsService {


    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    /**
     * 根据quantity 扣减商品库存
     *
     * @param productId  productId
     * @param quantity   quantity
     * @param oldVersion oldVersion
     * @author sunny
     * @since 2025/05/30@return @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateQuantity(Long productId, Integer quantity, Integer oldVersion) {
        return productDetailsMapper.updateQuantity(productId, quantity, oldVersion);
    }
}
