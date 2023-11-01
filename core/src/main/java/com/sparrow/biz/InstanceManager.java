package com.sparrow.biz;

import com.sparrow.common.entity.Instance;
import com.sparrow.common.entity.InstanceDO;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:34
 */
public interface InstanceManager {
    
    String register(InstanceDO instanceDO);
    
    List<Instance> query();
}
