package com.rs.leetcode.hard;

/* @LeetcodeMeta
 * @Title: Sudoku Solver
 * @TimeComplexity: O(9^m)
 * @SpaceComplexity: O(m)
 * @Algorithm: Backtracking
 */

/**
 * ================================================================================
 * SUDOKU SOLVER - BACKTRACKING SOLUTION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: April 27, 2026
 * <p>
 * <p>
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * 1. Each of the digits 1-9 must occur exactly once in each row
 * 2. Each of the digits 1-9 must occur exactly once in each column
 * 3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes
 * <p>
 * The '.' character indicates empty cells.
 * <p>
 * <p>
 * <p>
 * Example:
 * Input: board = [["5","3",".",".","7",".",".",".","."],
 *                 ["6",".",".","1","9","5",".",".","."],
 *                 [".","9","8",".",".",".",".","6","."],
 *                 ["8",".",".",".","6",".",".",".","3"],
 *                 ["4",".",".","8",".","3",".",".","1"],
 *                 ["7",".",".",".","2",".",".",".","6"],
 *                 [".","6",".",".",".",".","2","8","."],
 *                 [".",".",".","4","1","9",".",".","5"],
 *                 [".",".",".",".","8",".",".","7","9"]]
 * Output: Solved board with all digits 1-9 placed correctly
 * <p>
 * <p>
 * Constraints:
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 * 
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    solveSudoku(board)
 *                           |
 *                           v
 *                    sudoku(0, 0, board)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      rowId == 9 ?                    board[rowId][colId] != '.' ?
 *            |                              |
 *      YES ──→ return TRUE               YES ──→ sudoku(nextRow, nextCol)
 *            |                              |
 *           EXIT                           v
 *                                           
 *            NO                              NO
 *            |                              |
 *            v                              v
 *    Try digits 1-9 in loop         Try digits 1-9 in loop
 *            |                              |
 *            v                              v
 *    ┌───────────────────┐      ┌───────────────────┐
 *    | isSafe() check?  |      | isSafe() check?  |
 *    └───────────────────┘      └───────────────────┘
 *            |                              |
 *   ┌────────┴─────────┐              ┌───────┴───────┐
 *   |                  |              |               |
 *   v                  v              v               v
 * SAFE            UNSAFE         SAFE         UNSAFE
 *   |                  |              |               |
 *   v                  v              v               v
 * Place digit      Try next      Place digit    Try next
 * board[r][c]=d    digit         board[r][c]=d  digit
 *   |                  |              |               |
 *   v                  v              v               v
 * sudoku(nextRow,   Continue      sudoku(nextRow,  Continue
 * nextCol)          loop           nextCol)        loop
 *   |                  |              |               |
 *   v                  v              v               v
 * ┌─┴─┐            ┌───┴───┐      ┌───┴───┐      ┌───┴───┐
 * |TRUE|←──────────| FALSE |      |TRUE|←────────| FALSE |
 * └─┬─┘            └───────┘      └─┬─┘            └───────┘
 *   |                  |              |               |
 *   v                  v              v               v
 * return TRUE      Backtrack     return TRUE    Backtrack
 * (solution)       board[r][c]='.' (solution)    board[r][c]='.'
 *                    |                              |
 *                    v                              v
 *               Try next digit                 Try next digit
 *                    |                              |
 *                    └──────────────┬───────────────┘
 *                                   v
 *                        All digits tried?
 *                                   |
 *                          ┌───────┴───────┐
 *                          |               |
 *                          v               v
 *                        YES             NO (continue)
 *                          |               |
 *                          v               |
 *                    return FALSE         |
 *                    (backtrack)          |
 *                          |               |
 *                          └───────┬───────┘
 *                                  v
 *                           Continue loop
 * 
 * ================================================================================
 * isSafe() METHOD FLOW
 * ================================================================================
 * 
 *                    isSafe(row, col, digit)
 *                           |
 *                           v
 *                    ┌─────────────────┐
 *                    │  ROW CHECK      │
 *                    │  Scan entire    │
 *                    │  row for digit  │
 *                    └────────┬────────┘
 *                             │
 *                     ┌───────┴───────┐
 *                     |               |
 *                     v               v
 *                 FOUND           NOT FOUND
 *                     |               |
 *                     v               v
 *               return FALSE    Continue to
 *               (conflict)      column check
 *                                     |
 *                                     v
 *                          ┌─────────────────┐
 *                          │  COLUMN CHECK   │
 *                          │  Scan entire    │
 *                          │  column for     │
 *                          │  digit          │
 *                          └────────┬────────┘
 *                                   │
 *                            ┌──────┴───────┐
 *                            |              |
 *                            v              v
 *                        FOUND          NOT FOUND
 *                            |              |
 *                            v              v
 *                      return FALSE    Continue to
 *                      (conflict)      box check
 *                                           |
 *                                           v
 *                                ┌─────────────────────┐
 *                                │  3x3 BOX CHECK      │
 *                                │  Calculate:         │
 *                                │  stRow = (row/3)*3   │
 *                                │  stCol = (col/3)*3   │
 *                                │  Scan 3x3 box       │
 *                                └────────┬────────────┘
 *                                         │
 *                                  ┌──────┴───────┐
 *                                  |              |
 *                                  v              v
 *                              FOUND          NOT FOUND
 *                                  |              |
 *                                  v              v
 *                            return FALSE    return TRUE
 *                            (conflict)      (safe)
 * 
 * ================================================================================
 * BOX VALIDATION VISUALIZATION
 * ================================================================================
 * 
 * For a 9x9 board, the 3x3 boxes are indexed as:
 * 
 *     0   1   2   3   4   5   6   7   8 (cols)
 *   ┌───┬───┬───┬───┬───┬───┬───┬───┬───┐
 * 0 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 0 │ 0 │ 0 │ 1 │ 1 │ 1 │ 2 │ 2 │ 2 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 1 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 0 │ 0 │ 0 │ 1 │ 1 │ 1 │ 2 │ 2 │ 2 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 2 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 0 │ 0 │ 0 │ 1 │ 1 │ 1 │ 2 │ 2 │ 2 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 3 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 3 │ 3 │ 3 │ 4 │ 4 │ 4 │ 5 │ 5 │ 5 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 4 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 3 │ 3 │ 3 │ 4 │ 4 │ 4 │ 5 │ 5 │ 5 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 5 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 3 │ 3 │ 3 │ 4 │ 4 │ 4 │ 5 │ 5 │ 5 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 6 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 6 │ 6 │ 6 │ 7 │ 7 │ 7 │ 8 │ 8 │ 8 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 7 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 6 │ 6 │ 6 │ 7 │ 7 │ 7 │ 8 │ 8 │ 8 │
 *   ├───┼───┼───┼───┼───┼───┼───┼───┤
 * 8 │ B │ B │ B │ B │ B │ B │ B │ B │ B │
 *   │ 6 │ 6 │ 6 │ 7 │ 7 │ 7 │ 8 │ 8 │ 8 │
 *   └───┴───┴───┴───┴───┴───┴───┴───┘
 * (rows)
 * 
 * Box index calculation:
 * boxIndex = (row/3) * 3 + (col/3)
 * 
 * Starting position for box validation:
 * stRow = (row/3) * 3  // Gives 0, 3, or 6
 * stCol = (col/3) * 3  // Gives 0, 3, or 6
 * 
 * Example: For position (4, 5):
 * - row/3 = 4/3 = 1, col/3 = 5/3 = 1
 * - Box index = 1*3 + 1 = 4 (center box)
 * - stRow = 1*3 = 3, stCol = 1*3 = 3
 * - Check cells (3,3) to (5,5)
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Solving a simple puzzle (showing first few steps):
 * 
 * Initial board:
 * 5 3 . | . 7 . | . . .
 * 6 . . | 1 9 5 | . . .
 * . 9 8 | . . . | . 6 .
 * ------+-------+------
 * 8 . . | . 6 . | . . .
 * 4 . . | 8 . 3 | . . 1
 * 7 . . | . 2 . | . . 6
 * ------+-------+------
 * . 6 . | . . . | 2 8 .
 * . . . | 4 1 9 | . . 5
 * . . . | . 8 . | . 7 9
 * 
 * Step 1: sudoku(0, 0)
 * - Cell (0,0) has '5' → skip to (0,1)
 * 
 * Step 2: sudoku(0, 1)
 * - Cell (0,1) has '3' → skip to (0,2)
 * 
 * Step 3: sudoku(0, 2) - EMPTY CELL
 * - Try digit 1: isSafe(0,2,'1')?
 *   * Row check: OK
 *   * Col check: OK  
 *   * Box check: OK (box 0 has 5,3)
 * - Place '1' at (0,2)
 * - Recurse: sudoku(0, 3)
 * 
 * Step 4: sudoku(0, 3) - EMPTY CELL
 * - Try digit 1: FAIL (row has '1' at (0,2))
 * - Try digit 2: isSafe(0,3,'2')?
 *   * Row check: OK
 *   * Col check: OK
 *   * Box check: OK
 * - Place '2' at (0,3)
 * - Recurse: sudoku(0, 4)
 * 
 * ... and so on until either:
 * - We reach row 9 → SUCCESS!
 * - We hit a dead end → BACKTRACK!
 * 
 * ================================================================================
 */
public class Leetcode_37 {
    /**
     * Main entry point - solves the Sudoku puzzle using backtracking
     * 
     * @param board 9x9 Sudoku board with '.' for empty cells
     * 
     * Time: O(9^m) where m = number of empty cells
     * Space: O(m) for recursion stack
     * 
     * The method modifies the board in-place to contain the solution
     */
    public void solveSudoku (char[][] board) {
        // Start the backtracking algorithm from (0,0)
        sudoku (0, 0, board);
        
        // Print the solved board (for debugging/verification)
        for (char[] c : board) {
            System.out.println (new String (c));
        }
    }

    /**
     * RECURSIVE BACKTRACKING METHOD
     * Fills the Sudoku board cell by cell using backtracking
     * 
     * @param rowId Current row index (0-8)
     * @param colId Current column index (0-8)
     * @param board The Sudoku board being solved
     * @return true if solution found, false if backtrack needed
     * 
     * BACKTRACKING PATTERN:
     * 1. BASE CASE: If all rows processed → solution found
     * 2. NAVIGATE: Calculate next cell coordinates
     * 3. SKIP: If cell is pre-filled, move to next cell
     * 4. TRY: For each digit 1-9, check if safe to place
     * 5. PLACE: If safe, place digit and recurse to next cell
     * 6. BACKTRACK: If recursion fails, remove digit and try next
     */
    private boolean sudoku (int rowId, int colId, char[][] board) {
        // BASE CASE: All rows processed successfully
        if (rowId == board.length) {
            return true; // Puzzle solved!
        }
        
        // Calculate next cell coordinates
        int nextRow = rowId;
        int nextCol = colId + 1;
        if (nextCol == board.length) {
            nextRow = rowId + 1;
            nextCol = 0;
        }
        
        // SKIP: If current cell is pre-filled, move to next
        if (board[rowId][colId] != '.') {
            return sudoku (nextRow, nextCol, board);
        }
        
        // TRY: Attempt to place digits 1-9 in empty cell
        for (int i = 1; i <= board.length; i++) {
            char digit = (char) (i + '0'); // Convert int to char
            
            // CONSTRAINT CHECK: Is this digit safe to place?
            if (isSafe (rowId, colId, board, digit)) {
                // PLACE: Put digit in current cell
                board[rowId][colId] = digit;
                
                // EXPLORE: Recursively solve the rest of the board
                if (sudoku (nextRow, nextCol, board)) {
                    return true; // Solution found!
                }
                
                // BACKTRACK: Remove digit (didn't lead to solution)
                board[rowId][colId] = '.';
            }
        }
        
        // All digits tried, none worked - trigger backtrack
        return false;
    }

    /**
     * CRITICAL SAFETY CHECK
     * Validates if placing a digit at (rowId, colId) violates Sudoku rules
     * 
     * A position is SAFE if:
     * - Digit not already in the same ROW
     * - Digit not already in the same COLUMN
     * - Digit not already in the same 3x3 BOX
     * 
     * @param rowId Row index to check
     * @param colId Column index to check
     * @param board Current board state
     * @param digit Digit character ('1'-'9') to validate
     * @return true if safe to place, false if conflicts exist
     * 
     * Time: O(9) - checks row, column, and 3x3 box
     * Space: O(1) - no extra space used
     * 
     * NOTE: The box validation uses <= to include all boundary cells.
     * Using < would miss the last row/column of each box (common bug!).
     */
    private boolean isSafe (int rowId, int colId, char[][] board, char digit) {
        // CHECK 1: Row-wise validation
        // Scan entire row to see if digit already exists
        for (int cell : board[rowId]) {
            if (cell == digit) {
                return false; // Conflict in same row
            }
        }
        
        // CHECK 2: Column-wise validation
        // Scan entire column to see if digit already exists
        for (int r = 0; r < board.length; r++) {
            if (board[r][colId] == digit) {
                return false; // Conflict in same column
            }
        }
        
        // CHECK 3: 3x3 Box validation
        // Calculate the starting position of the 3x3 box
        int gridSize = (int) Math.sqrt (board.length); // = 3 for 9x9 board
        int stRow = (rowId / gridSize) * gridSize;    // Starting row of box (0, 3, or 6)
        int stCol = (colId / gridSize) * gridSize;    // Starting col of box (0, 3, or 6)
        
        // Scan all 9 cells in the 3x3 box
        // CRITICAL: Use <= to include boundary cells!
        for (int r = stRow; r <= stRow + (gridSize - 1); r++) {
            for (int c = stCol; c <= stCol + (gridSize - 1); c++) {
                if (board[r][c] == digit) {
                    return false; // Conflict in same 3x3 box
                }
            }
        }
        
        // All checks passed - position is safe!
        return true;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Sudoku Solver (Backtracking)
================================================================================

PROBLEM: Fill empty cells in a 9x9 Sudoku board following three constraints:
         1. Each row contains digits 1-9 exactly once
         2. Each column contains digits 1-9 exactly once  
         3. Each 3x3 sub-box contains digits 1-9 exactly once

APPROACH: Cell-by-Cell Backtracking with Constraint Validation
-------------------------------------------------------------
1. Traverse board left-to-right, top-to-bottom
2. When encountering empty cell ('.'), try digits 1-9
3. Before placing, check if digit is valid (no row/col/box conflicts)
4. If valid → place digit, recurse to next cell
5. If all cells filled → solution found
6. Backtrack by removing digit and trying next digit

BACKTRACKING PATTERN: Choose → Constraint → Explore → Unchoose
----------------------------------------------------------------
CHOOSE:      board[row][col] = digit           // Place digit
CONSTRAINT:  if (isSafe(board, row, col, digit)) // Check safety
EXPLORE:     sudoku(nextRow, nextCol, board)   // Recurse next cell
UNCHOOSE:    board[row][col] = '.'             // Remove digit (backtrack)

TIME COMPLEXITY: O(9^m) where m = number of empty cells
-----------------------
• For each empty cell: up to 9 choices (digits 1-9)
• For each choice: recursively solve remaining board
• Creates decision tree with 9 branches at each empty cell
• Total nodes = 9^m in worst case

Detailed breakdown:
- Total recursive calls: O(9^m) in worst case
- Each call does O(9) work in isSafe() to check constraints
- Overall: O(9^m × 9) but dominated by O(9^m)

Practical performance:
- Easy puzzles (m=30): ~9^30 operations worst case
- Hard puzzles (m=50): ~9^50 operations worst case
- Actual runtime is much better due to constraint propagation

SPACE COMPLEXITY: O(m) where m = number of empty cells
------------------------
• Board storage: O(81) - 9x9 char array (modified in-place)
• Recursion stack: O(m) - maximum depth = number of empty cells
• No additional storage for solutions (board modified directly)
• Total auxiliary space: O(m) (recursion stack dominates)

KEY INSIGHTS:
-----------
• **Constraint propagation** - isSafe() prunes invalid branches early
• **Box validation formula** - (row/3)*3 and (col/3)*3 give box start position
• **Backtracking power** - Systematically explores without trying all 9^81 possibilities
• **In-place modification** - No need for board copies, saves space
• **Single solution guarantee** - Can return first solution found

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Pre-computed validity arrays**
   - boolean[][] rows = new boolean[9][10]  // rows[r][digit]
   - boolean[][] cols = new boolean[9][10]  // cols[c][digit]
   - boolean[][] boxes = new boolean[9][10] // boxes[b][digit]
   - Reduces isSafe() from O(9) to O(1)

2. **Heuristic cell selection**
   - Choose cell with minimum valid digits (MRV heuristic)
   - Reduces branching factor significantly

3. **Bit masking**
   - Use 9-bit integers to track used digits
   - Faster bitwise operations instead of array lookups

4. **Parallel solving**
   - Try different digits in parallel threads
   - Useful for very hard puzzles

5. **Forward checking**
   - Maintain domains of possible digits for each cell
   - Prune when domain becomes empty

COMPARISON WITH OTHER BACKTRACKING PROBLEMS:
--------------------------------------------
| Problem | State Space | Constraints | Complexity |
|---------|-------------|-------------|------------|
| Sudoku | 9^(empty cells) | Row/Col/Box | O(9^m) |
| N-Queens | n! | 4 directions | O(n!) |
| Permutations | n! | No duplicates | O(n! × n) |
| Subset Sum | 2^n | Target sum | O(2^n) |

PRACTICAL APPLICATIONS:
-----------------------
• **Constraint satisfaction** - Template for CSP problems
• **Puzzle solving** - Crosswords, KenKen, etc.
• **Scheduling** - Non-conflicting time slot assignment
• **Resource allocation** - Assign resources without conflicts
• **Algorithm education** - Classic backtracking example

WHY THIS WORKS:
--------------
Backtracking systematically explores the solution space by:
1. Making a choice (place digit)
2. Checking constraints (isSafe)
3. Recursively solving subproblems (next cell)
4. Undoing choices that lead to dead ends (backtrack)

The key is PRUNING - we abandon entire branches as soon as we detect
a constraint violation, avoiding the need to explore all 9^81 possible
board configurations.

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Forgetting to backtrack (remove digit after recursion)
2. ❌ Using < instead of <= in box validation (misses boundary cells)
3. ❌ Wrong box calculation (not using integer division)
4. ❌ Not handling row transition when col reaches 8
5. ❌ Converting digit incorrectly (char)(i + '0') not (char)i

TESTING STRATEGIES:
------------------
• Easy puzzles: 30-40 empty cells
• Medium puzzles: 40-50 empty cells  
• Hard puzzles: 50+ empty cells
• Edge case: Already solved board
• Edge case: Board with single empty cell

DEBUGGING TIPS:
--------------
1. Add print statements to show digit placement
2. Track recursion depth to understand backtracking
3. Count isSafe() calls to measure constraint checking
4. Visualize board state at each recursion level

FUTURE ENHANCEMENTS:
-------------------
1. Support for N×N Sudoku where N is a perfect square
2. Multiple solution finding (instead of just first)
3. Difficulty estimation based on empty cells and constraints
4. Hint generation system (next valid move without full solve)
5. Puzzle generation with guaranteed unique solution
================================================================================
*/
