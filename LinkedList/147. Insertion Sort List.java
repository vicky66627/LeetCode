/*
Sort a linked list using insertion sort.
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
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);
        l1.next.next.next.next = new ListNode(6);
        l1.next.next.next.next.next = new ListNode(5);

        ListNode head = insertionSortList(l1);
        // output: 1->2->3->4->5->6

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curt = head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while (curt != null) {
            ListNode next = curt.next;
            while (prev.next != null && prev.next.val < curt.val) {
                prev = prev.next;
            }
            curt.next = prev.next;
            prev.next = curt;
            prev = dummy;
            curt = next;
        }

        return dummy.next;
    }
}