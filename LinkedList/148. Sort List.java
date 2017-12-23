/*
Sort a linked list in O(n log n) time using constant space complexity.
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

        ListNode head = sortList(l1);
        // output: 1->2->3->4->5->6

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = head, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode firHalf = sortList(head);
        ListNode secHalf = sortList(slow);

        return merge(firHalf, secHalf);
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
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