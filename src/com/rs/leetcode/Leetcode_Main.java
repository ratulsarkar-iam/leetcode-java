package com.rs.leetcode;

import com.rs.leetcode.extra.MergeSort;
import com.rs.leetcode.extra.QuickSort;

public class Leetcode_Main {

    public static void main (String[] args) {
        // TODO Auto-generated method stub
        QuickSort leet = new QuickSort ();
        int[][] matrix = {{1, 0, 0, 0}, {1, 1, 0, 0}, {1, 1, 0, 0}, {0, 1, 1, 1}};
        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        int[] nums = {1, 0,13,2, 9, 0, 3, 6};
        char[][] chars = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        String s1 = "aab";
//        System.out.println (leet.partition (s1));
//        Permutation_String permutationString= new Permutation_String ();
        leet.quickSort (nums);
    }

}
