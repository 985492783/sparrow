package com.sparrow.exception;

/**
 * @author 985492783@qq.com
 * @date 2023/10/11 0:09
 */
public class AccessException extends SparrowException {
    private static final long serialVersionUID = -2926344920552803270L;
    
    public AccessException() {
    }
    
    public AccessException(int code) {
        this.setErrCode(code);
    }
    
    public AccessException(String msg) {
        this.setErrMsg(msg);
    }
}
