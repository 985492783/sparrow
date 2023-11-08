package com.sparrow.biz.manager;

import com.sparrow.biz.InstanceManager;
import com.sparrow.biz.LogManager;
import com.sparrow.common.entity.Instance;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.convert.InstanceConvert;
import com.sparrow.core.factory.InstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:50
 */
@Service
public class InstanceManagerImpl implements InstanceManager {
    
    private final InstanceConvert convert = InstanceConvert.INSTANCE;
    
    private final InstanceRegistry instanceRegistry;
    
    @Autowired
    private LogManager logManager;
    
    public InstanceManagerImpl(InstanceRegistry instanceRegistry) {
        this.instanceRegistry = instanceRegistry;
    }
    
    @Override
    public String register(InstanceDO instanceDO) {
        Instance instance = convert.map(instanceDO);
        String register = instanceRegistry.register(instance);
        logManager.create(register);
        return register;
    }
    
    @Override
    public List<Instance> query(String key, String value) {
        List<Instance> instances = instanceRegistry.getInstances();
        if (key == null || value == null) {
            return instances;
        }
        return instances.stream().filter((instance) -> value.equals(instance.getParam(key)))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean deregister(InstanceDO instanceDO) {
        Instance instance = convert.map(instanceDO);
        Instance deregister = instanceRegistry.deregister(instance);
        if (deregister == null) {
            return false;
        }
        logManager.destroy(deregister.getId());
        return true;
    }
}
