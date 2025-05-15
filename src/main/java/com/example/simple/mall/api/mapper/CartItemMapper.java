package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * CartItemMapper
 *
 * @author sunny
 * @since 2025/05/15
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {


}