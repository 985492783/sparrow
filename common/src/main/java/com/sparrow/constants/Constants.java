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
        
        public static final String SPARROW = "";
        
        public static final String SPARROW_V1 = SPARROW + "/v1";
        
        public static final String CONSOLE_V1 = SPARROW_V1 + "/console";
        
        public static final String CONSOLE_V1_STATE = CONSOLE_V1 + "/state";
        
        public static final String INSTANCE_V1 = SPARROW_V1 + "/instance";
        
        public static final String INSTANCE_V1_REGISTER = INSTANCE_V1 + "/register";
        
        public static final String INSTANCE_V1_DEREGISTER = INSTANCE_V1 + "/deregister";
        
        public static final String INSTANCE_V1_QUERY = INSTANCE_V1 + "/query";
        
        public static final String EXECUTOR_V1_UPLOAD = SPARROW_V1 + "/executor/upload";
        
        public static final String LOG_V1 = SPARROW_V1 + "/log";
        
        public static final String LOG_V1_UPLOAD = LOG_V1 + "/upload";
    }
    
    public static final String NULL = "";
}
