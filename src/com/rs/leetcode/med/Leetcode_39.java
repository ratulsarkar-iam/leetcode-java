package com.rs.leetcode.med;

import java.util.ArrayList;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Combination Sum
 * @TimeComplexity: O(2^t)
 * @SpaceComplexity: O(t/min(candidates))
 * @Algorithm: Backtracking
 */

/**
 * ================================================================================
 * COMBINATION SUM - BACKTRACKING SOLUTION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 3, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Given an array of distinct integers candidates and a target integer 
 * target, return a list of all unique combinations of candidates where the chosen 
 * numbers sum to target.
 * <p>
 * KEY CONSTRAINTS:
 * - The same number may be chosen from candidates an unlimited number of times
 * - All elements of candidates are distinct
 * - Two combinations are unique if the frequency of at least one chosen number differs
 * <p>
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * - 2 + 2 + 3 = 7 (2 used multiple times)
 * - 7 = 7 (single element)
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: [] (no combination possible)
 * <p>
 * <p>
 * Constraints:
 * - 1 <= candidates.length <= 30
 * - 2 <= candidates[i] <= 40
 * - All elements of candidates are distinct
 * - 1 <= target <= 40
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    combinationSum(candidates, target)
 *                           |
 *                           v
 *                    Initialize retList = []
 *                    Initialize sumList = []
 *                           |
 *                           v
 *                    combiSum(candidates, 0, target, sumList, retList)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      i == candidates.length OR      target == 0?
 *      target < 0?                           |
 *            |                          |
 *      YES ──→ return                 YES ──→ Add copy of sumList
 *            |                          |        to retList
 *           EXIT                         |
 *                                        v
 *            NO                         EXIT
 *            |
 *            v
 *    ┌─────────────────────────────────┐
 *    |    TRY TWO CHOICES               |
 *    |    at index i                    |
 *    |                                 |
 *    |  1. INCLUDE candidates[i]        |
 *    |     (can reuse same element)     |
 *    |  2. EXCLUDE candidates[i]        |
 *    |     (move to next element)       |
 *    └─────────────────────────────────┘
 *            |
 *            v
 *   INCLUDE PATH:
 *   ┌─────────────────────────────────┐
 *   |  sumList.add(candidates[i])      |
 *   |  combiSum(i, target-candidates[i])|
 *   |  sumList.remove() // backtrack   |
 *   └─────────────────────────────────┘
 *            |
 *            v
 *   EXCLUDE PATH:
 *   ┌─────────────────────────────────┐
 *   |  combiSum(i+1, target)           |
 *   └─────────────────────────────────┘
 *            |
 *            v
 *          return
 * 
 * ================================================================================
 * BACKTRACKING PATTERN
 * ================================================================================
 * 
 * The algorithm follows the classic backtracking pattern:
 * 
 * 1. CHOOSE: Add current candidate to combination
 *    sumList.add(candidates[i])
 * 
 * 2. EXPLORE: Try to find combinations with this choice
 *    - IMPORTANT: Pass same index 'i' (not i+1) to allow reuse!
 *    - combiSum(i, target - candidates[i])
 * 
 * 3. UNCHOOSE: Remove current candidate (backtrack)
 *    sumList.remove(sumList.size() - 1)
 * 
 * 4. TRY ALTERNATIVE: Exclude current candidate
 *    - combiSum(i+1, target) (move to next element)
 * 
 * KEY INSIGHT: By trying include BEFORE exclude, we generate combinations
 * in a systematic order without duplicates!
 * 
 * ================================================================================
 * DECISION TREE VISUALIZATION
 * ================================================================================
 * 
 * For candidates = [2,3], target = 5:
 * 
 *                    combiSum(0, 5)
 *                           |
 *                  /         \
 *                 /           \
 *          Include 2        Exclude 2
 *          (i=0, t=3)        (i=1, t=5)
 *              |               |
 *            / | \              |
 *           /  |  \             |
 * Single 2 Multi 2          Include 3
 * Include Include          (i=1, t=2)
 * (i=1, t=3) (i=0, t=3)        |
 *     |        |              / | \
 *     |        |             /  |  \
 * Single 2 Multi 2     Single 3 Multi 3 Return
 * Include Include     Include Include (i=2, t=2)
 * (i=2, t=1) (i=1, t=1) (i=2, t=-1)(i=1, t=-1)
 *     |        |              |        |
 * Return   Single 2         Return    Return
 * (i=2)   Include
 *         (i=2, t=-1)
 *             |
 *           Return
 * 
 * Valid combinations found: [] (Note: This flow shows the structure,
 * actual combinations like [2,3] would be found through different paths)
 * 
 * CORRECTED FLOW for finding [2,3]:
 * 
 * combiSum(0,5) → Include 2 → Multi Include 2 → Include 3 → Target=0 → [2,3] found!
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing candidates = [2,3], target = 7:
 * 
 * Step 1: combiSum(0, 7, [], [])
 * - Include 2: sumList=[2], combiSum(0, 5)
 * 
 * Step 2: combiSum(0, 5, [2], [])
 * - Include 2: sumList=[2,2], combiSum(0, 3)
 * 
 * Step 3: combiSum(0, 3, [2,2], [])
 * - Include 2: sumList=[2,2,2], combiSum(0, 1)
 * 
 * Step 4: combiSum(0, 1, [2,2,2], [])
 * - Include 2: sumList=[2,2,2,2], combiSum(0, -1)
 * - Return (target < 0)
 * - Backtrack: sumList=[2,2,2]
 * - Exclude 2: combiSum(1, 3)
 * 
 * Step 5: combiSum(1, 3, [2,2,2], [])
 * - Include 3: sumList=[2,2,2,3], combiSum(1, 0)
 * - target == 0! Add [2,2,2,3] to results
 * - Backtrack: sumList=[2,2,2]
 * - Exclude 3: combiSum(2, 3) → Return (i == length)
 * 
 * Step 6: Backtrack to Step 3
 * - sumList=[2,2], Exclude 2: combiSum(1, 1)
 * - ... continues until all combinations found
 * 
 * Final result: [[2,2,3], [7]]
 * 
 * ================================================================================
 */
public class Leetcode_39 {
    /**
     * Main entry point - finds all unique combinations that sum to target
     * 
     * @param candidates Array of distinct positive integers
     * @param target Target sum to achieve
     * @return List of all unique combinations (order doesn't matter)
     * 
     * Time: O(2^t) where t = target/min(candidates)
     * Space: O(t/min(candidates)) for recursion stack
     * 
     * KEY: Each candidate can be used unlimited times!
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // List to store all valid combinations found
        List<List<Integer>> retList = new ArrayList<>();
        
        // Temporary list to build current combination
        List<Integer> sumList = new ArrayList<>();
        
        // Start backtracking from index 0
        combiSum(candidates, 0, target, sumList, retList);
        
        return retList;
    }

    /**
     * RECURSIVE BACKTRACKING METHOD
     * Explores all combinations by making include/exclude decisions at each index
     * 
     * @param candidates Input array of numbers
     * @param i Current index being considered
     * @param target Remaining target sum to achieve
     * @param sumList Current combination being built
     * @param retList List to store all valid combinations
     * 
     * BACKTRACKING PATTERN:
     * 1. BASE CASES: Check termination conditions
     * 2. INCLUDE: Add current candidate
     *    - First try single inclusion (i+1) - use element once
     *    - Then try multi inclusion (i) - allow unlimited reuse
     * 3. BACKTRACK: Remove candidate after exploring
     * 4. EXCLUDE: Skip current candidate and move to next index
     * 
     * NOTE: Duplicate check added to ensure unique combinations
     * CRITICAL: Two recursive calls after include handle both use-once and unlimited-reuse cases
     */
    private void combiSum(int[] candidates, int i, int target, 
                         List<Integer> sumList, List<List<Integer>> retList) {
        
        // BASE CASE 1: No more candidates or target became negative
        if (i == candidates.length || target < 0) {
            return;
        }
        
        // BASE CASE 2: Target achieved - valid combination found!
        if (target == 0) {
            // IMPORTANT: Create new ArrayList to avoid reference issues
            // NOTE: Added duplicate check to ensure uniqueness
            if(!retList.contains(sumList)) {
                retList.add(new ArrayList<>(sumList));
            }
            return;
        }
        
        // DECISION 1: INCLUDE current candidate
        // Add to current combination
        sumList.add(candidates[i]);
        
        // RECURSE with i+1 for single inclusion (use each element at most once)
        combiSum(candidates, i + 1, target - candidates[i], sumList, retList);
        
        // RECURSE with same index 'i' for multi inclusion (allow unlimited reuse)
        // Target reduced by the chosen candidate's value
        combiSum(candidates, i, target - candidates[i], sumList, retList);
        
        // BACKTRACK: Remove candidate to try other possibilities
        sumList.remove(sumList.size() - 1);
        
        // DECISION 2: EXCLUDE current candidate
        // Move to next index without using current candidate
        combiSum(candidates, i + 1, target, sumList, retList);
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Combination Sum (Backtracking)
================================================================================

PROBLEM: Find all unique combinations of numbers from an array that sum to target,
         where each number can be used unlimited times.

APPROACH: Backtracking with Include/Exclude Decisions
------------------------------------------------------
1. At each index, make two choices:
   - INCLUDE the number (stay at same index to allow reuse)
   - EXCLUDE the number (move to next index)
2. When target becomes 0, we've found a valid combination
3. When target becomes negative or we run out of numbers, backtrack

KEY INSIGHT: By always trying INCLUDE before EXCLUDE, we systematically
generate all combinations without duplicates!

TIME COMPLEXITY: O(3^t) where t = target/min(candidates)
-----------------------
• At each step, we have 3 recursive paths:
  1. Single include (i+1) - use element once
  2. Multi include (i) - allow unlimited reuse
  3. Exclude (i+1) - skip current element
• Maximum depth = target/min(candidates) (all smallest numbers)
• Total nodes in decision tree = 3^depth = 3^t
• Each valid combination requires O(k) time to copy, where k = combination length
• Overall: O(3^t + k×3^t) dominated by O(3^t)

Detailed breakdown:
- For target = 40 and min(candidate) = 2: t = 20
- Worst case: 3^20 ≈ 3.5 billion recursive calls
- Each call does O(1) work (comparisons, additions)
- Actual runtime is much better due to early pruning
- The duplicate check adds O(n) per found combination where n = retList.size()

SPACE COMPLEXITY: O(t/min(candidates))
------------------------
• Recursion stack depth: O(t/min(candidates))
• Current combination list: O(t/min(candidates))
• Output storage: O(k×number_of_combinations)
• Total auxiliary space: O(t/min(candidates))

For target = 40 and min(candidate) = 2:
- Stack depth: O(20)
- Combination list: O(20)
- Total auxiliary: O(20) - very efficient!

NOTE: The three-way branching increases time complexity from O(2^t) to O(3^t),
but allows more flexibility in combination generation.

WHY THIS WORKS:
--------------
The algorithm explores the entire solution space systematically:
1. **Include path** explores all combinations using current number
2. **Exclude path** explores combinations without current number
3. **Backtracking** ensures we return to try other possibilities
4. **Index control** (same index for include, next for exclude) prevents duplicates

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Sort candidates** - Try smaller numbers first for early pruning
2. **Early termination** - Stop if current number > remaining target
3. **Dynamic Programming** - For counting combinations (not listing them)
4. **Memoization** - Cache results for (index, target) pairs
5. **Iterative DP** - Bottom-up approach for large inputs

COMPARISON WITH OTHER APPROACHES:
--------------------------------
| Approach | Time | Space | Handles Duplicates | Finds All |
|----------|------|-------|-------------------|-----------|
| Backtracking | O(2^t) | O(t) | Yes | Yes |
| DP Counting | O(n×t) | O(t) | Yes | No (count only) |
| BFS | O(2^t) | O(2^t) | Yes | Yes |
| Iterative | O(2^t) | O(t) | Yes | Yes |

PRACTICAL APPLICATIONS:
-----------------------
• **Coin change problems** - Finding ways to make change
• **Portfolio allocation** - Investment combination strategies
• **Resource allocation** - Ways to distribute resources
• **Menu planning** - Meal combinations within budget
• **Game mechanics** - Item combination systems

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Using i+1 for include (prevents reuse of numbers)
2. ❌ Not copying sumList when adding to results (reference issues)
3. ❌ Forgetting to backtrack (remove from sumList)
4. ❌ Wrong base case order (check target == 0 before target < 0)
5. ❌ Not sorting candidates (can miss early pruning opportunities)

TESTING STRATEGIES:
------------------
• Single element: [2], target = 4 → [[2,2]]
• No solution: [2], target = 1 → []
• Multiple solutions: [2,3,5], target = 8 → [[2,2,2,2],[2,3,3],[3,5]]
• Large target with small numbers: [2], target = 40 → [[2×20]]
• Mixed sizes: [2,7,6,3,5,1], target = 9 → multiple combinations

DEBUGGING TIPS:
--------------
1. Print sumList at each recursive call
2. Track recursion depth with indentation
3. Count total recursive calls to measure complexity
4. Verify include/exclude decisions are correct
5. Check that all combinations sum to target

FUTURE ENHANCEMENTS:
-------------------
1. Support for duplicate numbers in candidates
2. Limit on number of elements per combination
3. Return combinations in lexicographic order
4. Parallel processing for large inputs
5. Streaming output for huge result sets

KEY LEARNING POINTS:
--------------------
• Backtracking pattern: Include → Explore → Exclude
• Index control prevents duplicate combinations
• Space efficiency comes from reusing the same list
• Time complexity depends on target, not input size
• Systematic exploration guarantees finding all solutions

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Combination Sum II** - Each number can be used at most once
2. **Combination Sum III** - Use numbers 1-9 exactly k times
3. **Combination Sum IV** - Count combinations (order matters)
4. **Letter Combinations** - Similar backtracking with strings
5. **Palindrome Partitioning** - Backtracking on strings

================================================================================
*/
