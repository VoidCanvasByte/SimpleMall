package com.example.simple.mall.common.dto.product;


import lombok.Data;
import java.util.Date;

@Data
public class ProductCategoryReturnDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 父级分类，顶级为0
     */
    private Integer parentId;

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
