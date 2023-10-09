package com.sparrow.advice.executor;

import com.sparrow.ThreadPoolHolder;
import net.bytebuddy.asm.Advice;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/6 1:20
 */

public class ThreadPoolExecutorConstructorAdvice {
    @Advice.OnMethodExit
    public static void constructor(@Advice.This Object obj, @Advice.AllArguments Object[] args){
        try {
            if(obj!=null){
                ThreadPoolExecutor executor = (ThreadPoolExecutor)obj;
                ThreadPoolHolder.register(executor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
