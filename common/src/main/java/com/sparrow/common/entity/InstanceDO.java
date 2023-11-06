package com.sparrow.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 11:51
 */
public class InstanceDO {
    
    private String serverName;
    
    private String ip;
    
    private Map<String, String> params;
    
    public InstanceDO(String serverName, String ip) {
        this.serverName = serverName;
        this.ip = ip;
    }
    
    public void put(String key, String value) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(key, value);
    }
    
    public InstanceDO() {
    }
    
    public Map<String, String> getParams() {
        return params;
    }
    
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    
    public String getServerName() {
        return serverName;
    }
    
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
}
