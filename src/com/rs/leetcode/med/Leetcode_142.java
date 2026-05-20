package com.rs.leetcode.med;

import com.rs.leetcode.easy.Leetcode_206;

/* @LeetcodeMeta
 * @Title: Linked List Cycle II
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Two Pointers (Floyd's Tortoise and Hare)
 */

/**
 * ================================================================================
 * LINKED LIST CYCLE II - FIND CYCLE START NODE
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 19, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Given the head of a linked list, return the node where the cycle
 * begins. If there is no cycle, return null. There is a cycle in a linked
 * list if there is some node in the list that can be reached again by
 * continuously following the next pointer.
 * <p>
 * <p>
 * Example 1: List with cycle at node 2
 * Input: head = [3,2,0,-4], pos = 1
 * Output: Node with value 2
 * Explanation: The cycle starts at node 2 (index 1)
 * <p>
 * Example 2: List without cycle
 * Input: head = [1,2]
 * Output: null
 * Explanation: No cycle exists
 * <p>
 * Example 3: Single node with self-cycle
 * Input: head = [1], pos = 0
 * Output: Node with value 1
 * Explanation: The node points to itself
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
 * (no cycle start)
 * 
 * List WITH cycle starting at node 2:
 * 1 → 2 → 3 → 4
 *         ↑       ↓
 *         └───────┘
 *     cycle start
 * <p>
 * ================================================================================
 * TWO-PHASE ALGORITHM
 * ================================================================================
 * 
 * Phase 1: Detect Cycle (Floyd's Algorithm)
 * - Use slow (1 step) and fast (2 steps) pointers
 * - If they meet, cycle exists
 * - If fast reaches null, no cycle
 * <p>
 * Phase 2: Find Cycle Start
 * - Reset slow pointer to head
 * - Keep fast at meeting point
 * - Move both at same speed (1 step)
 * - They will meet at cycle start
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    detectCycle(head)
 *                           |
 *                           v
 *                 Initialize slow=head, fast=head
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 │   PHASE 1: DETECT       │
 *                 │   Floyd's Algorithm     │
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 While fast != null AND fast.next != null:
 *                           |
 *                           v
 *                 Move slow by 1, fast by 2
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 │   If slow == fast?      │
 *                 └─────────────────────────┘
 *                           |
 *              ┌──────────┴──────────┐
 *              |                   |
 *              v                   v
 *          YES (cycle)       NO (no cycle)
 *              |                   |
 *              v                   v
 *    ┌─────────────────┐     Return null
 *    │   PHASE 2: FIND  │
 *    │   CYCLE START    │
 *    └─────────────────┘
 *              |
 *              v
 *    Reset slow to head
 *              |
 *              v
 *    While slow != fast:
 *              |
 *              v
 *    Move both by 1 step
 *              |
 *              v
 *    Return slow (cycle start)
 * <p>
 * ================================================================================
 * CYCLE START DETECTION EXPLANATION
 * ================================================================================
 * 
 * Why resetting slow to head finds cycle start:
 * 
 * Let:
 * - μ = distance from head to cycle start
 * - λ = length of cycle
 * - When slow and fast meet, slow has traveled: μ + kλ
 * - When slow and fast meet, fast has traveled: μ + kλ + nλ
 * - Since fast travels 2x distance: 2(μ + kλ) = μ + kλ + nλ
 * - Solving: μ = nλ - kλ = (n-k)λ
 * 
 * This means: distance from head to cycle start = distance from
 * meeting point to cycle start (going around the cycle)
 * 
 * Therefore, when we reset slow to head and move both at same speed,
 * they will meet exactly at the cycle start!
 * <p>
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing detectCycle([1 → 2 → 3 → 4 → (back to 2)]):
 * 
 * Phase 1: Detect Cycle
 * Start: slow=1, fast=1
 * Step 1: slow=2, fast=3
 * Step 2: slow=3, fast=2
 * Step 3: slow=4, fast=4
 * Cycle detected! slow and fast meet at node 4
 * 
 * Phase 2: Find Cycle Start
 * Reset slow to head: slow=1, fast=4
 * Step 1: slow=2, fast=2
 * They meet at node 2 - this is the cycle start!
 * 
 * Return node with value 2
 * <p>
 * ================================================================================
 */
public class Leetcode_142 {
    /**
     * Detects and returns the node where a linked list cycle begins
     * Also removes the cycle by breaking the link before the cycle start
     * 
     * @param head The head of the linked list to check
     * @return The node where the cycle begins, or null if no cycle exists
     * 
     * Time: O(n) - two phases, each at most O(n)
     * Space: O(1) - only uses two pointers plus prevNode
     * 
     * ALGORITHM: Two-Phase Floyd's Algorithm with Cycle Removal
     * Phase 1: Detect cycle using slow-fast pointers
     * Phase 2: Find cycle start by resetting slow to head
     * Extra: Remove cycle by setting prevNode.next = null
     * 
     * KEY INSIGHT: When slow and fast meet, the distance from head to
     * cycle start equals the distance from meeting point to cycle start.
     * 
     * NOTE: The cycle removal tracks the previous node during Phase 2
     * and breaks the cycle when the cycle start is found.
     */
    public Leetcode_206.ListNode detectCycle(Leetcode_206.ListNode head) {
        Leetcode_206.ListNode retNode = null;
        Leetcode_206.ListNode slowP = head;
        Leetcode_206.ListNode fastP = head;
        boolean isCycle = false;
        
        // PHASE 1: Detect if a cycle exists using Floyd's algorithm
        while (fastP != null && fastP.next != null) {
            slowP = slowP.next;          // Move 1 step
            fastP = fastP.next.next;     // Move 2 steps
            
            // If they meet, cycle exists
            if (slowP == fastP) {
                isCycle = true;
                break; // Preserve fastP at meeting point
            }
        }
        
        // PHASE 2: Find the cycle start node
        if (isCycle) {
            // Reset slow to head, keep fast at meeting point
            slowP = head;
            // Track previous node to remove cycle - extra
            Leetcode_206.ListNode prevNode = null;
            
            // Move both at same speed until they meet
            // Meeting point is the cycle start
            while (slowP != fastP) {
                prevNode = fastP;  // Track previous node before moving
                slowP = slowP.next;
                fastP = fastP.next;
            }
            
            // Remove the cycle by setting the last node's next to null
            if (prevNode != null) {
                prevNode.next = null;
            }
            
            retNode = slowP; // or fastP // Cycle start node
        }
        
        return retNode;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Linked List Cycle II (Find Cycle Start)
================================================================================

PROBLEM: Given the head of a linked list, return the node where the cycle
         begins. If there is no cycle, return null.

APPROACH: Two-Phase Floyd's Algorithm
-----------------------------------
Phase 1: Detect Cycle
- Use slow (1 step) and fast (2 steps) pointers
- If they meet, cycle exists
- If fast reaches null, no cycle

Phase 2: Find Cycle Start
- Reset slow pointer to head
- Keep fast at meeting point
- Move both at same speed (1 step)
- They will meet at cycle start

KEY INSIGHT: When slow and fast meet, the distance from head to cycle start
equals the distance from meeting point to cycle start (going around the cycle).

TIME COMPLEXITY: O(n)
--------------------
• Phase 1: O(n) - at most n iterations to detect cycle
• Phase 2: O(n) - at most n iterations to find cycle start
• Total: O(n) + O(n) = O(n)

SPACE COMPLEXITY: O(1)
-----------------------
• Only uses two pointers (slow and fast)
• No additional data structures
• Constant extra space regardless of list size

WHY TWO-PHASE ALGORITHM WORKS:
------------------------------
Mathematical proof:
Let μ = distance from head to cycle start
Let λ = length of cycle

When slow and fast meet:
- Slow has traveled: μ + kλ (entered cycle, completed k full cycles)
- Fast has traveled: μ + kλ + nλ (entered cycle, completed n full cycles)
- Since fast travels 2x distance: 2(μ + kλ) = μ + kλ + nλ
- Solving: μ = nλ - kλ = (n-k)λ

This means: distance from head to cycle start (μ) equals distance from
meeting point to cycle start ((n-k)λ) going around the cycle.

Therefore, when we reset slow to head and move both at same speed,
they will meet exactly at the cycle start!

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Find Cycle Length**
   - After detecting cycle, count steps until return to meeting point
   - Useful for problems requiring cycle length

2. **Remove Cycle**
   - Once cycle start is found, break the cycle
   - Set the last node's next to null

3. **Brent's Algorithm**
   - Alternative to Floyd's algorithm
   - Can find cycle length and start in one phase
   - Often faster in practice

4. **Hash Set Approach**
   - Store visited nodes in a set
   - First repeated node is cycle start
   - O(n) time, O(n) space

COMPARISON WITH OTHER APPROACHES:
----------------------------------
| Approach | Time | Space | Complexity | Use Case |
|----------|------|-------|------------|----------|
| Two-Phase Floyd | O(n) | O(1) | Optimal | Find cycle start |
| Hash Set | O(n) | O(n) | Simple | When space available |
| Marker/Flag | O(n) | O(1) | Modifies list | When modification allowed |
| Brent's Algorithm | O(n) | O(1) | Faster | Alternative to Floyd |

PRACTICAL APPLICATIONS:
-----------------------
• **Memory Leak Detection** - Find circular reference origins
• **Concurrent Systems** - Identify deadlock sources
• **Graph Algorithms** - Find cycle starts in graphs
• **Protocol Validation** - Locate loop entry points
• **Database Integrity** - Find circular dependency sources

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Not preserving fast pointer at meeting point (breaks Phase 2)
2. ❌ Moving fast pointer in Phase 2 (both should move at same speed)
3. ❌ Not handling null case (no cycle)
4. ❌ Using value comparison instead of reference equality
5. ❌ Forgetting to reset slow to head before Phase 2

TESTING STRATEGIES:
------------------
• Empty list: null → null
• Single node without cycle: [1] → null
• Single node with self-cycle: [1] where 1 points to 1 → [1]
• Two nodes without cycle: [1,2] → null
• Two nodes with cycle: [1,2] where 2 points to 1 → [1]
• Long list without cycle: [1,2,3,...,100] → null
• Long list with cycle: [1,2,3,...,50] where 50 points to 25 → [25]
• Cycle at head: [1,2,3] where 3 points to 1 → [1]
• Cycle at tail: [1,2,3] where 3 points to 2 → [2]

DEBUGGING TIPS:
--------------
1. Print slow and fast positions at each iteration
2. Verify they meet at the same node in Phase 1
3. Check that slow is reset to head in Phase 2
4. Ensure both move at same speed in Phase 2
5. Verify meeting point is the cycle start

FUTURE ENHANCEMENTS:
-------------------
1. Return cycle length along with start node
2. Count number of nodes before cycle
3. Return all nodes in the cycle
4. Support for doubly linked lists
5. Detect multiple cycles (if possible)

KEY LEARNING POINTS:
--------------------
• Two-phase algorithm elegantly solves cycle start detection
• Mathematical proof explains why the algorithm works
• Understanding Phase 1 is crucial for Phase 2
• Reference equality (==) is required for node comparison
• O(1) space solutions are possible with clever algorithms

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Find Cycle Length** - Return number of nodes in cycle
2. **Remove Cycle** - Break the cycle and return list
3. **Happy Number II** - Find cycle start in number sequence
4. **Duplicate Number II** - Find duplicate using cycle start
5. **Circular Array Loop** - Detect and find cycle in array

================================================================================
*/
