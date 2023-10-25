package com.sparrow.core.utils;

import cn.hutool.core.io.FileUtil;
import com.sparrow.strategy.Rule;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author 985492783@qq.com
 * @date 2023/10/25 15:56
 */
public class RuleUtilsTest {
    
    @Test
    public void testYamlToRule() {
        String content = FileUtil.readString("rule.yaml", StandardCharsets.UTF_8);
        Rule rule = RuleUtils.yamlToRule(content);
        System.out.println(rule);
    }
}
