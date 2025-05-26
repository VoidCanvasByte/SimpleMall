package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.ProductMain;

import java.util.List;

/**
 * 商品Mapper
 *
 * @author sunny
 * @since 2025/05/08
 */
public interface ProductMainMapper extends BaseMapper<com.example.simple.mall.common.entity.ProductMain> {


    /**
     * 分页查询
     *
     * @param productMain productMain
     * @return @return {@code List<ProductMain> }
     * @author sunny
     * @since 2025/05/25
     */
    List<ProductMain> selectPageList(ProductMain productMain);
}
