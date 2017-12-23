class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
        ListNode intersec = new ListNode(20);
        intersec.next = new ListNode(8);
        intersec.next.next = new ListNode(9);

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = intersec;

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(7);
        l2.next.next.next = intersec;

        ListNode head = getIntersectionNode(l1, l2);
        System.out.println(head == null ? "null" : head.val);
        // output: 20
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode hA = headA, hB = headB;
        while (hA != hB) {
            hA = hA == null ? headB : hA.next;
            hB = hB == null ? headA : hB.next;
            // System.out.println((hA == null ? "null" : hA.val) + " " + (hB == null ? "null" : hB.val));
        }

        return hA;
    }
}