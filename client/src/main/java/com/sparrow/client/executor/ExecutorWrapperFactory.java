package com.sparrow.client.executor;

import cn.hutool.json.JSONUtil;
import com.sparrow.ThreadPoolHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 23:19
 */
public class ExecutorWrapperFactory {
    
    private static ScheduledExecutorService executorService;
    
    static {
        executorService = new ScheduledThreadPoolExecutor(1, r -> {
            Thread thread = new Thread(r);
            thread.setName("agent-"+thread.getName());
            thread.setDaemon(true);
            return thread;
        });
        executorService.scheduleAtFixedRate(()-> {
            Map<Integer, AbstractExecutorWrapper> executorWrapperMap = ExecutorWrapperFactory.updateAndGet();
            System.out.println(JSONUtil.toJsonStr(executorWrapperMap));
        },0, 3000L, TimeUnit.MILLISECONDS);
    }
    
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
