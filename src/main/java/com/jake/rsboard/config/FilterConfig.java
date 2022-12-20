package com.jake.rsboard.config;

import com.jake.rsboard.filter.JwtFilter;
import com.jake.rsboard.filter.JwtFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<JwtFilter> filter1() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>(new JwtFilter());
        bean.addUrlPatterns("/*"); // 모든 요청에서 다해라
        bean.setOrder(1); // 낮은 번호가 필터 중에서 가장 먼저 실행됨
        return bean;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter2> filter2() {
        FilterRegistrationBean<JwtFilter2> bean = new FilterRegistrationBean<>(new JwtFilter2());
        bean.addUrlPatterns("/*"); // 모든 요청에서 다해라
        bean.setOrder(0); // 낮은 번호가 필터 중에서 가장 먼저 실행됨
        return bean;
    }
}
