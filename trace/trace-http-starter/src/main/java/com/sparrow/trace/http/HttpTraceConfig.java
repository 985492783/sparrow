package com.sparrow.trace.http;

import com.sparrow.client.SparrowClient;
import com.sparrow.trace.condition.SparrowConditional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 18:49
 */
@Configuration
@SparrowConditional(HttpTraceFilter.class)
@ConditionalOnBean(SparrowClient.class)
public class HttpTraceConfig {
    
    @Bean
    public FilterRegistrationBean<HttpTraceFilter> httpTraceFilterFilterRegistration(HttpTraceFilter httpTraceFilter) {
        FilterRegistrationBean<HttpTraceFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(httpTraceFilter);
        registration.addUrlPatterns("/*");
        registration.setName("httpTraceFilter");
        registration.setOrder(6);
        
        return registration;
    }
    
    @Bean
    public HttpTraceFilter httpTraceFilter(SparrowClient sparrowClient) {
        return new HttpTraceFilter(sparrowClient);
    }
}
