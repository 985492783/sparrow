package com.sparrow.plugin.valid;

import java.lang.reflect.Field;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 23:19
 */
public class SparrowValidate {

    public static <T> void valid(T data, String message) {
        Class<?> clazz = data.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
        }
    }
}
