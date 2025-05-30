package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductCategoryMapper;
import com.example.simple.mall.api.service.ProductCategoryService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.dto.product.ProductInfoDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateDTO;
import com.example.simple.mall.common.entity.ProductCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sunny
 * @since 2025/05/09
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryEntity> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProductDTO> listAllBySort(QueryWrapper<ProductDTO> sort) {
        return null;
    }

    /**
     * 添加商品分类
     *
     * @param productInfoDTO productInfoDTO
     * @author sunny
     * @since 2025/05/25
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(ProductInfoDTO productInfoDTO) {
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        productCategoryEntity.setName(productInfoDTO.getName());
        productCategoryMapper.insert(productCategoryEntity);
    }

    /**
     * 更新商品分类信息
     *
     * @param productUpdateDTO productUpdateDTO
     * @author sunny
     * @since 2025/05/25
     */
    @Override
    public void updateCategory(ProductUpdateDTO productUpdateDTO) {
        QueryWrapper<ProductCategoryEntity> productCategoryWrapper = new QueryWrapper<>();
        productCategoryWrapper.eq("id", productUpdateDTO.getId());
        productCategoryWrapper.eq("name", productUpdateDTO.getName());
        this.update(productCategoryWrapper);
    }
}
