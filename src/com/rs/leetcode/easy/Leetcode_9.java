package com.rs.leetcode.easy;

/* @LeetcodeMeta
 * @Title: Palindrome Number
 * @TimeComplexity: O(log n)
 * @SpaceComplexity: O(1)
 * @Algorithm: Mathematical Operations
 */

/*
Given an integer x, return true if x is a palindrome, and false otherwise.

		 

Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 

Constraints:

-231 <= x <= 231 - 1
 
--meaning: -2147483648 <= x <= 2147483647

Follow up: Could you solve it without converting the integer to a string?
*/
public class Leetcode_9 {
	public boolean isPalindrome(int x) {
		if(x<0) {
			return false;
		}
		int y=x;
		long ret = 0;
		while (x != 0) {
			ret = ret * 10 + (x % 10);
			if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
				ret= 0;
				break;
			}
			x = x / 10;
		}
		
		if((int)ret==y) {
			return true;
		}
		return false;
	}
}
