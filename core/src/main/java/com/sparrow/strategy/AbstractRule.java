package com.sparrow.strategy;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 15:19
 */
public abstract class AbstractRule implements Rule {
    private final Policy policy;
    
    public AbstractRule(Policy policy) {
        this.policy = policy;
    }
}
