package com.sparrow.strategy;

import com.sparrow.strategy.rule.QueueFullRule;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 15:36
 */
public enum RuleEnum {
    QUEUE_FULL(QueueFullRule.class);
    
    private final Class<? extends Rule> clazz;
    
    RuleEnum(Class<? extends Rule> clazz) {
        this.clazz = clazz;
    }
    
    public Class<? extends Rule> getClazz() {
        return clazz;
    }
}
