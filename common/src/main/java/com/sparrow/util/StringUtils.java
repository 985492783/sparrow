package com.sparrow.util;

/**
 * @author 985492783@qq.com
 * @date 2023/10/13 0:39
 */
public class StringUtils {
    
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
    
    public static boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
