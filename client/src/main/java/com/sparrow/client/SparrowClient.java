package com.sparrow.client;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.sparrow.client.config.SparrowConfig;
import com.sparrow.client.executor.AbstractExecutorWrapper;
import com.sparrow.client.executor.ExecutorWrapperFactory;
import com.sparrow.common.entity.ExecutorDataDO;
import com.sparrow.common.entity.ExecutorDataRequest;
import com.sparrow.common.entity.InstanceDO;
import com.sparrow.common.entity.LogMessageDO;
import com.sparrow.common.entity.Response;
import com.sparrow.constants.Constants;
import com.sparrow.util.IpUtils;

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
    
    private boolean initialise = false;
    
    public SparrowClient(SparrowConfig sparrowConfig) {
        this.sparrowConfig = sparrowConfig;
        register();
        buildHook();
    }
    
    private void buildHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                deregister();
            }
        });
    }
    
    public synchronized void deregister() {
        InstanceDO instanceDO = buildInstance();
        String content = post(Constants.Url.INSTANCE_V1_DEREGISTER, instanceDO);
        this.initialise = false;
    }
    
    public synchronized void register() {
        if (this.initialise) {
            return;
        }
        InstanceDO instanceDO = buildInstance();
        String content = post(Constants.Url.INSTANCE_V1_REGISTER, instanceDO);
        Response<String> response = JSONUtil.toBean(content, Response.class);
        sparrowConfig.setId(response.getData());
        this.initialise = true;
        if (sparrowConfig.getExecutorEnabled()) {
            initThreadPool();
        }
        if (sparrowConfig.getLogEnabled()) {
            initLogger();
        }
    }
    
    private InstanceDO buildInstance() {
        String ip = sparrowConfig.getIp();
        InstanceDO instanceDO = new InstanceDO(sparrowConfig.getName(), ip);
        instanceDO.put(SparrowConfig.LOG_ENABLED, sparrowConfig.getLogEnabled().toString());
        instanceDO.put(SparrowConfig.EXECUTOR_ENABLED, sparrowConfig.getExecutorEnabled().toString());
        return instanceDO;
    }
    
    private void initLogger() {
        Thread thread = new Thread(() -> {
            while (true) {
                List<LogMessageDO> messageDOs = LogCache.takeMessage();
                if (!messageDOs.isEmpty()) {
                    messageDOs.forEach((e) -> e.setProjectId(sparrowConfig.getId()));
                    String content = post(Constants.Url.LOG_V1_UPLOAD, messageDOs);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    private void initThreadPool() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Map<Integer, AbstractExecutorWrapper> executorWrapperMap = ExecutorWrapperFactory.updateAndGet();
                List<ExecutorDataDO> list = executorWrapperMap.values().stream()
                        .map(AbstractExecutorWrapper::buildExecutorData).collect(Collectors.toList());
                ExecutorDataRequest request = new ExecutorDataRequest(sparrowConfig.getId(), list);
                String content = post(Constants.Url.EXECUTOR_V1_UPLOAD, request);
                Response<Boolean> response = JSONUtil.toBean(content, Response.class);
            }
        }, 5000L, 5000L);
    }
    
    protected <T> String post(String url, T t) {
        return HttpUtil.post(sparrowConfig.getHost() + "/sparrow" + url, JSONUtil.toJsonStr(t));
    }
    
    public SparrowConfig getSparrowConfig() {
        return sparrowConfig;
    }
    
    public String getTraceId() {
        if (this.initialise) {
            return sparrowConfig.getId() + "." + IdUtil.getSnowflakeNextIdStr();
        }
        return IdUtil.getSnowflakeNextIdStr();
    }
    
}
