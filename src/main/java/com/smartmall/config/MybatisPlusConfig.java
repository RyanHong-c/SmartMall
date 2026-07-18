package com.smartmall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.smartmall.mapper")
public class MybatisPlusConfig {

}