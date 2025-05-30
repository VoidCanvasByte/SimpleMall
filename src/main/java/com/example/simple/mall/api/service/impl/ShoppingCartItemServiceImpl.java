package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.CartItemMapperStruct;
import com.example.simple.mall.api.mapper.ShoppingCartItemMapper;
import com.example.simple.mall.api.service.ShoppingCartItemService;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.CartItemDTO;
import com.example.simple.mall.common.entity.ShoppingCartItemEntity;
import com.example.simple.mall.common.enu.LabelEnum;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * ShoppingCartItemServiceImpl
 *
 * @author sunny
 * @since 2025/05/15
 */
@Service
public class ShoppingCartItemServiceImpl extends ServiceImpl<ShoppingCartItemMapper, ShoppingCartItemEntity> implements ShoppingCartItemService {

    @Autowired
    ShoppingCartItemMapper shoppingCartItemMapper;

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
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartItemDTO.getUserId());
        wrapper.eq("product_id", cartItemDTO.getProductMainId());
        ShoppingCartItemEntity shoppingCartItemEntityOld = shoppingCartItemMapper.selectOne(wrapper);
        if (Objects.nonNull(shoppingCartItemEntityOld)) {
            Integer quantityOld = shoppingCartItemEntityOld.getQuantity();
            quantityOld += cartItemDTO.getQuantity();
            shoppingCartItemMapper.updateQuantity(quantityOld, cartItemDTO.getId());
        } else {
            ShoppingCartItemEntity shoppingCartItemEntity = CartItemMapperStruct.INSTANCE.cartItemDtoToEntity(cartItemDTO);
            shoppingCartItemMapper.insert(shoppingCartItemEntity);
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
    public List<ShoppingCartItemEntity> listByUserId(Long userId) {
        //TODO 购物车中的物品要有时间限制
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return shoppingCartItemMapper.selectList(wrapper);
    }

    /**
     * 单次添加或者减少购物车中物品的数量
     *
     * @param cartItemDTO cartItemDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCartItem(CartItemDTO cartItemDTO) {
        Integer quantityOld = null;
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartItemDTO.getUserId());
        wrapper.eq("product_id", cartItemDTO.getProductMainId());
        ShoppingCartItemEntity shoppingCartItemEntityOld = shoppingCartItemMapper.selectOne(wrapper);
        quantityOld = shoppingCartItemEntityOld.getQuantity();
        //添加购物车中物品的数量
        if (ObjectUtils.equals(cartItemDTO.getLabel(), LabelEnum.ADD.getCode())) {
            quantityOld += cartItemDTO.getQuantity();
        } else {
            quantityOld -= cartItemDTO.getQuantity();
        }
        shoppingCartItemMapper.updateQuantity(quantityOld, cartItemDTO.getId());
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
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
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
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartItemDTO.getUserId());
        wrapper.eq("product_id", cartItemDTO.getProductMainId());
        ShoppingCartItemEntity shoppingCartItemEntityOld = shoppingCartItemMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(shoppingCartItemEntityOld)) {
            throw new RuntimeException(ResponseEnum.USER_CART_IS_EMPTY.getMessage());
        }
        //添加购物车中物品的数量
        Integer quantity = cartItemDTO.getQuantity();
        shoppingCartItemMapper.updateQuantity(quantity, shoppingCartItemEntityOld.getId());
    }
}
