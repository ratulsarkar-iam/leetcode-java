package com.rs.leetcode;

import com.rs.leetcode.easy.Leetcode_344;
import com.rs.leetcode.med.Leetcode_53;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Leetcode_53 leet = new Leetcode_53 ();
		int[] matrix={-2,1,-3,4,-1,2,1,-5,4};
        char[] s= new char[]{'h','e','l','l','o'};

        System.out.println (leet.maxSubArray_devide_and_conquer (matrix));

    }

}
