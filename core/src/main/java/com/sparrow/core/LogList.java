package com.sparrow.core;

import com.sparrow.common.entity.LogMessage;
import com.sparrow.core.utils.Arrays;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * @author 985492783@qq.com
 * @date 2023/10/22 23:24
 */
public class LogList {
    
    private int size;
    
    private Node first;
    
    private Node last;
    
    private final ReentrantReadWriteLock.ReadLock readLock;
    
    private final ReentrantReadWriteLock.WriteLock writeLock;
    
    private final Tree tree;
    
    
    public LogList() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
        tree = new Tree(this.readLock);
    }
    
    public boolean add(LogMessage logMessage) {
        try {
            writeLock.lock();
            Node node = new Node(null, logMessage, null);
            if (last == null) {
                first = node;
                last = first;
            } else {
                Node temp = last;
                while (temp != null && temp.item.getTimestamp() > logMessage.getTimestamp()) {
                    temp = temp.prev;
                }
                if (temp == null) {
                    node.next = first;
                    first.prev = node;
                    first = node;
                } else {
                    if (temp == last) {
                        last = node;
                    }
                    node.prev = temp;
                    node.next = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = node;
                    }
                    temp.next = node;
                }
            }
            size++;
            tree.add(node);
        } finally {
            writeLock.unlock();
        }
        return true;
    }
    
    public int getSize() {
        return size;
    }
    
    public List<LogMessage> search(Long startTime, Long endTime) {
        return tree.search(startTime, endTime);
    }
    
    public List<LogMessage> search(Long startTime, Long endTime, String patternStr) {
        try {
            Pattern pattern = Pattern.compile(patternStr);
            List<LogMessage> logMessageList = search(startTime, endTime);
            return logMessageList.stream().filter(log -> pattern.matcher(log.getLogger()).find())
                    .collect(Collectors.toList());
        } catch (PatternSyntaxException e) {
            return Arrays.newArrayList();
        }
    }
    
    private static class Tree {
        
        private final TreeMap<Long, Node> floorMap;
        
        /**
         * always put the fist.
         */
        private final TreeMap<Long, Node> higherMap;
        
        private final ReentrantReadWriteLock.ReadLock readLock;
        
        private Tree(ReentrantReadWriteLock.ReadLock readLock) {
            floorMap = new TreeMap<>();
            higherMap = new TreeMap<>();
            this.readLock = readLock;
        }
        
        private void add(Node node) {
            LogMessage logMessage = node.item;
            if (!higherMap.containsKey(logMessage.getTimestamp())) {
                higherMap.put(logMessage.getTimestamp(), node);
            }
            floorMap.put(logMessage.getTimestamp(), node);
        }
        
        public List<LogMessage> search(Long startTime, Long endTime) {
            try {
                readLock.lock();
                Long start = startTime;
                if (!higherMap.containsKey(start)) {
                    start = higherMap.higherKey(start);
                }
                Long end = floorMap.floorKey(endTime);
                List<LogMessage> list = Arrays.newArrayList();
                if (start == null || end == null) {
                    return list;
                }
                Node startNode = higherMap.get(start);
                Node endNode = floorMap.get(end);
                while (startNode != endNode) {
                    list.add(startNode.item);
                    startNode = startNode.next;
                }
                list.add(endNode.item);
                return list;
            } finally {
                readLock.unlock();
            }
        }
    }
    
    private static class Node {
        
        LogMessage item;
        
        LogList.Node next;
        
        LogList.Node prev;
        
        Node(LogList.Node prev, LogMessage element, LogList.Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
