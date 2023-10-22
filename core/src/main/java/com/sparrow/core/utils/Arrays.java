package com.sparrow.core.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/23 0:09
 */
public class Arrays {
    
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }
    
    public static <T> List<T> newLinkedList() {
        return new LinkedList<>();
    }
}
