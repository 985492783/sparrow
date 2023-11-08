package com.sparrow.trace.condition;

import com.sparrow.trace.core.TraceBeanRegister;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 16:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SparrowCondition.class)
public @interface SparrowConditional {
    
    Class<? extends TraceBeanRegister> value();
}
