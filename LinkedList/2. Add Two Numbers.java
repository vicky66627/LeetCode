/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(8);
//
//        ListNode l2 = new ListNode(8);
//        l2.next = new ListNode(7);
//        l2.next.next = new ListNode(3);
//        l2.next.next.next = new ListNode(5);

        // output: 248 + 8735 = 0226
        
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(8);
        l2.next = new ListNode(7);
        l2.next.next = new ListNode(3);

        ListNode head = addTwoNumbers(l1, l2);
        // output: 248 + 8735 = 0221
        
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
	}

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            head.next = new ListNode(sum % 10);
            head = head.next;
            carry = sum / 10;
        }

        if (carry == 1) {
        	head.next = new ListNode(1);
        }

        return dummy.next;
    }
}

