package com.sparrow.client.executor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 985492783@qq.com
 * @date 2023/10/9 23:29
 */
public class SimpleExecutorWrapper extends AbstractExecutorWrapper {
    
    public SimpleExecutorWrapper(ThreadPoolExecutor threadPoolExecutor) {
        super(threadPoolExecutor);
    }
    
}
