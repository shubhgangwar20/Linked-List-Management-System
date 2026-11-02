# Linked List Management System

A menu-driven console application in **Java** to practise Singly and Doubly Linked Lists.
Both lists are implemented **from scratch** with custom `Node` classes – no arrays, no `java.util.LinkedList`/`ArrayList`.

## Objective
Implement and compare a Singly Linked List and a Doubly Linked List with all common operations
(insert, delete, search, update, min/max/middle, reverse, sort, remove duplicates), and test all important edge cases.

## Language Used
Java (JDK 11 or higher). Uses only `java.util.Scanner` for input.

## Features
- Main menu: Singly list, Doubly list, Comparison table, Exit
- 20 operations on Singly list, 21 on Doubly list (each a separate method)
- Input validation (non-integer input is rejected, invalid positions reported)
- `Tests.java` – 100+ automated edge-case checks, including `prev`/`next`/`head`/`tail` consistency for the Doubly list

## Singly Linked List
`Node { int data; Node next; }` with a `head` reference (no tail, as in the assignment's complexity table).

## Doubly Linked List
`Node { int data; Node prev, next; }` with `head` **and** `tail`, so insert/delete at the end is O(1).
A private `unlink(node)` helper fixes head/tail and both neighbours in one place.

## Operations
| # | Operation | Notes |
|---|-----------|-------|
| 1-5 | Insert at beginning / end / position / after a value / before a value | positions are **1-based** |
| 6-9 | Delete from beginning / end / position / by value | deletes first match, reports "not found" |
| 10 | Search | returns 1-based position |
| 11 | Update | replaces first match |
| 12 | Display (Doubly: Forward + Backward) | `10 -> 20 -> NULL` |
| 13 | Count nodes | |
| 14-15 | Find max / min | |
| 16 | Find middle | slow/fast references; **even length → second middle** |
| 17 | Reverse | Singly: prev/current/next. Doubly: swap prev/next + swap head/tail |
| 18 | Sort | Bubble sort (swap data), ascending |
| 19 | Remove duplicates | keeps first occurrence |

## How to Run
```bash
git clone https://github.com/<your-username>/Linked-List-Management-System.git
cd Linked-List-Management-System

mkdir out
javac -d out src/*.java
java -cp out Main          # run the application
java -cp out Tests         # run edge-case tests
```

## Sample Output
```
Singly Linked List
Before:        40 -> 10 -> 30 -> 20 -> 50 -> NULL
After Sort:    10 -> 20 -> 30 -> 40 -> 50 -> NULL
After Reverse: 50 -> 40 -> 30 -> 20 -> 10 -> NULL

Doubly Linked List
Forward:  NULL <- 10 <-> 20 <-> 30 <-> 40 -> NULL
Backward: NULL <- 40 <-> 30 <-> 20 <-> 10 -> NULL
```
Full session and test log: [`sample-output/output.txt`](sample-output/output.txt)

## Time Complexity
| Operation | Singly | Doubly |
|-----------|--------|--------|
| Insert at beginning | O(1) | O(1) |
| Insert at end | O(n) | O(1) |
| Insert at position / after / before | O(n) | O(n) |
| Delete from beginning | O(1) | O(1) |
| Delete from end | O(n) | O(1) |
| Delete by position / value | O(n) | O(n) |
| Search / Display / Count / Min / Max / Middle | O(n) | O(n) |
| Reverse | O(n), O(1) space | O(n), O(1) space |
| Bubble sort | O(n²) | O(n²) |
| Remove duplicates (simple) | O(n²) | O(n²) |

## Edge Cases Covered
Empty list · one-node list · position 1 · last valid position (size+1 for insert) · invalid / out-of-range / negative position ·
delete non-existent value · duplicates · zero and negative values · sort/reverse on empty and single-node lists.

## Project Structure
```
Linked-List-Management-System/
├── README.md
├── src/
│   ├── SinglyLinkedList.java
│   ├── DoublyLinkedList.java
│   ├── Main.java
│   └── Tests.java
├── sample-output/output.txt
├── docs/            (add project-report.pdf here if your instructor requires it)
└── .gitignore
```

## Author
Your Name – Roll No. – College
