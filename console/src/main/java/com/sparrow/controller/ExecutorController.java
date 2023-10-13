package com.sparrow.controller;

import com.sparrow.biz.ExecutorManager;
import com.sparrow.common.entity.ExecutorDataDO;
import com.sparrow.common.entity.ExecutorDataRequest;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/13 14:10
 */
@RestController
public class ExecutorController {
    
    @Autowired
    private ExecutorManager executorManager;
    
    @PostMapping(Constants.Url.EXECUTOR_V1_UPLOAD)
    public Response<Boolean> upload(@RequestBody ExecutorDataRequest request) {
        executorManager.upload(request);
        return Response.success(true);
    }
}
