package com.sparrow.strategy.rule;

import com.sparrow.core.ThreadContext;
import com.sparrow.strategy.AbstractRule;
import com.sparrow.strategy.Policy;
import com.sparrow.strategy.RuleEnum;

import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 15:37
 */
public class QueueFullRule extends AbstractRule {
    
    private int frequency;
    
    public QueueFullRule(Map<String, Object> load, Policy policy) {
        super(policy);
        setFrequency((Integer) load.get("frequency"));
    }
    
    @Override
    public RuleEnum getType() {
        return RuleEnum.QUEUE_FULL;
    }
    
    @Override
    public void checkRule() {
    
    }
    
    @Override
    public boolean trigger(ThreadContext threadContext) {
        if (threadContext.getRemainingCapacities().size() < getFrequency()) {
            return false;
        }
        for (int i = 0; i < getFrequency(); i++) {
            if (threadContext.getRemainingCapacities().get(i) != 0) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Policy getPolicy() {
        return null;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
