package com.sparrow.client;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.sparrow.client.config.SparrowConfig;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;

import java.net.InetAddress;

/**
 * @author 985492783@qq.com
 * @date 2023/10/13 1:10
 */
public class SparrowClient {
    
    private final SparrowConfig sparrowConfig;
    
    public SparrowClient(SparrowConfig sparrowConfig) {
        this.sparrowConfig = sparrowConfig;
    }
    
    public void register(String host) {
        if (sparrowConfig.getExecutorEnabled()) {
            initThreadPool();
        }
        InetAddress inetAddress = NetUtil.getLocalhost();
        String ip = inetAddress.getHostAddress();
        InstanceDO instanceDO = new InstanceDO(sparrowConfig.getName(), ip);
        String content = HttpUtil.post(host + Constants.Url.SPARROW_V1_REGISTER, JSONUtil.toJsonStr(instanceDO));
        Response<String> response = JSONUtil.toBean(content, Response.class);
        System.out.println("client register success, id = " + response.getData() + "!");
    }
    
    private static void initThreadPool() {
        try {
            Class.forName("com.sparrow.client.executor.ExecutorWrapperFactory");
            System.out.println("agent success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
