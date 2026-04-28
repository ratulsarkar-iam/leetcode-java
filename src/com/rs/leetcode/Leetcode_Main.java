package com.rs.leetcode;

import com.rs.leetcode.hard.Leetcode_37;
import com.rs.leetcode.hard.Leetcode_51;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        Leetcode_37 leet = new Leetcode_37 ();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        int[] nums = {1, 2, 3};
        char[][] chars={{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};

        leet.solveSudoku (chars);
//        Permutation_String permutationString= new Permutation_String ();
//        permutationString.printPermutationOfString ("abc");
    }

}
