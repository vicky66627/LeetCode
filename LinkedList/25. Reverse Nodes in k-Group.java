/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5  
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

        ListNode head = reverseKGroup(l1, 2);
        // output: 2->1->4->3->6->5->7
        // ListNode head = reverseKGroup(l1, 3);
        // output: 3->2->1->6->5->4->7
        // ListNode head = reverseKGroup(l1, 1);
        // output: 1->2->3->4->5->6->7
        // ListNode head = reverseKGroup(l1, 10);
        // output: 1->2->3->4->5->6->7

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode begin = dummy;
        int idx = 1;
        while (head != null) {
            if (idx % k != 0) {
                head = head.next;
            } else {
                begin = reverseList(begin, head.next);
                head = begin.next;
            }
            idx++;
        }

        return dummy.next;
    }

    private static ListNode reverseList(ListNode begin, ListNode tail) {
        ListNode curt = begin.next;
        ListNode first = curt;
        ListNode prev = null;

        while (curt != tail) {
            ListNode next = curt.next;
            curt.next = prev;
            prev = curt;
            curt = next;
        }
        begin.next = prev;
        first.next = tail;

        return first;
    }
}
