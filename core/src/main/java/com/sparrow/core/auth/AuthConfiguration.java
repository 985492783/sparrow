package com.sparrow.core.auth;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author 985492783@qq.com
 * @date 2023/10/10 13:18
 */
@Configuration
@EnableConfigurationProperties(AuthConfig.class)
public class AuthConfiguration {
    
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(authFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(0);
        
        return registration;
    }
    
    @Bean
    public AuthFilter authFilter(RequestMappingHandlerMapping requestMappingHandlerMapping, AuthConfig authConfig) {
        return new AuthFilter(requestMappingHandlerMapping, authConfig);
    }
}
