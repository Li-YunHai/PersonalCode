package com.learn.notes.redis.lru;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

class LRUCache2{

    private int cacheSize;
    private Map<String, Node<String, Integer>> map;
    private DoublyLinkedList<String, Integer> doublyLinkedList;

    public LRUCache2(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        doublyLinkedList = new DoublyLinkedList<>();
    }

    public int get(String key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node<String, Integer> node = map.get(key);
        //更新节点位置，将节点移置链表头
        doublyLinkedList.removeNode(node);
        doublyLinkedList.addHead(node);

        return node.value;
    }

    public void put(String key, int value) {
        if(map.containsKey(key)) {
            Node<String, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);
            doublyLinkedList.removeNode(node);
            doublyLinkedList.addHead(node);
        }else {
            if(map.size() == cacheSize) {//已达到最大容量了，把旧的移除，让新的进来
                Node<String, Integer> lastNode = doublyLinkedList.getLast();
                map.remove(lastNode.key);//node.key主要用处，反向连接map
                doublyLinkedList.removeNode(lastNode);
            }
            Node<String, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doublyLinkedList.addHead(newNode);
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(map.keySet());
    }

    public static void main(String[] args) {
        LRUCache2 lruCache = new LRUCache2(3);
        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        System.out.println(lruCache.toString());

        lruCache.put("d", 4);
        System.out.println(lruCache.toString());
        lruCache.put("c", 3);
        System.out.println(lruCache.toString());
        lruCache.put("e", 5);
        System.out.println(lruCache.toString());
    }

    class Node<K, V>{//双向链表节点
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }
        public Node(K key, V value) {
            super();
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return JSON.toJSONString(key + "--" + value);
        }
    }

    //新的插入头部，旧的从尾部移除
    class DoublyLinkedList<K, V>{
        Node<K, V> head;
        Node<K, V> tail;

        public DoublyLinkedList() {
            //头尾哨兵节点
            this.head = new Node<K, V>();
            this.tail = new Node<K, V>();
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public void addHead(Node<K, V> node) {
            node.next = this.head.next;
            node.prev = this.head;
            this.head.next.prev = node;
            this.head.next = node;
        }

        public void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        public Node<K, V> getLast() {
            if(this.tail.prev == this.head)
                return null;
            return this.tail.prev;
        }
    }
}

