package com.sparrow.biz.manager;

import com.sparrow.biz.InstanceManager;
import com.sparrow.common.entity.Instance;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.convert.InstanceConvert;
import com.sparrow.core.factory.InstanceRegistry;
import org.springframework.stereotype.Service;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:50
 */
@Service
public class InstanceManagerImpl implements InstanceManager {
    
    private final InstanceConvert convert = InstanceConvert.INSTANCE;
    
    private final InstanceRegistry instanceRegistry;
    
    public InstanceManagerImpl(InstanceRegistry instanceRegistry) {
        this.instanceRegistry = instanceRegistry;
    }
    
    @Override
    public String register(InstanceDO instanceDO) {
        Instance instance = convert.map(instanceDO);
        return instanceRegistry.register(instance);
    }
}
