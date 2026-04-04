package com.rs.leetcode.med;

/* @LeetcodeMeta
 * @Title: Search a 2D Matrix
 * @TimeComplexity: O(log(m * n))
 * @SpaceComplexity: O(1)
 * @Algorithm: Binary Search
 */

/*
* You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
*
* Example 1:
https://assets.leetcode.com/uploads/2020/10/05/mat.jpg

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:

https://assets.leetcode.com/uploads/2020/10/05/mat2.jpg
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
* */
public class Leetcode_74 {
    public boolean searchMatrix (int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int st = 0;
        int end = m - 1;
        while (st <= end) {
            int midR = st + (end - st) / 2;
//            System.out.println (matrix[midR][0] + " " + target + " " + matrix[midR][n - 1]);
            if (matrix[midR][n - 1] >= target && matrix[midR][0] <= target) {
                int stRs = 0;
                int endRs = n - 1;
                while (stRs <= endRs) {
                    int midRs = stRs + (endRs - stRs) / 2;
                    if (matrix[midR][midRs] == target) {
                        return true;
                    } else if (matrix[midR][midRs] < target) {
                        stRs = midRs + 1;
                    } else {
                        endRs = midRs - 1;
                    }
                }
                return false;
            } else if (matrix[midR][n - 1] < target) {
                st = midR + 1;
            } else {
                end = midR - 1;
            }


        }
        return false;
    }
}
