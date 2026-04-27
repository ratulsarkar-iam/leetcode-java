package com.rs.leetcode.prc;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ratul Sarkar
 * Date:4/27/26
 *
 * @see com.rs.leetcode.hard.Leetcode_51
 */

public class L51_D1 {
    public List<List<String>> solveNQueens (int n) {
        List<List<String>> retList = new ArrayList<> ();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        placeQueens (board, retList, n, 0);
        return retList;
    }


    private boolean isSafe (int rowId, int colId, int n, char[][] board) {
        // RowWise
        for (int col = 0; col < n; col++) {
            if (board[rowId][col] == 'Q') {
                return false;
            }
        }
        // ColWise
        for (int row = 0; row < n; row++) {
            if (board[row][colId] == 'Q') {
                return false;
            }
        }
        // LeftDiag
        for (int r = rowId, c = colId; r >= 0 && c >= 0; r--, c--) {
//            System.out.println ("left :" + r + " - " + c);
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // RightDiag
        for (int r = rowId, c = colId; r >= 0 && c < n; r--, c++) {
//            System.out.println ("right :" + r + " - " + c);
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }
private List<String> getString(char[][]board){
        List<String> sList=new ArrayList<> ();
        for(char[] c:board){
            sList.add (new String (c));
        }
        return sList;
}
    private void placeQueens (char[][] board, List<List<String>> retList, int n, int rowId) {
        if (rowId == n) {
            // retList populate
//            System.out.println (board);
            retList.add (getString (board));
            return;
        }
        for (int colId = 0; colId < n; colId++) {
            if (isSafe (rowId, colId, n, board)) {
                board[rowId][colId] = 'Q';
                placeQueens (board, retList, n, rowId + 1);
                board[rowId][colId] = '.';
            }
        }
    }
}
