package com.rs.leetcode;

import com.rs.leetcode.easy.Leetcode_136;
import com.rs.leetcode.extra.InversionsCount;
import com.rs.leetcode.med.Leetcode_2596;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Leetcode_136 leet = new Leetcode_136 ();
        int[][] matrix = {{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}};
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        int[] nums = {4,1,2,1,2};
        char[][] chars = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        String s1 = "aab";
//        System.out.println (leet.partition (s1));
//        Permutation_String permutationString= new Permutation_String ();
        System.out.println ( leet.singleNumber (nums));
    }

}
