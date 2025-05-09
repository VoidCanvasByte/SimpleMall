package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ProductCategoryService;
import com.example.simple.mall.common.dto.ProductDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品分类控制层
 *
 * @author sunny
 * @since 2025/05/09
 */
@RestController
@RequestMapping("/apply/product/category")
@Tag(name = "ProductCategoryController", description = "商品分类控制层")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 添加管理员逻辑
     *
     * @param productDTO productDTO
     * @return @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @PostMapping("/add")
    public ResponseResult<ProductDTO> addCategory(@RequestBody ProductDTO productDTO) {
        return null;
    }

    /**
     * 更新商品分类
     *
     * @param productDTO productDTO
     * @return @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @PostMapping("/update")
    public ResponseResult<ProductDTO> updateCategory(@RequestBody ProductDTO productDTO) {
        return null;
    }

    /**
     * 获取所有分类（公开）
     *
     * @return @return {@code ResponseResult<List<ProductDTO>> }
     * @author sunny
     * @since 2025/05/09
     */
    @GetMapping("/list")
    public ResponseResult<List<ProductDTO>> listCategories() {
        List<ProductDTO> list = productCategoryService.listObjs();
        return ResponseResult.out(ResponseEnum.SUCCESS, list);
    }


    /**
     * 删除分类（管理员）
     *
     * @param id id
     * @return @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult<ProductDTO> deleteCategory(@PathVariable Long id) {
        productCategoryService.removeById(id);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}