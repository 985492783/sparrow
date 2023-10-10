package com.sparrow.core.auth;

import com.sparrow.core.annotation.Secured;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 985492783@qq.com
 * @date 2023/10/10 0:53
 */
public class AuthFilter implements Filter {
    
    private final RequestMappingHandlerMapping handlerMapping;
    
    public AuthFilter(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            HandlerExecutionChain executionChain = handlerMapping.getHandler(req);
            if (executionChain == null) {
                chain.doFilter(request, response);
                return;
            }
            HandlerMethod handler = (HandlerMethod) executionChain.getHandler();
            Method method = handler.getMethod();
            if (method.isAnnotationPresent(Secured.class)) {
                
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            chain.doFilter(request, response);
        }
    }
}
