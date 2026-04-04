package com.rs.leetcode.med;


/* @LeetcodeMeta
 * @Title: Maximum Subarray
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Kadan's Algorithm
 */

/*
*
* Given an integer array nums, find the subarray with the largest sum, and return its sum.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*
*
* */
public class Leetcode_53 {
    public int maxSubArray (int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            currentSum += nums[i];
            maxSum = Math.max (currentSum, maxSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    //    @Algorithm: divide and conquer
//     * @TimeComplexity: O(n log n)
// * @SpaceComplexity: O(log n)
    public int maxSubArray_devide_and_conquer (int[] nums) {
        return divide (nums, 0, nums.length - 1);
    }

    private int divide (int[] nums, int left, int right) {
        // Base case
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        // Recursively solve left and right
        int leftMax = divide (nums, left, mid);
        int rightMax = divide (nums, mid + 1, right);

        // Compute crossing sum
        int crossMax = crossSum (nums, left, mid, right);

        return Math.max (Math.max (leftMax, rightMax), crossMax);
    }

    private int crossSum (int[] nums, int left, int mid, int right) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;

        // max sum from mid → left
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max (leftSum, sum);
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;

        // max sum from mid+1 → right
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max (rightSum, sum);
        }

        return leftSum + rightSum;
    }
}
