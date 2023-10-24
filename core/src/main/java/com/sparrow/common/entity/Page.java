package com.sparrow.common.entity;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/23 23:30
 */
public class Page<T> {
    
    private int total;
    
    private int pageSize;
    
    private int pageNum;
    
    private List<T> data;
    
    public Page(int total, int pageSize, int pageNum) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }
    
    public Page() {
    }
    
    public static <T> Page<T> of(int total, int pageSize, int pageNum) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        int page = (total + pageSize - 1) / pageSize;
        if (pageNum > page) {
            pageNum = 1;
        }
        return new Page<>(total, pageSize, pageNum);
    }
    
    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public List<T> getData() {
        return data;
    }
    
    public void setData(List<T> data) {
        this.data = data;
    }
}
