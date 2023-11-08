package com.sparrow.client.config;

import cn.hutool.core.util.RandomUtil;
import com.sparrow.util.IpUtils;
import com.sparrow.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 23:39
 */
public class SparrowConfig {
    
    private static final SparrowConfig sparrowConfig = new SparrowConfig();
    
    public static final String LOG_ENABLED = "logEnabled";
    
    public static final String EXECUTOR_ENABLED = "executorEnabled";
    
    public static final String NAME = "name";
    
    private Boolean logEnabled = false;
    
    private Boolean executorEnabled = false;
    
    private String name;
    
    private String host;
    
    private String ip;
    
    private String id;
    
    public static SparrowConfig parserConfig(String args) {
        if (StringUtils.isBlank(args)) {
            return sparrowConfig;
        }
        Map<String, String> params = new HashMap<>();
        String[] configs = args.split("&");
        for (String config : configs) {
            String[] split = config.split("=");
            if (split.length != 2 || StringUtils.isBlank(split[0]) || StringUtils.isBlank(split[1])) {
                return sparrowConfig;
            }
            params.put(split[0], split[1]);
        }
        sparrowConfig.parserConfig(params);
        return sparrowConfig;
    }
    
    private void parserConfig(Map<String, String> params) {
        setLogEnabled(Boolean.valueOf(params.getOrDefault(LOG_ENABLED, logEnabled.toString())));
        setExecutorEnabled(Boolean.valueOf(params.getOrDefault(EXECUTOR_ENABLED, executorEnabled.toString())));
        setName(params.getOrDefault(NAME, RandomUtil.randomString(16)));
    }
    
    public static SparrowConfig getInstance() {
        return SparrowConfig.sparrowConfig;
    }
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getLogEnabled() {
        return logEnabled;
    }
    
    public void setLogEnabled(Boolean logEnabled) {
        this.logEnabled = logEnabled;
    }
    
    public Boolean getExecutorEnabled() {
        return executorEnabled;
    }
    
    public void setExecutorEnabled(Boolean executorEnabled) {
        this.executorEnabled = executorEnabled;
    }
    
    public Boolean state() {
        return getLogEnabled() || getExecutorEnabled();
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getIp() {
        if (ip == null) {
            ip = IpUtils.getLocalIp4Address().getHostAddress();
        }
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
}
