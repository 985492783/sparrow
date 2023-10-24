package com.sparrow.common.entity;

/**
 * @author 985492783@qq.com
 * @date 2023/10/23 23:53
 */
public class PageParam<T> {
    
    private int pageNum;
    
    private int pageSize;
    
    private T model;
    
    public int getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public T getModel() {
        return model;
    }
    
    public void setModel(T model) {
        this.model = model;
    }
}
