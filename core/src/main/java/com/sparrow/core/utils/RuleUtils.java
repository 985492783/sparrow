package com.sparrow.core.utils;

import com.sparrow.strategy.Policy;
import com.sparrow.strategy.PolicyEnum;
import com.sparrow.strategy.Rule;
import com.sparrow.strategy.RuleEnum;
import com.sparrow.strategy.policy.AddMaxThreadCountPolicy;
import com.sparrow.strategy.rule.QueueFullRule;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 15:04
 */
public class RuleUtils {
    
    public static Rule yamlToRule(String yamlString) {
        Yaml yaml = new Yaml();
        Map<String, Object> load = yaml.load(yamlString);
        Class<? extends Rule> clazz = RuleEnum.valueOf(String.valueOf(load.get("ruleType"))).getClazz();
        if (QueueFullRule.class == clazz) {
            return new QueueFullRule(load, yamlToPolicy((Map<String, Object>) load.get("policy")));
        }
        return null;
    }
    
    private static Policy yamlToPolicy(Map<String, Object> load) {
        Class<? extends Policy> policyClass = PolicyEnum.valueOf(String.valueOf(load.get("policyType"))).getClazz();
        if (AddMaxThreadCountPolicy.class == policyClass) {
            return new AddMaxThreadCountPolicy(load);
        }
        return null;
    }
}
