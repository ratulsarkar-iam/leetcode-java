package com.rs.leetcode.easy;

/* @LeetcodeMeta
 * @Title: Binary Search
 * @TimeComplexity: O(log n)
 * @SpaceComplexity: O(1)
 * @Algorithm: TODO
 */

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 *
 *
 */
public class Leetcode_704 {
    public int search(int[] nums, int target) {
        int st = 0;
        int end = nums.length - 1;
        while (st < end) {
            int mid = st + (end - st) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return -1;
    }

    public int search_rec(int[] nums, int target) {
        int st = 0;
        int end = nums.length - 1;
        return serach(nums, st, end, target);
    }
//    {-1,0,3,5,9,12};
    private int serach(int[] nums, int st, int end, int target) {
        if (st <= end) {
            int mid = st + (end - st) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= target) {
                return serach(nums, st, mid-1, target);
            } else {
                return serach(nums, mid + 1, end, target);
            }
        }
        return -1;
    }
}
