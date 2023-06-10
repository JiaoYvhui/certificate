package com.manage.certificate.comm.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;

/**
 * @Copyright: Shanghai Smec Company.All rights reserved.
 * @Description:
 * @author: wenbo.liao
 * @since: 2020/5/6 16:31
 * @history: 1.2020/5/6 created by wenbo.liao
 */
@Configuration
public class BeanConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 让Spring管理JPAQueryFactory
     */
    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean(name = "tokenRestTemplate")
    public RestTemplate restTemplate (){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(150000);
        httpRequestFactory.setConnectTimeout(150000);
        httpRequestFactory.setReadTimeout(150000);
        return new RestTemplate(httpRequestFactory);
    }

    @Bean
    public HttpHeaders requestHeaders(){
        return new HttpHeaders();
    }


}
