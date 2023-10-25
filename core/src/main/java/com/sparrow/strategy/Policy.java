package com.sparrow.strategy;

import com.sparrow.core.ThreadEndPoint;

/**
 * 规则的执行类。
 * @author 985492783@qq.com
 * @date 2023/10/25 0:16
 */
public interface Policy {

    void execute(ThreadEndPoint threadEndPoint);
}
