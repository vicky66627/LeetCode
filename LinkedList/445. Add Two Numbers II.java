/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

*/

class ListNode {
	int val;
	ListNode next;
	public ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(8);
		l1.next = new ListNode(5);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(5);
        l2.next.next.next = new ListNode(7);
        l2.next.next.next.next = new ListNode(9);

        ListNode head = addTwoNumbers(l1, l2);
        // output: 2->5->4->3->8

        while (head != null) {
        	System.out.print(head.val + " ");
        	head = head.next;
        }
	}

	// use stack
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = new ListNode(0);
        int carry = 0;
        while (!stack1.empty() || !stack2.empty()) {
            int sum = carry;
            if (!stack1.empty()) {
                sum += stack1.pop();
            }
            if (!stack2.empty()) {
                sum += stack2.pop();
            }

            ListNode curt = new ListNode(sum % 10);
            curt.next = head.next;
            head.next = curt;
            carry = sum / 10;
        }

        if (carry == 1) {
            ListNode curt = new ListNode(1);
            curt.next = head.next;
            head.next = curt;
        }

        return head.next;
	}
}
