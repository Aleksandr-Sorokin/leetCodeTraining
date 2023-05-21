package ru.sorokin.leetcodetraining.listnodes;

import ru.sorokin.leetcodetraining.listnodes.auxiliarys.ListNode;

public class SolutionListNode {

    /**
     * 2. Add Two Numbers
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * Example 1:
     * 2->4->3
     * 5->6->4
     * ---------
     * 7->0->8
     *
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     *
     * Example 2:
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     *
     * Example 3:
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode temp = null;
        int transfer = 0;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum = transfer;
            if (l1 != null) {
                sum += l1.getVal();
                l1 = l1.getNext();
            }
            if (l2 != null) {
                sum += l2.getVal();
                l2 = l2.getNext();
            }
            transfer = sum / 10;
            ListNode node = new ListNode(sum % 10);
            if (temp == null) {
                head = temp = node;
            } else {
                temp.setNext(node);
                temp = temp.getNext();
            }
            if (transfer > 0) {
                temp.setNext(new ListNode(transfer));
            }
        }
        return head;
    }

}
