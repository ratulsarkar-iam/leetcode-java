package com.rs.leetcode.extra;

/* @LeetcodeMeta
 * @Title: Quick Sort
 * @TimeComplexity: O(n log n) avg, O(n^2) worst
 * @SpaceComplexity: O(log n)
 * @Algorithm: Divide and Conquer
 */

/**
 * ================================================================================
 * QUICK SORT - DIVIDE AND CONQUER ALGORITHM
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 7, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Sort an array of integers using the quick sort algorithm.
 * This implementation uses the Lomuto partition scheme with the last
 * element as the pivot.
 * <p>
 * Quick sort is a highly efficient divide and conquer algorithm that:
 * 1. Selects a pivot element from the array
 * 2. Partitions the array around the pivot (smaller to left, larger to right)
 * 3. Recursively sorts the left and right subarrays
 * <p>
 * <p>
 * Example:
 * Input: [10, 80, 30, 90, 40, 50, 70]
 * Output: [10, 30, 40, 50, 70, 80, 90]
 * <p>
 * <p>
 * Algorithm Properties:
 * - In-place: Sorts in-place with O(log n) auxiliary space
 * - Unstable: May change relative order of equal elements
 * - Adaptive: Performance depends on pivot selection
 * - Cache-friendly: Good locality of reference
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    quickSort(arr)
 *                           |
 *                           v
 *                    sortRecursive(arr, 0, n-1)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      start >= end?                Partition array
 *            |                          around pivot
 *      YES ──→ return                 (Lomuto scheme)
 *            |                          |
 *           EXIT                         v
 *            |                    Returns pivot index
 *            NO                          |
 *            |                          v
 *    ┌─────────────────────┐    ┌─────────────────┐
 *    |   RECURSE          |    |   RECURSE       |
 *    |   Sort left part   |    |   Sort right    |
 *    |   arr[st..pivot-1]  |    |   arr[pivot+1..end]
 *    └─────────────────────┘    └─────────────────┘
 *            |                          |
 *            └──────────────┬───────────┘
 *                           |
 *                           v
 *                        return
 * 
 * ================================================================================
 * LOMUTO PARTITION SCHEME
 * ================================================================================
 * 
 * The partition process with pivot = last element:
 * 
 * Initial: [10, 80, 30, 90, 40, 50, 70]
 *          st=0                       end=6
 *          pivot=70, i=-1
 * 
 * Step 1: j=0, arr[0]=10 ≤ 70
 *         i=0, swap(arr[0], arr[0]) → [10, 80, 30, 90, 40, 50, 70]
 * 
 * Step 2: j=1, arr[1]=80 > 70 → no swap
 * 
 * Step 3: j=2, arr[2]=30 ≤ 70
 *         i=1, swap(arr[1], arr[2]) → [10, 30, 80, 90, 40, 50, 70]
 * 
 * Step 4: j=3, arr[3]=90 > 70 → no swap
 * 
 * Step 5: j=4, arr[4]=40 ≤ 70
 *         i=2, swap(arr[2], arr[4]) → [10, 30, 40, 90, 80, 50, 70]
 * 
 * Step 6: j=5, arr[5]=50 ≤ 70
 *         i=3, swap(arr[3], arr[5]) → [10, 30, 40, 50, 80, 90, 70]
 * 
 * Final: i=4, swap(arr[4], arr[6]) → [10, 30, 40, 50, 70, 80, 90]
 *        Pivot at index 4, all left ≤ 70, all right > 70
 * 
 * ================================================================================
 * RECURSION TREE EXAMPLE
 * ================================================================================
 * 
 * Sorting [10, 80, 30, 90, 40, 50, 70]:
 * 
 *                    [10, 80, 30, 90, 40, 50, 70]
 *                           | pivot=70
 *                           v
 *              ┌─────────────────┴─────────────────┐
 *              |                                 |
 *              v                                 v
 *      [10, 30, 40, 50]                     [80, 90]
 *         | pivot=50                           | pivot=90
 *         v                                   v
 *   ┌───────────┴───────────┐              ┌─────┴─────┐
 *   |                       |              |           |
 *   v                       v              v           v
 * [10, 30, 40]              []            [80]        [90]
 *    | pivot=40                |              |           |
 *    v                        v              v           v
 * ┌────┴────┐               return         return      return
 * |         |
 * v         v
 * [10, 30]   [40]
 *  | pivot=30  |
 *  v           v
 * [10]        [30]
 *  |           |
 *  v           v
 * return      return
 * 
 * Final sorted array: [10, 30, 40, 50, 70, 80, 90]
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing quickSort([5, 2, 8, 1, 6]):
 * 
 * Step 1: sortRecursive([5, 2, 8, 1, 6], 0, 4)
 * - getPivotIdx with pivot=6
 * - After partition: [5, 2, 1, 6, 8], pivotIdx=3
 * - Recurse left: sortRecursive([5, 2, 1, 6, 8], 0, 2)
 * - Recurse right: sortRecursive([5, 2, 1, 6, 8], 4, 4)
 * 
 * Step 2: sortRecursive([5, 2, 1, 6, 8], 0, 2)
 * - getPivotIdx with pivot=1
 * - After partition: [1, 5, 2, 6, 8], pivotIdx=0
 * - Recurse left: sortRecursive([1, 5, 2, 6, 8], 0, -1) → return
 * - Recurse right: sortRecursive([1, 5, 2, 6, 8], 1, 2)
 * 
 * Step 3: sortRecursive([1, 5, 2, 6, 8], 1, 2)
 * - getPivotIdx with pivot=2
 * - After partition: [1, 2, 5, 6, 8], pivotIdx=1
 * - Recurse left: sortRecursive([1, 2, 5, 6, 8], 1, 0) → return
 * - Recurse right: sortRecursive([1, 2, 5, 6, 8], 2, 2) → return
 * 
 * Step 4: sortRecursive([1, 2, 5, 6, 8], 4, 4) → return
 * 
 * Final result: [1, 2, 5, 6, 8]
 * 
 * ================================================================================
 */
public class QuickSort {
    /**
     * Main entry point - sorts the array using quick sort algorithm
     * 
     * @param arr Array of integers to be sorted
     * 
     * Time: O(n log n) average, O(n^2) worst case
     * Space: O(log n) for recursion stack
     * 
     * NOTE: In-place sorting with no additional arrays needed
     */
    public void quickSort(int[] arr) {
        // Start the recursive quick sort from the entire array
        sortRecursive(arr, 0, arr.length - 1);
        
        // Print the sorted array (for demonstration)
        for (int j : arr) {
            System.out.print(j + "\t");
        }
    }

    /**
     * RECURSIVE DIVIDE AND CONQUER METHOD
     * Partitions the array around a pivot and recursively sorts subarrays
     * 
     * @param arr Array being sorted
     * @param st Starting index of current subarray
     * @param end Ending index of current subarray
     * 
     * ALGORITHM:
     * 1. Base case: If subarray has 0 or 1 element, it's already sorted
     * 2. Partition: Find pivot's correct position
     * 3. Recurse: Sort elements left of pivot
     * 4. Recurse: Sort elements right of pivot
     * 
     * NOTE: Uses Lomuto partition scheme with last element as pivot
     */
    private void sortRecursive(int[] arr, int st, int end) {
        // Base case: Subarray of size 0 or 1 is already sorted
        if (st < end) {
            // PARTITION: Place pivot at its correct sorted position
            int pivotIdx = getPivotIdx(arr, st, end);
            
            // RECURSE: Sort left subarray (elements < pivot)
            sortRecursive(arr, st, pivotIdx - 1);
            
            // RECURSE: Sort right subarray (elements > pivot)
            sortRecursive(arr, pivotIdx + 1, end);
        }
    }

    /**
     * LOMUTO PARTITION SCHEME
     * Partitions array around pivot (last element) and returns pivot's final index
     * 
     * @param arr Array to be partitioned
     * @param st Starting index of subarray
     * @param end Ending index of subarray (pivot position)
     * @return Final index of pivot after partition
     * 
     * ALGORITHM:
     * 1. Choose last element as pivot
     * 2. Maintain index i for boundary of elements ≤ pivot
     * 3. Scan array, swapping elements ≤ pivot to left side
     * 4. Finally place pivot after all smaller elements
     * 
     * RESULT: All elements left of pivot ≤ pivot < all elements right of pivot
     * 
     * Time: O(n) where n = end - st + 1
     * Space: O(1) - in-place partitioning
     */
    private int getPivotIdx(int[] arr, int st, int end) {
        // i tracks the boundary of elements ≤ pivot
        int pivotIdx = st - 1;
        
        // Choose last element as pivot
        int pivot = arr[end];
        
        // SCAN: Move all elements ≤ pivot to the left side
        for (int j = st; j < end; j++) {
            if (arr[j] <= pivot) {
                pivotIdx++;
                swap(arr, pivotIdx, j);
            }
        }
        
        // PLACE PIVOT: Put pivot after all smaller elements
        pivotIdx++;
        swap(arr, pivotIdx, end);
        
        return pivotIdx;
    }

    /**
     * SWAP TWO ELEMENTS
     * Exchanges the values at two indices in the array
     * 
     * @param arr Array containing elements to swap
     * @param st Index of first element
     * @param end Index of second element
     * 
     * Time: O(1)
     * Space: O(1)
     * 
     * NOTE: Uses temporary variable for exchange
     */
    private void swap(int[] arr, int st, int end) {
        // Store first element temporarily
        int temp = arr[st];
        
        // Place second element at first position
        arr[st] = arr[end];
        
        // Place stored first element at second position
        arr[end] = temp;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Quick Sort (Divide and Conquer)
================================================================================

PROBLEM: Sort an array of integers in ascending order.

APPROACH: Divide and Conquer with Partitioning
--------------------------------------------
1. CHOOSE: Select a pivot element (last element in this implementation)
2. PARTITION: Rearrange array so all elements ≤ pivot are left, > pivot are right
3. CONQUER: Recursively sort left and right subarrays
4. COMBINE: No combine step needed - array is sorted in-place

KEY INSIGHT: After partitioning, pivot is in its final sorted position!

TIME COMPLEXITY: O(n log n) average, O(n^2) worst
-----------------------------------------------
Best Case: O(n log n)
- Pivot divides array into equal halves each time
- Recurrence: T(n) = 2T(n/2) + O(n)
- Solution: T(n) = O(n log n)

Average Case: O(n log n)
- Expected partition is reasonably balanced
- Recurrence: T(n) = T(n/9) + T(9n/10) + O(n) (example)
- Solution: T(n) = O(n log n)

Worst Case: O(n^2)
- Occurs when pivot is always smallest or largest element
- Happens with sorted arrays and last element as pivot
- Recurrence: T(n) = T(n-1) + O(n)
- Solution: T(n) = O(n^2)

SPACE COMPLEXITY: O(log n)
-------------------------
• Recursion stack depth: O(log n) average, O(n) worst
• No additional arrays needed (in-place sorting)
• Total auxiliary space: O(log n) average case

Note: Worst case space O(n) occurs with degenerate partitions

WHY THIS WORKS:
--------------
Quick sort's efficiency comes from the partition step:
1. **Partitioning** places pivot in its final position in O(n) time
2. **Divide** creates independent subproblems
3. **Conquer** recursively solves smaller subproblems
4. **In-place** nature avoids extra memory allocation

The key insight: after partitioning, we know the pivot is exactly where
it should be in the final sorted array!

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Randomized Pivot**
   - Choose random pivot instead of last element
   - Eliminates worst-case for already sorted arrays
   - Expected O(n log n) guaranteed

2. **Median-of-Three**
   - Choose median of first, middle, last elements
   - Better pivot selection, reduces chance of worst case
   - Simple to implement, effective improvement

3. **Hybrid with Insertion Sort**
   - Use insertion sort for small subarrays (< 10 elements)
   - Reduces recursive overhead
   - Improves constant factors

4. **Tail Call Optimization**
   - Always recurse on smaller subarray first
   - Use loop for larger subarray
   - Reduces stack depth to O(log n) guaranteed

5. **Three-way Partitioning (Dutch National Flag)**
   - Handles duplicate elements efficiently
   - Partitions into < pivot, = pivot, > pivot
   - Linear time for arrays with many duplicates

COMPARISON WITH OTHER SORTING ALGORITHMS:
----------------------------------------
| Algorithm | Time (Best) | Time (Avg) | Time (Worst) | Space | Stable | In-place |
|-----------|-------------|------------|--------------|-------|--------|----------|
| Quick Sort | O(n log n) | O(n log n) | O(n^2) | O(log n) | No | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes | No |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No | Yes |
| Insertion | O(n) | O(n^2) | O(n^2) | O(1) | Yes | Yes |
| Tim Sort | O(n) | O(n log n) | O(n log n) | O(n) | Yes | No |

PRACTICAL APPLICATIONS:
-----------------------
• **General purpose sorting** - Fast in practice, good cache performance
• **Internal sorting** - When data fits in memory
• **Virtual memory** - Good locality of reference
• **Embedded systems** - Low memory requirements
• **Libraries** - Often the default sorting algorithm

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Using last element as pivot for sorted data (causes O(n^2))
2. ❌ Not handling duplicate elements efficiently
3. ❌ Recursing on larger subarray first (stack overflow risk)
4. ❌ Off-by-one errors in partition implementation
5. ❌ Forgetting base case (st >= end)

TESTING STRATEGIES:
------------------
• Empty array: [] → []
• Single element: [5] → [5]
• Already sorted: [1, 2, 3, 4] → [1, 2, 3, 4]
• Reverse sorted: [4, 3, 2, 1] → [1, 2, 3, 4] (worst case!)
• All equal: [2, 2, 2, 2] → [2, 2, 2, 2]
• Negative numbers: [-3, -1, -2] → [-3, -2, -1]
• Large array: Test with 100,000+ random elements
• Duplicate heavy: [1, 5, 1, 5, 1, 5, 1, 5]

DEBUGGING TIPS:
--------------
1. Print array after each partition to verify correctness
2. Track pivot value and final pivot position
3. Verify all elements left of pivot ≤ pivot
4. Verify all elements right of pivot > pivot
5. Check recursion depth for potential stack overflow

FUTURE ENHANCEMENTS:
-------------------
1. Generic type support for sorting any Comparable type
2. Custom comparator support
3. Iterative implementation to avoid recursion
4. Multi-threaded version for large arrays
5. Count and return number of comparisons/swaps

KEY LEARNING POINTS:
--------------------
• Partition is the heart of quick sort - O(n) time, in-place
• Pivot selection critically affects performance
• Randomization can guarantee expected O(n log n) time
• In-place sorting saves memory but may not be stable
• Tail recursion optimization can prevent stack overflow

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Quick Select** - Find kth smallest element using partition
2. **Dutch National Flag** - Three-way partitioning
3. **External Quick Sort** - Sort data larger than memory
4. **Parallel Quick Sort** - Multi-threaded implementation
5. **Introsort** - Switch to heap sort if recursion too deep

================================================================================
*/
