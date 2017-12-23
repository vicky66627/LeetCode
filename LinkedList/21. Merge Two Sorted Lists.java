/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(8);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(10);

        ListNode head = mergeTwoLists(l1, l2);
        // output: 1->2->4->5->8->8->9->10

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        while (l1 != null && l2 != null) {
        	if (l1.val < l2.val) {
        		head.next = l1;
        		l1 = l1.next;
        	} else {
        		head.next = l2;
        		l2 = l2.next;
        	}
        	head = head.next;
        }

        if (l1 != null) {
        	head.next = l1;
        } else if (l2 != null) {
        	head.next = l2;
        }

        return dummy.next;
    }
}