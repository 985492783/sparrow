package com.sparrow.common.entity;

/**
 * @author 985492783@qq.com
 * @date 2023/10/22 22:59
 */
public class LogMessageDO {

    private String projectId;
    
    private String logger;
    
    private long timestamp;
    
    public String getProjectId() {
        return projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public String getLogger() {
        return logger;
    }
    
    public void setLogger(String logger) {
        this.logger = logger;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
