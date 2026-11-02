import java.util.Scanner;

/** Menu-driven console application: Linked List Management System. */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    // ------------------------------------------------------------ input helpers

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.hasNextLine() ? sc.nextLine().trim() : null;
            if (line == null) { // EOF -> exit cleanly
                System.out.println("\nInput ended. Exiting.");
                System.exit(0);
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    private static void line(char c, int n) {
        System.out.println(String.valueOf(c).repeat(n));
    }

    // ------------------------------------------------------------ main menu

    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();
        DoublyLinkedList dll = new DoublyLinkedList();

        while (true) {
            System.out.println();
            line('=', 50);
            System.out.println("        LINKED LIST MANAGEMENT SYSTEM");
            line('=', 50);
            System.out.println();
            System.out.println(" 1. Singly Linked List");
            System.out.println(" 2. Doubly Linked List");
            System.out.println(" 3. Compare Singly vs Doubly Linked List");
            System.out.println(" 4. Exit");
            System.out.println();
            int choice = readInt(" Enter your choice: ");
            switch (choice) {
                case 1: singlyMenu(sll); break;
                case 2: doublyMenu(dll); break;
                case 3: compare(); break;
                case 4: System.out.println("Thank you! Goodbye."); return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ------------------------------------------------------------ singly menu

    private static void singlyMenu(SinglyLinkedList list) {
        while (true) {
            System.out.println();
            System.out.println("********** Operation on Singly Linked List **********");
            System.out.println();
            System.out.println(" 1. Insert at Beginning");
            System.out.println(" 2. Insert at End");
            System.out.println(" 3. Insert at Position");
            System.out.println(" 4. Insert After a Node");
            System.out.println(" 5. Insert Before a Node");
            System.out.println();
            System.out.println(" 6. Delete from Beginning");
            System.out.println(" 7. Delete from End");
            System.out.println(" 8. Delete from Position");
            System.out.println(" 9. Delete by Value");
            System.out.println();
            System.out.println(" 10. Search");
            System.out.println(" 11. Update");
            System.out.println(" 12. Display");
            System.out.println(" 13. Count Nodes");
            System.out.println(" 14. Find Maximum");
            System.out.println(" 15. Find Minimum");
            System.out.println(" 16. Find Middle Element");
            System.out.println(" 17. Reverse");
            System.out.println(" 18. Sort");
            System.out.println(" 19. Remove Duplicates");
            System.out.println(" 20. Exit");
            System.out.println();
            int ch = readInt(" Enter the choice:- ");
            switch (ch) {
                case 1: list.insertAtBeginning(readInt("Enter value: ")); System.out.println("Inserted at beginning."); break;
                case 2: list.insertAtEnd(readInt("Enter value: ")); System.out.println("Inserted at end."); break;
                case 3: {
                    int pos = readInt("Enter position (1-based): ");
                    int v = readInt("Enter value: ");
                    System.out.println(list.insertAtPosition(pos, v) ? "Inserted at position " + pos + "." : "Invalid position!");
                    break;
                }
                case 4: {
                    int t = readInt("Enter existing node value: ");
                    int v = readInt("Enter new value: ");
                    System.out.println(list.insertAfter(t, v) ? "Inserted after " + t + "." : "Value " + t + " not found.");
                    break;
                }
                case 5: {
                    int t = readInt("Enter existing node value: ");
                    int v = readInt("Enter new value: ");
                    System.out.println(list.insertBefore(t, v) ? "Inserted before " + t + "." : "Value " + t + " not found.");
                    break;
                }
                case 6: System.out.println(list.deleteFromBeginning() ? "Deleted from beginning." : "List is empty!"); break;
                case 7: System.out.println(list.deleteFromEnd() ? "Deleted from end." : "List is empty!"); break;
                case 8: {
                    int pos = readInt("Enter position (1-based): ");
                    System.out.println(list.deleteFromPosition(pos) ? "Deleted node at position " + pos + "." : "Invalid position!");
                    break;
                }
                case 9: {
                    int v = readInt("Enter value to delete: ");
                    System.out.println(list.deleteByValue(v) ? "Deleted " + v + "." : "Value " + v + " not found.");
                    break;
                }
                case 10: {
                    int v = readInt("Enter value to search: ");
                    int p = list.search(v);
                    System.out.println(p == -1 ? "Value " + v + " not found." : "Value " + v + " found at position " + p + ".");
                    break;
                }
                case 11: {
                    int o = readInt("Enter old value: ");
                    int n = readInt("Enter new value: ");
                    System.out.println(list.update(o, n) ? "Updated " + o + " to " + n + "." : "Value " + o + " not found.");
                    break;
                }
                case 12: list.display(); break;
                case 13: System.out.println("Total nodes: " + list.count()); break;
                case 14: System.out.println(list.isEmpty() ? "List is empty!" : "Maximum: " + list.findMax()); break;
                case 15: System.out.println(list.isEmpty() ? "List is empty!" : "Minimum: " + list.findMin()); break;
                case 16: System.out.println(list.isEmpty() ? "List is empty!" : "Middle element: " + list.findMiddle() + "  (even length: second middle is returned)"); break;
                case 17: list.reverse(); System.out.println("List reversed."); list.display(); break;
                case 18: list.sort(); System.out.println("List sorted (ascending)."); list.display(); break;
                case 19: System.out.println("Duplicates removed: " + list.removeDuplicates()); list.display(); break;
                case 20: return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ------------------------------------------------------------ doubly menu

    private static void doublyMenu(DoublyLinkedList list) {
        while (true) {
            System.out.println();
            System.out.println("********** Operation on Doubly Linked List **********");
            System.out.println();
            System.out.println(" 1. Insert at Beginning");
            System.out.println(" 2. Insert at End");
            System.out.println(" 3. Insert at Position");
            System.out.println(" 4. Insert After a Node");
            System.out.println(" 5. Insert Before a Node");
            System.out.println();
            System.out.println(" 6. Delete from Beginning");
            System.out.println(" 7. Delete from End");
            System.out.println(" 8. Delete from Position");
            System.out.println(" 9. Delete by Value");
            System.out.println();
            System.out.println(" 10. Search");
            System.out.println(" 11. Update");
            System.out.println(" 12. Display Forward");
            System.out.println(" 13. Display Backward");
            System.out.println(" 14. Count Nodes");
            System.out.println(" 15. Find Maximum");
            System.out.println(" 16. Find Minimum");
            System.out.println(" 17. Find Middle Element");
            System.out.println(" 18. Reverse");
            System.out.println(" 19. Sort");
            System.out.println(" 20. Remove Duplicates");
            System.out.println(" 21. Exit");
            System.out.println();
            int ch = readInt(" Enter the choice:- ");
            switch (ch) {
                case 1: list.insertAtBeginning(readInt("Enter value: ")); System.out.println("Inserted at beginning."); break;
                case 2: list.insertAtEnd(readInt("Enter value: ")); System.out.println("Inserted at end."); break;
                case 3: {
                    int pos = readInt("Enter position (1-based): ");
                    int v = readInt("Enter value: ");
                    System.out.println(list.insertAtPosition(pos, v) ? "Inserted at position " + pos + "." : "Invalid position!");
                    break;
                }
                case 4: {
                    int t = readInt("Enter existing node value: ");
                    int v = readInt("Enter new value: ");
                    System.out.println(list.insertAfter(t, v) ? "Inserted after " + t + "." : "Value " + t + " not found.");
                    break;
                }
                case 5: {
                    int t = readInt("Enter existing node value: ");
                    int v = readInt("Enter new value: ");
                    System.out.println(list.insertBefore(t, v) ? "Inserted before " + t + "." : "Value " + t + " not found.");
                    break;
                }
                case 6: System.out.println(list.deleteFromBeginning() ? "Deleted from beginning." : "List is empty!"); break;
                case 7: System.out.println(list.deleteFromEnd() ? "Deleted from end." : "List is empty!"); break;
                case 8: {
                    int pos = readInt("Enter position (1-based): ");
                    System.out.println(list.deleteFromPosition(pos) ? "Deleted node at position " + pos + "." : "Invalid position!");
                    break;
                }
                case 9: {
                    int v = readInt("Enter value to delete: ");
                    System.out.println(list.deleteByValue(v) ? "Deleted " + v + "." : "Value " + v + " not found.");
                    break;
                }
                case 10: {
                    int v = readInt("Enter value to search: ");
                    int p = list.search(v);
                    System.out.println(p == -1 ? "Value " + v + " not found." : "Value " + v + " found at position " + p + ".");
                    break;
                }
                case 11: {
                    int o = readInt("Enter old value: ");
                    int n = readInt("Enter new value: ");
                    System.out.println(list.update(o, n) ? "Updated " + o + " to " + n + "." : "Value " + o + " not found.");
                    break;
                }
                case 12: list.displayForward(); break;
                case 13: list.displayBackward(); break;
                case 14: System.out.println("Total nodes: " + list.count()); break;
                case 15: System.out.println(list.isEmpty() ? "List is empty!" : "Maximum: " + list.findMax()); break;
                case 16: System.out.println(list.isEmpty() ? "List is empty!" : "Minimum: " + list.findMin()); break;
                case 17: System.out.println(list.isEmpty() ? "List is empty!" : "Middle element: " + list.findMiddle() + "  (even length: second middle is returned)"); break;
                case 18: list.reverse(); System.out.println("List reversed."); list.displayForward(); break;
                case 19: list.sort(); System.out.println("List sorted (ascending)."); list.displayForward(); break;
                case 20: System.out.println("Duplicates removed: " + list.removeDuplicates()); list.displayForward(); break;
                case 21: return;
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ------------------------------------------------------------ comparison

    private static void compare() {
        System.out.println();
        line('-', 78);
        System.out.printf("%-26s | %-24s | %-22s%n", "Feature", "Singly Linked List", "Doubly Linked List");
        line('-', 78);
        String[][] rows = {
            {"Links per node", "next", "prev + next"},
            {"Memory per node", "Less", "More (extra reference)"},
            {"Traversal", "Forward only", "Forward and backward"},
            {"Insert at beginning", "O(1)", "O(1)"},
            {"Insert at end", "O(n) (no tail)", "O(1) (with tail)"},
            {"Delete from beginning", "O(1)", "O(1)"},
            {"Delete from end", "O(n)", "O(1) (with tail)"},
            {"Delete a known node", "O(n) (need previous)", "O(1)"},
            {"Search / Count / Min / Max", "O(n)", "O(n)"},
            {"Reverse", "O(n), 3 refs", "O(n), swap prev/next"},
            {"Bubble sort", "O(n^2)", "O(n^2)"},
            {"Implementation", "Simpler", "More pointer handling"},
        };
        for (String[] r : rows) System.out.printf("%-26s | %-24s | %-22s%n", r[0], r[1], r[2]);
        line('-', 78);
    }
}
