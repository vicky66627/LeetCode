class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(2);
        l1.next.next.next.next.next = new ListNode(1);
        
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(3);
        l2.next.next.next.next = new ListNode(2);
        l2.next.next.next.next.next = new ListNode(1);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(2);
        l3.next.next = new ListNode(3);
        l3.next.next.next = new ListNode(4);
        l3.next.next.next.next = new ListNode(3);
        l3.next.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(l1));
        System.out.println(isPalindrome(l2));
        System.out.println(isPalindrome(l3));
        // output: false, true, true
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        ListNode prev = head, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        ListNode right = reverseList(slow);

        while (head != null) {
            if (head.val != right.val) {
                return false;
            }
            head = head.next;
            right = right.next;
        }

        return true;
    }

    private static ListNode reverseList(ListNode head) {
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