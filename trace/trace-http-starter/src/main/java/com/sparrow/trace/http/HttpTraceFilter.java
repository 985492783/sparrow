package com.sparrow.trace.http;

import cn.hutool.core.util.IdUtil;
import com.sparrow.client.SparrowClient;
import com.sparrow.client.TraceCache;
import com.sparrow.trace.core.TraceBeanRegister;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 985492783@qq.com
 * @date 2023/11/8 18:49
 */
@Order(-1)
public class HttpTraceFilter implements Filter, TraceBeanRegister {
    
    private final SparrowClient sparrowClient;
    
    public HttpTraceFilter(SparrowClient sparrowClient) {
        this.sparrowClient = sparrowClient;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String idStr = sparrowClient.getTraceId();
            TraceCache.setTraceId(idStr);
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("traceId", idStr);
            chain.doFilter(request, response);
        } finally {
            TraceCache.setTraceId(null);
        }
    }
}
