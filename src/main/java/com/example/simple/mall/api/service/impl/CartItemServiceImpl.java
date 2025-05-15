package com.example.simple.mall.api.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.CartItemMapper;
import com.example.simple.mall.api.service.CartItemService;
import com.example.simple.mall.common.dto.CartItemDTO;
import com.example.simple.mall.common.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CartItemServiceImpl
 *
 * @author sunny
 * @since 2025/05/15
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {
    /**
     * 添加购物车
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    public void addToCart(CartItemDTO cartItemDTO) {

    }

    /**
     * 根据用户查询购物车商品信息
     *
     * @param userId 用户ID
     * @return @return {@code List<CartItemDTO> }
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    public List<CartItemDTO> listByUserId(Long userId) {
        return null;
    }

    /**
     * 修改购物车的数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    public void updateCartItem(CartItemDTO cartItemDTO) {


    }

    /**
     * 删除商品数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    public void deleteCartItem(CartItemDTO cartItemDTO) {

    }

    /**
     * 一键清空购物车
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    public void clearCart(Long userId) {

    }
}
