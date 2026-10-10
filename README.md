
# BST vs Hash Table Performance Comparison

## 1. Introduction

The goal of this project is to implement a Binary Search Tree (BST) and a Hash Table in Java and compare their performance. The experiment checks insertion, searching, and deletion for 100, 1,000, and 10,000 elements.

## 2. Implementation

The BST stores values in nodes. Smaller keys are placed in the left subtree and larger keys in the right subtree. The implementation supports insertion, searching, and deletion. Duplicate keys are ignored.

The Hash Table uses separate chaining. A hash function selects a bucket, and each bucket contains a linked list. If multiple keys have the same hash index, they are stored in the same list.

## 3. Time Complexity

Let n be the number of stored elements.

| Operation | BST Best | BST Average* | BST Worst | Hash Table Best | Hash Table Average** | Hash Table Worst |
|---|---|---|---|---|---|---|
| Insert | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |
| Search | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |
| Delete | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |

*The BST average case assumes a reasonably balanced tree. This implementation is not self-balancing. If keys are inserted in sorted order, the tree can become unbalanced and operations can take O(n).

**The Hash Table average case assumes a good hash distribution and a controlled load factor. If many keys collide in one bucket, operations can take O(n).

The best case for BST search is O(1) when the key is at the root. For insertion, O(1) is possible when the tree is empty. For deletion, O(1) is possible when the key is at the root and has at most one child.

## 4. Experimental Method

The program tests 100, 1,000, and 10,000 unique integer keys. The input order is shuffled using a fixed random seed. Each test is repeated five times, and the average elapsed time is reported in microseconds.

The same input values are used for both structures. The hash table is created with a capacity equal to twice the number of input elements to reduce collisions.

## 5. Experimental Results

Replace the dashes with the actual output from your own computer.

| Structure | Elements | Insert (µs) | Search (µs) | Delete (µs) |
|---|---:|---:|---:|---:|
| BST | 100 | — | — | — |
| Hash Table | 100 | — | — | — |
| BST | 1,000 | — | — | — |
| Hash Table | 1,000 | — | — | — |
| BST | 10,000 | — | — | — |
| Hash Table | 10,000 | — | — | — |

## 6. Discussion

Big-O notation describes how the amount of work grows as the input size increases. It does not guarantee an exact running time.

A hash table can be faster for exact-key searches because it calculates a bucket index directly. However, collisions require scanning a bucket. A BST follows links between nodes, and its performance depends on its shape.

Practical measurements can also be affected by memory allocation, cache behavior, JVM warm-up, garbage collection, and other running programs. Very short operations may have noisy measurements, so the results should be treated as an experiment rather than a universal rule.

## 7. Conclusion

Use the measured results to compare the two structures for each operation. State which structure was faster in your experiment and whether the measurements agree with the expected average-case complexity.

A hash table is useful when fast exact-key lookup is important and sorted order is not required. A BST is useful when maintaining ordered keys matters, but a self-balancing BST is preferable when logarithmic performance must be guaranteed.
