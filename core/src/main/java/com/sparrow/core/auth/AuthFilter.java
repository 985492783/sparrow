package com.sparrow.core.auth;

import com.sparrow.core.annotation.Secured;
import com.sparrow.exception.AccessException;
import org.springframework.http.HttpStatus;
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
    
    private AuthConfig authConfig;
    
    public AuthFilter(RequestMappingHandlerMapping handlerMapping, AuthConfig authConfig) {
        this.handlerMapping = handlerMapping;
        this.authConfig = authConfig;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            if (!authConfig.isEnabled()) {
                chain.doFilter(request, response);
                return;
            }
            HandlerExecutionChain executionChain = handlerMapping.getHandler(req);
            if (executionChain == null) {
                chain.doFilter(request, response);
                return;
            }
            HandlerMethod handler = (HandlerMethod) executionChain.getHandler();
            Method method = handler.getMethod();
            if (method.isAnnotationPresent(Secured.class)) {
                if (!authConfig.login(req)) {
                    throw new AccessException("登录失败");
                }
            }
            chain.doFilter(request, response);
        } catch (AccessException e) {
            resp.sendError(HttpStatus.FORBIDDEN.value(), "auth fail!");
        } catch (Exception e) {
            chain.doFilter(request, response);
        }
    }
}
