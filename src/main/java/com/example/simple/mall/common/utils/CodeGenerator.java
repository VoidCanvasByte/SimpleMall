package com.example.simple.mall.common.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import java.util.Collections;

/**
 * 代码自动生成器
 *
 * @author sunny
 * @since 2025/06/03
 */
public class CodeGenerator {

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/simple_mall?useSSL=false&serverTimezone=UTC",
                        "xxx", "xxxx")
                .globalConfig(builder -> {
                    builder.author("sunny")
                            .enableSwagger()
                            .outputDir("src/generator");
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.simple.mall") // 设置父包名
                            .moduleName("product")            // 设置模块名（可选）
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order_main_logistics");
                })
                .execute();
    }
}