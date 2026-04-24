package com.rs.leetcode.med;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Permutations
 * @TimeComplexity: O(n!*n)
 * @SpaceComplexity: O(n!+n)
 * @Algorithm: Backtracking
 */

/**
 * Author: Ratul Sarkar
 * Date:4/24/26
 * <p>
 * <p>
 * <p>
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Leetcode_46 {
    public List<List<Integer>> permute (int[] nums) {
        List<List<Integer>> retList = new ArrayList<> ();
        List<Integer> pList = new ArrayList<> ();
        for (int num : nums) {
            pList.add (num);
        }

        permute (pList, 0, retList);
        return retList;
    }

    private void permute (List<Integer> pList, int idx, List<List<Integer>> retList) {
        if (idx == pList.size ()) {
            retList.add (new ArrayList<> (pList));
            return;
        }

        for (int i = idx; i < pList.size (); i++) {
            Collections.swap (pList, idx, i);
            permute (pList, idx + 1, retList);
            Collections.swap (pList, idx, i);

        }
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Permutations (Backtracking)
================================================================================

PROBLEM: Generate all permutations of distinct integers [1,2,3,...]

APPROACH: Swap-based Backtracking
----------------------------------
1. Convert array to ArrayList for in-place swaps
2. Use recursive backtracking with index parameter
3. At each level, fix position 'idx' by swapping with all possible elements

BACKTRACKING PATTERN: Choose → Explore → Unchoose
----------------------------------------------------
CHOOSE:  Collections.swap(pList, idx, i)  // Place element at position idx
EXPLORE: permute(pList, idx + 1, retList) // Recurse for next position
UNCHOOSE: Collections.swap(pList, idx, i) // Restore original order

VISUALIZATION for [1,2,3]:

RECURSIVE TREE (shows call stack and permutation building):
permute([1,2,3], idx=0)
├─ i=0: swap(0,0) → [1,2,3]
│  └─ permute([1,2,3], idx=1)
│     ├─ i=1: swap(1,1) → [1,2,3]
│     │  └─ permute([1,2,3], idx=2)
│     │     └─ idx==size: ADD [1,2,3] ✓
│     │  swap(1,1) → [1,2,3] (backtrack)
│     └─ i=2: swap(1,2) → [1,3,2]
│        └─ permute([1,3,2], idx=2)
│           └─ idx==size: ADD [1,3,2] ✓
│        swap(1,2) → [1,2,3] (backtrack)
│  swap(0,0) → [1,2,3] (backtrack)
├─ i=1: swap(0,1) → [2,1,3]
│  └─ permute([2,1,3], idx=1)
│     ├─ i=1: swap(1,1) → [2,1,3]
│     │  └─ permute([2,1,3], idx=2)
│     │     └─ idx==size: ADD [2,1,3] ✓
│     │  swap(1,1) → [2,1,3] (backtrack)
│     └─ i=2: swap(1,2) → [2,3,1]
│        └─ permute([2,3,1], idx=2)
│           └─ idx==size: ADD [2,3,1] ✓
│        swap(1,2) → [2,1,3] (backtrack)
│  swap(0,1) → [1,2,3] (backtrack)
└─ i=2: swap(0,2) → [3,2,1]
   └─ permute([3,2,1], idx=1)
      ├─ i=1: swap(1,1) → [3,2,1]
      │  └─ permute([3,2,1], idx=2)
      │     └─ idx==size: ADD [3,2,1] ✓
      │  swap(1,1) → [3,2,1] (backtrack)
      └─ i=2: swap(1,2) → [3,1,2]
         └─ permute([3,1,2], idx=2)
            └─ idx==size: ADD [3,1,2] ✓
         swap(1,2) → [3,2,1] (backtrack)
   swap(0,2) → [1,2,3] (backtrack)

SIMPLIFIED VIEW (shows permutation progression):
Level 0 (idx=0): Fix first position
├─ Swap(0,0): [1,2,3] → Level 1 (idx=1): Fix second position
│  ├─ Swap(1,1): [1,2,3] → Level 2: Base case → [1,2,3]
│  └─ Swap(1,2): [1,3,2] → Level 2: Base case → [1,3,2]
├─ Swap(0,1): [2,1,3] → Level 1 (idx=1): Fix second position
│  ├─ Swap(1,1): [2,1,3] → Level 2: Base case → [2,1,3]
│  └─ Swap(1,2): [2,3,1] → Level 2: Base case → [2,3,1]
└─ Swap(0,2): [3,2,1] → Level 1 (idx=1): Fix second position
   ├─ Swap(1,1): [3,2,1] → Level 2: Base case → [3,2,1]
   └─ Swap(1,2): [3,1,2] → Level 2: Base case → [3,1,2]

KEY INSIGHTS:
-----------
• Swapping is O(1) and avoids extra space for "used" tracking
• The second swap (backtrack) is CRUCIAL - it restores state for other branches
• Deep copy (new ArrayList) needed when adding to results to avoid reference issues
• Recursion depth = n (array length)

TIME COMPLEXITY: O(n! × n)
---------------------------
• n! permutations to generate
• Each permutation requires n recursive calls
• Copying each permutation takes O(n)
• Total: n! × O(n) = O(n! × n)

SPACE COMPLEXITY: O(n! + n)
----------------------------
• Output: O(n! × n) to store all permutations
• Recursion stack: O(n) maximum depth
• Working list: O(n) for current permutation
• Total: O(n! × n) (output dominates)

ALTERNATIVE APPROACHES:
----------------------
1. Used array + boolean visited (more intuitive, O(n) extra space)
2. Next Permutation algorithm (generate lexicographically)
3. Heap's Algorithm (different swapping pattern)

WHY THIS WORKS:
--------------
Backtracking explores ALL possibilities by:
1. Making a choice (placing an element at current position)
2. Recursively solving the subproblem (remaining positions)
3. Undoing the choice to explore other possibilities

The swap mechanism ensures each element gets a chance to be at each position,
generating all n! arrangements systematically.
================================================================================
*/
