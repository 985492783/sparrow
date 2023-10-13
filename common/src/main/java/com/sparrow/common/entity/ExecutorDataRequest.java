package com.sparrow.common.entity;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/13 16:32
 */
public class ExecutorDataRequest {
    
    private String instanceId;
    
    private List<ExecutorDataDO> list;
    
    public ExecutorDataRequest(String instanceId, List<ExecutorDataDO> list) {
        this.instanceId = instanceId;
        this.list = list;
    }
    
    public ExecutorDataRequest() {
    }
    
    public String getInstanceId() {
        return instanceId;
    }
    
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    
    public List<ExecutorDataDO> getList() {
        return list;
    }
    
    public void setList(List<ExecutorDataDO> list) {
        this.list = list;
    }
}
