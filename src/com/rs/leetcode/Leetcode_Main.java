package com.rs.leetcode;

import com.rs.leetcode.med.Leetcode_240;
import com.rs.leetcode.med.Leetcode_50;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Leetcode_240 leet = new Leetcode_240 ();
		int[][] matrix={{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        char[] s= new char[]{'h','e','l','l','o'};

        System.out.println (leet.searchMatrix (matrix,21));

    }

}
