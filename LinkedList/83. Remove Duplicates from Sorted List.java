/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3. 
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
		ListNode l0 = new ListNode(1);
		ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(5);
        ListNode l7 = new ListNode(5);
        ListNode l8 = new ListNode(6);
        ListNode l9 = new ListNode(7);

        l0.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;

        ListNode head = deleteDuplicates(l0);
        // output: 1->2->4->5->6->7

        while (head != null) {
        	System.out.print(head.val + " ");
        	head = head.next;
        }
	}

	public static ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
            return head;
        }

        ListNode curt = head;

        while (curt != null) {
            int val = curt.val;
            while (curt.next != null && curt.next.val == val) {
                curt.next = curt.next.next;
            }
            curt = curt.next;
        }

        return head;
	}
}