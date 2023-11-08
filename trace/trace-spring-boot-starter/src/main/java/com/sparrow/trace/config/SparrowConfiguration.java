package com.sparrow.trace.config;

import com.sparrow.client.SparrowClient;
import com.sparrow.client.config.SparrowConfig;
import com.sparrow.trace.core.TraceBeanRegister;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 13:29
 */
@ConfigurationProperties(prefix = SparrowConfiguration.PREFIX)
@Order(Integer.MIN_VALUE)
public class SparrowConfiguration {
    
    public static final String PREFIX = "sparrow.trace";
    
    private Boolean enabled = true;
    
    private String name = SparrowConfig.getInstance().getName();
    
    private String host = "127.0.0.1:8888";
    
    private List<Class<? extends TraceBeanRegister>> protocols = new ArrayList<>();
    
    @Bean
    @ConditionalOnMissingBean
    public SparrowClient sparrowClient() {
        SparrowConfig sparrowConfig = SparrowConfig.getInstance();
        sparrowConfig.setHost(host);
        sparrowConfig.setName(name);
        SparrowClient sparrowClient = new SparrowClient(sparrowConfig);
        return sparrowClient;
    }
    
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public List<Class<? extends TraceBeanRegister>> getProtocols() {
        return protocols;
    }
    
    public void setProtocols(List<Class<? extends TraceBeanRegister>> protocols) {
        this.protocols = protocols;
    }
}
