package com.sparrow.controller;

import com.sparrow.biz.LogManager;
import com.sparrow.common.entity.LogMessage;
import com.sparrow.common.entity.LogMessageDO;
import com.sparrow.common.entity.LogMessageQuery;
import com.sparrow.common.entity.Page;
import com.sparrow.common.entity.PageParam;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/24 0:00
 */
@RestController
public class LogController {
    
    @Autowired
    private LogManager logManager;
    
    @PostMapping(Constants.Url.LOG_V1 + "/query")
    public Page<LogMessage> page(PageParam<LogMessageQuery> pageParam) {
        List<LogMessage> logMessages = logManager.query(pageParam.getModel());
        Page<LogMessage> page = Page.of(logMessages.size(), pageParam.getPageSize(), pageParam.getPageNum());
        page.setData(logMessages.subList(page.getOffset(),
                Math.min(logMessages.size(), page.getOffset() + page.getPageSize())));
        return page;
    }
    
    @PostMapping(Constants.Url.LOG_V1_UPLOAD)
    public Response<Boolean> add(List<LogMessageDO> list) {
        logManager.batchAdd(list);
        return Response.success(true);
    }
}
