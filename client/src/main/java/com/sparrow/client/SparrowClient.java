package com.sparrow.client;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.sparrow.client.config.SparrowConfig;
import com.sparrow.client.executor.AbstractExecutorWrapper;
import com.sparrow.client.executor.ExecutorWrapperFactory;
import com.sparrow.common.entity.ExecutorDataDO;
import com.sparrow.common.entity.ExecutorDataRequest;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

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
        sparrowConfig.setHost(host);
        InetAddress inetAddress = NetUtil.getLocalhost();
        String ip = inetAddress.getHostAddress();
        InstanceDO instanceDO = new InstanceDO(sparrowConfig.getName(), ip);
        String content = HttpUtil.post(host + Constants.Url.INSTANCE_V1_REGISTER, JSONUtil.toJsonStr(instanceDO));
        Response<String> response = JSONUtil.toBean(content, Response.class);
        sparrowConfig.setId(response.getData());
        
        if (sparrowConfig.getExecutorEnabled()) {
            initThreadPool();
        }
    }
    
    private void initThreadPool() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Map<Integer, AbstractExecutorWrapper> executorWrapperMap = ExecutorWrapperFactory.updateAndGet();
                List<ExecutorDataDO> list = executorWrapperMap.values().stream()
                        .map(AbstractExecutorWrapper::buildExecutorData).collect(Collectors.toList());
                ExecutorDataRequest request = new ExecutorDataRequest(sparrowConfig.getId(), list);
                String content = HttpUtil.post(sparrowConfig.getHost() + Constants.Url.EXECUTOR_V1_UPLOAD,
                        JSONUtil.toJsonStr(request));
                Response<Boolean> response = JSONUtil.toBean(content, Response.class);
            }
        }, 5000L, 5000L);
    }
    
}
