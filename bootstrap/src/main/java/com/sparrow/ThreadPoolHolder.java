package com.sparrow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 23:07
 */
public class ThreadPoolHolder {
    private final static Map<Class<? extends Executor> , Map<Integer, Executor>> threadPoolMap = new ConcurrentHashMap<>();
    
    public static void register(Executor executor) {
        Class<? extends Executor> clazz = executor.getClass();
        Map<Integer, Executor> executorMap = threadPoolMap.computeIfAbsent(clazz,
                (key) -> new ConcurrentHashMap<>());
        executorMap.put(executor.hashCode(), executor);
    }
    
    public static void deregister(Executor executor) {
        Class<? extends Executor> clazz = executor.getClass();
        Map<Integer, Executor> executorMap = threadPoolMap.computeIfAbsent(clazz,
                (key) -> new ConcurrentHashMap<>());
        executorMap.remove(executor.hashCode());
    }
    
    public static Map<Class<? extends Executor>, Map<Integer, Executor>> getThreadPoolMap() {
        return threadPoolMap;
    }
}
