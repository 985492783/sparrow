package com.sparrow.client.config;

import cn.hutool.core.util.RandomUtil;
import com.sparrow.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 23:39
 */
public class SparrowConfig {
    
    private Boolean logEnabled = false;
    
    private Boolean executorEnabled = false;
    
    private String name;
    
    private String sparrow = "http://localhost:8888";
    
    public void parserConfig(String args) {
        if (StringUtils.isBlank(args)) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        String[] configs = args.split("&");
        for (String config : configs) {
            String[] split = config.split("=");
            if (split.length != 2 || StringUtils.isBlank(split[0]) || StringUtils.isBlank(split[1])) {
                return;
            }
            params.put(split[0], split[1]);
        }
        parserConfig(params);
    }
    
    private void parserConfig(Map<String, String> params) {
        setLogEnabled(Boolean.valueOf(params.getOrDefault("logEnabled", logEnabled.toString())));
        setExecutorEnabled(Boolean.valueOf(params.getOrDefault("executorEnabled", executorEnabled.toString())));
        setName(params.getOrDefault("name", RandomUtil.randomString(16)));
        setSparrow(params.getOrDefault("sparrow", getSparrow()));
    }
    
    public String getSparrow() {
        return sparrow;
    }
    
    public void setSparrow(String sparrow) {
        this.sparrow = sparrow;
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
}
