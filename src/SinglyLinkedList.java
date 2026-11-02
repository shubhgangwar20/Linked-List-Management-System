/**
 * Singly Linked List implemented from scratch (no java.util collections).
 * Positions are 1-based. Middle rule: for even length, the SECOND middle node is returned.
 */
public class SinglyLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    // ---------------------------------------------------------- INSERT

    /** O(1) */
    public void insertAtBeginning(int value) {
        Node n = new Node(value);
        n.next = head;
        head = n;
        size++;
    }

    /** O(n) - no tail reference */
    public void insertAtEnd(int value) {
        Node n = new Node(value);
        if (head == null) {
            head = n;
        } else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    /** Valid positions: 1 .. size+1. Returns false if invalid. */
    public boolean insertAtPosition(int pos, int value) {
        if (pos < 1 || pos > size + 1) return false;
        if (pos == 1) {
            insertAtBeginning(value);
            return true;
        }
        Node prev = head;
        for (int i = 1; i < pos - 1; i++) prev = prev.next;
        Node n = new Node(value);
        n.next = prev.next;
        prev.next = n;
        size++;
        return true;
    }

    /** Inserts newValue after the first node holding target. */
    public boolean insertAfter(int target, int newValue) {
        Node cur = head;
        while (cur != null && cur.data != target) cur = cur.next;
        if (cur == null) return false;
        Node n = new Node(newValue);
        n.next = cur.next;
        cur.next = n;
        size++;
        return true;
    }

    /** Inserts newValue before the first node holding target. */
    public boolean insertBefore(int target, int newValue) {
        if (head == null) return false;
        if (head.data == target) {
            insertAtBeginning(newValue);
            return true;
        }
        Node prev = head;
        while (prev.next != null && prev.next.data != target) prev = prev.next;
        if (prev.next == null) return false;
        Node n = new Node(newValue);
        n.next = prev.next;
        prev.next = n;
        size++;
        return true;
    }

    // ---------------------------------------------------------- DELETE

    /** O(1). Returns false if list is empty. */
    public boolean deleteFromBeginning() {
        if (head == null) return false;
        head = head.next;
        size--;
        return true;
    }

    /** O(n). Returns false if list is empty. */
    public boolean deleteFromEnd() {
        if (head == null) return false;
        if (head.next == null) {
            head = null;
        } else {
            Node prev = head;
            while (prev.next.next != null) prev = prev.next;
            prev.next = null;
        }
        size--;
        return true;
    }

    /** Valid positions: 1 .. size. */
    public boolean deleteFromPosition(int pos) {
        if (pos < 1 || pos > size) return false;
        if (pos == 1) return deleteFromBeginning();
        Node prev = head;
        for (int i = 1; i < pos - 1; i++) prev = prev.next;
        prev.next = prev.next.next;
        size--;
        return true;
    }

    /** Deletes first node holding value. */
    public boolean deleteByValue(int value) {
        if (head == null) return false;
        if (head.data == value) return deleteFromBeginning();
        Node prev = head;
        while (prev.next != null && prev.next.data != value) prev = prev.next;
        if (prev.next == null) return false;
        prev.next = prev.next.next;
        size--;
        return true;
    }

    // ---------------------------------------------------------- QUERY

    /** Returns 1-based position of first match, or -1. */
    public int search(int value) {
        Node cur = head;
        int pos = 1;
        while (cur != null) {
            if (cur.data == value) return pos;
            cur = cur.next;
            pos++;
        }
        return -1;
    }

    /** Replaces first occurrence of oldValue with newValue. */
    public boolean update(int oldValue, int newValue) {
        Node cur = head;
        while (cur != null && cur.data != oldValue) cur = cur.next;
        if (cur == null) return false;
        cur.data = newValue;
        return true;
    }

    public int count() {
        int c = 0;
        for (Node cur = head; cur != null; cur = cur.next) c++;
        return c;
    }

    /** Caller must check isEmpty() first. */
    public int findMax() {
        if (head == null) throw new IllegalStateException("List is empty");
        int max = head.data;
        for (Node cur = head.next; cur != null; cur = cur.next) if (cur.data > max) max = cur.data;
        return max;
    }

    public int findMin() {
        if (head == null) throw new IllegalStateException("List is empty");
        int min = head.data;
        for (Node cur = head.next; cur != null; cur = cur.next) if (cur.data < min) min = cur.data;
        return min;
    }

    /** Slow/fast pointers. Even length -> second middle. */
    public int findMiddle() {
        if (head == null) throw new IllegalStateException("List is empty");
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    // ---------------------------------------------------------- TRANSFORM

    /** O(n) time, O(1) space: prev / current / next. */
    public void reverse() {
        Node prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    /** Bubble sort (ascending) by swapping data. O(n^2). */
    public void sort() {
        if (head == null || head.next == null) return;
        boolean swapped;
        Node end = null;
        do {
            swapped = false;
            Node cur = head;
            while (cur.next != end) {
                if (cur.data > cur.next.data) {
                    int t = cur.data;
                    cur.data = cur.next.data;
                    cur.next.data = t;
                    swapped = true;
                }
                cur = cur.next;
            }
            end = cur;
        } while (swapped);
    }

    /** Removes repeated values, keeping the first occurrence. O(n^2). Returns number removed. */
    public int removeDuplicates() {
        int removed = 0;
        for (Node cur = head; cur != null; cur = cur.next) {
            Node runner = cur;
            while (runner.next != null) {
                if (runner.next.data == cur.data) {
                    runner.next = runner.next.next;
                    size--;
                    removed++;
                } else {
                    runner = runner.next;
                }
            }
        }
        return removed;
    }

    // ---------------------------------------------------------- DISPLAY

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) sb.append(cur.data).append(" -> ");
        return sb.append("NULL").toString();
    }

    public void display() {
        System.out.println(isEmpty() ? "List is empty. (NULL)" : toString());
    }
}
