package com.sparrow;

import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.sparrow.advice.executor.ThreadPoolExecutorConstructorAdvice;
import com.sparrow.advice.executor.ThreadPoolExecutorDestroyAdvice;
import com.sparrow.advice.logger.LoggerConfigureAdvice;
import com.sparrow.advice.logger.LoggerGatherAdvice;
import com.sparrow.client.config.SparrowConfig;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 22:49
 */
public class PreMain {
    
    private static void premain(String args, Instrumentation instrumentation) {
        SparrowConfig sparrowConfig = SparrowConfig.parserConfig(args);
        if (sparrowConfig.getLogEnabled()) {
            agentLog(instrumentation);
        }
        if (sparrowConfig.getExecutorEnabled()) {
            agentThreadPool(instrumentation);
        }
    }
    
    private static void agentThreadPool(Instrumentation instrumentation) {
        System.out.println("threadPool agent start!");
        new AgentBuilder.Default().disableClassFormatChanges()
                //默认是不对bootstrap类加载器加载的对象instrumentation，忽略某个type后，就可以了
                .ignore(ElementMatchers.not(ElementMatchers.hasSuperType(ElementMatchers.is(ThreadPoolExecutor.class))))
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION).with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .with(AgentBuilder.InjectionStrategy.UsingUnsafe.INSTANCE)
                .type(ElementMatchers.is(ThreadPoolExecutor.class)).transform(
                        (builder, typeDescription, classLoader, javaModule) -> builder.visit(
                                        Advice.to(ThreadPoolExecutorDestroyAdvice.class).on(ElementMatchers.named("finalize")
                                                .or(ElementMatchers.named("shutdown").or(ElementMatchers.named("shutdownNow")))))
                                .visit(Advice.to(ThreadPoolExecutorConstructorAdvice.class)
                                        .on(ElementMatchers.isConstructor()))).installOn(instrumentation);
    }
    
    private static void agentLog(Instrumentation instrumentation) {
        System.out.println("log agent start!");
        new AgentBuilder.Default().type(
                ElementMatchers.hasSuperType(ElementMatchers.namedOneOf(ILoggingEvent.class.getName()))
                        .and(ElementMatchers.not(ElementMatchers.isInterface()))).transform(
                (builder, typeDescription, classLoader, javaModule) -> builder.method(
                                ElementMatchers.named("prepareForDeferredProcessing"))
                        .intercept(Advice.to(LoggerGatherAdvice.class))).installOn(instrumentation);
        new AgentBuilder.Default().type(
                ElementMatchers.hasSuperType(ElementMatchers.namedOneOf(Configurator.class.getName()))).transform(
                        ((builder, typeDescription, classLoader, javaModule) -> builder.method(
                                ElementMatchers.named("configure")).intercept(Advice.to(LoggerConfigureAdvice.class))))
                .installOn(instrumentation);
    }
}
