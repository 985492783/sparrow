package com.sparrow.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

/**
 * @author 985492783@qq.com
 * @date 2023/10/23 23:55
 */
public class LogMessageQuery {
    
    private String projectId;
    
    @JsonSerialize(using = StringSerializer.class)
    private long startTime;
    
    @JsonSerialize(using = StringSerializer.class)
    private long endTime;
    
    private String pattern;
    
    public String getProjectId() {
        return projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public long getStartTime() {
        return startTime;
    }
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    public long getEndTime() {
        return endTime;
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    public String getPattern() {
        return pattern;
    }
    
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
