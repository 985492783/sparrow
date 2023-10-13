package com.sparrow.common.entity;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 12:30
 */
public class ExecutorDataDO {
    
    private int hashcode;
    
    private long completedTaskCount;
    
    private int corePoolSize;
    
    private int maximumPoolSize;
    
    private long keepAliveTime;
    
    public int getHashcode() {
        return hashcode;
    }
    
    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }
    
    public long getCompletedTaskCount() {
        return completedTaskCount;
    }
    
    public void setCompletedTaskCount(long completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }
    
    public int getCorePoolSize() {
        return corePoolSize;
    }
    
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }
    
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }
    
    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }
    
    public long getKeepAliveTime() {
        return keepAliveTime;
    }
    
    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }
    
    public ExecutorDataDO hashcode(int hashcode) {
        setHashcode(hashcode);
        return this;
    }
    
    public ExecutorDataDO completedTaskCount(long completedTaskCount) {
        setCompletedTaskCount(completedTaskCount);
        return this;
    }
    
    public ExecutorDataDO corePoolSize(int corePoolSize) {
        setCorePoolSize(corePoolSize);
        return this;
    }
    
    public ExecutorDataDO maximumPoolSize(int maximumPoolSize) {
        setMaximumPoolSize(maximumPoolSize);
        return this;
    }
    
    public ExecutorDataDO keepAliveTime(long keepAliveTime) {
        setKeepAliveTime(keepAliveTime);
        return this;
    }
}