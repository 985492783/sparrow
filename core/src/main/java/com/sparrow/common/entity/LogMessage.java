package com.sparrow.common.entity;

/**
 * @author 985492783@qq.com
 * @date 2023/10/22 22:59
 */
public class LogMessage {
    
    private String logger;
    
    private long timestamp;
    
    public LogMessage(String logger, long timestamp) {
        this.logger = logger;
        this.timestamp = timestamp;
    }
    
    public LogMessage() {
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
    
    @Override
    public String toString() {
        return "LogMessage{" + "logger='" + logger + '\'' + ", timestamp=" + timestamp + '}';
    }
}
