package com.rs.leetcode.extra;

/**
 * Definition for singly-linked list.
 * 
 * <p>A linked list is a linear data structure where elements are stored in nodes.
 * Each node contains:
 * <ul>
 *   <li><b>val</b>: The data/value stored in the node</li>
 *   <li><b>next</b>: A reference (pointer) to the next node in the sequence</li>
 * </ul>
 * 
 * <p><b>Key Characteristics:</b>
 * <ul>
 *   <li>Dynamic size: Can grow or shrink at runtime</li>
 *   <li>Efficient insertion/deletion: O(1) at known positions</li>
 *   <li>No random access: Must traverse from head to reach specific nodes</li>
 *   <li>Memory efficient: Only allocates memory as needed</li>
 * </ul>
 * 
 * <p><b>Common Operations:</b>
 * <ul>
 *   <li>Traversal: Visit each node sequentially</li>
 *   <li>Insertion: Add nodes at beginning, end, or middle</li>
 *   <li>Deletion: Remove nodes from any position</li>
 *   <li>Reversal: Reverse the direction of pointers</li>
 *   <li>Cycle detection: Detect if list has a loop</li>
 * </ul>
 * 
 * <p><b>Time Complexity:</b>
 * <ul>
 *   <li>Access by index: O(n)</li>
 *   <li>Insert at head: O(1)</li>
 *   <li>Insert at tail: O(n) without tail pointer, O(1) with tail pointer</li>
 *   <li>Delete at head: O(1)</li>
 *   <li>Delete at tail: O(n)</li>
 *   <li>Search: O(n)</li>
 * </ul>
 * 
 * <p><b>Space Complexity:</b> O(n) where n is the number of nodes
 * 
 * <p><b>Example Usage:</b>
 * <pre>{@code
 * // Create nodes
 * ListNode node1 = new ListNode(1);
 * ListNode node2 = new ListNode(2);
 * ListNode node3 = new ListNode(3);
 * 
 * // Link nodes together
 * node1.next = node2;
 * node2.next = node3;
 * 
 * // node1 is now the head of the list: 1 -> 2 -> 3 -> null
 * 
 * // Alternative: create list using constructor
 * ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
 * }</pre>
 */
public class ListNode {
    /**
     * The value stored in this node.
     * Can be any integer value depending on the problem requirements.
     */
    public int val;
    
    /**
     * Reference to the next node in the linked list.
     * If this is the last node, next will be null.
     */
    public ListNode next;
    
    /**
     * Default constructor.
     * Creates a node with val = 0 and next = null.
     */
    public ListNode() {
        this.val = 0;
        this.next = null;
    }
    
    /**
     * Constructor with value only.
     * Creates a node with the specified value and next = null.
     * 
     * @param val The value to store in this node
     */
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
    
    /**
     * Constructor with value and next node.
     * Creates a node with the specified value and next pointer.
     * 
     * @param val The value to store in this node
     * @param next Reference to the next node in the list
     */
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    
    /**
     * Utility method to print the entire linked list starting from this node.
     * Useful for debugging and visualization.
     * 
     * @return String representation of the linked list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }else{
                sb.append (" -> NULL");
            }
            current = current.next;
        }
        return sb.toString();
    }
    
    /**
     * Utility method to get the length of the linked list starting from this node.
     * 
     * @return The number of nodes in the list
     */
    public int length() {
        int count = 0;
        ListNode current = this;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
