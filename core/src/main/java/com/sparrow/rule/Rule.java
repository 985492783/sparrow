package com.sparrow.rule;

import com.sparrow.core.ThreadContext;

/**
 * 规则->yaml。
 *
 * @author 985492783@qq.com
 * @date 2023/10/24 23:47
 */
public interface Rule {
    
    void checkRule();
    
    boolean trigger(ThreadContext threadContext);
    
    Policy getPolicy();
}
