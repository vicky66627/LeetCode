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
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(5);
        ListNode l7 = new ListNode(5);
        ListNode l8 = new ListNode(6);
        ListNode l9 = new ListNode(7);
        ListNode l10 = new ListNode(5);

        l0.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;

        ListNode head = removeElements(l0, 5);
        // output: 1->1->2->3->4->6->7
        // ListNode head = removeElements(l0, 1);
        // output: 2->3->4->5->5->5->6->7->5

        while (head != null) {
        	System.out.print(head.val + " ");
        	head = head.next;
        }
	}

	public static ListNode removeElements(ListNode head, int val) {
		if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head != null && head.next != null) {
            while (head.next != null && head.next.val == val) {
                head.next = head.next.next;
            }
            head = head.next;
        }

        return dummy.next;
	}
}