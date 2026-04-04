package com.rs.leetcode.easy;

/*
* Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
The input is generated such that a majority element will exist in the array.


Follow-up: Could you solve the problem in linear time and in O(1) space?
* */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* @LeetcodeMeta
 * @Title: Majority Element
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(n)
 * @Algorithm: Hash Map
 */

/**
 * Author: Ratul Sarkar
 * Date:4/4/26
 */
public class Leetcode_169 {
    // brute force
    public int majorityElement (int[] nums) {
        Map<Integer, Integer> map = new HashMap<> ();

        for (int i : nums) {
            if (map.containsKey (i)) {
                map.put (i, map.get (i) + 1);
            } else {
                map.put (i, 1);
            }
        }
        int max = Collections.max (map.values ());

        if (max > nums.length / 2) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet ()) {
                if (entry.getValue () == max) {
                    return entry.getKey (); // Return the key with the highest value
                }
            }
        }
        return -1;


    }


        public int majorityElement_0_1(int[] nums) {
            int size = nums.length;
            int retVal = 0;
            int freq = 0;
            for (int i = 0; i < size; i++) {
                if (freq == 0) {
                    retVal = nums[i];
                }
                if (retVal == nums[i]) {
                    freq++;
                } else {
                    freq--;
                }

            }
            return retVal;
        }

}


