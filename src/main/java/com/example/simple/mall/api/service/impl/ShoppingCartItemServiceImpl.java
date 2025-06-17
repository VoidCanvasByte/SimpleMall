package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.CartMapperStruct;
import com.example.simple.mall.api.mapper.ShoppingCartItemMapper;
import com.example.simple.mall.api.mapper.ShoppingCartMapper;
import com.example.simple.mall.api.service.ShoppingCartItemService;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.cart.CartItemAddDTO;
import com.example.simple.mall.common.dto.cart.CartItemUpdateDTO;
import com.example.simple.mall.common.dto.cart.CartUpdateDTO;
import com.example.simple.mall.common.entity.ShoppingCartEntity;
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
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    ProductMainService productMainService;


    /**
     * 添加购物车以及物品
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addToCart(CartItemAddDTO cartItemAddDTO) {
        QueryWrapper<ShoppingCartEntity> shoppingCartQueryWrapper = new QueryWrapper<>();
        shoppingCartQueryWrapper.eq("user_id", cartItemAddDTO.getUserId());
        ShoppingCartEntity shoppingCartEntity = shoppingCartMapper.selectOne(shoppingCartQueryWrapper);
        if (Objects.nonNull(shoppingCartEntity)) {
            QueryWrapper<ShoppingCartItemEntity> shoppingCartItemWrapper = new QueryWrapper<>();
            shoppingCartItemWrapper.eq("cart_id", shoppingCartEntity.getId());
            shoppingCartItemWrapper.eq("variant_id", cartItemAddDTO.getVariantId());
            ShoppingCartItemEntity shoppingCartItemEntityOld = shoppingCartItemMapper.selectOne(shoppingCartItemWrapper);
            if (Objects.nonNull(shoppingCartItemEntityOld)) {
                shoppingCartItemMapper.updateQuantity(cartItemAddDTO.getQuantity(), shoppingCartItemEntityOld.getId());
            } else {
                ShoppingCartItemEntity shoppingCartItemEntity = CartMapperStruct.INSTANCE.cartItemDtoToEntity(cartItemAddDTO);
                shoppingCartItemEntity.setCartId(shoppingCartEntity.getId());
                shoppingCartItemMapper.insert(shoppingCartItemEntity);
            }
        } else {
            ShoppingCartEntity shoppingCartEntityTemp = CartMapperStruct.INSTANCE.cartItemDTOToEntity(cartItemAddDTO);
            shoppingCartMapper.insert(shoppingCartEntityTemp);
            ShoppingCartItemEntity shoppingCartItemEntity = CartMapperStruct.INSTANCE.cartItemDtoToEntity(cartItemAddDTO);
            Long id = shoppingCartEntityTemp.getId();
            shoppingCartItemEntity.setCartId(id);
            shoppingCartItemMapper.insert(shoppingCartItemEntity);
        }
    }

    /**
     * 根据用户查询购物车商品信息
     *
     * @param userId 用户ID
     * @return @return {@code List<CartItemAddDTO> }
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ShoppingCartItemEntity> listByUserId(Long userId) {
        QueryWrapper<ShoppingCartEntity> shoppingCartWrapper = new QueryWrapper<>();
        shoppingCartWrapper.eq("user_id", userId);
        ShoppingCartEntity shoppingCartEntity = shoppingCartMapper.selectOne(shoppingCartWrapper);
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("cart_id", shoppingCartEntity.getId());
        return shoppingCartItemMapper.selectList(wrapper);
    }

    /**
     * 单次添加或者减少购物车中物品的数量
     *
     * @param cartUpdateDTO cartUpdateDTO
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCartItem(CartUpdateDTO cartUpdateDTO) {
        Integer quantityOld = null;
        QueryWrapper<ShoppingCartEntity> shoppingCartWrapper = new QueryWrapper<>();
        shoppingCartWrapper.eq("id", cartUpdateDTO.getId());
        ShoppingCartEntity shoppingCartEntity = shoppingCartMapper.selectOne(shoppingCartWrapper);
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("cart_id", shoppingCartEntity.getId());
        wrapper.eq("variant_id", cartUpdateDTO.getVariantId());
        ShoppingCartItemEntity shoppingCartItemEntityOld = shoppingCartItemMapper.selectOne(wrapper);
        //添加购物车中物品的数量
        if (ObjectUtils.equals(LabelEnum.ADD.getCode(), cartUpdateDTO.getLabel())) {
            quantityOld = shoppingCartItemEntityOld.getQuantity() + cartUpdateDTO.getQuantity();
        } else {
            quantityOld = shoppingCartItemEntityOld.getQuantity() - cartUpdateDTO.getQuantity();
        }
        shoppingCartItemMapper.updateQuantity(quantityOld, shoppingCartItemEntityOld.getId());
    }

    /**
     * 自定义修改商品数量
     *
     * @param cartItemAddDTO cartItemAddDTO
     * @author sunny
     * @since 2025/05/15@return@return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void customUpdateTag(CartItemUpdateDTO cartItemAddDTO) {
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("cart_id", cartItemAddDTO.getCartId());
        wrapper.eq("variant_id", cartItemAddDTO.getVariantId());
        ShoppingCartItemEntity shoppingCartItemEntityOld = shoppingCartItemMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(shoppingCartItemEntityOld)) {
            throw new RuntimeException(ResponseEnum.USER_CART_IS_EMPTY.getMessage());
        }
        //添加购物车中物品的数量
        Integer quantity = cartItemAddDTO.getQuantity();
        shoppingCartItemMapper.updateQuantity(quantity, shoppingCartItemEntityOld.getId());
    }

    /**
     * 一键清空购物车
     *
     * @param cartId 购物车ID
     * @author sunny
     * @since 2025/05/15
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clearCart(Long cartId) {
        QueryWrapper<ShoppingCartItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("cart_id", cartId);
        this.remove(wrapper);
    }
}
