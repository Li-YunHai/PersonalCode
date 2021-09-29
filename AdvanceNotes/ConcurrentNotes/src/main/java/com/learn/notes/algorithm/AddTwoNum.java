package com.learn.notes.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字0之外，这两个数都不会以0开头
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class AddTwoNum {

    public static void main(String[] args) {
        ListNode a1 = new ListNode(3);
        ListNode a2 = new ListNode(4,a1);
        ListNode a3 = new ListNode(8,a2);

        ListNode b1 = new ListNode(4);
        ListNode b2 = new ListNode(6,b1);
        ListNode b3 = new ListNode(5,b2);

        ListNode newNode = AddTwoNum.addTwoNumbers(a3, b3);
        System.out.println(newNode);

        ListNode reverseNode = AddTwoNum.reverseListNode(a3);
        System.out.println(reverseNode);
    }

    /**
     * 342 + 465 = 807
     * 348 + 465 = 813
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static ListNode reverseListNode(ListNode curNode){
        ListNode next = null;
        ListNode newList = new ListNode(0);
        while(curNode != null){
            next = curNode.next;
            curNode.next = newList.next;
            newList.next = curNode;
            curNode = next;
        }
        return newList.next;
    }
}
