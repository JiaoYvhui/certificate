package com.manage.certificate.comm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description: 跨域
 * @author: guohao.tan
 * @since: 2022/2/24 下午1:37
 * @history: 1.2022/2/24 created by guohao.tan
 */
@Configuration
public class CrossConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }
}

