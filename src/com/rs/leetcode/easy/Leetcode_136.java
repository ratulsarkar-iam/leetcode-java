package com.rs.leetcode.easy;

/* @LeetcodeMeta
 * @Title: Single Number
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Bit Manipulation (XOR)
 */

/**
 * ================================================================================
 * SINGLE NUMBER - XOR BIT MANIPULATION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 8, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Given a non-empty array where every element appears twice
 * except for one element that appears exactly once, find that single element.
 * <p>
 * The solution must use linear runtime and constant extra space.
 * <p>
 * <p>
 * Example 1:
 * Input: [2, 2, 1]
 * Output: 1
 * Explanation: 2 appears twice, 1 appears once
 * <p>
 * Example 2:
 * Input: [4, 1, 2, 1, 2]
 * Output: 4
 * Explanation: 1 and 2 appear twice, 4 appears once
 * <p>
 * Example 3:
 * Input: [1]
 * Output: 1
 * Explanation: Single element array
 * <p>
 * <p>
 * Constraints:
 * - 1 ≤ nums.length ≤ 3 × 10⁴
 * - -3 × 10⁴ ≤ nums[i] ≤ 3 × 10⁴
 * - Exactly one element appears once, all others appear twice
 * <p>
 * ================================================================================
 * XOR PROPERTIES
 * ================================================================================
 * 
 * The XOR (^) operator has special properties that make it perfect for this problem:
 * 
 * 1. x ^ x = 0 (Any number XORed with itself is 0)
 *    Example: 5 ^ 5 = 0
 *           Binary: 101 ^ 101 = 000
 * 
 * 2. x ^ 0 = x (Any number XORed with 0 is itself)
 *    Example: 5 ^ 0 = 5
 *           Binary: 101 ^ 000 = 101
 * 
 * 3. x ^ y = y ^ x (Commutative property)
 *    Order doesn't matter: 5 ^ 3 = 3 ^ 5
 * 
 * 4. (x ^ y) ^ z = x ^ (y ^ z) (Associative property)
 *    Grouping doesn't matter
 * 
 * 5. XOR is a bitwise operation that compares each bit:
 *    - 0 ^ 0 = 0
 *    - 0 ^ 1 = 1
 *    - 1 ^ 0 = 1
 *    - 1 ^ 1 = 0
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    singleNumber(nums)
 *                           |
 *                           v
 *                 Initialize result = 0
 *                           |
 *                           v
 *                 For each number in nums:
 *                           |
 *                           v
 *                 result = result ^ number
 *                           |
 *                           v
 *                 After processing all numbers
 *                           |
 *                           v
 *                     Return result
 * 
 * ================================================================================
 * XOR PROCESS VISUALIZATION
 * ================================================================================
 * 
 * Example with nums = [4, 1, 2, 1, 2]:
 * 
 * Step 1: result = 0 ^ 4 = 4
 *          0000 ^ 0100 = 0100 (4)
 * 
 * Step 2: result = 4 ^ 1 = 5
 *          0100 ^ 0001 = 0101 (5)
 * 
 * Step 3: result = 5 ^ 2 = 7
 *          0101 ^ 0010 = 0111 (7)
 * 
 * Step 4: result = 7 ^ 1 = 6
 *          0111 ^ 0001 = 0110 (6)
 * 
 * Step 5: result = 6 ^ 2 = 4
 *          0110 ^ 0010 = 0100 (4)
 * 
 * Final result: 4 (the single number)
 * 
 * Notice how pairs cancel out:
 * - 1 appears twice: ... ^ 1 ^ 1 ^ ... = ... ^ 0 ^ ...
 * - 2 appears twice: ... ^ 2 ^ 2 ^ ... = ... ^ 0 ^ ...
 * - 4 appears once: remains in final result
 * <p>
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing singleNumber([2, 2, 1]):
 * 
 * Initial: result = 0
 * 
 * Iteration 1: num = 2
 * - result = 0 ^ 2 = 2
 * - Binary: 000 ^ 010 = 010
 * 
 * Iteration 2: num = 2
 * - result = 2 ^ 2 = 0
 * - Binary: 010 ^ 010 = 000
 * - First pair cancels out!
 * 
 * Iteration 3: num = 1
 * - result = 0 ^ 1 = 1
 * - Binary: 000 ^ 001 = 001
 * 
 * Return: 1 (the single number)
 * 
 * ================================================================================
 */
public class Leetcode_136 {
    /**
     * Finds the single number in an array where every other element appears twice
     * 
     * @param nums Array of integers where exactly one element appears once
     * @return The single element that appears only once
     * 
     * Time: O(n) - single pass through the array
     * Space: O(1) - only using a single integer variable
     * 
     * ALGORITHM: XOR all numbers together
     * - Pairs cancel out (x ^ x = 0)
     * - Single number remains (0 ^ x = x)
     */
    public int singleNumber(int[] nums) {
        // Initialize result to 0 (identity element for XOR)
        int singleNum = 0;
        
        // XOR all numbers in the array
        for (int i : nums) {
            singleNum = singleNum ^ i;
        }
        
        // After processing, singleNum contains the unique element
        return singleNum;
    }
}
