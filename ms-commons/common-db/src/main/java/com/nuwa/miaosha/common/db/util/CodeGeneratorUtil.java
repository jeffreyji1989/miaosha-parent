package com.nuwa.miaosha.common.db.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @description: mybatis-plus代码自动生成工具类
 * @author: Jeffrey
 * @date: 2021/8/28 下午7:36
 * @version: 1.0
 */
@Slf4j
public class CodeGeneratorUtil {
    /**
     * @description: 项目路径
     * @version 1.0
     */
    private static final String PROJECT_PATH = "/home/jeffrey/workspace/nuwa/nuwa-demo";

    private static final String URL = "jdbc:mysql://121.36.80.21:3306/muwa-alibaba?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("jeffrey") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("TB_USER") // 设置需要生成的表名
                            .addTablePrefix("TB_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
