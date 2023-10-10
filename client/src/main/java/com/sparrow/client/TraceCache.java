package com.sparrow.client;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 22:59
 */
public class TraceCache {
    
    private final static ThreadLocal<String> TRACE_ID_CACHE = new ThreadLocal<>();
    
    private final static String EMPTY = "";
    
    public static void setTraceId(String traceId) {
        TRACE_ID_CACHE.set(traceId);
    }
    
    public static void clear() {
        TRACE_ID_CACHE.remove();
    }
    
    public static String getTrace() {
        if (TRACE_ID_CACHE.get() == null) {
            return EMPTY;
        } else {
            return "[traceId=" + TRACE_ID_CACHE.get() + "]";
        }
    }
    
    public static String getTraceId() {
        return TRACE_ID_CACHE.get();
    }
}
