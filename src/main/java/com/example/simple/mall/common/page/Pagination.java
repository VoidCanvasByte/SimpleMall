package com.example.simple.mall.common.page;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "页数")
    Integer page;

    /**
     * 条数
     */
    @Schema(description = "条数")
    Integer size;
}
