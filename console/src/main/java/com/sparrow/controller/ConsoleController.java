package com.sparrow.controller;

import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/10 13:21
 */
@RestController
public class ConsoleController {
    
    @Value("${project.version}")
    private String version;
    
    @GetMapping(Constants.Url.CONSOLE_V1_STATE)
    public Response<Map<String, Object>> test() {
        return Response.success(new HashMap<String, Object>() {{
            put("version", version);
        }});
    }
}
