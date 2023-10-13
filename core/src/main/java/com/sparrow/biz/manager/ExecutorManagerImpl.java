package com.sparrow.biz.manager;

import cn.hutool.json.JSONUtil;
import com.sparrow.biz.ExecutorManager;
import com.sparrow.common.entity.ExecutorData;
import com.sparrow.common.entity.ExecutorDataRequest;
import com.sparrow.convert.ExecutorConvert;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/13 14:40
 */
@Service
public class ExecutorManagerImpl implements ExecutorManager {
    
    private final ExecutorConvert executorConvert = ExecutorConvert.INSTANCE;
    @Override
    public void upload(ExecutorDataRequest request) {
        List<ExecutorData> list = executorConvert.map(request.getList());
        System.out.println(JSONUtil.toJsonStr(list));
    }
}
