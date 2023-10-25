package com.sparrow.core;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author 985492783@qq.com
 * @date 2023/10/24 23:58
 */
public class ThreadContext {
    
    private ContextData<Long> completedTaskCounts = new ContextData<>(1000);
    
    private ContextData<Integer> corePoolSizes = new ContextData<>(1000);
    
    private ContextData<Integer> maximumPoolSizes = new ContextData<>(1000);
    
    private ContextData<Long> keepAliveTimes = new ContextData<>(1000);
    
    private ContextData<Integer> queueSizes = new ContextData<>(1000);
    
    private ContextData<Integer> remainingCapacities = new ContextData<>(1000);
    
    public ContextData<Long> getCompletedTaskCounts() {
        return completedTaskCounts;
    }
    
    public void setCompletedTaskCounts(ContextData<Long> completedTaskCounts) {
        this.completedTaskCounts = completedTaskCounts;
    }
    
    public ContextData<Integer> getCorePoolSizes() {
        return corePoolSizes;
    }
    
    public void setCorePoolSizes(ContextData<Integer> corePoolSizes) {
        this.corePoolSizes = corePoolSizes;
    }
    
    public ContextData<Integer> getMaximumPoolSizes() {
        return maximumPoolSizes;
    }
    
    public void setMaximumPoolSizes(ContextData<Integer> maximumPoolSizes) {
        this.maximumPoolSizes = maximumPoolSizes;
    }
    
    public ContextData<Long> getKeepAliveTimes() {
        return keepAliveTimes;
    }
    
    public void setKeepAliveTimes(ContextData<Long> keepAliveTimes) {
        this.keepAliveTimes = keepAliveTimes;
    }
    
    public ContextData<Integer> getQueueSizes() {
        return queueSizes;
    }
    
    public void setQueueSizes(ContextData<Integer> queueSizes) {
        this.queueSizes = queueSizes;
    }
    
    public ContextData<Integer> getRemainingCapacities() {
        return remainingCapacities;
    }
    
    public void setRemainingCapacities(ContextData<Integer> remainingCapacities) {
        this.remainingCapacities = remainingCapacities;
    }
    
    public static class ContextData<T> extends LinkedList<T> {
        
        private final int capacity;
    
        public ContextData(int capacity) {
            this.capacity = capacity;
        }
    
        private Semaphore semaphore = new Semaphore(1);
        
        @Override
        public boolean add(T t) {
            try {
                semaphore.acquire();
                super.addFirst(t);
                if (this.size() > capacity) {
                    this.removeLast();
                }
                return true;
            } catch (InterruptedException ignored) {
            } finally {
                semaphore.release();
            }
            return false;
        }
    }
}
