package com.sparrow.common.entity;

import java.util.Objects;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:37
 */
public class Instance {
    private String id;
    
    private String serverName;
    
    private String ip;
    
    private boolean alive;
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instance instance = (Instance) o;
        return Objects.equals(serverName, instance.serverName) && Objects.equals(ip, instance.ip);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(serverName, ip);
    }
}
