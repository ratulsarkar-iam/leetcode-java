package com.rs.leetcode.med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
*Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
* */


public class Leetcode_15 {
    //Brute force

    /**
     * TC : O(n^3 log n)
     * SC: O(n+triplet*3)
     * This method uses three nested loops to find all combinations and a set to filter out duplicate triplets. It has a high time complexity and is primarily used for understanding the core logic.
     *
     */
    public List<List<Integer>> threeSum_Brute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    List<Integer> is = new ArrayList<>();
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        is.add(nums[i]);
                        is.add(nums[j]);
                        is.add(nums[k]);
                        Collections.sort(is);
                        if (!lists.contains(is)) {
                            lists.add(is);
                        }
                    }
                }
            }
        }
        return lists;
    }
//
//    /**
//     * Brute but optimized
//     * considering {@link com.rs.leetcode.easy.Leetcode_1}
//     *
//     * An optimized version that uses a hash map/set to store values, reducing the need for the third nested loop. This brings the time complexity down to O(n²).
//     */
//
//    public List<List<Integer>> threeSum_option_2(int[] nums) {
//        List<List<Integer>> lists = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//int target=-nums[i];
//            for (int j = i + 1; j < nums.length; j++) {
//                int thirdNum=target-nums[j];
//                System.out.println(nums[i]+" -> "+thirdNum+" "+target +" "+ nums[j]);
//            }
//        }
//        return lists;
//    }

    /**
     * This is the most optimal solution. By first sorting the array,
     * the problem becomes significantly more efficient to solve.
     * This approach uses one fixed element and two moving pointers
     * (left and right) to find the remaining two numbers,
     * resulting in a cleaner and faster implementation.
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
      for(int i=0; i<nums.length;i++){
          if(i>0&& nums[i]==nums[i-1]){
              continue;
          }
          int j=i+1;
          int k =nums.length-1;
          while (j<k){
              int sum=nums[i]+nums[j]+nums[k];
              if(sum<0){
                  j++;
              }else if(sum>0){
                  k--;
              }else{
                  lists.add(Arrays.asList(nums[i],nums[j],nums[k]));
                  j++;
                  k--;
                  while (j<k && nums[j]==nums[j-1]){
                      j++;
                  }

              }
          }
      }
        return lists;
    }
}
