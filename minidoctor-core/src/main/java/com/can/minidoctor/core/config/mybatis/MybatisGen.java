package com.can.minidoctor.core.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:21 2018/12/17
 */
@Configuration
public class MybatisGen {
    @Bean
    public MapperScannerConfigurer getMapperConfig(){
        MapperScannerConfigurer configurer=new MapperScannerConfigurer();
        configurer.setBasePackage("com.can.minidoctor.core.dao.mapper");
        return configurer;
    }
}
