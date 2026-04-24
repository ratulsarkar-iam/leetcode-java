package com.rs.leetcode.med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Subsets II
 * @TimeComplexity: O(n × 2^n)
 * @SpaceComplexity: O(n) auxiliary
 * @Algorithm: Sorting
 */

/**
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * */
public class Leetcode_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> retList=new ArrayList<>();
        List<Integer> list= new ArrayList<>();
        subsetsWithDup(nums,list,0,retList);
        return retList;
    }

    private void subsetsWithDup(int[] nums, List<Integer> list, int i, List<List<Integer>> retList){
        if(i==nums.length){
            retList.add(new ArrayList<>(list));
            return;
        }
        //include
        list.add(nums[i]);
        subsetsWithDup(nums,list,i+1,retList);
        //////exclude
        list.remove(list.size()-1);
        // duplicate check
        int idx=i+1;
        while (idx<nums.length && nums[idx]==nums[idx-1]){
            idx=idx+1;
        }
        subsetsWithDup(nums,list,idx,retList);
    }

    /*
    ============================================================================
    BACKTRACKING WITH DUPLICATES - LOGIC BREAKDOWN:
    ============================================================================

    PROBLEM DIFFERENCE FROM Leetcode_78:
    - Leetcode_78: Array has unique elements → all subsets are unique
    - Leetcode_90: Array may have duplicates → need to avoid duplicate subsets

    KEY CHANGES FROM Subsets I:

    1. SORTING (Line 32): Arrays.sort(nums)
       Purpose: Groups duplicate elements together so we can identify and skip them.
       Example: [2,1,2] → [1,2,2] (duplicates are now adjacent)

    2. DUPLICATE HANDLING IN EXCLUDE BRANCH (Lines 50-54):
       When we decide to EXCLUDE an element, we skip ALL its duplicates to prevent
       generating the same subset multiple times.

    HOW DUPLICATE SKIPPING WORKS:

    Without skipping (WRONG) for nums = [1,2,2]:
      At i=1 (first 2):
        - Include first 2, exclude second 2 → [1,2]
        - Exclude first 2, include second 2 → [1,2]  ← DUPLICATE!

    With skipping (CORRECT):
      At i=1 (first 2):
        - Include first 2 → recurse to i=2 (second 2)
        - Exclude first 2 → skip to i=3 (past all 2s) → no chance to include second 2

    VISUAL RECURSION TREE (nums = [1,2,2]):

                            subsets([], i=0)
                           /              \
                include(1)                exclude(1)
                /        \                /        \
        subsets([1],i=1)  subsets([1],i=1)  subsets([],i=1)  subsets([],i=1)
         i=2              i=2              i=2              i=2
        /    \           /    \           /    \           /    \
   inc(2) exc(2)    inc(2) exc(2)    inc(2) exc(2)    inc(2) exc(2)
     |      |         |      |         |      |         |      |
  [1,2]   [1]      [1,2]   [1]      [2]    []      [2]    []
  i=2    i=3       i=2    i=3       i=2    i=3      i=2    i=3
   |      |         |      |         |      |         |      |
 [1,2,2] [1,2]    [1,2,2] [1,2]    [2,2]  [2]     [2,2]  [2]
  (add)  (add)     (add)  (add)     (add)  (add)   (add)  (add)

    Key: When excluding at i=1 (first 2), we skip to i=3 (past second 2)

    STEP-BY-STEP EXECUTION (nums = [1,2,2]):

    Sorted array: [1, 2, 2]

    Start: list = [], i = 0

    Path 1 (include 1): list = [1], i = 1
      → include first 2: list = [1,2], i = 2
        → include second 2: list = [1,2,2], i = 3 → BASE CASE → ADD [1,2,2]
        → exclude second 2: list = [1,2], i = 3 → BASE CASE → ADD [1,2]
      → exclude first 2: list = [1], skip to i = 3 (past all 2s)
        → BASE CASE → ADD [1]

    Path 2 (exclude 1): list = [], i = 1
      → include first 2: list = [2], i = 2
        → include second 2: list = [2,2], i = 3 → BASE CASE → ADD [2,2]
        → exclude second 2: list = [2], i = 3 → BASE CASE → ADD [2]
      → exclude first 2: list = [], skip to i = 3 (past all 2s)
        → BASE CASE → ADD []

    Final result: [[1,2,2], [1,2], [1], [2,2], [2], []]

    WHY THIS PREVENTS DUPLICATES:

    The insight: If we have [2,2], the subsets involving 2s are:
    - [] (no 2s)
    - [2] (one 2)
    - [2,2] (both 2s)

    If we treat each 2 independently, we'd generate [2] twice:
    - Include first 2, exclude second 2 → [2]
    - Exclude first 2, include second 2 → [2] ← duplicate!

    Solution: When we decide to exclude a value, exclude ALL its duplicates.
    This means either we include k copies of a value (for k from 0 to count),
    or we exclude all of them at once.

    TIME COMPLEXITY: O(n × 2^n)

    - Sorting: O(n log n)
    - Total subsets: Still 2^n in worst case (all unique elements)
    - Per subset: O(n) to copy the list
    - Overall: O(n log n + n × 2^n) = O(n × 2^n)

    The duplicate check while loop doesn't change asymptotic complexity because:
    - Each element is skipped at most once total
    - Total work from all skips = O(n)

    SPACE COMPLEXITY: O(n) auxiliary

    - Recursion stack: Maximum depth = n
    - Current list: At most n elements
    - Output storage: O(n × 2^n) for the result (not counted in auxiliary space)

    KEY PATTERN FOR HANDLING DUPLICATES IN BACKTRACKING:
    1. SORT the array first
    2. INCLUDE branch: proceed normally
    3. EXCLUDE branch: skip all consecutive duplicates before recursing

    This pattern applies to many backtracking problems with duplicates
    (permutations, combinations, etc.).

    ============================================================================
    DIFFERENCE FROM Leetcode_78 (Subsets I):
    ============================================================================

    Leetcode_78 handles arrays with UNIQUE elements, while Leetcode_90 handles
    arrays that may contain DUPLICATES. The core backtracking structure is the
    same, but Leetcode_90 adds two critical modifications:

    1. SORTING: Arrays.sort(nums) is called first to group duplicates together.
       Leetcode_78 doesn't need sorting since all elements are unique.

    2. DUPLICATE SKIPPING: In the exclude branch, instead of simply recursing
       with i+1, Leetcode_90 uses a while loop to skip ALL consecutive duplicates
       before recursing. This ensures that if we decide to exclude a value,
       we exclude all its copies, preventing duplicate subsets.

    Example: For [1,2,2]
    - Leetcode_78 would generate: [[],[1],[2],[1,2],[2],[1,2],[2,2],[1,2,2]]
      (has duplicates: [2] and [1,2] appear twice)
    - Leetcode_90 generates: [[],[1],[1,2],[1,2,2],[2],[2,2]]
      (no duplicates)

    The time and space complexity remain the same asymptotically (O(n × 2^n)
    time and O(n) auxiliary space), but Leetcode_90 has an additional O(n log n)
    cost for sorting.
    */
}
