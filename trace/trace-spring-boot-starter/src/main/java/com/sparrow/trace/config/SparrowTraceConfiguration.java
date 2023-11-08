package com.sparrow.trace.config;

import com.sparrow.trace.core.TraceBeanRegister;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 13:29
 */
@ConfigurationProperties(prefix = SparrowTraceConfiguration.PREFIX)
@Order(Integer.MIN_VALUE)
public class SparrowTraceConfiguration {
    
    public static final String PREFIX = "sparrow.trace";
    
    private Boolean enabled = true;
    
    private List<Class<? extends TraceBeanRegister>> protocols = new ArrayList<>();
    
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
