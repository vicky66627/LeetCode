/*
Given a list, rotate the list to the right by k places, where k is non-negative.

Example:

Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL. 
*/

class ListNode {
	int val;
	ListNode next;
	public ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode head = rotateRight(l1, 1);
        // output: 7->1->2->3->4->5->6
        // ListNode head = rotateRight(l1, 10);
        // output: 5->6->7->1->2->3->4
        // ListNode head = rotateRight(l1, 7);
        // output: 1->2->3->4->5->6->7

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
        	return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head, prev = dummy;
        
        int len = 0;
        while (tail != null) {
            tail = tail.next;
            len++;
        }

        k = k % len;
        if (k == 0) {
            return head;
        }

        tail = dummy;
        for (int i = 0; i < k; i++) {
            tail = tail.next;
        }

        while (tail.next != null) {
            tail = tail.next;
            prev = prev.next;
        }

        dummy.next = prev.next;
        prev.next = null;
        tail.next = head;

        return dummy.next;
    }
}