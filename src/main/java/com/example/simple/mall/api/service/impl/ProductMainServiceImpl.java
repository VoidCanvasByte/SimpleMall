package com.example.simple.mall.api.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductMainMapper;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.ProductDTO;
import com.example.simple.mall.common.entity.ProductMain;
import org.springframework.stereotype.Service;

/**
 * 商品实现层
 *
 * @author sunny
 * @since 2025/05/08
 */
@Service
public class ProductMainServiceImpl extends ServiceImpl<ProductMainMapper, ProductMain> implements ProductMainService {

    /**
     * 分页查询
     *
     * @param page        page
     * @param size        size
     * @param productMain productMain
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductDTO> }
     */
    @Override
    public Page<ProductMain> queryPageList(Integer page, Integer size, ProductMain productMain) {
        QueryWrapper<ProductMain> wrapper = new QueryWrapper<>();
        wrapper.eq(!ObjectUtil.isEmpty(productMain.getProductCode()), "product_code", productMain.getProductCode());
        wrapper.eq(!ObjectUtil.isEmpty(productMain.getProductCode()), "product_name", productMain.getProductName());
        Page<ProductMain> pageParam = new Page<>(page, size);
        return this.page(pageParam, wrapper);
    }

    /**
     * 添加商品
     *
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/08
     */
    @Override
    public void addProduct(ProductDTO productDTO) {

    }

    /**
     * 更新商品信息
     *
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/09
     */
    @Override
    public void updateProduct(ProductDTO productDTO) {

    }

    /**
     * 删除商品
     *
     * @param productCode productCode
     * @author sunny
     * @since 2025/05/09
     */
    @Override
    public void deleteProduct(String productCode) {

    }
}
