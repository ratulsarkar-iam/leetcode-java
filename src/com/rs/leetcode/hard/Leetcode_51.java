package com.rs.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/* @LeetcodeMeta
 * @Title: N-Queens
 * @TimeComplexity: O(n!)
 * @SpaceComplexity: O(n^2)
 * @Algorithm: Backtracking
 */

/**
 * ================================================================================
 * N-QUEENS PROBLEM - BACKTRACKING SOLUTION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: 4/24/26
 * <p>
 * <p>
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <a href="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg">IMAGE</a>
 * <p>
 * Input: n = 4
 * Output: [[".Q..","...Q","Q..."," ̰..Q."],["..Q.","Q...","...Q",".Q.."]] ̰
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [["Q"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 9
 */
public class Leetcode_51 {
    /**
     * Main entry point - initializes the board and starts backtracking
     * 
     * @param n Size of the chessboard (n x n)
     * @return List of all valid board configurations
     * 
     * Time: O(n!) - factorial solutions to explore
     * Space: O(n^2) - board storage
     */
    public List<List<String>> solveNQueens (int n) {
        // Initialize the n x n board with all empty cells ('.')
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        // Result list to store all valid solutions
        List<List<String>> retVal = new ArrayList<> ();
        
        // Start backtracking from row 0
        placeQueens (board, 0, retVal, n);
        
        return retVal;
    }

    /**
     * RECURSIVE BACKTRACKING METHOD
     * Places queens row by row, ensuring no conflicts
     * 
     * @param board Current state of the chessboard
     * @param row Current row being processed (0-indexed)
     * @param retVal List to store all valid solutions
     * @param n Board size
     * 
     * BACKTRACKING PATTERN:
     * 1. BASE CASE: If all rows filled → valid solution found
     * 2. CHOOSE: Try placing queen at each column in current row
     * 3. CONSTRAINT: Only place if position is safe (no conflicts)
     * 4. EXPLORE: Recursively solve for next row
     * 5. UNCHOOSE: Remove queen (backtrack) to try other positions
     */
    private void placeQueens (char[][] board, int row, List<List<String>> retVal, int n) {
        // BASE CASE: All queens placed successfully
        if (row == board.length) {
            // Convert board to string format and add to results
            retVal.add (getStringVal (board));
            return;
        }
        
        // RECURSIVE CASE: Try placing queen in each column of current row
        for (int col = 0; col < board.length; col++) {
            // CONSTRAINT CHECK: Is this position safe?
            if (isSafe (board, row, col, n)) {
                // CHOOSE: Place queen at (row, col)
                board[row][col] = 'Q';
                
                // EXPLORE: Recursively place queens in next row
                placeQueens (board, row + 1, retVal, n);
                
                // UNCHOOSE: Remove queen (backtrack) to try next column
                board[row][col] = '.';
            }
        }
    }

    /**
     * Converts the 2D char board to List<String> format
     * Each row becomes a string (e.g., ".Q.." for a 4x4 board)
     * 
     * @param board Current board configuration
     * @return List of strings representing each row
     * 
     * Time: O(n^2) - iterate through all cells
     * Space: O(n^2) - create new string list
     */
    private List<String> getStringVal (char[][] board) {
        List<String> sList = new ArrayList<> ();
        
        // Convert each row (char array) to a String
        for (char[] row : board) {
            sList.add (new String (row));
        }
        
        return sList;
    }

    /**
     * CRITICAL SAFETY CHECK
     * Validates if placing a queen at (row, col) is safe
     * 
     * A position is SAFE if no other queen can attack it:
     * - No queen in the same ROW
     * - No queen in the same COLUMN
     * - No queen in the same DIAGONAL (both directions)
     * 
     * @param board Current board state
     * @param row Row to check
     * @param col Column to check
     * @param n Board size
     * @return true if safe, false if conflicts exist
     * 
     * Time: O(n) - check row, column, and diagonals
     * Space: O(1) - no extra space
     * 
     * NOTE: We only check UPWARD directions because we place queens
     * row by row from top to bottom. Lower rows are still empty.
     */
    private boolean isSafe (char[][] board, int row, int col, int n) {
        // CHECK 1: Row-wise (horizontal) - any queen in same row?
        // Not strictly needed since we place one queen per row,
        // but included for completeness
        for (int c = 0; c < n; c++) {
            if (board[row][c] == 'Q') {
                return false;  // Conflict found in same row
            }
        }
        
        // CHECK 2: Column-wise (vertical) - any queen in same column?
        // Check all rows above current row
        for (int r = 0; r < n; r++) {
            if (board[r][col] == 'Q') {
                return false;  // Conflict found in same column
            }
        }
        
        // CHECK 3: Upper-left diagonal (↖ direction)
        // Move diagonally up-left from (row, col)
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;  // Conflict found on upper-left diagonal
            }
        }
        
        // CHECK 4: Upper-right diagonal (↗ direction)
        // Move diagonally up-right from (row, col)
        for (int r = row, c = col; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;  // Conflict found on upper-right diagonal
            }
        }
        
        // All checks passed - position is safe!
        return true;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - N-Queens Problem (Backtracking)
================================================================================

PROBLEM: Place N queens on an N×N chessboard such that no two queens attack 
         each other (same row, column, or diagonal).

APPROACH: Row-by-Row Backtracking with Constraint Checking
-----------------------------------------------------------
1. Place queens ONE PER ROW (guarantees no row conflicts)
2. For each row, try placing queen in EACH COLUMN
3. Before placing, check if position is SAFE (no column/diagonal conflicts)
4. If safe → place queen, recurse to next row
5. If all rows filled → valid solution found
6. Backtrack by removing queen and trying next column

BACKTRACKING PATTERN: Choose → Constraint → Explore → Unchoose
----------------------------------------------------------------
CHOOSE:      board[row][col] = 'Q'           // Place queen
CONSTRAINT:  if (isSafe(board, row, col))    // Check safety
EXPLORE:     placeQueens(board, row + 1)     // Recurse next row
UNCHOOSE:    board[row][col] = '.'           // Remove queen (backtrack)

EXECUTION TRACE for n=4 (first solution):
------------------------------------------

Initial board (4×4):
. . . .
. . . .
. . . .
. . . .

Step 1: Try placing queen in Row 0
├─ Try col 0: [Q,.,.,.]
│  └─ Row 1, col 0: UNSAFE (same column)
│  └─ Row 1, col 1: UNSAFE (diagonal)
│  └─ Row 1, col 2: SAFE → Place [.,.,Q,.]
│     └─ Row 2, col 0: UNSAFE (diagonal)
│     └─ Row 2, col 1: UNSAFE (column)
│     └─ Row 2, col 2: UNSAFE (column)
│     └─ Row 2, col 3: UNSAFE (diagonal)
│     └─ DEAD END → Backtrack to Row 1
│  └─ Row 1, col 3: SAFE → Place [...,Q]
│     └─ Row 2, col 0: UNSAFE (diagonal)
│     └─ Row 2, col 1: SAFE → Place [.,Q,.,.]
│        └─ Row 3, col 0: UNSAFE (column)
│        └─ Row 3, col 1: UNSAFE (column)
│        └─ Row 3, col 2: UNSAFE (diagonal)
│        └─ Row 3, col 3: UNSAFE (column)
│        └─ DEAD END → Backtrack to Row 2
│     └─ DEAD END → Backtrack to Row 1
│  └─ DEAD END → Backtrack to Row 0
│
├─ Try col 1: [.,Q,.,.]
│  └─ Row 1, col 0: UNSAFE (diagonal)
│  └─ Row 1, col 1: UNSAFE (column)
│  └─ Row 1, col 2: UNSAFE (diagonal)
│  └─ Row 1, col 3: SAFE → Place [...,Q]
│     └─ Row 2, col 0: SAFE → Place [Q,.,.,.]
│        └─ Row 3, col 0: UNSAFE (column)
│        └─ Row 3, col 1: UNSAFE (diagonal)
│        └─ Row 3, col 2: SAFE → Place [.,.,Q,.]
│           └─ SUCCESS! Solution found:
│              . Q . .
│              . . . Q
│              Q . . .
│              . . Q .

VISUAL REPRESENTATION of isSafe() checks:
-----------------------------------------
For position (row=2, col=1) marked with X:

Checking directions (only UPWARD since we fill top-to-bottom):

    0   1   2   3
  ┌───┬───┬───┬───┐
0 │ ↖ │ ↑ │ ↗ │   │  ← Check upper-left diagonal (↖)
  ├───┼───┼───┼───┤     Check column upward (↑)
1 │ ↖ │ ↑ │ ↗ │   │     Check upper-right diagonal (↗)
  ├───┼───┼───┼───┤
2 │ ← │ X │ → │ → │  ← Check current row (←→) [optional]
  ├───┼───┼───┼───┤
3 │   │   │   │   │  ← Not checked (empty rows below)
  └───┴───┴───┴───┘

WHY only check UPWARD?
- We place queens row-by-row from top (row 0) to bottom (row n-1)
- Lower rows are always empty when checking current position
- No need to check downward directions

TIME COMPLEXITY: O(n!)
-----------------------
• For the first row: n choices (n columns)
• For the second row: at most (n-2) valid choices (excluding attacked positions)
• For the third row: at most (n-4) valid choices
• Pattern: n × (n-2) × (n-4) × ... ≈ n!

Detailed breakdown:
- Total recursive calls: O(n!) in worst case
- Each call does O(n) work in isSafe() to check constraints
- Overall: O(n! × n) but dominated by O(n!)

For n=8 (standard chessboard): ~92 solutions, ~2,057 nodes explored
For n=4: 2 solutions, ~16 nodes explored

SPACE COMPLEXITY: O(n²)
------------------------
• Board storage: O(n²) - char[n][n] array
• Recursion stack: O(n) - maximum depth = n rows
• Solution storage: O(n² × k) where k = number of solutions
  - For n=8: 92 solutions × 64 cells = ~5,888 cells
• Total auxiliary space: O(n²) (board dominates)

KEY INSIGHTS:
-----------
• **One queen per row** - Eliminates row conflicts by design
• **Column conflicts** - Tracked by checking entire column upward
• **Diagonal conflicts** - Two types:
  - Upper-left (↖): row--, col--
  - Upper-right (↗): row--, col++
• **Pruning power** - isSafe() prunes invalid branches early, avoiding
  exponential explosion of 2^(n²) possible board states
• **Symmetry** - Solutions often come in symmetric pairs (mirror images)

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Bit manipulation** - Use bitmasks to track attacked columns/diagonals
   - Space: O(1) instead of O(n²)
   - Time: O(1) for isSafe() instead of O(n)

2. **Diagonal tracking arrays**
   - leftDiag[row - col + n - 1] = true
   - rightDiag[row + col] = true
   - Reduces isSafe() from O(n) to O(1)

3. **Symmetry pruning** - Only explore half the first row, mirror solutions

COMPARISON WITH OTHER BACKTRACKING PROBLEMS:
--------------------------------------------
| Problem | State Space | Constraints | Complexity |
|---------|-------------|-------------|------------|
| N-Queens | n! | 4 directions | O(n!) |
| Permutations | n! | No duplicates | O(n! × n) |
| Sudoku | 9^(empty cells) | Row/Col/Box | O(9^m) |
| Subset Sum | 2^n | Target sum | O(2^n) |

PRACTICAL APPLICATIONS:
-----------------------
• **Constraint satisfaction** - Template for CSP problems
• **Resource allocation** - Non-conflicting task scheduling
• **Graph coloring** - Assign colors without adjacent conflicts
• **Puzzle solving** - Sudoku, crosswords, etc.
• **Algorithm education** - Classic backtracking example

WHY THIS WORKS:
--------------
Backtracking systematically explores the solution space by:
1. Making a choice (place queen)
2. Checking constraints (isSafe)
3. Recursively solving subproblems (next row)
4. Undoing choices that lead to dead ends (backtrack)

The key is PRUNING - we abandon entire branches as soon as we detect
a constraint violation, avoiding the need to explore all 2^(n²) possible
board configurations.

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Forgetting to backtrack (remove queen after recursion)
2. ❌ Checking downward in isSafe() (unnecessary, rows below are empty)
3. ❌ Not creating a copy when adding solution to result list
4. ❌ Checking only row/column but forgetting diagonals
5. ❌ Using wrong diagonal formulas (r-c vs r+c)

TESTING EDGE CASES:
------------------
• n=1: Single queen → [["Q"]]
• n=2, n=3: No solutions possible
• n=4: 2 solutions (shown above)
• n=8: 92 solutions (classic chessboard)
• n=9: Maximum constraint (1 ≤ n ≤ 9)
================================================================================
*/
