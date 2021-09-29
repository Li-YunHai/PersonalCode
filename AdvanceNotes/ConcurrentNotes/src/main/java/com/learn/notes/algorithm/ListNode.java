package com.learn.notes.algorithm;

public class ListNode {
    /**
     * 定义val变量值，存储节点值
     */
    int val;
    /**
     * 定义next指针，指向下一个节点，维持节点连接
     */
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
