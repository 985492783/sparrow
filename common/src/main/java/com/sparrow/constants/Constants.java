package com.sparrow.constants;

/**
 * @author 985492783@qq.com
 * @date 2023/10/10 23:30
 */
public class Constants {
    
    public static class Auth {
        
        public static final String SPARROW_AUTH_ENABLED = "sparrow.auth.enabled";
    }
    
    public static class Url {
    
        public static final String SPARROW = "/sparrow";
    
        public static final String SPARROW_V1 = SPARROW + "/v1";
    
        public static final String SPARROW_V1_REGISTER = SPARROW_V1 + "/register";
        
        public static final String EXECUTOR_V1_UPLOAD = SPARROW_V1 + "/executor/upload";
    }
    
    public static final String NULL = "";
}
