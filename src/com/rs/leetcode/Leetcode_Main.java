package com.rs.leetcode;

import com.rs.leetcode.hard.Code360_RatInAMaze;
import com.rs.leetcode.hard.Code360_RatInAMaze_Optimized;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Code360_RatInAMaze_Optimized leet = new Code360_RatInAMaze_Optimized ();
        int[][] matrix = {{1, 0, 0, 0}, {1, 1, 0, 0}, {1, 1, 0, 0}, {0, 1, 1, 1}};
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        int[] nums = {1, 2, 3};
        char[][] chars = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println (leet.findSum (matrix, 4));
//        Permutation_String permutationString= new Permutation_String ();
//        permutationString.printPermutationOfString ("abc");
    }

}
