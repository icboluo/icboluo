package com.icboluo.leetcode.建模;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-05-22 21:12
 */
class N0146_LRU缓存 {
    /**
     * val node 映射
     */
    Map<Integer, Node> map;

    /**
     * 缓存列表，聚合头尾节点和size，尾部说明是刚访问过的节点
     */
    DoubleList cache;

    int cap;

    // Least Recently used（最近最少使用）其实就是刚访问过的放到链表尾部，内存满了从头部删
    public N0146_LRU缓存(int cap) {
        this.map = new HashMap<>();
        this.cache = new DoubleList();
        this.cap = cap;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将这个节点从缓存中移动到最后
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
        return node.val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // del key
            Node node = map.get(key);
            cache.remove(node);
            map.remove(key);
        } else {
            if (cap == cache.size) {
                // remove first
                Node delNode = cache.removeFirst();
                map.remove(delNode.key);
            }
        }
        // add
        Node node = new Node(key, val);
        cache.addLast(node);
        map.put(key, node);
    }

    static class DoubleList {
        /**
         * 头尾节点均为虚拟节点
         */
        Node head;

        Node tail;

        int size;

        public DoubleList() {
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        public void addLast(Node node) {
            node.pre = tail.pre;
            tail.pre.next = node;
            node.next = tail;
            tail.pre = node;
            size++;
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }
    }

    static class Node {
        int key;

        int val;

        Node next;

        Node pre;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
