package com.sparrow.common.entity;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 11:51
 */
public class InstanceDO {
    
    private String serverName;
    
    private String ip;
    
    public InstanceDO(String serverName, String ip) {
        this.serverName = serverName;
        this.ip = ip;
    }
    
    public InstanceDO() {
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
