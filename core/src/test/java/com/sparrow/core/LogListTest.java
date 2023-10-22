package com.sparrow.core;

import com.sparrow.common.entity.LogMessage;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author 985492783@qq.com
 * @date 2023/10/23 0:15
 */
public class LogListTest {

    @Test
    public void testLogListAdd() {
        LogList logList = new LogList();
        logList.add(new LogMessage("8", 8));
        logList.add(new LogMessage("10", 10));
        logList.add(new LogMessage("91", 9));
        logList.add(new LogMessage("92", 9));
        logList.add(new LogMessage("93", 9));
        logList.add(new LogMessage("102", 10));
        List<LogMessage> list = logList.search(8L, 9L);
        assertEquals(list.get(0).getLogger(), "8");
        assertEquals(list.get(1).getLogger(), "91");
        assertEquals(list.get(2).getLogger(), "92");
        assertEquals(list.get(3).getLogger(), "93");
    }
    
    @Test
    public void testLogListMatcher() {
        LogList logList = new LogList();
        logList.add(new LogMessage("8", 8));
        logList.add(new LogMessage("10", 10));
        logList.add(new LogMessage("91", 9));
        logList.add(new LogMessage("92", 9));
        logList.add(new LogMessage("93", 9));
        logList.add(new LogMessage("102", 10));
        List<LogMessage> list = logList.search(8L, 9L, "9");
        assertEquals(list.get(0).getLogger(), "91");
        assertEquals(list.get(1).getLogger(), "92");
        assertEquals(list.get(2).getLogger(), "93");
    }
    
}
