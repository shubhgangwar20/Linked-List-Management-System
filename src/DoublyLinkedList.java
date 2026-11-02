/**
 * Doubly Linked List implemented from scratch (head + tail, prev + next).
 * Positions are 1-based. Middle rule: for even length, the SECOND middle node is returned.
 */
public class DoublyLinkedList {

    private static class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head, tail;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    // ---------------------------------------------------------- INSERT

    /** O(1) */
    public void insertAtBeginning(int value) {
        Node n = new Node(value);
        if (head == null) {
            head = tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
        size++;
    }

    /** O(1) thanks to tail. */
    public void insertAtEnd(int value) {
        Node n = new Node(value);
        if (tail == null) {
            head = tail = n;
        } else {
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
        size++;
    }

    /** Valid positions: 1 .. size+1. */
    public boolean insertAtPosition(int pos, int value) {
        if (pos < 1 || pos > size + 1) return false;
        if (pos == 1) {
            insertAtBeginning(value);
            return true;
        }
        if (pos == size + 1) {
            insertAtEnd(value);
            return true;
        }
        Node cur = head;
        for (int i = 1; i < pos; i++) cur = cur.next; // cur = node currently at pos
        linkBefore(cur, value);
        return true;
    }

    public boolean insertAfter(int target, int newValue) {
        Node cur = find(target);
        if (cur == null) return false;
        if (cur == tail) {
            insertAtEnd(newValue);
        } else {
            linkBefore(cur.next, newValue);
        }
        return true;
    }

    public boolean insertBefore(int target, int newValue) {
        Node cur = find(target);
        if (cur == null) return false;
        if (cur == head) {
            insertAtBeginning(newValue);
        } else {
            linkBefore(cur, newValue);
        }
        return true;
    }

    /** Inserts a new node immediately before 'succ' (succ must not be head). */
    private void linkBefore(Node succ, int value) {
        Node n = new Node(value);
        Node pred = succ.prev;
        n.prev = pred;
        n.next = succ;
        pred.next = n;
        succ.prev = n;
        size++;
    }

    // ---------------------------------------------------------- DELETE

    public boolean deleteFromBeginning() {
        if (head == null) return false;
        unlink(head);
        return true;
    }

    public boolean deleteFromEnd() {
        if (tail == null) return false;
        unlink(tail);
        return true;
    }

    public boolean deleteFromPosition(int pos) {
        if (pos < 1 || pos > size) return false;
        Node cur = head;
        for (int i = 1; i < pos; i++) cur = cur.next;
        unlink(cur);
        return true;
    }

    public boolean deleteByValue(int value) {
        Node cur = find(value);
        if (cur == null) return false;
        unlink(cur);
        return true;
    }

    /** Safely removes any node, fixing head/tail and both neighbours. */
    private void unlink(Node n) {
        if (n.prev == null) head = n.next; else n.prev.next = n.next;
        if (n.next == null) tail = n.prev; else n.next.prev = n.prev;
        n.prev = n.next = null;
        size--;
    }

    // ---------------------------------------------------------- QUERY

    private Node find(int value) {
        for (Node cur = head; cur != null; cur = cur.next) if (cur.data == value) return cur;
        return null;
    }

    public int search(int value) {
        int pos = 1;
        for (Node cur = head; cur != null; cur = cur.next, pos++) if (cur.data == value) return pos;
        return -1;
    }

    public boolean update(int oldValue, int newValue) {
        Node cur = find(oldValue);
        if (cur == null) return false;
        cur.data = newValue;
        return true;
    }

    public int count() {
        int c = 0;
        for (Node cur = head; cur != null; cur = cur.next) c++;
        return c;
    }

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

    /** Slow/fast references. Even length -> second middle. */
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

    /** Swap prev/next in every node, then swap head/tail. O(n). */
    public void reverse() {
        Node cur = head;
        while (cur != null) {
            Node tmp = cur.next;
            cur.next = cur.prev;
            cur.prev = tmp;
            cur = tmp;
        }
        Node t = head;
        head = tail;
        tail = t;
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

    /** Keeps first occurrence of each value. O(n^2). Returns number removed. */
    public int removeDuplicates() {
        int removed = 0;
        for (Node cur = head; cur != null; cur = cur.next) {
            Node runner = cur.next;
            while (runner != null) {
                Node nxt = runner.next;
                if (runner.data == cur.data) {
                    unlink(runner);
                    removed++;
                }
                runner = nxt;
            }
        }
        return removed;
    }

    // ---------------------------------------------------------- DISPLAY

    public String forwardString() {
        StringBuilder sb = new StringBuilder("NULL");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur == head ? " <- " : " <-> ").append(cur.data);
        }
        return sb.append(" -> NULL").toString();
    }

    public String backwardString() {
        StringBuilder sb = new StringBuilder("NULL");
        for (Node cur = tail; cur != null; cur = cur.prev) {
            sb.append(cur == tail ? " <- " : " <-> ").append(cur.data);
        }
        return sb.append(" -> NULL").toString();
    }

    public void displayForward() {
        System.out.println(isEmpty() ? "List is empty. (NULL)" : forwardString());
    }

    public void displayBackward() {
        System.out.println(isEmpty() ? "List is empty. (NULL)" : backwardString());
    }

    /** Verifies prev/next/head/tail consistency (used by tests). */
    public boolean isConsistent() {
        if (head == null) return tail == null && size == 0;
        if (head.prev != null || tail.next != null) return false;
        int n = 0;
        Node last = null;
        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.prev != last) return false;
            last = cur;
            n++;
        }
        return last == tail && n == size;
    }
}
