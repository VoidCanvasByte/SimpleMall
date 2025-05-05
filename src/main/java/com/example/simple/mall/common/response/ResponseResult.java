package com.example.simple.mall.common.response;

import com.example.simple.mall.common.enu.ResponseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 静态工厂方法
 *
 * @author sunny
 * @since 2025/05/05
 */
@Getter
@Setter
public class ResponseResult<T> extends BaseResponse {

    private T data;


    private ResponseResult(ResponseEnum respEnum) {
        super(respEnum);
    }

    private ResponseResult(ResponseEnum respEnum, T data) {
        super(respEnum);
        this.data = data;
    }

    /**
     * 返回值
     *
     * @return @return {@code ResponseResult<T> }
     * @author sunny
     * @since 2025/05/05
     */
    public static <T> ResponseResult<T> out(ResponseEnum respEnum) {
        return new ResponseResult<>(respEnum);
    }

    /**
     * 返回值
     *
     * @param data 数据
     * @return @return {@code ResponseResult<T> }
     * @author sunny
     * @since 2025/05/05
     */
    public static <T> ResponseResult<T> out(ResponseEnum respEnum, T data) {
        return new ResponseResult<>(respEnum, data);
    }
}
