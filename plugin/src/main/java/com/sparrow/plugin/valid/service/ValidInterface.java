package com.sparrow.plugin.valid.service;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 23:33
 */
public interface ValidInterface<T> {
    
    boolean valid(T t);
}
