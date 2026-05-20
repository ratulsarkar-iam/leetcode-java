package com.rs.leetcode.easy;

import com.rs.leetcode.extra.ListNode;

/* @LeetcodeMeta
 * @Title: Merge Two Sorted Lists
 * @TimeComplexity: O(n + m)
 * @SpaceComplexity: O(n + m)
 * @Algorithm: Recursion
 */

/**
 * ================================================================================
 * MERGE TWO SORTED LISTS - RECURSIVE APPROACH
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 20, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Merge two sorted linked lists and return it as a new sorted list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * Both input lists are sorted in non-decreasing order.
 * <p>
 * <p>
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Explanation: Both lists are merged in sorted order
 * <p>
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 * Explanation: Both empty lists result in empty list
 * <p>
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * Explanation: Empty list merged with [0] results in [0]
 * <p>
 * <p>
 * Constraints:
 * - The number of nodes in both lists is in the range [0, 50]
 * - -100 ≤ Node.val ≤ 100
 * - Both lists are sorted in non-decreasing order
 * <p>
 * ================================================================================
 * MERGE VISUALIZATION
 * ================================================================================
 * 
 * Merging [1,2,4] and [1,3,4]:
 * 
 * list1: 1 → 2 → 4 → null
 * list2: 1 → 3 → 4 → null
 * 
 * Step 1: Compare 1 and 1
 * - Equal, choose list1's 1
 * - Recurse with list1.next=[2,4], list2=[1,3,4]
 * 
 * Step 2: Compare 2 and 1
 * - 1 is smaller, choose list2's 1
 * - Recurse with list1=[2,4], list2.next=[3,4]
 * 
 * Step 3: Compare 2 and 3
 * - 2 is smaller, choose list1's 2
 * - Recurse with list1.next=[4], list2=[3,4]
 * 
 * Step 4: Compare 4 and 3
 * - 3 is smaller, choose list2's 3
 * - Recurse with list1=[4], list2.next=[4]
 * 
 * Step 5: Compare 4 and 4
 * - Equal, choose list1's 4
 * - Recurse with list1.next=null, list2=[4]
 * 
 * Step 6: list1 is null, return list2's [4]
 * 
 * Result: 1 → 1 → 2 → 3 → 4 → 4 → null
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    mergeTwoLists(list1, list2)
 *                           |
 *                           v
 *                 Call merge(list1, list2)
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 │   BASE CASE CHECK        │
 *                 │   If h1 == null OR h2 == null │
 *                 └─────────────────────────┘
 *                           |
 *              ┌──────────┴──────────┐
 *              |                   |
 *              v                   v
 *          YES (base)        NO (recurse)
 *              |                   |
 *              v                   v
 *    Return non-null list  ┌─────────────────────┐
 *                          │ Compare h1.val and │
 *                          │ h2.val              │
 *                          └─────────────────────┘
 *                                    |
 *                       ┌────────────┴────────────┐
 *                       |                         |
 *                       v                         v
 *                 h1.val <= h2.val           h1.val > h2.val
 *                       |                         |
 *                       v                         v
 *            h1.next = merge(h1.next, h2)  h2.next = merge(h1, h2.next)
 *                       |                         |
 *                       v                         v
 *            Return h1                  Return h2
 * <p>
 * ================================================================================
 * RECURSION TREE EXAMPLE
 * ================================================================================
 * 
 * Merging [1,3] and [2,4]:
 * 
 *                    merge([1,3], [2,4])
 *                           |
 *                           v
 *                    1 <= 2 ✓
 *                           |
 *                           v
 *            merge([3], [2,4])  (h1.next)
 *                           |
 *                           v
 *                    3 > 2 ✗
 *                           |
 *                           v
 *            merge([3], [4])   (h2.next)
 *                           |
 *                           v
 *                    3 <= 4 ✓
 *                           |
 *                           v
 *            merge(null, [4])  (h1.next)
 *                           |
 *                           v
 *            BASE: h1 == null
 *                           |
 *                           v
 *            Return [4]
 *                           |
 *                           v
 *            Unwind: 3 → 4
 *                           |
 *                           v
 *            Unwind: 2 → 3 → 4
 *                           |
 *                           v
 *            Unwind: 1 → 2 → 3 → 4
 * 
 * Final result: [1,2,3,4]
 * <p>
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing mergeTwoLists([1,2], [1,3]):
 * 
 * Call 1: merge([1,2], [1,3])
 * - h1.val=1, h2.val=1
 * - 1 <= 1 ✓
 * - h1.next = merge([2], [1,3])
 * 
 * Call 2: merge([2], [1,3])
 * - h1.val=2, h2.val=1
 * - 2 > 1 ✗
 * - h2.next = merge([2], [3])
 * 
 * Call 3: merge([2], [3])
 * - h1.val=2, h2.val=3
 * - 2 <= 3 ✓
 * - h1.next = merge(null, [3])
 * 
 * Call 4: merge(null, [3])
 * - h1 == null (BASE CASE)
 * - Return h2 = [3]
 * 
 * Unwind Call 3:
 * - h1.next = [3]
 * - Return h1 = [2,3]
 * 
 * Unwind Call 2:
 * - h2.next = [2,3]
 * - Return h2 = [1,2,3]
 * 
 * Unwind Call 1:
 * - h1.next = [1,2,3]
 * - Return h1 = [1,1,2,3]
 * 
 * Final result: [1,1,2,3]
 * <p>
 * ================================================================================
 */
public class Leetcode_21 {
    /**
     * Merges two sorted linked lists into one sorted list
     * 
     * @param list1 First sorted linked list
     * @param list2 Second sorted linked list
     * @return Merged sorted linked list
     * 
     * Time: O(n + m) - visits each node exactly once
     * Space: O(n + m) - recursion stack depth in worst case
     * 
     * ALGORITHM: Recursive merge
     * - Compare heads of both lists
     * - Choose smaller node as current head
     * - Recursively merge remaining nodes
     * - Base case: if either list is null, return the other
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return merge(list1, list2);
    }

    /**
     * RECURSIVE MERGE HELPER
     * Recursively merges two sorted linked lists by comparing heads
     * 
     * @param h1 Head of first sorted list
     * @param h2 Head of second sorted list
     * @return Merged sorted list head
     * 
     * ALGORITHM:
     * 1. BASE CASE: If either list is null, return the other
     * 2. RECURSIVE CASE: Compare heads
     *    - If h1.val <= h2.val: h1 becomes head, recurse with h1.next and h2
     *    - If h1.val > h2.val: h2 becomes head, recurse with h1 and h2.next
     * 3. Return the chosen head
     * 
     * KEY: The smaller node becomes the current head, and we recursively
     * merge the remaining nodes to build the sorted result.
     */
    private ListNode merge(ListNode h1, ListNode h2) {
        // BASE CASE: If either list is null, return the other
        if (h1 == null || h2 == null) {
            return h1 == null ? h2 : h1;
        }
        
        // RECURSIVE CASE: Compare heads and choose smaller
        if (h1.val <= h2.val) {
            // h1 is smaller or equal, make it the current head
            h1.next = merge(h1.next, h2);
            return h1;
        } else {
            // h2 is smaller, make it the current head
            h2.next = merge(h1, h2.next);
            return h2;
        }
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Merge Two Sorted Lists
================================================================================

PROBLEM: Merge two sorted linked lists into one sorted list by splicing
         together the nodes of the first two lists.

APPROACH: Recursive Merge
---------------------------
1. Compare the heads of both lists
2. Choose the smaller (or equal) node as the current head
3. Recursively merge the remaining nodes
4. Base case: if either list is null, return the other

KEY INSIGHT: By always choosing the smaller head and recursively merging
the rest, we naturally build a sorted merged list.

TIME COMPLEXITY: O(n + m)
--------------------------
• n = length of list1, m = length of list2
• Each node is visited exactly once
• Each comparison takes O(1)
• Total comparisons: n + m - 1
• Total time: O(n + m)

SPACE COMPLEXITY: O(n + m)
---------------------------
• Recursion stack depth in worst case: n + m
• When lists are interleaved, each recursive call adds to stack
• No additional data structures used
• Total space: O(n + m) for recursion stack

WHY RECURSIVE MERGE WORKS:
--------------------------
1. Base Case: If either list is null, return the other
   - Handles empty lists gracefully
   - Terminates the recursion

2. Recursive Case: Compare and choose smaller
   - If h1.val <= h2.val: h1 becomes head, merge h1.next with h2
   - If h1.val > h2.val: h2 becomes head, merge h1 with h2.next
   - Guarantees sorted order at each step

3. Inductive Step:
   - Assume merge(h1.next, h2) returns sorted merge
   - Then h1 → merge(h1.next, h2) is also sorted
   - Same logic for h2 case

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Iterative Approach**
   - Use dummy node and tail pointer
   - O(n + m) time, O(1) space (no recursion stack)
   - Better for very large lists

2. **Tail Recursion Optimization**
   - Convert to tail-recursive form
   - Some compilers can optimize to iteration
   - Not guaranteed in Java

3. **Parallel Merge**
   - Split lists and merge in parallel
   - Useful for very large lists
   - Complex to implement

4. **In-Place Merge**
   - Modify one list instead of creating new structure
   - Saves space but modifies input
   - Not always desirable

COMPARISON WITH OTHER APPROACHES:
----------------------------------
| Approach | Time | Space | Complexity | Use Case |
|----------|------|-------|------------|----------|
| Recursive | O(n+m) | O(n+m) | Elegant | Small to medium lists |
| Iterative | O(n+m) | O(1) | Optimal space | Large lists |
| Parallel | O(n+m)/p | O(n+m) | Fast | Very large lists |
| In-Place | O(n+m) | O(1) | Modifies input | When space critical |

PRACTICAL APPLICATIONS:
-----------------------
• **Merge Sort** - Core operation in merge sort algorithm
• **Database Operations** - Merging sorted query results
• **File Processing** - Merging sorted log files
• **Version Control** - Merging sorted change lists
• **Data Streaming** - Merging sorted data streams

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Forgetting base case (infinite recursion)
2. ❌ Not handling null lists (NullPointerException)
3. ❌ Using wrong comparison (affects stability)
4. ❌ Creating new nodes instead of reusing (wastes memory)
5. ❌ Not preserving original list structure when required

TESTING STRATEGIES:
------------------
• Both empty: [], [] → []
• One empty: [], [1,2,3] → [1,2,3]
• Both single: [1], [2] → [1,2]
• Same values: [1,1], [1,1] → [1,1,1,1]
• Interleaved: [1,3,5], [2,4,6] → [1,2,3,4,5,6]
• One subset: [1,2], [1,2,3,4] → [1,1,2,2,3,4]
• Negative values: [-3,-1], [-2,0] → [-3,-2,-1,0]
• Large lists: Test with 50+ nodes each

DEBUGGING TIPS:
--------------
1. Print values at each recursive call
2. Verify base case is reached
3. Check that returned list is sorted
4. Ensure all nodes from both lists are included
5. Verify no cycles in result list

FUTURE ENHANCEMENTS:
-------------------
1. Add iterative version for large lists
2. Support for custom comparators
3. Merge k sorted lists (generalization)
4. Merge in descending order
5. Merge with stability guarantee

KEY LEARNING POINTS:
--------------------
• Recursion elegantly solves merge problems
• Base case is crucial for termination
• Space complexity includes recursion stack
• Iterative approach can reduce space to O(1)
• Understanding both approaches is important

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Merge k Sorted Lists** - Merge multiple sorted lists (Leetcode 23)
2. **Merge Sorted Arrays** - Same concept with arrays
3. **Merge in Descending Order** - Reverse comparison
4. **Merge with Custom Comparator** - Use custom ordering
5. **Merge Without Extra Space** - In-place merge

================================================================================
*/
