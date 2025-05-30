package com.example.simple.mall.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回统一格式的错误 JSON
 *
 * @author sunny
 * @since 2025/05/25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException ex) {
        // 这样写兼容 message 为空的情况
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", ex.getMessage() == null ? "系统异常" : ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}

