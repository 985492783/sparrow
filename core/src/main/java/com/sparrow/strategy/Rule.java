package com.sparrow.strategy;

import com.sparrow.core.ThreadContext;
import com.sparrow.exception.RuleIllegalException;

/**
 * 规则->yaml。
 *
 * @author 985492783@qq.com
 * @date 2023/10/24 23:47
 */
public interface Rule {
    
    RuleEnum getType();
    
    void checkRule() throws RuleIllegalException;
    
    boolean trigger(ThreadContext threadContext);
    
    Policy getPolicy();
}
