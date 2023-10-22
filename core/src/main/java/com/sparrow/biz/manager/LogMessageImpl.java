package com.sparrow.biz.manager;

import com.sparrow.biz.LogManager;
import com.sparrow.common.entity.LogMessage;
import com.sparrow.common.entity.LogMessageDO;
import com.sparrow.convert.LogConvert;
import com.sparrow.core.LogList;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 985492783@qq.com
 * @date 2023/10/22 23:06
 */
@Service
public class LogMessageImpl implements LogManager {
    
    private final LogConvert logConvert = LogConvert.INSTANCE;
    
    private final Map<String, LogList> logManager = new ConcurrentHashMap<>();
    
    @Override
    public void addLog(LogMessageDO logMessageDO) {
        LogMessage logMessage = logConvert.map(logMessageDO);
        LogList logList = logManager.get(logMessageDO.getProjectId());
        if (logList == null) {
            return;
        }
        logList.add(logMessage);
    }
    
}
