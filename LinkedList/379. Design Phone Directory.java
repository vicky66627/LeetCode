class ListNode {
    int val;
    ListNode next;
    public ListNode(int x) { val = x; }
}

// Solution1: use LinkedList
class PhoneDirectory {
    ListNode head;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        head = new ListNode(0);
        ListNode curt = head;
        for (int i = 0; i < maxNumbers; i++) {
            curt.next = new ListNode(i);
            curt = curt.next;
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        ListNode curt = head;
        if (curt.next != null) {
            ListNode next = curt.next;
            curt.next = curt.next.next;
            return next.val;
        }

        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        ListNode curt = head;
        while (curt.next != null) {
            if (curt.next.val == number) {
                return true;
            }
            curt = curt.next;
        }

        return false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        ListNode curt = head;
        ListNode node = new ListNode(number);
        while (curt.next != null) {
            if (curt.next.val == number) {
                return;
            }
            curt = curt.next;
        }
        curt.next = node;
    }
}

// Solution2: use set and queue
class PhoneDirectory {
    Set<Integer> used = new HashSet<>();
    Queue<Integer> available = new LinkedList<>();
    int max;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.isEmpty()) {
            return -1;
        }

        Integer val = available.poll();
        used.add(val);
        return val;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (number >= max || number < 0) {
            return false;
        }

        return !used.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */