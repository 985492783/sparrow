package com.sparrow.strategy;

import com.sparrow.strategy.policy.AddMaxThreadCountPolicy;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 16:04
 */
public enum PolicyEnum {
    ADD_MAX_THREAD_COUNT(AddMaxThreadCountPolicy.class);
    
    private Class<? extends Policy> clazz;
    
    PolicyEnum(Class<? extends Policy> clazz) {
        this.clazz = clazz;
    }
    
    public Class<? extends Policy> getClazz() {
        return clazz;
    }
}
