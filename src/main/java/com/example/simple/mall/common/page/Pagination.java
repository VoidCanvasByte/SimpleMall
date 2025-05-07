package com.example.simple.mall.common.page;

import lombok.Data;

/**
 * 分页参数
 *
 * @author sunny
 * @since 2025/05/08
 */
@Data
public class Pagination {

    /**
     * 页数
     */
    Integer page;

    /**
     * 条数
     */
    Integer size;
}
