package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.CartItemMapperStruct;
import com.example.simple.mall.api.mapper.CartItemMapper;
import com.example.simple.mall.api.service.CartItemService;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.CartItemDTO;
import com.example.simple.mall.common.entity.CartItem;
import com.example.simple.mall.common.enu.LabelEnum;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * CartItemServiceImpl
 *
 * @author sunny
 * @since 2025/05/15
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

    @Autowired
    CartItemMapper cartItemMapper;

    @Autowired
    ProductMainService productMainService;


    /**
     * 添加购物车
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addToCart(CartItemDTO cartItemDTO) {
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartItemDTO.getUserId());
        wrapper.eq("product_id", cartItemDTO.getProductMainId());
        CartItem cartItemOld = cartItemMapper.selectOne(wrapper);
        if (Objects.nonNull(cartItemOld)) {
            Integer quantityOld = cartItemOld.getQuantity();
            quantityOld += cartItemDTO.getQuantity();
            cartItemMapper.updateQuantity(quantityOld, cartItemDTO.getId());
        } else {
            CartItem cartItem = CartItemMapperStruct.INSTANCE.cartItemDtoToEntity(cartItemDTO);
            cartItemMapper.insert(cartItem);
        }
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
    @Transactional(rollbackFor = Exception.class)
    public List<CartItem> listByUserId(Long userId) {
        //TODO 购物车中的物品要有时间限制
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return cartItemMapper.selectList(wrapper);
    }

    /**
     * 添加或者减少购物车中物品的数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCartItem(CartItemDTO cartItemDTO) {
        Integer quantityOld = null;
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartItemDTO.getUserId());
        wrapper.eq("product_id", cartItemDTO.getProductMainId());
        CartItem cartItemOld = cartItemMapper.selectOne(wrapper);
        quantityOld = cartItemOld.getQuantity();
        //添加购物车中物品的数量
        if (ObjectUtils.equals(cartItemDTO.getLabel(), LabelEnum.ADD.getCode())) {
            quantityOld += cartItemDTO.getQuantity();
        } else {
            quantityOld -= cartItemDTO.getQuantity();
        }
        cartItemMapper.updateQuantity(quantityOld, cartItemDTO.getId());
    }


    /**
     * 一键清空购物车
     *
     * @param userId 用户ID
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clearCart(Long userId) {
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        this.remove(wrapper);
    }

    /**
     * 自定义修改商品数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15@return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void customUpdateTag(CartItemDTO cartItemDTO) {
        QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartItemDTO.getUserId());
        wrapper.eq("product_id", cartItemDTO.getProductMainId());
        CartItem cartItemOld = cartItemMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(cartItemOld)) {
            throw new RuntimeException(ResponseEnum.USER_CART_IS_EMPTY.getMessage());
        }
        //添加购物车中物品的数量
        Integer quantity = cartItemDTO.getQuantity();
        cartItemMapper.updateQuantity(quantity, cartItemOld.getId());
    }
}
