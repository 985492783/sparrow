package com.sparrow.core;

import com.sparrow.common.entity.ExecutorData;
import com.sparrow.strategy.Policy;
import com.sparrow.strategy.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * thread pool message.
 *
 * @author 985492783@qq.com
 * @date 2023/10/25 0:18
 */
public class ThreadEndPoint {
    
    private final String projectId;
    
    private final int hashCode;
    
    public ThreadEndPoint(String projectId, int hashCode) {
        this.projectId = projectId;
        this.hashCode = hashCode;
    }
    
    private ThreadContext threadContext = new ThreadContext();
    
    private Map<String, Rule> rules = new ConcurrentHashMap<>();
    
    public void update(ExecutorData executorData) {
        loadData(executorData);
        for (Rule rule : rules.values()) {
            try {
                if (rule.trigger(threadContext)) {
                    Policy policy = rule.getPolicy();
                    policy.execute(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void loadData(ExecutorData executorData) {
        threadContext.getCorePoolSizes().add(executorData.getCorePoolSize());
        threadContext.getKeepAliveTimes().add(executorData.getKeepAliveTime());
        threadContext.getCompletedTaskCounts().add(executorData.getCompletedTaskCount());
        threadContext.getQueueSizes().add(executorData.getQueueSize());
        threadContext.getMaximumPoolSizes().add(executorData.getMaximumPoolSize());
        threadContext.getRemainingCapacities().add(executorData.getRemainingCapacity());
    }
}
