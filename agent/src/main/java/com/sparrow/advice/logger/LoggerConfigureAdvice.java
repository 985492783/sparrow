package com.sparrow.advice.logger;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.layout.TTLLLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import net.bytebuddy.asm.Advice;

/**
 * @author 985492783@qq.com
 * @date 2023/11/1 21:17
 */
public class LoggerConfigureAdvice {
    
    public static LayoutWrappingEncoder<ILoggingEvent> encoder;
    
    @Advice.OnMethodEnter
    public static void configure(@Advice.Argument(0) Object obj) {
        if (encoder == null) {
            LoggerContext loggerContext = (LoggerContext) obj;
            encoder = new LayoutWrappingEncoder<>();
            encoder.setContext(loggerContext);
            TTLLLayout layout = new TTLLLayout();
            layout.setContext(loggerContext);
            layout.start();
            encoder.setLayout(layout);
        }
    }
}
