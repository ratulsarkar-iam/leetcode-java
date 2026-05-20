package com.rs.leetcode.easy;

/* @LeetcodeMeta
 * @Title: Reverse Linked List
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Iterative Three Pointers
 */

/**
 * ================================================================================
 * REVERSE LINKED LIST - ITERATIVE THREE POINTER APPROACH
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 19, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Reverse a singly linked list.
 * Given the head of a singly linked list, reverse the list and return
 * the reversed list's head.
 * <p>
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Explanation: The entire list is reversed
 * <p>
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * Explanation: Two nodes are swapped
 * <p>
 * Example 3:
 * Input: head = []
 * Output: []
 * Explanation: Empty list remains empty
 * <p>
 * <p>
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5000]
 * - -5000 ≤ Node.val ≤ 5000
 * <p>
 * ================================================================================
 * LINKED LIST STRUCTURE
 * ================================================================================
 * 
 * A singly linked list node consists of:
 * - val: The stored value
 * - next: Reference to the next node (or null for last node)
 * 
 * Original List:    1 → 2 → 3 → 4 → 5 → null
 *                  ^    ^    ^    ^    ^
 *                  |    |    |    |    |
 *                head  ...           ... tail
 * 
 * Reversed List:  null ← 1 ← 2 ← 3 ← 4 ← 5
 *                                    ^    ^
 *                                    |    |
 *                                  head  tail
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    reverseList(head)
 *                           |
 *                           v
 *                 Initialize prev=null, curr=head, next=null
 *                           |
 *                           v
 *                 While curr != null:
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 |    Store next node       |
 *                 |    next = curr.next      |
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 |    Reverse pointer       |
 *                 |    curr.next = prev      |
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 ┌─────────────────────────┐
 *                 |    Move pointers forward |
 *                 |    prev = curr           |
 *                 |    curr = next           |
 *                 └─────────────────────────┘
 *                           |
 *                           v
 *                 Return prev (new head)
 * <p>
 * ================================================================================
 * ITERATION PROCESS VISUALIZATION
 * ================================================================================
 * 
 * Reversing [1 → 2 → 3 → null]:
 * 
 * Initial State:
 * prev: null
 * curr: 1 → 2 → 3 → null
 * next: null
 * 
 * Iteration 1:
 * - next = curr.next = 2 → 3 → null
 * - curr.next = prev = null
 * - prev = curr = 1
 * - curr = next = 2 → 3 → null
 * 
 * List after iteration 1:
 * null ← 1    2 → 3 → null
 *   ^         ^
 *   |         |
 * prev      curr
 * 
 * Iteration 2:
 * - next = curr.next = 3 → null
 * - curr.next = prev = 1
 * - prev = curr = 2
 * - curr = next = 3 → null
 * 
 * List after iteration 2:
 * null ← 1 ← 2    3 → null
 *   ^              ^
 *   |              |
 * prev           curr
 * 
 * Iteration 3:
 * - next = curr.next = null
 * - curr.next = prev = 2
 * - prev = curr = 3
 * - curr = next = null
 * 
 * List after iteration 3:
 * null ← 1 ← 2 ← 3    null
 *   ^                   ^
 *   |                   |
 * prev                curr
 * 
 * Loop ends (curr == null)
 * Return prev = 3 (new head)
 * Final list: 3 → 2 → 1 → null
 * <p>
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing reverseList([10 → 20 → 30]):
 * 
 * Start:
 * prev = null
 * curr = 10 → 20 → 30
 * 
 * Loop 1:
 * - next = 20 → 30
 * - 10.next = null (10 now points to prev)
 * - prev = 10
 * - curr = 20 → 30
 * State: null ← 10    20 → 30
 * 
 * Loop 2:
 * - next = 30
 * - 20.next = 10 (20 now points to prev)
 * - prev = 20
 * - curr = 30
 * State: null ← 10 ← 20    30
 * 
 * Loop 3:
 * - next = null
 * - 30.next = 20 (30 now points to prev)
 * - prev = 30
 * - curr = null
 * State: null ← 10 ← 20 ← 30
 * 
 * End:
 * Return prev = 30
 * Result: 30 → 20 → 10 → null
 * 
 * ================================================================================
 */
public class Leetcode_206 {
    /**
     * Reverses a singly linked list iteratively using three pointers
     * 
     * @param head The head of the linked list to be reversed
     * @return The new head of the reversed linked list
     * 
     * Time: O(n) - visits each node exactly once
     * Space: O(1) - only uses three extra pointers
     * 
     * ALGORITHM: Three-pointer technique
     * 1. prev - tracks the previous node (starts as null)
     * 2. curr - tracks the current node (starts as head)
     * 3. next - temporarily stores the next node
     * 
     * In each iteration:
     * - Store next node
     * - Reverse current node's pointer
     * - Move pointers forward
     */
    public ListNode reverseList(ListNode head) {
        // prev will become the new head (starts as null)
        ListNode prev = null;
        
        // curr is the node we're currently processing
        ListNode curr = head;
        
        // next temporarily stores the next node
        ListNode next = null;
        
        // Traverse the list, reversing pointers as we go
        while (curr != null) {
            // STEP 1: Save the next node before we overwrite curr.next
            next = curr.next;
            
            // STEP 2: Reverse the pointer of current node
            curr.next = prev;
            
            // STEP 3: Move pointers one position forward
            prev = curr;  // prev becomes current node
            curr = next;  // curr becomes next node
        }
        
        // When loop ends, prev is at the new head
        return prev;
    }

    /**
     * Definition for a singly-linked list node.
     * This inner class represents a node in the linked list structure.
     */
    public class ListNode {
        /** The value stored in this node */
        int val;
        
        /** Reference to the next node in the list (null if last) */
        public ListNode next;

        /**
         * Default constructor - creates an empty node
         * val defaults to 0, next defaults to null
         */
        ListNode() {
        }

        /**
         * Constructor to create a node with a specific value
         * 
         * @param val The integer value to store in this node
         */
        ListNode(int val) {
            this.val = val;
        }

        /**
         * Constructor to create a node with value and next pointer
         * 
         * @param val The integer value to store in this node
         * @param next Reference to the next node in the list
         */
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
