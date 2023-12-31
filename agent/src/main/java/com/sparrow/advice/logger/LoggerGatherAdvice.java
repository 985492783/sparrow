package com.sparrow.advice.logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.sparrow.client.LogCache;
import com.sparrow.client.TraceCache;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

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
    
    @Advice.OnMethodExit
    public static void gatherMessage(@Advice.This Object obj) {
        try {
            ILoggingEvent loggingEvent = (ILoggingEvent) obj;
            long timeStamp = loggingEvent.getTimeStamp();
            byte[] encode = LoggerConfigureAdvice.encoder.encode(loggingEvent);
            LogCache.addMessage(timeStamp, new String(encode, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
