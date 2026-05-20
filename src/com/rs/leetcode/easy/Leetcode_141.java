package com.rs.leetcode.easy;

/* @LeetcodeMeta
 * @Title: Linked List Cycle
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Two Pointers (Floyd's Tortoise and Hare)
 */

/**
 * ================================================================================
 * LINKED LIST CYCLE - FLOYD'S TORTOISE AND HARE ALGORITHM
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 19, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Given the head of a linked list, determine if the linked list
 * has a cycle in it. A cycle occurs when a node's next pointer points
 * back to a previous node, creating an infinite loop.
 * <p>
 * <p>
 * Example 1: List with cycle
 * Input: head = [3,2,0,-4], pos = 1 (index where tail connects)
 * Output: true
 * Explanation: Node 2's next points back to node 1, forming a cycle
 * <p>
 * Example 2: List without cycle
 * Input: head = [1,2]
 * Output: false
 * Explanation: The list ends normally at null
 * <p>
 * Example 3: Single node without cycle
 * Input: head = [1]
 * Output: false
 * Explanation: Single node with no cycle
 * <p>
 * <p>
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10⁴]
 * - -10⁵ ≤ Node.val ≤ 10⁵
 * - pos is -1 or a valid index in the linked list
 * <p>
 * ================================================================================
 * CYCLE VISUALIZATION
 * ================================================================================
 * 
 * List WITHOUT cycle:
 * 1 → 2 → 3 → 4 → null
 * (reaches end normally)
 * 
 * List WITH cycle:
 * 1 → 2 → 3 → 4
 *         ↑       ↓
 *         └───────┘
 * (infinite loop)
 * 
 * The cycle starts at node 2 (pos = 1)
 * <p>
 * ================================================================================
 * FLOYD'S TORTOISE AND HARE ALGORITHM
 * ================================================================================
 * 
 * Uses two pointers moving at different speeds:
 * - Slow pointer (tortoise): Moves 1 step at a time
 * - Fast pointer (hare): Moves 2 steps at a time
 * <p>
 * Key insight: If there's a cycle, the fast pointer will eventually
 * catch up to the slow pointer inside the cycle. If there's no cycle,
 * the fast pointer will reach null.
 * <p>
 * Why it works:
 * - In a cycle, fast moves 2x faster than slow
 * - Each iteration, the distance between them decreases by 1
 * - They will eventually meet (guaranteed)
 * - If no cycle, fast reaches null (guaranteed)
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    hasCycle(head)
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
 *                 ┌─────────────────────────┐
 *                 |    Check if slow == fast  |
 *                 |    If yes, cycle exists   |
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 Return true (cycle found)
 *                           |
 *                           v
 *                 Loop ends (fast reached null)
 *                           |
 *                           v
 *                 Return false (no cycle)
 * <p>
 * ================================================================================
 * CYCLE DETECTION VISUALIZATION
 * ================================================================================
 * 
 * Detecting cycle in [1 → 2 → 3 → 4 → (back to 2)]:
 * 
 * Initial:
 * slow: 1 → 2 → 3 → 4 → 2 → ...
 * fast: 1 → 2 → 3 → 4 → 2 → ...
 *        ^
 *   both start here
 * 
 * Step 1:
 * slow: 1 → 2 → 3 → 4 → 2 → ...
 *        ^
 * fast: 1 → 2 → 3 → 4 → 2 → ...
 *              ^
 * 
 * Step 2:
 * slow: 1 → 2 → 3 → 4 → 2 → ...
 *              ^
 * fast: 1 → 2 → 3 → 4 → 2 → ...
 *                    ^
 * 
 * Step 3:
 * slow: 1 → 2 → 3 → 4 → 2 → ...
 *                    ^
 * fast: 1 → 2 → 3 → 4 → 2 → ...
 *                          ^
 * 
 * Step 4:
 * slow: 1 → 2 → 3 → 4 → 2 → ...
 *                          ^
 * fast: 1 → 2 → 3 → 4 → 2 → ...
 *                        ^ (fast catches slow!)
 * 
 * Cycle detected! Return true
 * <p>
 * ================================================================================
 * NO CYCLE EXAMPLE
 * ================================================================================
 * 
 * Checking [1 → 2 → 3 → null]:
 * 
 * Step 1: slow at 1, fast at 1
 * Step 2: slow at 2, fast at 3
 * Step 3: slow at 3, fast at null (stop)
 * 
 * Loop ends (fast == null)
 * Return false (no cycle)
 * <p>
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing hasCycle([1 → 2 → 3 → 4 → (back to 2)]):
 * 
 * Start:
 * slow = 1 → 2 → 3 → 4 → 2 → ...
 * fast = 1 → 2 → 3 → 4 → 2 → ...
 * 
 * Iteration 1:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 2
 * - fast = fast.next.next = 3
 * - slow (2) != fast (3)
 * 
 * Iteration 2:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 3
 * - fast = fast.next.next = 4
 * - slow (3) != fast (4)
 * 
 * Iteration 3:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 4
 * - fast = fast.next.next = 2
 * - slow (4) != fast (2)
 * 
 * Iteration 4:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 2
 * - fast = fast.next.next = 4
 * - slow (2) != fast (4)
 * 
 * Iteration 5:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 3
 * - fast = fast.next.next = 2
 * - slow (3) != fast (2)
 * 
 * Iteration 6:
 * - fast != null ✓, fast.next != null ✓
 * - slow = slow.next = 4
 * - fast = fast.next.next = 4
 * - slow (4) == fast (4) ✓
 * 
 * Return true (cycle detected!)
 * <p>
 * ================================================================================
 */
public class Leetcode_141 {
    /**
     * Detects if a linked list contains a cycle using Floyd's algorithm
     * 
     * @param head The head of the linked list to check
     * @return true if a cycle exists, false otherwise
     * 
     * Time: O(n) - in worst case, traverses list once before meeting or ending
     * Space: O(1) - only uses two pointers
     * 
     * ALGORITHM: Floyd's Tortoise and Hare (Cycle Detection)
     * 1. Initialize slow and fast pointers at head
     * 2. Move slow by 1 step, fast by 2 steps
     * 3. If they meet, cycle exists
     * 4. If fast reaches null, no cycle
     * 
     * KEY INSIGHT: In a cycle, fast (2x speed) will eventually lap
     * and meet slow. Without a cycle, fast reaches null first.
     */
    public boolean hasCycle(Leetcode_206.ListNode head) {
        // Initialize both pointers at the head
        Leetcode_206.ListNode slowP = head;
        Leetcode_206.ListNode fastP = head;
        
        // Move fast by 2 steps, slow by 1 step
        // Continue until fast reaches end of list
        while (fastP != null && fastP.next != null) {
            slowP = slowP.next;          // Move 1 step
            fastP = fastP.next.next;     // Move 2 steps
            
            // If they meet, a cycle exists
            if (slowP == fastP) {
                return true;
            }
        }
        
        // Fast reached null - no cycle exists
        return false;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Linked List Cycle (Floyd's Tortoise and Hare)
================================================================================

PROBLEM: Determine if a linked list contains a cycle (a node's next pointer
         points back to a previous node, creating an infinite loop).

APPROACH: Floyd's Tortoise and Hare Algorithm
-------------------------------------------
1. Initialize two pointers (slow and fast) at the head
2. Move slow by 1 step, fast by 2 steps in each iteration
3. If they meet at any point, a cycle exists
4. If fast reaches null, no cycle exists

KEY INSIGHT: In a cycle, the fast pointer (2x speed) will eventually
catch up to the slow pointer because they're both trapped in the loop.
Without a cycle, fast reaches null first since it moves faster.

TIME COMPLEXITY: O(n)
--------------------
• With cycle: Fast meets slow within cycle length iterations
• Without cycle: Fast reaches null in n/2 iterations
• Worst case: O(n) - traverses at most the entire list once

SPACE COMPLEXITY: O(1)
-----------------------
• Only uses two pointers (slow and fast)
• No additional data structures
• Constant extra space regardless of list size

WHY FLOYD'S ALGORITHM WORKS:
---------------------------
Mathematical proof for cycle detection:
1. Let the cycle start at position μ from head
2. Let the cycle have length λ
3. Fast moves 2 steps, slow moves 1 step per iteration
4. When slow enters cycle (at μ), fast is at position 2μ
5. Since 2μ mod λ = μ mod λ, they will meet
6. The distance between them decreases by 1 each iteration
7. They are guaranteed to meet within λ iterations

For non-cyclic lists:
- Fast moves 2x faster than slow
- Fast will reach null before slow
- Guaranteed termination in n/2 iterations

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Find Cycle Start** (Leetcode 142)
   - After detecting cycle, reset one pointer to head
   - Move both at same speed until they meet
   - Meeting point is cycle start

2. **Find Cycle Length**
   - After detecting cycle, keep one pointer fixed
   - Move other until it returns
   - Count steps to get cycle length

3. **Brent's Algorithm**
   - Alternative to Floyd's algorithm
   - Uses exponential search + cycle detection
   - Often faster in practice

4. **Hash Set Approach**
   - Store visited nodes in a set
   - O(n) time, O(n) space
   - Simpler but uses more memory

COMPARISON WITH OTHER APPROACHES:
----------------------------------
| Approach | Time | Space | Complexity | Use Case |
|----------|------|-------|------------|----------|
| Floyd's Algorithm | O(n) | O(1) | Optimal | Cycle detection |
| Hash Set | O(n) | O(n) | Simple | When space available |
| Brent's Algorithm | O(n) | O(1) | Faster | Alternative to Floyd |
| Marker/Flag | O(n) | O(1) | Modifies list | When modification allowed |

PRACTICAL APPLICATIONS:
-----------------------
• **Memory Leak Detection** - Detect circular references
• **Concurrent Systems** - Detect deadlocks in resource allocation
• **Graph Algorithms** - Detect cycles in graphs
• **Protocol Validation** - Verify no loops in state machines
• **Database Integrity** - Check for circular dependencies

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Not checking fast.next before accessing fast.next.next (NullPointerException)
2. ❌ Starting pointers at different positions (breaks the algorithm)
3. ❌ Using wrong comparison (slow.val == fast.val instead of slow == fast)
4. ❌ Forgetting to handle empty list (head == null)
5. ❌ Modifying the list structure while traversing

TESTING STRATEGIES:
------------------
• Empty list: null → false
• Single node: [1] → false
• Two nodes without cycle: [1,2] → false
• Two nodes with cycle: [1,2] where 2 points to 1 → true
• Long list without cycle: [1,2,3,...,100] → false
• Long list with cycle: [1,2,3,...,50] where 50 points to 25 → true
• Cycle at head: [1] where 1 points to itself → true
• Cycle at tail: [1,2,3] where 3 points to 1 → true

DEBUGGING TIPS:
--------------
1. Print slow and fast values at each iteration
2. Track iteration count to detect infinite loops
3. Verify fast reaches null for non-cyclic lists
4. Check that slow == fast comparison uses reference equality
5. Use a counter to limit iterations for debugging

FUTURE ENHANCEMENTS:
-------------------
1. Return cycle start node (Leetcode 142)
2. Return cycle length
3. Count number of nodes before cycle
4. Support for doubly linked lists
5. Detect self-loops (node pointing to itself)

KEY LEARNING POINTS:
--------------------
• Two pointer technique is powerful for cycle detection
• Different speeds can reveal structural properties
• O(1) space solutions are often possible with clever algorithms
• Understanding why an algorithm works is as important as how it works
• Edge cases (empty list, single node) need special handling

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Find Cycle Start** - Return node where cycle begins
2. **Find Cycle Length** - Return number of nodes in cycle
3. **Remove Cycle** - Break the cycle and return list
4. **Happy Number** - Detect cycle in number sequence
5. **Duplicate Number** - Find duplicate in array using cycle detection

================================================================================
*/
