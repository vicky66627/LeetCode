/* 
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list. 
*/

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}

public class Solution {
	public static void main(String[] args) {
        RandomListNode l1 = new RandomListNode(1);
        RandomListNode l2 = new RandomListNode(2);
        RandomListNode l3 = new RandomListNode(3);
        RandomListNode l4 = new RandomListNode(4);
        RandomListNode l5 = new RandomListNode(5);
        RandomListNode l6 = new RandomListNode(6);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        l1.random = l3;
        l4.random = l6;
        
        RandomListNode head = copyRandomList(l1);
        // output: 1->label 3->2->3->4->label 6->5->6

        while (head != null) {
            System.out.println(head.label);
            if (head.random != null) {
                System.out.println("label " + head.random.label);
            }
            head = head.next;
        }
    }

    // use map
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
        	return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode curt = head;
        while (curt != null) {
        	map.put(curt, new RandomListNode(curt.label));
        	curt = curt.next;
        }

        curt = head;
        while (curt != null) {
        	map.get(curt).next = map.get(curt.next);
        	map.get(curt).random = map.get(curt.random);
        	curt = curt.next;
        }

        return map.get(head);
    }
}