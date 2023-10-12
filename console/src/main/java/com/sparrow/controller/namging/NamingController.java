package com.sparrow.controller.namging;

import com.sparrow.biz.InstanceManager;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:29
 */
@RestController
public class NamingController {
    
    @Autowired
    private InstanceManager instanceManager;
    
    @PostMapping(Constants.Url.SPARROW_V1_REGISTER)
    public Response<String> register(@RequestBody InstanceDO instanceDO) {
        //TODO valid instanceDO
        String id = instanceManager.register(instanceDO);
        return Response.success(id);
    }
}
