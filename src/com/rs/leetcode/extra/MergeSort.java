package com.rs.leetcode.extra;

import java.util.ArrayList;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Merge Sort
 * @TimeComplexity: O(n log n)
 * @SpaceComplexity: O(n)
 * @Algorithm: Divide and Conquer
 */

/**
 * ================================================================================
 * MERGE SORT - DIVIDE AND CONQUER ALGORITHM
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 5, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Sort an array of integers using the merge sort algorithm.
 * The implementation supports both ascending and descending order sorting.
 * <p>
 * Merge sort is a classic divide and conquer algorithm that:
 * 1. Divides the array into two halves recursively
 * 2. Sorts each half independently
 * 3. Merges the two sorted halves back together
 * <p>
 * <p>
 * Example:
 * Input: [38, 27, 43, 3, 9, 82, 10]
 * Output (ASC): [3, 9, 10, 27, 38, 43, 82]
 * Output (DESC): [82, 43, 38, 27, 10, 9, 3]
 * <p>
 * <p>
 * Algorithm Properties:
 * - Stable: Maintains relative order of equal elements
 * - Not in-place: Requires O(n) extra space
 * - Predictable: Always O(n log n) time complexity
 * - Parallelizable: Can be parallelized for large datasets
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    mergeSort(arr, asc)
 *                           |
 *                           v
 *                    recursive(arr, 0, n-1, asc)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      start >= end?                Calculate mid
 *            |                          |
 *      YES ──→ return                 mid = start + (end-start)/2
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
 *    |   Sort each half   |    |   MERGE          |
 *    |   recursively      |    |   Combine halves |
 *    |   (same algorithm) |    |   into sorted    |
 *    └─────────────────────┘    |   array          |
 *            |                   └─────────────────┘
 *            v                          |
 *          return                      v
 *                                    return
 * 
 * ================================================================================
 * MERGE PROCESS VISUALIZATION
 * ================================================================================
 * 
 * Merging two sorted halves [3, 27, 38] and [9, 10, 43, 82]:
 * 
 * Left:  [3, 27, 38]          i = 0
 * Right: [9, 10, 43, 82]       j = 0
 * Result: []
 * 
 * Step 1: Compare 3 and 9
 *         3 < 9, so take 3
 * Left:  [27, 38]            i = 1
 * Right: [9, 10, 43, 82]       j = 0
 * Result: [3]
 * 
 * Step 2: Compare 27 and 9
 *         9 < 27, so take 9
 * Left:  [27, 38]            i = 1
 * Right: [10, 43, 82]        j = 1
 * Result: [3, 9]
 * 
 * Step 3: Compare 27 and 10
 *         10 < 27, so take 10
 * Left:  [27, 38]            i = 1
 * Right: [43, 82]           j = 2
 * Result: [3, 9, 10]
 * 
 * ... continues until both arrays exhausted
 * 
 * Final Result: [3, 9, 10, 27, 38, 43, 82]
 * 
 * ================================================================================
 * RECURSION TREE EXAMPLE
 * ================================================================================
 * 
 * Sorting [38, 27, 43, 3, 9, 82, 10]:
 * 
 *                    [38, 27, 43, 3, 9, 82, 10]
 *                           |
 *                    /         \
 *                   /           \
 *           [38, 27, 43, 3]    [9, 82, 10]
 *              |                   |
 *            /   \               /   \
 *           /     \             /     \
 *      [38, 27]   [43, 3]    [9, 82]   [10]
 *         |         |         |        |
 *       /   \     /   \     /   \      |
 *      /     \   /     \   /     \     |
 *    [38]   [27] [43]   [3] [9]   [82]  [10]
 *      |       |   |     |   |     |     |
 *      └───────┘   └─────┘   └─────┘     |
 *        [27, 38]   [3, 43]   [9, 82]     |
 *           \         /          \       |
 *            └──────[3, 27, 38, 43]───────┘
 *                     |
 *               [3, 9, 10, 27, 38, 43, 82]
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing mergeSort([5, 2, 8, 1], true):
 * 
 * Step 1: recursive([5, 2, 8, 1], 0, 3, true)
 * - mid = 0 + (3-0)/2 = 1
 * - recursive([5, 2, 8, 1], 0, 1, true)
 * 
 * Step 2: recursive([5, 2, 8, 1], 0, 1, true)
 * - mid = 0 + (1-0)/2 = 0
 * - recursive([5, 2, 8, 1], 0, 0, true) → return (single element)
 * - recursive([5, 2, 8, 1], 1, 1, true) → return (single element)
 * - mergeASC([5, 2, 8, 1], 0, 0, 1)
 *   - Compare 5 and 2: 2 < 5, take 2
 *   - Left: [5], Right: [] → take 5
 *   - Result: [2, 5]
 *   - Array becomes: [2, 5, 8, 1]
 * 
 * Step 3: Back to Step 1
 * - recursive([5, 2, 8, 1], 2, 3, true)
 * - mid = 2 + (3-2)/2 = 2
 * - recursive([5, 2, 8, 1], 2, 2, true) → return
 * - recursive([5, 2, 8, 1], 3, 3, true) → return
 * - mergeASC([5, 2, 8, 1], 2, 2, 3)
 *   - Compare 8 and 1: 1 < 8, take 1
 *   - Left: [8], Right: [] → take 8
 *   - Result: [1, 8]
 *   - Array becomes: [2, 5, 1, 8]
 * 
 * Step 4: Final merge
 * - mergeASC([5, 2, 8, 1], 0, 1, 3)
 *   - Merge [2, 5] and [1, 8]
 *   - Final result: [1, 2, 5, 8]
 * 
 * ================================================================================
 */
public class MergeSort {
    /**
     * Main entry point - sorts the array using merge sort algorithm
     * 
     * @param arr Array of integers to be sorted
     * @param asc true for ascending order, false for descending order
     * 
     * Time: O(n log n) - divide and conquer with n levels, log n work per level
     * Space: O(n) - temporary array for merging
     * 
     * NOTE: The array is sorted in-place, but requires O(n) auxiliary space
     */
    public void mergeSort(int[] arr, boolean asc) {
        // Start the recursive merge sort from the entire array
        recursive(arr, 0, arr.length - 1, asc);
        
        // Print the sorted array (for demonstration)
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    /**
     * RECURSIVE DIVIDE AND CONQUER METHOD
     * Splits the array into halves, sorts each half, then merges them
     * 
     * @param arr Array being sorted
     * @param st Starting index of current subarray
     * @param end Ending index of current subarray
     * @param isASC true for ascending, false for descending sort
     * 
     * ALGORITHM:
     * 1. Base case: If subarray has 0 or 1 element, it's already sorted
     * 2. Divide: Find middle point to split array
     * 3. Conquer: Recursively sort left and right halves
     * 4. Combine: Merge the two sorted halves
     * 
     * NOTE: Uses st + (end - st) / 2 to prevent integer overflow
     */
    private void recursive(int[] arr, int st, int end, boolean isASC) {
        // Base case: Subarray of size 0 or 1 is already sorted
        if (st < end) {
            // Calculate middle point (overflow-safe)
            int mid = st + (end - st) / 2;
            
            // RECURSIVE: Sort left half
            recursive(arr, st, mid, isASC);
            
            // RECURSIVE: Sort right half
            recursive(arr, mid + 1, end, isASC);
            
            // MERGE: Combine the two sorted halves
            if (isASC) {
                mergeASC(arr, st, mid, end);
            } else {
                mergeDESC(arr, st, mid, end);
            }
        }
    }

    /**
     * MERGE TWO SORTED HALVES - ASCENDING ORDER
     * Combines two sorted subarrays into one sorted array
     * 
     * @param arr Original array containing the two subarrays
     * @param st Starting index of left subarray
     * @param mid Ending index of left subarray
     * @param end Ending index of right subarray
     * 
     * SUBARRAYS:
     * - Left: arr[st..mid] (already sorted)
     * - Right: arr[mid+1..end] (already sorted)
     * 
     * ALGORITHM:
     * 1. Use two pointers i and j for left and right subarrays
     * 2. Compare elements and add smaller to temp list
     * 3. Handle remaining elements in either subarray
     * 4. Copy merged result back to original array
     * 
     * Time: O(n) where n = end - st + 1
     * Space: O(n) for temporary list
     */
    private void mergeASC(int[] arr, int st, int mid, int end) {
        // Temporary list to store merged result
        List<Integer> tempList = new ArrayList<>();
        
        // Pointers for left and right subarrays
        int i = st;        // Left subarray pointer
        int j = mid + 1;   // Right subarray pointer
        
        // MERGE: Compare and merge while both subarrays have elements
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tempList.add(arr[i]);
                i++;
            } else {
                tempList.add(arr[j]);
                j++;
            }
        }
        
        // HANDLE REMAINING: Copy any remaining elements from left subarray
        while (i <= mid) {
            tempList.add(arr[i]);
            i++;
        }
        
        // HANDLE REMAINING: Copy any remaining elements from right subarray
        while (j <= end) {
            tempList.add(arr[j]);
            j++;
        }
        
        // UPDATE: Copy merged result back to original array
        for (int idx = 0; idx < tempList.size(); idx++) {
            arr[idx + st] = tempList.get(idx);
        }
    }

    /**
     * MERGE TWO SORTED HALVES - DESCENDING ORDER
     * Combines two sorted subarrays into one sorted array (descending)
     * 
     * @param arr Original array containing the two subarrays
     * @param st Starting index of left subarray
     * @param mid Ending index of left subarray
     * @param end Ending index of right subarray
     * 
     * SUBARRAYS:
     * - Left: arr[st..mid] (already sorted in descending order)
     * - Right: arr[mid+1..end] (already sorted in descending order)
     * 
     * ALGORITHM:
     * Same as mergeASC, but comparison is reversed (>= instead of <=)
     * to achieve descending order
     * 
     * Time: O(n) where n = end - st + 1
     * Space: O(n) for temporary list
     */
    private void mergeDESC(int[] arr, int st, int mid, int end) {
        // Temporary list to store merged result
        List<Integer> tempList = new ArrayList<>();
        
        // Pointers for left and right subarrays
        int i = st;        // Left subarray pointer
        int j = mid + 1;   // Right subarray pointer
        
        // MERGE: Compare and merge while both subarrays have elements
        // NOTE: Comparison reversed for descending order
        while (i <= mid && j <= end) {
            if (arr[i] >= arr[j]) {  // Only difference from mergeASC
                tempList.add(arr[i]);
                i++;
            } else {
                tempList.add(arr[j]);
                j++;
            }
        }
        
        // HANDLE REMAINING: Copy any remaining elements from left subarray
        while (i <= mid) {
            tempList.add(arr[i]);
            i++;
        }
        
        // HANDLE REMAINING: Copy any remaining elements from right subarray
        while (j <= end) {
            tempList.add(arr[j]);
            j++;
        }
        
        // UPDATE: Copy merged result back to original array
        for (int idx = 0; idx < tempList.size(); idx++) {
            arr[idx + st] = tempList.get(idx);
        }
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Merge Sort (Divide and Conquer)
================================================================================

PROBLEM: Sort an array of integers in ascending or descending order.

APPROACH: Divide and Conquer Strategy
------------------------------------
1. DIVIDE: Split the array into two equal halves recursively
2. CONQUER: Sort each half independently (using the same algorithm)
3. COMBINE: Merge the two sorted halves into a single sorted array

KEY INSIGHT: The merge step is linear time, and we have log n levels of recursion!

TIME COMPLEXITY: O(n log n)
--------------------------
• Recursion depth: log n (array halves at each level)
• Work per level: O(n) (merging all elements once)
• Total: O(n × log n) for all cases (best, average, worst)

Detailed breakdown:
- Level 0: 1 array of size n → O(n) work
- Level 1: 2 arrays of size n/2 → O(n) work total
- Level 2: 4 arrays of size n/4 → O(n) work total
- ...
- Level log n: n arrays of size 1 → O(n) work total
- Sum across all levels: O(n × log n)

SPACE COMPLEXITY: O(n)
-----------------------
• Recursion stack: O(log n) depth
• Temporary array for merging: O(n) at each level
• Total auxiliary space: O(n) (temp array dominates)

Note: While we need O(n) extra space, the sorting is done in-place
(the original array is modified, not a new one created).

WHY THIS WORKS:
--------------
Merge sort leverages the fact that merging two sorted arrays is linear:
1. **Divide phase** creates smaller subproblems that are easier to solve
2. **Base case** (single element) is trivially sorted
3. **Merge phase** combines sorted subarrays efficiently
4. **Recursion** ensures all subproblems are solved before merging

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **In-place Merge**
   - Merge without extra array (complex implementation)
   - Reduces space to O(1) but increases time complexity

2. **Iterative Bottom-Up**
   - Eliminate recursion overhead
   - Start with size 1 subarrays, double each pass
   - Better cache performance

3. **Parallel Merge Sort**
   - Sort halves in parallel on multiple cores
   - Merge can also be parallelized
   - Significant speedup for large arrays

4. **Hybrid Approach**
   - Use insertion sort for small subarrays (< 16 elements)
   - Reduces recursive overhead for small partitions

5. **Optimized Temp Array**
   - Reuse single temp array instead of creating new ones
   - Reduces garbage collection pressure

COMPARISON WITH OTHER SORTING ALGORITHMS:
----------------------------------------
| Algorithm | Time (Best) | Time (Avg) | Time (Worst) | Space | Stable |
|-----------|-------------|------------|--------------|-------|--------|
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n^2) | O(log n) | No |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |
| Insertion | O(n) | O(n^2) | O(n^2) | O(1) | Yes |
| Bubble | O(n) | O(n^2) | O(n^2) | O(1) | Yes |

PRACTICAL APPLICATIONS:
-----------------------
• **External sorting** - Sorting data that doesn't fit in memory
• **Stable sorting requirement** - When maintaining order of equal elements
• **Parallel processing** - Natural fit for multi-core systems
• **Linked list sorting** - O(1) extra space with linked lists
• **Inversion counting** - Count pairs (i,j) where i < j and arr[i] > arr[j]

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Using (st + end) / 2 instead of st + (end - st) / 2 (overflow risk)
2. ❌ Forgetting to handle remaining elements after merge loop
3. ❌ Incorrect array bounds in merge (off-by-one errors)
4. ❌ Not copying merged result back to original array
5. ❌ Using wrong comparison operator for descending sort

TESTING STRATEGIES:
------------------
• Empty array: [] → []
• Single element: [5] → [5]
• Already sorted: [1, 2, 3, 4] → [1, 2, 3, 4]
• Reverse sorted: [4, 3, 2, 1] → [1, 2, 3, 4]
• All equal: [2, 2, 2, 2] → [2, 2, 2, 2]
• Negative numbers: [-3, -1, -2] → [-3, -2, -1]
• Mixed: [3, -1, 2, -5, 0] → [-5, -1, 0, 2, 3]
• Large array: Test with 10,000+ random elements

DEBUGGING TIPS:
--------------
1. Print array after each merge to verify correctness
2. Track recursion depth with indentation
3. Verify subarray bounds before each merge
4. Check that temp list size equals expected merged size
5. Ensure both ascending and descending work correctly

FUTURE ENHANCEMENTS:
-------------------
1. Generic type support for sorting any Comparable type
2. Custom comparator support
3. Return sorted array instead of in-place modification
4. Count and return number of inversions
5. Support for parallel execution

KEY LEARNING POINTS:
--------------------
• Divide and conquer reduces problem complexity recursively
• Linear merge operation makes overall complexity O(n log n)
• Space-time tradeoff: O(n) space for guaranteed O(n log n) time
• Stability is important when sorting objects with multiple fields
• Recursive thinking helps break complex problems into simpler ones

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Counting Inversions** - Count pairs out of order during merge
2. **K-way Merge** - Merge k sorted arrays simultaneously
3. **External Merge Sort** - Sort data larger than memory
4. **Merge Sort Tree** - Data structure for range queries
5. **TimSort** - Hybrid of merge sort and insertion sort (used in Python)

================================================================================
*/
