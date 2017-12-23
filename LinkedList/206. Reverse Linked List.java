class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;

        ListNode res = reverseList(head);
        // output: 4->3->2->1

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }

        ListNode prev = null;

        while (head != null) {
        	ListNode next = head.next;
        	head.next = prev;
        	prev = head;
        	head = next;
        }

        return prev;
    }
}