package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.dto.CartItemDTO;
import com.example.simple.mall.common.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CartItemMapper
 *
 * @author sunny
 * @since 2025/05/15
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

    /**
     * 根据ID更新购物车中的数量
     *
     * @param quantityOld 数量
     * @param id ID
     * @author sunny
     * @since 2025/05/15
     */
    void updateQuantity(Integer quantityOld, Long id);

    List<CartItemDTO> listByUserId(Long userId);
}