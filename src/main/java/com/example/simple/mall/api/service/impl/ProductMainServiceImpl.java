package com.example.simple.mall.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.ProductMainMapperStruct;
import com.example.simple.mall.api.mapper.ProductCategoryMapper;
import com.example.simple.mall.api.mapper.ProductDetailsMapper;
import com.example.simple.mall.api.mapper.ProductMainMapper;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.entity.Product;
import com.example.simple.mall.common.entity.ProductCategory;
import com.example.simple.mall.common.entity.ProductDetails;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品实现层
 *
 * @author sunny
 * @since 2025/05/08
 */
@Service
public class ProductMainServiceImpl extends ServiceImpl<ProductMainMapper, Product> implements ProductMainService {


    @Autowired
    private ProductMainMapper productMainMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    /**
     * 分页查询
     *
     * @param page        page
     * @param size        size
     * @param product product
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductDTO> }
     */
    @Override
    public List<Product> queryPageList(Integer page, Integer size, Product product) {
        List<Product> productList = productMainMapper.selectPageList(product);
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
    @Transactional(rollbackFor = Exception.class)
    public void addProduct(ProductDTO productDTO) {
        String productCategoryId = productDTO.getProductCategoryId();
        QueryWrapper<ProductCategory> productCategoryWrapper = new QueryWrapper<ProductCategory>();
        productCategoryWrapper.eq("id", productCategoryId);
        ProductCategory productCategory = productCategoryMapper.selectOne(productCategoryWrapper);
        if (ObjectUtils.isEmpty(productCategory)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_CATEGORY_NOT_EXIST.getMessage());
        }
        //商品主表
        Product product = ProductMainMapperStruct.INSTANCE.productDTOToProductMain(productDTO);
        //商品详细表
        ProductDetails productDetails = ProductMainMapperStruct.INSTANCE.productDTOToProductDetails(productDTO);

        productMainMapper.insert(product);
        productDetailsMapper.insert(productDetails);
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
