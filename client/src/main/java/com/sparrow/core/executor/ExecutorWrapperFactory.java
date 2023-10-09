package com.sparrow.core.executor;

import com.sparrow.ThreadPoolHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 23:19
 */
public class ExecutorWrapperFactory {
    
    public static AbstractExecutorWrapper getInstance(Executor executor) {
        if (executor instanceof ThreadPoolExecutor) {
            return new SimpleExecutorWrapper((ThreadPoolExecutor) executor);
        }
        return null;
    }
    
    public static Map<Integer, AbstractExecutorWrapper> updateAndGet() {
        Map<Integer, AbstractExecutorWrapper> map = new HashMap<>();
        Map<Class<? extends Executor>, Map<Integer, Executor>> threadPoolMap = ThreadPoolHolder.getThreadPoolMap();
        for (Map<Integer, Executor> executorMap : threadPoolMap.values()) {
            executorMap.values().forEach(executor -> {
                map.put(executor.hashCode(), getInstance(executor));
            });
        }
        return map;
    }
}
