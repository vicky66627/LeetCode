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

        ListNode head = swapPairs(l1);
        // output: 2->1->4->3->6->5->7
        
        while (head != null) {
        	System.out.print(head.val + " ");
        	head = head.next;
        }
	}

	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;

		while (head != null && head.next != null) {
			ListNode next = head.next;
			head.next = next.next;
			next.next = head;
			prev.next = next;

			prev = head;
			head = head.next;
		}

		return dummy.next;
	}
}