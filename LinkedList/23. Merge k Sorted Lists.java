class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(7);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(10);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(12);
        l3.next.next = new ListNode(15);

        ListNode l4 = new ListNode(3);
        l4.next = new ListNode(6);
        l4.next.next = new ListNode(14);

        ListNode[] lists = new ListNode[4];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        lists[3] = l4;
        ListNode head = mergeKLists(lists);
        // output: 1->2->2->3->4->5->6->7->8->9->10->12->14->15

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    // Solution1: heap: time O(nklogk), space O(k)  n: the max number of nodes in each list  k: the size of the list
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }

        while (!pq.isEmpty()) {
            ListNode curt = pq.poll();
            head.next = curt;
            head = head.next;
            if (curt.next != null) {
                pq.offer(curt.next);
            }
        }

        return dummy.next;
    }

    // Solution2: divide and conquer
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int end = lists.length - 1;
        while (end > 0) {
            int start = 0;
            while (start < end) {
                lists[start] = mergeTwoLists(lists[start], lists[end]);
                start++;
                end--;
            }
        }

        return lists[0];
        // return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);

        return mergeTwoLists(left, right);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

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