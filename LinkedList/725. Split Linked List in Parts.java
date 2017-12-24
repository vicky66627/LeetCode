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

        ListNode[] res = splitListToParts(l1, 2);
        // output: [[1,2,3,4],[5,6,7]]
        // ListNode[] res = splitListToParts(l1, 3);
        // output: [[1,2,3],[4,5],[6,7]]
        // ListNode[] res = splitListToParts(l1, 4);
        // output: [[1,2],[3,4],[5,6],[7]]
        // ListNode[] res = splitListToParts(l1, 5);
        // output: [[1,2],[3,4],[5],[6],[7]]
        // ListNode[] res = splitListToParts(l1, 10);
        // output: [[1],[2],[3],[4],[5],[6],[7],[],[],[]]

        for (int i = 0; i < res.length; i++) {
            ListNode head = res[i];
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next;
            }
            System.out.println();
        }
    }

    public static ListNode[] splitListToParts(ListNode root, int k) {
        if (root == null) {
            return new ListNode[k];
        }

        ListNode curt = root;
        int len = 0;
        while (curt != null) {
            curt = curt.next;
            len++;
        }

        int remain = len % k;
        len /= k;

        ListNode[] res = new ListNode[k];

        curt = root;
        for (int i = 0; i < k; i++) {
            res[i] = curt;
            ListNode prev = curt;
            for (int j = 0; j < (len + (remain == 0 ? 0 : 1)) && curt != null; j++) {
                prev = curt;
                curt = curt.next;
            }
            if (remain > 0) {
                remain--;
            }
            if (prev != null) {
                prev.next = null;
            }
        }

        return res;
    }
}
