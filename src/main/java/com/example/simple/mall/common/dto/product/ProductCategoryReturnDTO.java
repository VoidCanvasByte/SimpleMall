package com.example.simple.mall.common.dto.product;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ProductCategoryReturnDTO
 *
 * @author sunny
 * @since 2025/06/06
 */
@Data
public class ProductCategoryReturnDTO {


    /**
     * 子节点集合
     */
    private List<ProductCategoryReturnDTO> children = new ArrayList<>();

    /**
     * id
     */
    private Long id;

    /**
     * 父级分类，顶级为0
     */
    private Long parentId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序值
     */
    private Integer sortOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateTime;

}
