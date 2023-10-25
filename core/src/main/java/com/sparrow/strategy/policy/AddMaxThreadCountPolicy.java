package com.sparrow.strategy.policy;

import com.sparrow.core.ThreadEndPoint;
import com.sparrow.strategy.Policy;

import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 16:04
 */
public class AddMaxThreadCountPolicy implements Policy {
    
    private int maximumCount;
    
    private int addCount;
    
    public AddMaxThreadCountPolicy(Map<String, Object> load) {
        this.maximumCount = (int) load.get("maximumCount");
        this.addCount = (int) load.get("addCount");
    }
    
    @Override
    public void execute(ThreadEndPoint threadEndPoint) {
    
    }
}
