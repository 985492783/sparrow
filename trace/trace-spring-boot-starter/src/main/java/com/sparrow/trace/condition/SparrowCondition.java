package com.sparrow.trace.condition;

import com.sparrow.trace.config.SparrowConfiguration;
import com.sparrow.trace.core.TraceBeanRegister;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 16:55
 */
public class SparrowCondition implements Condition {
    
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Set<String> list = context.getEnvironment().getProperty(SparrowConfiguration.PREFIX + ".protocols", Set.class, new HashSet());
        Set<Class<? extends TraceBeanRegister>> classList = new HashSet<>();
        for (String name : list) {
            try {
                classList.add((Class<? extends TraceBeanRegister>) Class.forName(name));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        Map<String, Object> attributes = metadata.getAnnotationAttributes(SparrowConditional.class.getName());
        Class<? extends TraceBeanRegister> value = (Class<? extends TraceBeanRegister>) attributes.get("value");
        return classList.contains(value);
    }
}
