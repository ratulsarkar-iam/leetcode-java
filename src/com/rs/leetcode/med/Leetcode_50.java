package com.rs.leetcode.med;

/* @LeetcodeMeta
 * @Title: Pow(x, n)
 * @TimeComplexity: O(log n)
 * @SpaceComplexity: O(1)
 * @Algorithm: TODO
 */

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -104 <= xn <= 104
 * <p>
 * <p>
 * Author: Ratul Sarkar
 * Date:4/4/26
 */
public class Leetcode_50 {
    public double myPow (double x, int n) {
        long d = n;
        double pow = 1;
        // negative value is taken cared.
        if (d < 0) {
            d = d * -1;
            x = 1 / x;
        }
        while (d > 0) {
            if (d % 2 == 1) {
                pow = pow * x;
            }
            x = x * x;
            d = d / 2;
        }
        return pow;
    }
}
