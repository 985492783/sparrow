package com.sparrow.strategy;

import com.sparrow.common.entity.RuleDTO;
import com.sparrow.core.utils.RuleUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 14:55
 */
@Service
public class RuleCache {
    
    private Map<String, Rule> ruleMap = new ConcurrentHashMap<>();
    
    public void loadRule(RuleDTO ruleDTO) {
        String ruleId = ruleDTO.getId();
        Rule rule = RuleUtils.yamlToRule(ruleDTO.getYaml());
        rule.checkRule();
        ruleMap.put(ruleId, rule);
    }
    
}
