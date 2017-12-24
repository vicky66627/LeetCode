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

        ListNode head = removeNthFromEnd(l1, 3);
        // output: 1->2->3->4->6->7

        while (head != null) {
        	System.out.print(head.val + " ");
        	head = head.next;
        }
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return null;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;

		for (int i = 0; i < n && head != null; i++) {
			head = head.next;
		}

		while (head != null) {
			head = head.next;
			prev = prev.next;
		}

		prev.next = prev.next.next;

		return dummy.next;
	}
}