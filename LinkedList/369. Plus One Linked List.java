/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5. 
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
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);

        ListNode l3 = new ListNode(9);

        ListNode l4 = new ListNode(7);
        l4.next = new ListNode(9);


        ListNode head = plusOne(l1);
        // output: 1->4->4
        // ListNode head = plusOne(l2);
        // output: 1->0->0->0
        // ListNode head = plusOne(l3);
        // output: 1->0
        // ListNode head = plusOne(l4);
        // output: 8->0

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy, prev = dummy;

        while (tail.next != null) {
            tail = tail.next;
            if (tail.val != 9) {
                prev = tail;
            }
        }

        if (tail.val != 9) {
            tail.val++;
            return dummy.next;
        }

        prev.val++;
        prev = prev.next;
        while (prev != null) {
            prev.val = 0;
            prev = prev.next;
        }

        return dummy.val == 0 ? dummy.next : dummy;
    }
}