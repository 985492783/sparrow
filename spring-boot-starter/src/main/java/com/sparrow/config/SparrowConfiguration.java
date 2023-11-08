package com.sparrow.config;

import com.sparrow.client.SparrowClient;
import com.sparrow.client.config.SparrowConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 19:29
 */
@ConfigurationProperties(prefix = SparrowConfiguration.PREFIX)
@Order(Integer.MIN_VALUE)
public class SparrowConfiguration {
    
    public static final String PREFIX = "sparrow.client";
    
    private String name = SparrowConfig.getInstance().getName();
    
    private String host = "127.0.0.1:8888";
    
    @Bean
    @ConditionalOnMissingBean
    public SparrowClient sparrowClient() {
        SparrowConfig sparrowConfig = SparrowConfig.getInstance();
        sparrowConfig.setHost(host);
        sparrowConfig.setName(name);
        return new SparrowClient(sparrowConfig);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
}
