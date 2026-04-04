package com.rs.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/* @LeetcodeMeta
 * @Title: Two Sum
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(n)
 * @Algorithm: Hash Map
 */

/*
* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
*
*
* */
public class Leetcode_1 {
    public int[] twoSum (int[] nums, int target) {
        int[] r = new int[2];
        Map<Integer,Integer> existsMap= new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (!existsMap.containsKey (target-nums[i])){
                existsMap.put (nums[i],i);
            }else{
                r[0]=i;
                r[1]=existsMap.get(target-nums[i]);
                System.out.println (r[0]+" "+r[1]);
            }
        }
        return r;
    }
}
