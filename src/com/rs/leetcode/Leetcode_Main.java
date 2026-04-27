package com.rs.leetcode;

import com.rs.leetcode.prc.L51_D1;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        L51_D1 leet = new L51_D1 ();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        int[] nums = {1, 2, 3};

        System.out.println (leet.solveNQueens (1));
//        Permutation_String permutationString= new Permutation_String ();
//        permutationString.printPermutationOfString ("abc");
    }

}
