package com.sparrow.common.entity;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 12:07
 */
public class Response<T> {
    
    private int code;
    
    private String message;
    
    private T data;
    
    public Response() {
    }
    
    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public static <T> Response<T> success(T t) {
        return new Response<>(200, null, t);
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
