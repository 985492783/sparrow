package com.sparrow.controller;

import com.sparrow.biz.InstanceManager;
import com.sparrow.common.entity.Instance;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:29
 */
@RestController
public class NamingController {
    
    @Autowired
    private InstanceManager instanceManager;
    
    @PostMapping(Constants.Url.INSTANCE_V1_REGISTER)
    public Response<String> register(@RequestBody InstanceDO instanceDO) {
        String id = instanceManager.register(instanceDO);
        return Response.success(id);
    }
    
    @PostMapping(Constants.Url.INSTANCE_V1_QUERY)
    public Response<List<Instance>> query() {
        return Response.success(instanceManager.query());
    }
}
