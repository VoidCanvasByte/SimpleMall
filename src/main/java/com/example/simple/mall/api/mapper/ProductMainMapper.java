package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.dto.product.ProductPaginationDTO;
import com.example.simple.mall.common.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品Mapper
 *
 * @author sunny
 * @since 2025/05/08
 */
@Mapper
public interface ProductMainMapper extends BaseMapper<ProductEntity> {


    /**
     * 分页查询
     *
     * @param productPaginationDTO productPaginationDTO
     * @return @return {@code List<ProductEntity> }
     * @author sunny
     * @since 2025/05/25
     */
    List<ProductEntity> selectPageList(ProductPaginationDTO productPaginationDTO);
}
