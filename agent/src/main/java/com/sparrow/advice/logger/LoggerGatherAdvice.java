package com.sparrow.advice.logger;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.sparrow.client.TraceCache;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Field;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 22:58
 */
public class LoggerGatherAdvice {
    
    @Advice.OnMethodEnter
    public static void changeMessage(@Advice.This Object obj) {
        LoggingEvent loggingEvent = (LoggingEvent) obj;
        try {
            Field field = loggingEvent.getClass().getDeclaredField("message");
            field.setAccessible(true);
            field.set(loggingEvent, TraceCache.getTrace() + loggingEvent.getMessage());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        
    }
}
