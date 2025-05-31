package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.product.ProductAddInfoDTO;
import com.example.simple.mall.common.entity.ProductEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理层
 *
 * @author sunny
 * @since 2025/05/08
 */
@RestController
@RequestMapping("/apply/product")
@Tag(name = "ProductControllerAPI", description = "商品管理层控制层")
public class ProductController {

    @Autowired
    private ProductMainService productMainService;

    /**
     * 分页查询
     *
     * @param page          page
     * @param size          size
     * @param productEntity productEntity
     * @return @return {@code Page<ProductEntity> }
     * @author sunny
     * @since 2025/05/08
     */
    @GetMapping(value = "/query/page")
    @Operation(summary = "商品列表-分页", description = "商品列表-分页")
    public List<ProductEntity> queryPageList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size,
                                             @RequestBody(required = false) ProductEntity productEntity) {
        return productMainService.queryPageList(page, size, productEntity);

    }

    /**
     * 添加商品
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @return @return {@code ResponseResult<ProductAddInfoDTO> }
     * @author sunny
     * @since 2025/05/08
     */
    @PostMapping(value = "/add")
    @Operation(summary = "添加商品", description = "添加商品")
    public ResponseResult<ProductAddInfoDTO> addProduct(@Validated @RequestBody ProductAddInfoDTO productAddInfoDTO) {
        productMainService.addProduct(productAddInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 商品信息更新
     *
     * @param productAddInfoDTO productAddInfoDTO
     * @return {@code ResponseResult<ProductAddInfoDTO> }
     * @author sunny
     * @since 2025/05/08
     */
    @PutMapping
    public ResponseResult<ProductAddInfoDTO> updateProduct(@Validated @RequestBody ProductAddInfoDTO productAddInfoDTO) {
        productMainService.updateProduct(productAddInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 删除商品信息
     *
     * @param productCode 商品货号
     * @return {@code ResponseResult<ProductAddInfoDTO> }
     * @author sunny
     * @since 2025/05/08
     */
    @DeleteMapping("/{productCode}")
    public ResponseResult<ProductAddInfoDTO> deleteProduct(@PathVariable String productCode) {
        productMainService.deleteProduct(productCode);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}
