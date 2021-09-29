package com.learn.notes.algorithm;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;

public class ReverseKGroup {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ReverseKGroup reverseKGroup = new ReverseKGroup();
        ListNode newNode = reverseKGroup.reverseKGroup(node1, 3);
        System.out.println(JSON.toJSONString(newNode));
    }

    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        return handler(head, k);
    }

    public ListNode handler(ListNode head, int k){
        if(isUseful(head, k)){
            ListNode currNode = head;
            ListNode nextNode = head;
            ListNode newNode = new ListNode(0);
            for(int i=0; i<k; i++){
                nextNode = currNode.next;
                currNode.next = newNode.next;
                newNode.next = currNode;
                currNode = nextNode;
            }
            newNode = newNode.next;
            head.next = handler(currNode, k);
            return newNode;
        } else {
            return head;
        }
    }

    public boolean isUseful(ListNode head, int k){
        ListNode nextNode = head;
        for(int i=0; i<k; i++){
            if(nextNode == null){
                return false;
            }
            nextNode =  nextNode.next;
        }
        return true;
    }
}
