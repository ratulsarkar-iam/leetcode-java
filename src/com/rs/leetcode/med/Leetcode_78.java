package com.rs.leetcode.med;

import java.util.ArrayList;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Subsets
 * @TimeComplexity: O(n × 2^n)
 * @SpaceComplexity: O(n)
 * @Algorithm: Backtracking
 */

/**
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 *
 *
 * ============================================================================
 * BACKTRACKING EXPLANATION:
 * ============================================================================
 *
 * Core Idea: At each index i, make two binary choices:
 * 1. INCLUDE nums[i] in the current subset
 * 2. EXCLUDE nums[i] from the current subset
 *
 * Visual Recursion Tree (for nums = [1, 2, 3]):
 *
 *                            subsets([], i=0)
 *                           /              \
 *                include(1)                exclude(1)
 *                /        \                /        \
 *        subsets([1],i=1)  subsets([1],i=1)  subsets([],i=1)  subsets([],i=1)
 *         i=2              i=2              i=2              i=2
 *        /    \           /    \           /    \           /    \
 *   inc(2) exc(2)    inc(2) exc(2)    inc(2) exc(2)    inc(2) exc(2)
 *     |      |         |      |         |      |         |      |
 *  [1,2]   [1]      [1,2]   [1]      [2]    []      [2]    []
 *  i=3    i=3       i=3    i=3       i=3    i=3      i=3    i=3
 *   |      |         |      |         |      |         |      |
 * [1,2,3] [1,3]    [1,2,3] [1,3]    [2,3]  [3]     [2,3]  [3]
 *  (add)  (add)     (add)  (add)     (add)  (add)   (add)  (add)
 *
 * Step-by-step execution:
 * - Start with empty list, i=0
 * - For each element, we branch: include it OR exclude it
 * - When we include: add to list, recurse to next index
 * - When we exclude: remove last element (backtrack!), recurse to next index
 * - Base case (i == nums.length): we've made decisions for all elements → add subset to result
 *
 * WHY new ArrayList<>(list) IS CRITICAL:
 * - list is a SINGLE mutable object being shared across all recursive calls
 * - Without copying, all result entries point to the SAME object reference
 * - After backtracking, list becomes empty → all subsets in result become empty!
 * - Solution: Create a copy to preserve the current state independently
 *
 * TIME COMPLEXITY: O(n × 2^n)
 * - 2^n subsets: Each element has 2 choices (include/exclude), total subsets = 2^n
 * - O(n) per subset: Creating a copy of list takes O(n) time (avg subset size = n/2)
 * - Total: 2^n × n = O(n × 2^n)
 *
 * SPACE COMPLEXITY: O(n) auxiliary
 * - Recursion stack: Maximum depth = n
 * - Current list: At most n elements
 * - Output storage: O(n × 2^n) for the result (not counted in auxiliary space)
 *
 * 
 * 
 * 
 * Start: list = [], i = 0

Path 1 (include 1):  list = [1], i = 1
  → include 2:       list = [1,2], i = 2
    → include 3:     list = [1,2,3], i = 3 → BASE CASE → ADD [1,2,3]
    → exclude 3:     list = [1,2], i = 3   → BASE CASE → ADD [1,2]
  → exclude 2:       list = [1], i = 2
    → include 3:     list = [1,3], i = 3   → BASE CASE → ADD [1,3]
    → exclude 3:     list = [1], i = 3     → BASE CASE → ADD [1]

Path 2 (exclude 1):  list = [], i = 1
  → include 2:       list = [2], i = 2
    → include 3:     list = [2,3], i = 3   → BASE CASE → ADD [2,3]
    → exclude 3:     list = [2], i = 3     → BASE CASE → ADD [2]
  → exclude 2:       list = [], i = 2
    → include 3:     list = [3], i = 3     → BASE CASE → ADD [3]
    → exclude 3:     list = [], i = 3      → BASE CASE → ADD []

Final result: [[1,2,3], [1,2], [1,3], [1], [2,3], [2], [3], []]
 * 
 */
public class Leetcode_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        subsets(nums, list, 0, retList);
        return retList;
    }

//    public void subsets(int[] nums) {
//        List<Integer> list = new ArrayList<>();
//        subsets(nums, list, 0);

    /// /        return false;
//    }
    private void subsets(int[] nums, List<Integer> list, int i, List<List<Integer>> retList) {
        if (i == nums.length) {
            retList.add(new ArrayList<>(list));
            return;
        }
        // include
        list.add(nums[i]);
        subsets(nums, list, i + 1, retList);
        // exclude while back track the recursion
        list.remove(list.size() - 1);
        subsets(nums, list, i + 1, retList);
    }
}

