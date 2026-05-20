package com.rs.leetcode.easy;

/* @LeetcodeMeta
 * @Title: Middle of the Linked List
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Two Pointers (Slow-Fast)
 */

/**
 * ================================================================================
 * MIDDLE OF THE LINKED LIST - TWO POINTER TECHNIQUE
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 19, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Given the head of a singly linked list, return the middle node.
 * If there are two middle nodes, return the second middle node.
 * <p>
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node is 3, and the list from 3 is [3,4,5]
 * <p>
 * Example 2:
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: There are two middle nodes (3 and 4), return the second (4)
 * <p>
 * <p>
 * Constraints:
 * - The number of nodes in the list is in the range [1, 100]
 * - 1 ≤ Node.val ≤ 100
 * <p>
 * ================================================================================
 * TWO POINTER TECHNIQUE
 * ================================================================================
 * 
 * The slow-fast pointer technique uses two pointers:
 * - Slow pointer: Moves one step at a time
 * - Fast pointer: Moves two steps at a time
 * <p>
 * When fast pointer reaches the end, slow pointer is at the middle!
 * <p>
 * Why this works:
 * - Fast moves at 2x speed of slow
 * - When fast travels distance d, slow travels d/2
 * - When fast reaches end (n nodes), slow is at n/2 (middle)
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    middleNode(head)
 *                           |
 *                           v
 *                 Initialize slow=head, fast=head
 *                           |
 *                           v
 *                 While fast != null AND fast.next != null:
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 |    Move slow by 1 step     |
 *                 |    slow = slow.next       |
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 |    Move fast by 2 steps  |
 *                 |    fast = fast.next.next |
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 Return slow (middle node)
 * <p>
 * ================================================================================
 * TWO POINTER VISUALIZATION
 * ================================================================================
 * 
 * Finding middle of [1 → 2 → 3 → 4 → 5]:
 * 
 * Initial:
 * slow: 1 → 2 → 3 → 4 → 5
 * fast: 1 → 2 → 3 → 4 → 5
 *        ^
 *   both start here
 * 
 * Step 1:
 * slow: 1 → 2 → 3 → 4 → 5
 *        ^
 * fast: 1 → 2 → 3 → 4 → 5
 *              ^
 * 
 * Step 2:
 * slow: 1 → 2 → 3 → 4 → 5
 *              ^
 * fast: 1 → 2 → 3 → 4 → 5
 *                    ^
 * 
 * Step 3:
 * slow: 1 → 2 → 3 → 4 → 5
 *                    ^
 * fast: 1 → 2 → 3 → 4 → 5
 *                          ^ (fast.next = null, stop)
 * 
 * Return slow = 3 (middle node)
 * <p>
 * ================================================================================
 * EVEN LENGTH LIST EXAMPLE
 * ================================================================================
 * 
 * Finding middle of [1 → 2 → 3 → 4 → 5 → 6]:
 * 
 * Step 1: slow at 1, fast at 1
 * Step 2: slow at 2, fast at 3
 * Step 3: slow at 3, fast at 5
 * Step 4: slow at 4, fast at null (stop)
 * 
 * Return slow = 4 (second middle node)
 * <p>
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing middleNode([10 → 20 → 30 → 40]):
 * 
 * Start:
 * slow = 10 → 20 → 30 → 40
 * fast = 10 → 20 → 30 → 40
 * 
 * Iteration 1:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 20
 * - fast = fast.next.next = 30
 * State: slow=20, fast=30
 * 
 * Iteration 2:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 30
 * - fast = fast.next.next = null
 * State: slow=30, fast=null
 * 
 * Loop ends (fast == null)
 * Return slow = 30
 * 
 * Result: [30, 40] (second middle node)
 * <p>
 * ================================================================================
 */
public class Leetcode_876 {
    /**
     * BRUTE FORCE APPROACH - Count then traverse
     * First counts the total nodes, then traverses to the middle
     * 
     * @param head The head of the linked list
     * @return The middle node of the linked list
     * 
     * Time: O(n) + O(n) = O(n) - two passes through the list
     * Space: O(1) - only using a few pointers
     * 
     * ALGORITHM:
     * 1. First pass: Count total number of nodes
     * 2. Calculate middle index: size / 2
     * 3. Second pass: Traverse to the middle index
     * 
     * NOTE: Simple but requires two passes through the list
     */
    public ListNode_1 middleNode_brute_force(ListNode_1 head) {
        // First pass: Count the total number of nodes
        int size = 0;
        ListNode_1 temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        
        // Calculate middle index (returns second middle for even length)
        int middleNode = size / 2;
        
        // Second pass: Traverse to the middle node
        temp = head;
        for (int i = 0; i < middleNode; i++) {
            temp = temp.next;
        }
        
        return temp;
    }

    /**
     * OPTIMAL APPROACH - Two Pointer (Slow-Fast) Technique
     * Uses two pointers moving at different speeds to find middle in one pass
     * 
     * @param head The head of the linked list
     * @return The middle node of the linked list
     * 
     * Time: O(n) - single pass through the list
     * Space: O(1) - only using two pointers
     * 
     * ALGORITHM:
     * 1. Initialize slow and fast pointers at head
     * 2. Move slow by 1 step, fast by 2 steps
     * 3. When fast reaches end, slow is at middle
     * 
     * KEY INSIGHT: Fast moves at 2x speed, so when fast travels
     * the entire list, slow travels half the distance (middle)
     * 
     * NOTE: Returns second middle node for even-length lists
     */
    public ListNode_1 middleNode(ListNode_1 head) {
        // Initialize both pointers at the head
        ListNode_1 slowPointer = head;
        ListNode_1 fastPointer = head;
        
        // Move fast by 2 steps, slow by 1 step
        // Continue until fast reaches the end
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;          // Move 1 step
            fastPointer = fastPointer.next.next;     // Move 2 steps
        }
        
        // When fast reaches end, slow is at the middle
        return slowPointer;
    }

    /**
     * Definition for a singly-linked list node.
     * This inner class represents a node in the linked list structure.
     */
    public class ListNode_1 {
        /**
         * The value stored in this node
         */
        int val;

        /**
         * Reference to the next node in the list (null if last)
         */
        ListNode_1 next;

        /**
         * Default constructor - creates an empty node
         * val defaults to 0, next defaults to null
         */
        ListNode_1 () {
        }

        /**
         * Constructor to create a node with a specific value
         *
         * @param val The integer value to store in this node
         */
        ListNode_1 (int val) {
            this.val = val;
        }

        /**
         * Constructor to create a node with value and next pointer
         *
         * @param val  The integer value to store in this node
         * @param next Reference to the next node in the list
         */
        ListNode_1 (int val, ListNode_1 next) {
            this.val = val;
            this.next = next;
        }
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Middle of Linked List
================================================================================

PROBLEM: Find the middle node of a singly linked list. If there are two middle
         nodes (even length), return the second middle node.

APPROACH: Two Pointer (Slow-Fast) Technique
-------------------------------------------
1. Initialize two pointers (slow and fast) at the head
2. Move slow by 1 step, fast by 2 steps in each iteration
3. When fast reaches the end, slow is at the middle
4. Return slow as the middle node

KEY INSIGHT: Fast pointer moves at 2x speed, so when it travels the entire
list (n nodes), slow travels half the distance (n/2 nodes) - exactly the middle!

TIME COMPLEXITY: O(n)
--------------------
• Brute Force: O(n) + O(n) = O(n) - two passes through the list
• Optimal (Two Pointer): O(n) - single pass through the list
• Both approaches are linear, but two-pointer does it in one pass

SPACE COMPLEXITY: O(1)
-----------------------
• Brute Force: O(1) - only uses a few pointers
• Optimal (Two Pointer): O(1) - only uses two pointers
• Both approaches use constant extra space

WHY TWO POINTER WORKS:
----------------------
The mathematical reasoning:
- Fast moves 2 steps per iteration, slow moves 1 step
- After k iterations: fast at position 2k, slow at position k
- When fast reaches end (position n), slow is at position n/2
- This is exactly the middle of the list

For even-length lists:
- Fast becomes null (out of bounds)
- Slow is at n/2, which is the second middle node
- If we wanted first middle, we'd stop when fast.next is null

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **First Middle Node for Even Lists**
   - Modify condition to stop when fast.next.next is null
   - Returns first middle instead of second middle

2. **Return Both Middles**
   - Track previous pointer along with slow
   - Return both nodes for even-length lists

3. **Count While Finding Middle**
   - Maintain a counter to get position of middle
   - Useful for problems requiring index

4. **Recursive Approach**
   - Use recursion to find length and middle
   - O(n) time, O(n) space (worse due to stack)

COMPARISON WITH OTHER APPROACHES:
----------------------------------
| Approach | Time | Space | Passes | Complexity |
|----------|------|-------|--------|------------|
| Two Pointer | O(n) | O(1) | 1 | Simple, optimal |
| Brute Force | O(n) | O(1) | 2 | Simple, slower |
| Recursive | O(n) | O(n) | 1 | Complex, uses stack |
| Array Copy | O(n) | O(n) | 1 | Converts to array |

PRACTICAL APPLICATIONS:
-----------------------
• **Palindrome Check** - Find middle to compare halves
• **Merge Sort on Linked List** - Split list at middle
• **Cycle Detection** - Modified slow-fast technique
• **Delete Middle Node** - Need to find middle first
• **Linked List Partitioning** - Split at middle for parallel processing

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Not checking fast.next before accessing fast.next.next (NullPointerException)
2. ❌ Using wrong condition (fast.next vs fast.next.next) for middle selection
3. ❌ Forgetting to handle empty list or single node
4. ❌ Not understanding which middle is returned for even-length lists
5. ❌ Modifying the list while traversing

TESTING STRATEGIES:
------------------
• Empty list: [] → null (if allowed) or handle separately
• Single node: [1] → [1]
• Odd length: [1,2,3] → [2,3] (middle is 2)
• Even length: [1,2,3,4] → [3,4] (second middle is 3)
• Long list: Test with 100+ nodes
• All same values: [2,2,2,2] → [2,2] (verify by position, not value)

DEBUGGING TIPS:
--------------
1. Print slow and fast values at each iteration
2. Verify fast reaches end (null) correctly
3. Check that slow is exactly at n/2 position
4. For even length, verify second middle is returned
5. Handle edge cases (empty, single node) explicitly

FUTURE ENHANCEMENTS:
-------------------
1. Generic type support for any Comparable type
2. Option to return first or second middle
3. Return index of middle node along with node
4. Support for circular linked lists
5. Find k-th node from end (generalization)

KEY LEARNING POINTS:
--------------------
• Two pointer technique is powerful for linked list problems
• Different speeds can divide the problem space
• One pass is better than two passes for efficiency
• Understanding pointer movement is crucial for linked lists
• Edge cases (empty, single node) need special handling

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Find k-th Middle** - Find node at n/k position
2. **Find N-th from End** - Reverse the problem
3. **Delete Middle Node** - Find and remove middle
4. **Check Palindrome** - Use middle to compare halves
5. **Reorder List** - Use middle to split and reorder

================================================================================
*/
