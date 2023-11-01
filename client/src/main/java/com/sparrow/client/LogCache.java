package com.sparrow.client;

import com.sparrow.common.entity.LogMessageDO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 985492783@qq.com
 * @date 2023/11/1 23:22
 */
public class LogCache {
    private static BlockingQueue<LogMessageDO> messageDODeque = new LinkedBlockingQueue<>();
    
    public static void addMessage(long timestamp, String log) {
        messageDODeque.offer(new LogMessageDO(log, timestamp));
    }
    
    public static List<LogMessageDO> takeMessage() {
        try {
            LogMessageDO take = messageDODeque.take();
            List<LogMessageDO> list = new ArrayList<>();
            list.add(take);
            while (true) {
                LogMessageDO messageDO = messageDODeque.poll();
                if (messageDO == null) {
                    return list;
                }
                list.add(messageDO);
            }
        } catch (InterruptedException e) {
            return new ArrayList<>();
        }
    }
}
