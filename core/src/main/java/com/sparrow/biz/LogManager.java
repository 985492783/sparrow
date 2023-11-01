package com.sparrow.biz;

import com.sparrow.common.entity.LogMessage;
import com.sparrow.common.entity.LogMessageDO;
import com.sparrow.common.entity.LogMessageQuery;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/22 22:57
 */
public interface LogManager {
    
    void create(String projectId);
    
    void addLog(LogMessageDO logMessageDO);
    
    List<LogMessage> query(LogMessageQuery logMessageQuery);
    
    boolean batchAdd(List<LogMessageDO> list);
}
