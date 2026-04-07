package com.rs.leetcode.med;
/* @LeetcodeMeta
 * @Title: Search a 2D Matrix II
 * @TimeComplexity: O(n log n)
 * @SpaceComplexity: O(1)
 * @Algorithm: TODO
 */

/*
* Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.


Example 1:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
Example 2:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false


Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109
* */

/**
 * Author: Ratul Sarkar
 * Date:4/4/26
 */
public class Leetcode_240 {
    public boolean searchMatrix (int[][] matrix, int target) {
        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < height; i++) {
            if (matrix[i][0] <= target && matrix[i][width - 1] >= target) {
                // binary search;
                int st = 0;
                int end = width - 1;
                while (st <= end) {
                    int mid = st + (end - st) / 2;
                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (target < matrix[i][mid]) {
                        end = mid - 1;
                    } else {
                        st = mid + 1;
                    }
                }
            }
        }
        return false;
    }
}
