package com.example.simple.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SimpleMallApplication
 *
 * @author sunny
 * @since 2025/05/05
 */
@SpringBootApplication
@MapperScan("com.example.simple.mall.api.mapper")
public class SimpleMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleMallApplication.class, args);
    }

}
