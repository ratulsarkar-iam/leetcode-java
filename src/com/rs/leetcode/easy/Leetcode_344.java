package com.rs.leetcode.easy;
/* @LeetcodeMeta
 * @Title: Reverse String
 * @TimeComplexity: O(n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Two Pointers
 */

/*
Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.



Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


Constraints:

        1 <= s.length <= 105
s[i] is a printable ascii character.
*/

public class Leetcode_344 {
    //Current:
    //Two Pointers/
    //Simulation
    public void reverseString (char[] s) {
        int n = s.length - 1;
        for (int i = 0; i <= s.length - 1; i++) {
//            System.out.println (s[i]);
            if (i < n) {
                char c1 = s[i];
                char c2 = s[n];
                s[i] = c2;
                s[n] = c1;
                n--;
            }
        }
//        for (int i = 0; i <= s.length - 1; i++) {
//            System.out.println (s[i]);
//        }
    }

//Current complexity:
//O(N)
    public void reverseString_another_way (char[] s) {
        int st = 0;
        int end = s.length - 1;

        while (st < end) {
            swap (s, st, end);
            st++;
            end--;
        }
    }

    private void swap (char[] s, int st, int end) {

        char temp = s[st];
        s[st] = s[end];
        s[end] = temp;
    }

}
