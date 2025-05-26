package com.example.simple.mall.api.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductMainMapper;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.entity.ProductMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品实现层
 *
 * @author sunny
 * @since 2025/05/08
 */
@Service
public class ProductMainServiceImpl extends ServiceImpl<ProductMainMapper, ProductMain> implements ProductMainService {


    @Autowired
    private ProductMainMapper productMainMapper;

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
    public List<ProductMain> queryPageList(Integer page, Integer size, ProductMain productMain) {
        List<ProductMain> productList = productMainMapper.selectPageList(productMain);
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, productList.size());
        return CollUtil.sub(productList, fromIndex, toIndex);
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
