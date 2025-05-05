package com.example.simple.mall.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger的配置
 *
 * @author sunny
 * @since 2025/05/05
 */
@Configuration
public class OpenAPIConfig {


    /**
     * swagger的配置类
     *
     * @return @return {@code OpenAPI }
     * @author sunny
     * @since 2025/05/05
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("电商系统API文档")
                        .version("1.0")
                        .description("这是电商系统的后端接口文档")
                        .contact(new Contact().name("sunny").email("sunny@com")));
    }

}