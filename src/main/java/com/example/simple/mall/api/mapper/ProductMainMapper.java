package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.Product;

import java.util.List;

/**
 * 商品Mapper
 *
 * @author sunny
 * @since 2025/05/08
 */
public interface ProductMainMapper extends BaseMapper<Product> {


    /**
     * 分页查询
     *
     * @param product product
     * @return @return {@code List<Product> }
     * @author sunny
     * @since 2025/05/25
     */
    List<Product> selectPageList(Product product);
}
