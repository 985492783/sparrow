package com.sparrow.core.executor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 23:18
 */
public abstract class AbstractExecutorWrapper {
    
    private final ThreadPoolExecutor threadPoolExecutor;
    
    private String className;
    
    private int hashCode;
    
    private long completedTaskCount;
    
    private int corePoolSize;
    
    private int maximumPoolSize;
    
    private long keepAliveTime;
    
    public AbstractExecutorWrapper(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.hashCode = threadPoolExecutor.hashCode();
        this.className = threadPoolExecutor.getClass().getName();
        this.completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        this.corePoolSize = threadPoolExecutor.getCorePoolSize();
        this.maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        this.keepAliveTime = threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS);
    }
    
    public AbstractExecutorWrapper update() {
        this.completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        this.corePoolSize = threadPoolExecutor.getCorePoolSize();
        this.maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        this.keepAliveTime = threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS);
        return this;
    }
    
    public String getClassName() {
        return className;
    }
    
    public int getHashCode() {
        return hashCode;
    }
    
    public long getCompletedTaskCount() {
        return completedTaskCount;
    }
    
    public int getCorePoolSize() {
        return corePoolSize;
    }
    
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }
    
    public long getKeepAliveTime() {
        return keepAliveTime;
    }
}
