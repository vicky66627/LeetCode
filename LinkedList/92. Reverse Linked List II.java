/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list. 
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

        ListNode head = reverseBetween(l1, 1, 5);
        // output: 5->4->3->2->1->6->7
        // ListNode head = reverseBetween(l1, 5, 7);
        // output: 1->2->3->4->7->6->5
        // ListNode head = reverseBetween(l1, 5, 5);
        // output: 1->2->3->4->5->6->7

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
        	return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 1; i < m; i++) {
        	prev = prev.next;
        }
        ListNode tail = head;
        for (int i = 0; i < n; i++) {
        	tail = tail.next;
        }

        ListNode reversedHead = reverseList(prev.next, tail);
        prev.next = reversedHead;

        return dummy.next;
    }

    private static ListNode reverseList(ListNode head, ListNode tail) {
    	ListNode first = head;
    	ListNode prev = null;

    	while (head != tail) {
    		ListNode next = head.next;
    		head.next = prev;
    		prev = head;
    		head = next;
    	}
    	first.next = tail;

    	return prev;
    }
}