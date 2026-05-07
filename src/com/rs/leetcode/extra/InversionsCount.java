package com.rs.leetcode.extra;

import java.util.ArrayList;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Count Inversions
 * @TimeComplexity: O(n log n)
 * @SpaceComplexity: O(n)
 * @Algorithm: Modified Merge Sort
 */

/**
 * ================================================================================
 * COUNT INVERSIONS - MODIFIED MERGE SORT APPROACH
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 7, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Count the number of inversions in an array. An inversion is a pair
 * of indices (i, j) such that i < j and arr[i] > arr[j].
 * <p>
 * An inversion represents a pair of elements that are out of order. The total
 * number of inversions indicates how far the array is from being sorted.
 * <p>
 * <p>
 * Example 1:
 * Input: [2, 4, 1, 3, 5]
 * Output: 3
 * Inversions: (2,1), (4,1), (4,3)
 * <p>
 * Example 2:
 * Input: [5, 4, 3, 2, 1]
 * Output: 10
 * All pairs are inversions: n(n-1)/2 = 5×4/2 = 10
 * <p>
 * Example 3:
 * Input: [1, 2, 3, 4, 5]
 * Output: 0
 * No inversions - array is already sorted
 * <p>
 * <p>
 * Applications:
 * - Measure how sorted an array is
 * - Calculate Kendall tau distance in statistics
 * - Collaborative filtering in recommendation systems
 * - Ranking similarity in search engines
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    inversionCount(arr)
 *                           |
 *                           v
 *                 getCount_mergeSort(arr, 0, n-1)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      start >= end?                Calculate mid
 *            |                          |
 *      YES ──→ return 0              mid = start + (end-start)/2
 *            |                          |
 *           EXIT                         v
 *            |                    ┌───────────────┐
 *            NO                   |   RECURSE     |
 *            |                   |               |
 *            v                   | 1. Left half  |
 *    ┌─────────────────────┐    |    [st,mid]   |
 *    |   DIVIDE            |    |               |
 *    |   Split into two    |    | 2. Right half |
 *    |   halves at mid     |    |   [mid+1,end] |
 *    └─────────────────────┘    |               |
 *            |                   └───────────────┘
 *            v                          |
 *    ┌─────────────────────┐            v
 *    |   CONQUER          |    ┌─────────────────┐
 *    |   Count inversions  |    |   MERGE & COUNT |
 *    |   in each half      |    |   Count cross    |
 *    |   recursively      |    |   inversions     |
 *    └─────────────────────┘    └─────────────────┘
 *            |                          |
 *            └──────────────┬───────────┘
 *                           |
 *                           v
 *                        return sum
 * 
 * ================================================================================
 * COUNTING INVERSIONS DURING MERGE
 * ================================================================================
 * 
 * Key insight: When merging two sorted halves, if we pick an element from
 * the right half, it's smaller than ALL remaining elements in the left half.
 * 
 * Example: Merging [2, 8, 10] and [5, 9, 12]
 * 
 * Left:  [2, 8, 10]    i = 0
 * Right: [5, 9, 12]    j = 0
 * 
 * Step 1: Compare 2 and 5
 *         2 < 5, take 2 (no new inversions)
 *         Left: [8, 10], Right: [5, 9, 12]
 * 
 * Step 2: Compare 8 and 5
 *         5 < 8, take 5 from RIGHT
 *         IMPORTANT: 5 is smaller than ALL remaining in left [8, 10]
 *         New inversions = mid - i + 1 = 2 - 1 + 1 = 2
 *         Inversions: (8,5) and (10,5)
 *         Left: [8, 10], Right: [9, 12]
 * 
 * Step 3: Compare 8 and 9
 *         8 < 9, take 8 (no new inversions)
 *         Left: [10], Right: [9, 12]
 * 
 * Step 4: Compare 10 and 9
 *         9 < 10, take 9 from RIGHT
 *         New inversions = mid - i + 1 = 2 - 2 + 1 = 1
 *         Inversion: (10,9)
 *         Left: [10], Right: [12]
 * 
 * Total cross inversions = 2 + 1 = 3
 * 
 * ================================================================================
 * RECURSION TREE WITH INVERSION COUNTING
 * ================================================================================
 * 
 * Counting inversions in [2, 4, 1, 3, 5]:
 * 
 *                    [2, 4, 1, 3, 5]
 *                           |
 *                    /         \
 *                   /           \
 *           [2, 4, 1]         [3, 5]
 *              |                   |
 *            /   \               /   \
 *           /     \             /     \
 *      [2, 4]     [1]         [3]     [5]
 *         |         |         |        |
 *       /   \       |         |        |
 *      /     \     |         |        |
 *    [2]     [4]   [1]       [3]      [5]
 *      |       |   |         |        |
 *      └───────┘   |         |        |
 *        [2, 4]     |         |        |
 *        count=0    |         |        |
 *           \       |         |        |
 *            └────[1, 2, 4]───────┘
 *                 count=2
 *                 (2,1) and (4,1)
 *                     |
 *               [1, 2, 4, 3, 5]
 *                    |
 *                    |
 *               [1, 2, 3, 4, 5]
 *               count=1
 *               (4,3)
 * 
 * Total inversions = 0 + 2 + 0 + 1 = 3
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing inversionCount([2, 4, 1, 3]):
 * 
 * Step 1: getCount_mergeSort([2, 4, 1, 3], 0, 3)
 * - mid = 1
 * - Left: getCount_mergeSort([2, 4, 1, 3], 0, 1)
 * - Right: getCount_mergeSort([2, 4, 1, 3], 2, 3)
 * 
 * Step 2: getCount_mergeSort([2, 4, 1, 3], 0, 1)
 * - mid = 0
 * - Left: getCount_mergeSort([2, 4, 1, 3], 0, 0) → return 0
 * - Right: getCount_mergeSort([2, 4, 1, 3], 1, 1) → return 0
 * - merge([2, 4, 1, 3], 0, 1, 0)
 *   - Compare 2 and 4: take 2
 *   - Take remaining 4
 *   - No inversions found, count = 0
 * - Return 0
 * 
 * Step 3: getCount_mergeSort([2, 4, 1, 3], 2, 3)
 * - mid = 2
 * - Left: getCount_mergeSort([2, 4, 1, 3], 2, 2) → return 0
 * - Right: getCount_mergeSort([2, 4, 1, 3], 3, 3) → return 0
 * - merge([2, 4, 1, 3], 2, 3, 2)
 *   - Compare 1 and 3: take 1
 *   - Take remaining 3
 *   - No inversions found, count = 0
 * - Return 0
 * 
 * Step 4: merge([2, 4, 1, 3], 0, 3, 1)
 * - Merge [2, 4] and [1, 3]
 * - Compare 2 and 1: take 1 from right
 *   - count += 1 - 0 + 1 = 2 (inversions: (2,1), (4,1))
 * - Compare 2 and 3: take 2
 * - Compare 4 and 3: take 3 from right
 *   - count += 1 - 1 + 1 = 1 (inversion: (4,3))
 * - Take remaining 4
 * - Total cross inversions = 3
 * 
 * Final result: 0 + 0 + 3 = 3 inversions
 * 
 * ================================================================================
 */
public class InversionsCount {
    /**
     * Main entry point - counts inversions in the array using merge sort approach
     * 
     * @param arr Input array of integers
     * @return Total number of inversions in the array
     * 
     * Time: O(n log n) - modified merge sort
     * Space: O(n) - temporary array for merging
     * 
     * NOTE: Brute force method available but commented out (O(n^2))
     */
    public int inversionCount(int[] arr) {
        // Option 1: Brute force O(n^2) - uncomment to use
        // return getCount_BruteForce(arr);
        
        // Option 2: Modified merge sort O(n log n)
        int count = getCount_mergeSort(arr, 0, arr.length - 1);
        
        // Optional: Print sorted array (commented out)
        // for(int i : arr) {
        //     System.out.print(i);
        // }
        
        return count;
    }

    /**
     * BRUTE FORCE INVERSION COUNTING
     * Counts inversions by checking all pairs of elements
     * 
     * @param arr Input array
     * @return Number of inversions
     * 
     * Time: O(n^2) - check all n(n-1)/2 pairs
     * Space: O(1) - constant extra space
     * 
     * ALGORITHM:
     * For each element at index i, count elements after it (j > i)
     * that are smaller than arr[i]
     * 
     * NOTE: Simple but inefficient for large arrays
     */
    private int getCount_BruteForce(int[] arr) {
        int count = 0;
        
        // Check all pairs (i, j) where i < j
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // If element at i is greater than element at j,
                // we found an inversion
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        
        return count;
    }

    /**
     * MODIFIED MERGE SORT FOR INVERSION COUNTING
     * Recursively sorts array while counting inversions
     * 
     * @param arr Array being processed
     * @param st Starting index
     * @param end Ending index
     * @return Number of inversions in arr[st..end]
     * 
     * ALGORITHM:
     * 1. Divide array into two halves
     * 2. Recursively count inversions in each half
     * 3. Count cross inversions during merge
     * 4. Return sum of all three counts
     * 
     * KEY: Inversions = left inversions + right inversions + cross inversions
     */
    private int getCount_mergeSort(int[] arr, int st, int end) {
        // Base case: Single element has no inversions
        if (st < end) {
            // Calculate middle point (overflow-safe)
            int mid = st + (end - st) / 2;
            
            // RECURSIVE: Count inversions in left half
            int leftCount = getCount_mergeSort(arr, st, mid);
            
            // RECURSIVE: Count inversions in right half
            int rightCount = getCount_mergeSort(arr, mid + 1, end);
            
            // MERGE: Count cross inversions and merge halves
            int crossCount = merge(arr, st, end, mid);
            
            // TOTAL: Sum of all inversions
            return leftCount + rightCount + crossCount;
        }
        
        return 0; // No inversions in single element
    }

    /**
     * MERGE AND COUNT CROSS INVERSIONS
     * Merges two sorted halves while counting cross inversions
     * 
     * @param arr Original array containing two sorted halves
     * @param st Start index of left half
     * @param end End index of right half
     * @param mid End index of left half
     * @return Number of cross inversions
     * 
     * CROSS INVERSIONS: Pairs (i,j) where i in left half, j in right half,
     * and arr[i] > arr[j]
     * 
     * KEY INSIGHT: When arr[i] > arr[j] and we pick arr[j],
     * it forms inversions with ALL remaining elements in left half!
     * 
     * Time: O(n) where n = end - st + 1
     * Space: O(n) for temporary list
     */
    private int merge(int[] arr, int st, int end, int mid) {
        // Temporary list for merged result
        List<Integer> tempList = new ArrayList<>();
        
        // Count of cross inversions
        int count = 0;
        
        // Pointers for left and right halves
        int i = st;        // Left half pointer
        int j = mid + 1;   // Right half pointer
        
        // MERGE: Compare elements from both halves
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                // Element from left is smaller or equal
                // No new inversions
                tempList.add(arr[i]);
                i++;
            } else {
                // Element from right is smaller!
                // It forms inversions with ALL remaining elements in left
                tempList.add(arr[j]);
                j++;
                
                // COUNT: All elements from i to mid are greater than arr[j]
                // WHY: Since left half is sorted, if arr[i] > arr[j],
                // then ALL elements from i to mid are > arr[j]
                // Number of such elements = mid - i + 1
                count += mid - i + 1;
            }
        }
        
        // COPY REMAINING: Add any remaining elements from left half
        while (i <= mid) {
            tempList.add(arr[i]);
            i++;
        }
        
        // COPY REMAINING: Add any remaining elements from right half
        while (j <= end) {
            tempList.add(arr[j]);
            j++;
        }
        
        // UPDATE: Copy merged result back to original array
        for (int idx = 0; idx < tempList.size(); idx++) {
            arr[idx + st] = tempList.get(idx);
        }
        
        return count;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Count Inversions (Modified Merge Sort)
================================================================================

PROBLEM: Count the number of inversions in an array where an inversion is
         a pair (i, j) with i < j and arr[i] > arr[j].

APPROACH: Modified Merge Sort
---------------------------
1. DIVIDE: Split array into two halves recursively
2. CONQUER: Count inversions in each half independently
3. COMBINE: Count cross inversions while merging sorted halves
4. SUM: Total = left inversions + right inversions + cross inversions

KEY INSIGHT: During merge, when an element from right half is placed before
elements from left half, it forms inversions with ALL remaining left elements!

TIME COMPLEXITY: O(n log n)
--------------------------
• Recursion depth: log n (array halves at each level)
• Work per level: O(n) (merging and counting)
• Total: O(n × log n)

Detailed breakdown:
- Level 0: 1 array of size n → O(n) work
- Level 1: 2 arrays of size n/2 → O(n) work total
- Level 2: 4 arrays of size n/4 → O(n) work total
- ...
- Level log n: n arrays of size 1 → O(n) work total
- Sum across all levels: O(n × log n)

Brute Force Alternative: O(n^2)
- Check all n(n-1)/2 pairs
- Simple but impractical for large n

SPACE COMPLEXITY: O(n)
-----------------------
• Recursion stack: O(log n) depth
• Temporary array for merging: O(n) at each level
• Total auxiliary space: O(n) (temp array dominates)

Note: The array is sorted as a side effect of counting inversions

WHY THIS WORKS:
--------------
The algorithm leverages the properties of merge sort:
1. **Divide phase** isolates inversions within each half
2. **Merge phase** efficiently counts cross inversions
3. **Sorted halves** make cross inversion counting linear
4. **Recursion** ensures all inversions are counted exactly once

The key insight: When merging sorted halves A and B, if we pick an element
from B before elements remaining in A, it's smaller than ALL those elements!

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Fenwick Tree (Binary Indexed Tree)**
   - O(n log M) where M is the range of values
   - Better for small value ranges
   - Requires coordinate compression

2. **Segment Tree**
   - O(n log M) time complexity
   - More flexible than Fenwick Tree
   - Supports range queries

3. **AVL/Red-Black Tree**
   - O(n log n) with online processing
   - Can handle streaming data
   - More memory overhead

4. **Parallel Processing**
   - Count inversions in halves in parallel
   - Merge results with cross inversions
   - Significant speedup for large arrays

5. **In-place Merge**
   - Reduce space complexity to O(1)
   - Complex implementation
   - May increase constant factors

COMPARISON WITH OTHER APPROACHES:
----------------------------------
| Approach | Time | Space | Online | Stable | Implementation |
|----------|------|-------|--------|--------|----------------|
| Modified Merge Sort | O(n log n) | O(n) | No | Yes | Moderate |
| Brute Force | O(n^2) | O(1) | Yes | Yes | Simple |
| Fenwick Tree | O(n log M) | O(M) | Yes | No | Complex |
| Segment Tree | O(n log M) | O(M) | Yes | No | Complex |
| Balanced BST | O(n log n) | O(n) | Yes | Yes | Complex |

PRACTICAL APPLICATIONS:
-----------------------
• **Kendall Tau Distance** - Measure ranking similarity in statistics
• **Collaborative Filtering** - User preference analysis in recommendation
• **Search Engine Quality** - Measure result list similarity
• **Genome Analysis** - Count inversions in gene sequences
• **E-commerce** - Analyze customer preference patterns
• **Social Networks** - Measure influence hierarchy violations

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Using arr[mid] instead of arr[end] as pivot in merge sort
2. ❌ Forgetting to count inversions when elements are equal
3. ❌ Off-by-one errors in cross inversion counting
4. ❌ Not using long for large arrays (inversion count can exceed int)
5. ❌ Modifying array when only counting is needed

TESTING STRATEGIES:
------------------
• Empty array: [] → 0
• Single element: [5] → 0
• Already sorted: [1, 2, 3, 4] → 0
• Reverse sorted: [4, 3, 2, 1] → 6 (n(n-1)/2)
• All equal: [2, 2, 2, 2] → 0
• One inversion: [1, 3, 2, 4] → 1
• Multiple inversions: [2, 4, 1, 3, 5] → 3
• Large array: Test with 100,000+ elements
• Negative numbers: [-1, -3, -2] → 2

DEBUGGING TIPS:
--------------
1. Verify brute force and merge sort results match
2. Print array after each merge to ensure sorting
3. Track cross inversions at each merge step
4. Check that total = left + right + cross
5. Use long for counts with large arrays

FUTURE ENHANCEMENTS:
-------------------
1. Support for counting inversions in subarrays
2. Return pairs of indices forming inversions
3. Count inversions for streaming data
4. Parallel implementation for multi-core systems
5. Generic type support for any Comparable type

KEY LEARNING POINTS:
--------------------
• Divide and conquer can solve counting problems efficiently
• Merge sort's merge step reveals powerful insights
• Cross inversions can be counted in linear time with sorted halves
• O(n^2) to O(n log n) is a significant optimization
• Sometimes sorting is a useful side effect, not the goal

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Local Inversions** - Count only adjacent inversions
2. **K-Inversions** - Count pairs with distance ≤ k
3. **Weighted Inversions** - Each pair has a weight
4. **2D Inversions** - Count in 2D space
5. **Inversion Pairs** - Return all inversion pairs

================================================================================
*/
