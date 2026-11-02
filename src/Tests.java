/**
 * Plain-Java edge-case tests (no JUnit needed).
 * Run:  java -cp out Tests
 */
public class Tests {

    static int passed = 0, failed = 0;

    static void check(String name, boolean cond) {
        if (cond) { passed++; System.out.println("[PASS] " + name); }
        else      { failed++; System.out.println("[FAIL] " + name); }
    }

    static void eq(String name, String actual, String expected) {
        boolean ok = actual.equals(expected);
        check(name + (ok ? "" : "  (expected '" + expected + "' got '" + actual + "')"), ok);
    }

    public static void main(String[] args) {
        singly();
        doubly();
        System.out.println("\nResult: " + passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }

    // ------------------------------------------------------------------ singly
    static void singly() {
        System.out.println("=== Singly Linked List ===");
        SinglyLinkedList l = new SinglyLinkedList();

        // empty list
        eq("empty display", l.toString(), "NULL");
        check("empty delete begin -> false", !l.deleteFromBeginning());
        check("empty delete end -> false", !l.deleteFromEnd());
        check("empty delete value -> false", !l.deleteByValue(1));
        check("empty search -> -1", l.search(1) == -1);
        check("empty count = 0", l.count() == 0);
        l.sort(); l.reverse(); l.removeDuplicates();
        eq("empty sort/reverse/dedup safe", l.toString(), "NULL");

        // one node
        l.insertAtBeginning(10);
        l.sort(); l.reverse();
        eq("one-node sort/reverse", l.toString(), "10 -> NULL");
        check("one-node middle = 10", l.findMiddle() == 10);
        check("one-node delete end", l.deleteFromEnd() && l.isEmpty());

        // build 40,10,30,20,50
        for (int v : new int[]{40, 10, 30, 20, 50}) l.insertAtEnd(v);
        eq("insert at end", l.toString(), "40 -> 10 -> 30 -> 20 -> 50 -> NULL");
        l.insertAtBeginning(5);
        eq("insert at beginning", l.toString(), "5 -> 40 -> 10 -> 30 -> 20 -> 50 -> NULL");
        check("insert pos 1", l.insertAtPosition(1, 1));
        check("insert middle pos 4", l.insertAtPosition(4, 99));
        check("insert last valid pos (size+1)", l.insertAtPosition(l.count() + 1, 77));
        check("insert pos 0 invalid", !l.insertAtPosition(0, 1));
        check("insert pos size+2 invalid", !l.insertAtPosition(l.count() + 2, 1));
        check("insert pos negative invalid", !l.insertAtPosition(-3, 1));
        eq("after positional inserts", l.toString(), "1 -> 5 -> 40 -> 99 -> 10 -> 30 -> 20 -> 50 -> 77 -> NULL");

        check("insert after 99", l.insertAfter(99, 100));
        check("insert before 1 (head)", l.insertBefore(1, 0));
        check("insert before 77 (tail)", l.insertBefore(77, 76));
        check("insert after 77 (tail)", l.insertAfter(77, 78));
        check("insert after missing -> false", !l.insertAfter(12345, 1));
        check("insert before missing -> false", !l.insertBefore(12345, 1));
        eq("after before/after", l.toString(), "0 -> 1 -> 5 -> 40 -> 99 -> 100 -> 10 -> 30 -> 20 -> 50 -> 76 -> 77 -> 78 -> NULL");

        // deletes
        check("delete begin", l.deleteFromBeginning());
        check("delete end", l.deleteFromEnd());
        check("delete pos 1", l.deleteFromPosition(1));
        check("delete pos 3", l.deleteFromPosition(3));
        check("delete pos invalid 0", !l.deleteFromPosition(0));
        check("delete pos invalid size+1", !l.deleteFromPosition(l.count() + 1));
        check("delete last valid pos", l.deleteFromPosition(l.count()));
        check("delete by value", l.deleteByValue(100));
        check("delete missing value", !l.deleteByValue(424242));
        check("delete leftover tail", l.deleteFromEnd());
        eq("after deletes", l.toString(), "5 -> 40 -> 10 -> 30 -> 20 -> 50 -> NULL");

        // search/update/stats
        check("search 30 -> pos 4", l.search(30) == 4);
        check("update 30 -> 35", l.update(30, 35) && l.search(35) == 4);
        check("update missing -> false", !l.update(999, 1));
        check("count = 6", l.count() == 6);
        check("max = 50", l.findMax() == 50);
        check("min = 5", l.findMin() == 5);
        check("middle (even len 6) = 4th node = 35", l.findMiddle() == 35);

        // reverse / sort / dedup
        l.reverse();
        eq("reverse", l.toString(), "50 -> 20 -> 35 -> 10 -> 40 -> 5 -> NULL");
        l.sort();
        eq("sort", l.toString(), "5 -> 10 -> 20 -> 35 -> 40 -> 50 -> NULL");

        SinglyLinkedList d = new SinglyLinkedList();
        for (int v : new int[]{3, 1, 3, 3, -2, 0, 1, -2, 0, 0}) d.insertAtEnd(v);
        int removed = d.removeDuplicates();
        eq("dedup (zero & negatives)", d.toString(), "3 -> 1 -> -2 -> 0 -> NULL");
        check("dedup removed 6", removed == 6 && d.count() == 4);
        d.insertAtEnd(8);
        check("insert after dedup works (size ok)", d.insertAtPosition(6, 9) && d.count() == 6);
        d.sort();
        eq("sort negatives", d.toString(), "-2 -> 0 -> 1 -> 3 -> 8 -> 9 -> NULL");

        // example from the PDF
        SinglyLinkedList e = new SinglyLinkedList();
        for (int v : new int[]{40, 10, 30, 20, 50}) e.insertAtEnd(v);
        e.sort();
        eq("PDF example sort", e.toString(), "10 -> 20 -> 30 -> 40 -> 50 -> NULL");
        e.reverse();
        eq("PDF example reverse", e.toString(), "50 -> 40 -> 30 -> 20 -> 10 -> NULL");
        check("odd length middle = 30", e.findMiddle() == 30);
    }

    // ------------------------------------------------------------------ doubly
    static void doubly() {
        System.out.println("\n=== Doubly Linked List ===");
        DoublyLinkedList l = new DoublyLinkedList();

        eq("empty forward", l.forwardString(), "NULL -> NULL");
        check("empty consistent", l.isConsistent());
        check("empty delete begin -> false", !l.deleteFromBeginning());
        check("empty delete end -> false", !l.deleteFromEnd());
        check("empty delete value -> false", !l.deleteByValue(1));
        l.sort(); l.reverse(); l.removeDuplicates();
        check("empty sort/reverse/dedup safe", l.isEmpty() && l.isConsistent());

        l.insertAtEnd(10);
        l.sort(); l.reverse();
        eq("one-node forward", l.forwardString(), "NULL <- 10 -> NULL");
        eq("one-node backward", l.backwardString(), "NULL <- 10 -> NULL");
        check("one-node delete begin", l.deleteFromBeginning() && l.isEmpty() && l.isConsistent());

        for (int v : new int[]{40, 10, 30, 20, 50}) l.insertAtEnd(v);
        l.insertAtBeginning(5);
        eq("forward", l.forwardString(), "NULL <- 5 <-> 40 <-> 10 <-> 30 <-> 20 <-> 50 -> NULL");
        eq("backward", l.backwardString(), "NULL <- 50 <-> 20 <-> 30 <-> 10 <-> 40 <-> 5 -> NULL");

        check("insert pos 1", l.insertAtPosition(1, 1));
        check("insert middle pos 4", l.insertAtPosition(4, 99));
        check("insert last valid pos", l.insertAtPosition(l.count() + 1, 77));
        check("insert pos 0 invalid", !l.insertAtPosition(0, 1));
        check("insert pos size+2 invalid", !l.insertAtPosition(l.count() + 2, 1));
        check("insert after 99", l.insertAfter(99, 100));
        check("insert after tail", l.insertAfter(77, 78));
        check("insert before head", l.insertBefore(1, 0));
        check("insert before tail", l.insertBefore(78, 777));
        check("insert after/before missing", !l.insertAfter(-5, 1) && !l.insertBefore(-5, 1));
        check("consistent after inserts", l.isConsistent());
        eq("after inserts", l.forwardString(),
           "NULL <- 0 <-> 1 <-> 5 <-> 40 <-> 99 <-> 100 <-> 10 <-> 30 <-> 20 <-> 50 <-> 77 <-> 777 <-> 78 -> NULL");

        check("delete begin", l.deleteFromBeginning());
        check("delete end", l.deleteFromEnd());
        check("delete pos 1", l.deleteFromPosition(1));
        check("delete middle pos 3", l.deleteFromPosition(3));
        check("delete last valid pos", l.deleteFromPosition(l.count()));
        check("delete invalid pos", !l.deleteFromPosition(0) && !l.deleteFromPosition(l.count() + 1));
        check("delete by value", l.deleteByValue(100));
        check("delete missing value", !l.deleteByValue(424242));
        check("delete leftover tail", l.deleteFromEnd());
        check("consistent after deletes", l.isConsistent());
        eq("after deletes", l.forwardString(), "NULL <- 5 <-> 40 <-> 10 <-> 30 <-> 20 <-> 50 -> NULL");

        check("search 30 -> pos 4", l.search(30) == 4);
        check("update", l.update(30, 35) && l.search(35) == 4);
        check("update missing", !l.update(999, 1));
        check("count 6 / max 50 / min 5", l.count() == 6 && l.findMax() == 50 && l.findMin() == 5);
        check("middle even len 6 -> 35", l.findMiddle() == 35);

        l.reverse();
        check("reverse consistent", l.isConsistent());
        eq("reverse fwd", l.forwardString(), "NULL <- 50 <-> 20 <-> 35 <-> 10 <-> 40 <-> 5 -> NULL");
        eq("reverse bwd", l.backwardString(), "NULL <- 5 <-> 40 <-> 10 <-> 35 <-> 20 <-> 50 -> NULL");
        l.sort();
        check("sort consistent", l.isConsistent());
        eq("sort", l.forwardString(), "NULL <- 5 <-> 10 <-> 20 <-> 35 <-> 40 <-> 50 -> NULL");

        DoublyLinkedList d = new DoublyLinkedList();
        for (int v : new int[]{3, 1, 3, 3, -2, 0, 1, -2, 0, 0}) d.insertAtEnd(v);
        int removed = d.removeDuplicates();
        eq("dedup", d.forwardString(), "NULL <- 3 <-> 1 <-> -2 <-> 0 -> NULL");
        check("dedup removed 6 & consistent", removed == 6 && d.count() == 4 && d.isConsistent());
        eq("dedup backward", d.backwardString(), "NULL <- 0 <-> -2 <-> 1 <-> 3 -> NULL");

        DoublyLinkedList e = new DoublyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) e.insertAtEnd(v);
        eq("PDF example forward", e.forwardString(), "NULL <- 10 <-> 20 <-> 30 <-> 40 -> NULL");
        eq("PDF example backward", e.backwardString(), "NULL <- 40 <-> 30 <-> 20 <-> 10 -> NULL");
    }
}
