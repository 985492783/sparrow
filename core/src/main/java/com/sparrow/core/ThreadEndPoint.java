package com.sparrow.core;

import com.sparrow.common.entity.ExecutorData;
import com.sparrow.rule.Policy;
import com.sparrow.rule.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * thread pool message.
 *
 * @author 985492783@qq.com
 * @date 2023/10/25 0:18
 */
public class ThreadEndPoint {
    
    private final String projectId;
    
    private final int hashCode;
    
    private long completedTaskCount;
    
    private int corePoolSize;
    
    private int maximumPoolSize;
    
    private long keepAliveTime;
    
    private int queueSize;
    
    private int remainingCapacity;
    
    public ThreadEndPoint(String projectId, int hashCode) {
        this.projectId = projectId;
        this.hashCode = hashCode;
    }
    
    private ThreadContext threadContext = new ThreadContext();
    
    private List<Rule> rules = new ArrayList<>();
    
    public void update(ExecutorData executorData) {
        loadData(executorData);
        //TODO update ruleContext
        for (Rule rule : rules) {
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
        
    }
}
