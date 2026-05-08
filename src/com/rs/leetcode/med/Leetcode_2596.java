package com.rs.leetcode.med;

/* @LeetcodeMeta
 * @Title: Check Knight Tour Configuration
 * @TimeComplexity: O(8^(n^2))
 * @SpaceComplexity: O(n^2)
 * @Algorithm: Backtracking/DFS
 */

/**
 * ================================================================================
 * CHECK KNIGHT TOUR CONFIGURATION - BACKTRACKING VALIDATION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 8, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Validate if a given grid represents a valid knight's tour.
 * A knight's tour is a sequence of moves by a knight on a chessboard such that
 * the knight visits every square exactly once. The knight starts at (0,0) and
 * the grid contains numbers from 0 to n²-1 indicating the order of visits.
 * <p>
 * A knight moves in an L-shape: 2 squares in one direction and 1 square
 * perpendicular to that. There are 8 possible moves from any position.
 * <p>
 * <p>
 * Example 1: Valid Tour
 * Input: grid = [[0,11,16,5,20],
 *                [17,4,19,10,15],
 *                [12,1,8,21,6],
 *                [3,18,23,14,9],
 *                [24,13,2,7,22]]
 * Output: true
 * Explanation: This represents a complete knight's tour
 * <p>
 * Example 2: Invalid Tour
 * Input: grid = [[0,3,6],
 *                [5,8,1],
 *                [2,7,4]]
 * Output: false
 * Explanation: The 8th move is not a valid knight move
 * <p>
 * <p>
 * Constraints:
 * - 3 ≤ n ≤ 7 (board size)
 * - All integers from 0 to n²-1 appear exactly once
 * - Knight must start at (0,0) with value 0
 * <p>
 * ================================================================================
 * KNIGHT MOVES VISUALIZATION
 * ================================================================================
 * 
 * From any position (r, c), a knight can move to 8 possible positions:
 * 
 *     ┌───┬───┬───┬───┬───┐
 *     │   │   │ 7 │   │ 8 │
 *     ├───┼───┼───┼───┼───┤
 *     │   │ 6 │   │   │ 1 │
 *     ├───┼───┼───┼───┼───┤
 *     │ 5 │   │ K │   │ 2 │
 *     ├───┼───┼───┼───┼───┤
 *     │   │ 4 │   │   │ 3 │
 *     ├───┼───┼───┼───┼───┤
 *     │   │   │ 3 │   │ 4 │
 *     └───┴───┴───┴───┴───┘
 * 
 * Move coordinates (row, col):
 * 1: (-2, +1)    2: (-1, +2)    3: (+1, +2)    4: (+2, +1)
 * 5: (+2, -1)    6: (+1, -2)    7: (-1, -2)    8: (-2, -1)
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    checkValidGrid(grid)
 *                           |
 *                           v
 *                 canMoveKnight(grid, 0, 0, n, 0)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      Out of bounds OR              Expected value is
 *      wrong value?                   n²-1 (last)?
 *            |                          |
 *      YES ──→ return false           YES ──→ return true
 *            |                          |
 *           EXIT                         EXIT
 *            NO                          NO
 *            |                          |
 *            v                          v
 *    ┌─────────────────────┐    ┌─────────────────┐
 *    |   TRY ALL 8         |    |   TRY ALL 8     |
 *    |   KNIGHT MOVES      |    |   KNIGHT MOVES  |
 *    |   from current      |    |   recursively    |
 *    |   position          |    |                 |
 *    └─────────────────────┘    └─────────────────┘
 *            |                          |
 *            v                          v
 *    Return true if ANY            Return false if
 *    move leads to solution        ALL moves fail
 * 
 * ================================================================================
 * RECURSION TREE EXAMPLE
 * ================================================================================
 * 
 * Validating a 3x3 grid (partial tree):
 * 
 *                    canMoveKnight(0,0,0)
 *                           |
 *              ┌──────┬──────┬──────┐
 *              |      |      |      |
 *              v      v      v      v
 *          (-2,1) (-1,2) (1,2)  (2,1)
 *          (OOB)  (OOB)  (1,2)  (2,1)
 *                 |      |      |
 *                 v      v      v
 *            canMoveKnight  canMoveKnight
 *            (1,2,1)        (2,1,1)
 *                 |              |
 *            ...            ...
 * 
 * OOB = Out of Bounds
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing grid = [[0,3,6],[5,8,1],[2,7,4]]:
 * 
 * Step 1: canMoveKnight(0, 0, 0)
 * - grid[0][0] = 0 ✓ (matches expected)
 * - Not last move (0 ≠ 8)
 * - Try all 8 moves from (0,0)
 * 
 * Step 2: Try move (-2, 1) → (-2, 1)
 * - Out of bounds → return false
 * 
 * Step 3: Try move (-1, 2) → (-1, 2)
 * - Out of bounds → return false
 * 
 * Step 4: Try move (1, 2) → (1, 2)
 * - In bounds, grid[1][2] = 1 ✓ (expected)
 * - Recurse: canMoveKnight(1, 2, 1)
 * 
 * Step 5: From (1,2), try moves to find value 2
 * - Only valid move leads to (0,0) which has 0 ≠ 2
 * - All moves fail → return false
 * 
 * Step 6: Continue trying other initial moves...
 * - Eventually all paths fail → return false
 * 
 * Final result: false (invalid knight tour)
 * 
 * ================================================================================
 */
public class Leetcode_2596 {
    /**
     * Main entry point - validates if the grid represents a valid knight's tour
     * 
     * @param grid n x n matrix containing visitation order from 0 to n²-1
     * @return true if valid knight's tour, false otherwise
     * 
     * Time: O(8^(n^2)) - worst case explores all paths
     * Space: O(n^2) - recursion stack depth
     * 
     * NOTE: Knight must start at (0,0) with value 0
     */
    public boolean checkValidGrid(int[][] grid) {
        // Start validation from top-left corner with expected value 0
        return canMoveKnight(grid, 0, 0, grid.length, 0);
    }

    /**
     * RECURSIVE KNIGHT TOUR VALIDATION
     * Checks if the knight can follow the complete tour from current position
     * 
     * @param grid The chessboard with visitation order
     * @param r Current row position
     * @param c Current column position
     * @param size Board size (n x n)
     * @param expValue Expected value at current position
     * @return true if valid tour from here, false otherwise
     * 
     * ALGORITHM:
     * 1. VALIDATE: Check bounds and expected value
     * 2. BASE CASE: If last move reached, tour is complete
     * 3. RECURSE: Try all 8 knight moves for next position
     * 4. RETURN: True if any move leads to valid tour
     * 
     * KEY: We follow the exact path specified by grid values
     */
    private boolean canMoveKnight(int[][] grid, int r, int c, int size, int expValue) {
        // VALIDATE: Check if position is valid and has expected value
        if (r < 0 || c < 0 || r >= size || c >= size || grid[r][c] != expValue) {
            return false;
        }
        
        // BASE CASE: Successfully reached the last move
        if (expValue == size * size - 1) {
            return true;
        }
        // boolean a1 = canMoveKnight (grid, r - 2, c + 1, size, expValue + 1);
        // boolean a2 = canMoveKnight (grid, r - 1, c + 2, size, expValue + 1);
        // boolean a3 = canMoveKnight (grid, r + 1, c + 2, size, expValue + 1);
        // boolean a4 = canMoveKnight (grid, r + 2, c + 1, size, expValue + 1);
        // boolean a5 = canMoveKnight (grid, r + 2, c - 1, size, expValue + 1);
        // boolean a6 = canMoveKnight (grid, r + 1, c - 2, size, expValue + 1);
        // boolean a7 = canMoveKnight (grid, r - 1, c - 2, size, expValue + 1);
        // boolean a8 = canMoveKnight (grid, r - 2, c - 1, size, expValue + 1);
        // return a1 || a2 || a3 || a4 || a5 || a6 || a7 || a8;
        // RECURSE: Try all 8 possible knight moves
        // Each move searches for the next expected value (expValue + 1)
        
        // Move 1: Two up, one right
        boolean a1 = canMoveKnight(grid, r - 2, c + 1, size, expValue + 1);
        if (a1) return true; // Early return if found valid path
        
        // Move 2: One up, two right
        boolean a2 = canMoveKnight(grid, r - 1, c + 2, size, expValue + 1);
        if (a2) return true;
        
        // Move 3: One down, two right
        boolean a3 = canMoveKnight(grid, r + 1, c + 2, size, expValue + 1);
        if (a3) return true;
        
        // Move 4: Two down, one right
        boolean a4 = canMoveKnight(grid, r + 2, c + 1, size, expValue + 1);
        if (a4) return true;
        
        // Move 5: Two down, one left
        boolean a5 = canMoveKnight(grid, r + 2, c - 1, size, expValue + 1);
        if (a5) return true;
        
        // Move 6: One down, two left
        boolean a6 = canMoveKnight(grid, r + 1, c - 2, size, expValue + 1);
        if (a6) return true;
        
        // Move 7: One up, two left
        boolean a7 = canMoveKnight(grid, r - 1, c - 2, size, expValue + 1);
        if (a7) return true;
        
        // Move 8: Two up, one left
        boolean a8 = canMoveKnight(grid, r - 2, c - 1, size, expValue + 1);
        if (a8) return true;
        
        // All moves exhausted - no valid path found
        return false;
    }
}
