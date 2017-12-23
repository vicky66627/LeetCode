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
        ListNode head4 = new ListNode(5);
        ListNode head5 = new ListNode(6);
        ListNode head6 = new ListNode(3);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = head;

        System.out.println(hasCycle(head));
    }

	// O(1) space
    public static boolean hasCycle(ListNode head) {
    	if (head == null) {
    		return false;
    	}

    	ListNode slow = head;
    	ListNode fast = head;

    	while (fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    		if (slow == fast) {
    			return true;
    		}
    	}

    	return false;
    }

    // O(n) space
    /*
    public static boolean hasCycle(ListNode head) {
    	if (head == null) {
    		return false;
    	}

        Set<ListNode> set = new HashSet<>();
        while (head != null) {
        	if (set.add(head)) {
        		head = head.next;
        	} else {
        		return true;
        	}
        }

        return false;
    }
    */
}