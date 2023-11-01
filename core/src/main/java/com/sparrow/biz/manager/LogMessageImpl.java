package com.sparrow.biz.manager;

import com.sparrow.biz.LogManager;
import com.sparrow.common.entity.LogMessage;
import com.sparrow.common.entity.LogMessageDO;
import com.sparrow.common.entity.LogMessageQuery;
import com.sparrow.convert.LogConvert;
import com.sparrow.core.LogList;
import com.sparrow.core.utils.Arrays;
import com.sparrow.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void create(String projectId) {
        logManager.computeIfAbsent(projectId, (k) -> new LogList());
    }
    
    @Override
    public void addLog(LogMessageDO logMessageDO) {
        LogMessage logMessage = logConvert.map(logMessageDO);
        LogList logList = logManager.get(logMessageDO.getProjectId());
        if (logList == null) {
            return;
        }
        logList.add(logMessage);
    }
    
    @Override
    public List<LogMessage> query(LogMessageQuery logMessageQuery) {
        String projectId = logMessageQuery.getProjectId();
        LogList logList = logManager.get(projectId);
        if (logList == null) {
            return Arrays.newArrayList();
        }
        if (StringUtils.isEmpty(logMessageQuery.getPattern())) {
            return logList.search(logMessageQuery.getStartTime(), logMessageQuery.getEndTime());
        }
        return logList.search(logMessageQuery.getStartTime(), logMessageQuery.getEndTime(),
                logMessageQuery.getPattern());
    }
    
    @Override
    public boolean batchAdd(List<LogMessageDO> list) {
        list.forEach(this::addLog);
        return true;
    }
    
    
}
