package com.rs.leetcode.hard;

import java.util.ArrayList;

/* @LeetcodeMeta
 * @Title: Rat In A Maze
 * @TimeComplexity: O(4^(n^2))
 * @SpaceComplexity: O(n^2)
 * @Algorithm: Backtracking
 */

/**
 * ================================================================================
 * RAT IN A MAZE - BACKTRACKING SOLUTION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: April 28, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Find all possible paths for a rat to navigate from the top-left 
 * corner (0, 0) to the bottom-right corner (N-1, N-1) in an N×N maze.
 * <p>
 * The maze is represented as a square matrix where:
 * - 1 represents an open path that the rat can traverse
 * - 0 represents a blocked location that the rat cannot enter
 * <p>
 * The rat can move in four directions:
 * - 'U' (up): (x, y-1)
 * - 'D' (down): (x, y+1)
 * - 'L' (left): (x-1, y)
 * - 'R' (right): (x+1, y)
 * <p>
 * IMPORTANT: The output paths must be sorted alphabetically.
 * <p>
 * <p>
 * Example:
 * Input: N = 4
 * 1 0 0 0
 * 1 1 0 1
 * 1 1 0 0
 * 0 1 1 1
 * <p>
 * Output: DDRDRR DRDDRR
 * <p>
 * Explanation: Two valid paths exist:
 * Path 1: Down, Down, Right, Down, Right, Right (DDRDRR)
 * Path 2: Down, Right, Down, Down, Right, Right (DRDDRR)
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= N <= 5
 * 0 <= MATRIX[i][j] <= 1
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    findSum(arr, n)
 *                           |
 *                           v
 *                    Initialize vMat[n][n] = false
 *                    Initialize paths = []
 *                    Initialize path = ""
 *                           |
 *                           v
 *                    getPaths(vMat, arr, n, 0, 0, paths, path)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      Out of bounds OR           At destination (n-1, n-1)?
 *      Blocked cell OR                  |
 *      Already visited?                |
 *            |                          |
 *      YES ──→ return                 YES ──→ Add path to paths
 *            |                          |        return
 *           EXIT                         |
 *                                        v
 *            NO                         EXIT
 *            |
 *            v
 *      Mark current cell as visited
 *      vMat[row][col] = true
 *            |
 *            v
 *    ┌─────────────────────────────────┐
 *    |    TRY ALL 4 DIRECTIONS         |
 *    |    (in lexicographic order)     |
 *    |                                 |
 *    |  1. Down  (row+1, col)          |
 *    |  2. Left   (row, col-1)         |
 *    |  3. Right  (row, col+1)         |
 *    |  4. Up     (row-1, col)         |
 *    └─────────────────────────────────┘
 *            |
 *            v
 *   For each direction:
 *   ┌─────────────────────────────────┐
 *   |  getPaths(..., path + direction) |
 *   └─────────────────────────────────┘
 *            |
 *            v
 *   After trying all directions:
 *   ┌─────────────────────────────────┐
 *   |  BACKTRACK:                     |
 *   |  vMat[row][col] = false         |
 *   |  (unmark current cell)          |
 *   └─────────────────────────────────┘
 *            |
 *            v
 *          return
 * 
 * ================================================================================
 * MAZE TRAVERSAL VISUALIZATION
 * ================================================================================
 * 
 * For the example maze:
 * 
 *     0   1   2   3 (cols)
 *   ┌───┬───┬───┬───┐
 * 0 │ 1 │ 0 │ 0 │ 0 │
 *   ├───┼───┼───┼───┤
 * 1 │ 1 │ 1 │ 0 │ 1 │
 *   ├───┼───┼───┼───┤
 * 2 │ 1 │ 1 │ 0 │ 0 │
 *   ├───┼───┼───┼───┤
 * 3 │ 0 │ 1 │ 1 │ 1 │
 *   └───┴───┴───┴───┘
 * (rows)
 * 
 * Path DDRDRR visualization:
 * S = Start (0,0), E = End (3,3)
 * 
 * S → ↓     0 1 2 3
 *   ↓   →   ┌───┬───┬───┬───┐
 *     ↓     │ S │   │   │   │ 0
 *   ←   →   ├───┼───┼───┼───┤
 *     ←     │ ↓ │ ↓ │   │   │ 1
 *           ├───┼───┼───┼───┤
 *           │   │ ↓ │   │   │ 2
 *           ├───┼───┼───┼───┤
 *           │   │ ← │ ← │ E │ 3
 *           └───┴───┴───┴───┘
 * 
 * ================================================================================
 * BACKTRACKING PATTERN
 * ================================================================================
 * 
 * The algorithm follows the classic backtracking pattern:
 * 
 * 1. CHOOSE: Mark current cell as visited
 *    vMat[row][col] = true
 * 
 * 2. EXPLORE: Try all 4 directions recursively
 *    - Down: getPaths(row+1, col, path+"D")
 *    - Left: getPaths(row, col-1, path+"L")
 *    - Right: getPaths(row, col+1, path+"R")
 *    - Up: getPaths(row-1, col, path+"U")
 * 
 * 3. UNCHOOSE: Unmark current cell (backtrack)
 *    vMat[row][col] = false
 * 
 * IMPORTANT: Directions are tried in lexicographic order
 * (D, L, R, U) to ensure sorted output!
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing first path discovery for 2×2 maze:
 * 
 * Maze:
 * 1 1
 * 1 1
 * 
 * Step 1: getPaths(0,0,"")
 * - Mark (0,0) visited
 * - Try Down: getPaths(1,0,"D")
 * 
 * Step 2: getPaths(1,0,"D")
 * - Mark (1,0) visited
 * - Try Down: OUT OF BOUNDS
 * - Try Left: OUT OF BOUNDS
 * - Try Right: getPaths(1,1,"DR")
 * 
 * Step 3: getPaths(1,1,"DR")
 * - At destination! Add "DR" to paths
 * - Return
 * 
 * Step 4: Backtrack to (1,0)
 * - Try Up: getPaths(0,0,"DU") → ALREADY VISITED
 * - Unmark (1,0), return
 * 
 * Step 5: Backtrack to (0,0)
 * - Try Left: OUT OF BOUNDS
 * - Try Right: getPaths(0,1,"R")
 * - ... continues until all paths found
 * 
 * Final result: ["DR", "RD"]
 * 
 * ================================================================================
 */
public class Code360_RatInAMaze {
    /**
     * Main entry point - finds all valid paths for the rat in the maze
     * 
     * @param arr N×N maze matrix where 1 = open path, 0 = blocked
     * @param n Size of the maze (N)
     * @return ArrayList of all valid paths in alphabetical order
     * 
     * Time: O(4^(n^2)) - worst case tries all 4 directions at each cell
     * Space: O(n^2) - visited matrix + recursion stack
     */
    public ArrayList<String> findSum (int[][] arr, int n) {
        // Initialize visited matrix to track visited cells
        boolean[][] vMat = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vMat[i][j] = false;  // Initially no cells are visited
            }
        }
        
        // List to store all valid paths found
        ArrayList<String> paths = new ArrayList<> ();
        
        // Start with empty path string
        String path = new String ();
        
        // Begin backtracking from starting position (0,0)
        getPaths (vMat, arr, n, 0, 0, paths, path);
        
        return paths;
    }

    /**
     * RECURSIVE BACKTRACKING METHOD
     * Explores all possible paths from current position to destination
     * 
     * @param vMat Visited matrix to avoid cycles
     * @param arr Maze matrix (1 = open, 0 = blocked)
     * @param n Size of the maze
     * @param rowId Current row position
     * @param colId Current column position
     * @param paths List to store found paths
     * @param currentPath Current path string built so far
     * 
     * BACKTRACKING PATTERN:
     * 1. BOUNDARY CHECK: Validate current position
     * 2. BASE CASE: Check if destination reached
     * 3. MARK: Mark current cell as visited
     * 4. EXPLORE: Try all 4 directions (in lexicographic order)
     * 5. UNMARK: Unmark cell (backtrack)
     */
    private void getPaths (boolean[][] vMat, int[][] arr, int n, int rowId, int colId, 
                          ArrayList<String> paths, String currentPath) {
        
        // BOUNDARY AND VALIDATION CHECKS
        // Return if:
        // 1. Out of maze boundaries
        // 2. Cell is blocked (value 0)
        // 3. Cell already visited (to avoid cycles)
        if (rowId < 0 || colId < 0 || rowId >= n || colId >= n || 
            arr[rowId][colId] == 0 || vMat[rowId][colId]) {
            return;
        }

        // BASE CASE: Destination reached!
        if (rowId == n - 1 && colId == n - 1) {
            paths.add (currentPath);  // Add completed path to results
            return;
        }

        // MARK: Mark current cell as visited
        vMat[rowId][colId] = true;
        
        // EXPLORE: Try all 4 directions in LEXICOGRAPHIC ORDER
        // This ensures paths are automatically sorted!
        //
          // 1. DOWN (D) - (row+1, col)
        getPaths (vMat, arr, n, rowId + 1, colId, paths, currentPath + "D");

        // 2. LEFT (L) - (row, col-1)
        getPaths (vMat, arr, n, rowId, colId - 1, paths, currentPath + "L");
        
        // 3. RIGHT (R) - (row, col+1)
        getPaths (vMat, arr, n, rowId, colId + 1, paths, currentPath + "R");
        
        // 4. UP (U) - (row-1, col)
        getPaths (vMat, arr, n, rowId - 1, colId, paths, currentPath + "U");
        
        // WHY THIS WORKS:
        // ================
        // 1. DFS explores paths completely before backtracking
        // 2. Path strings are built incrementally as we explore
        // 3. Trying D,L,R,U in order generates paths in alphabetical order
        //
        // LEXICOGRAPHIC ORDER: 'D' < 'L' < 'R' < 'U'
        // ===========================================
        // All paths starting with 'D' are found first
        // Then all paths starting with 'L'  
        // Then all paths starting with 'R'
        // Finally all paths starting with 'U'
        //
        // EXAMPLE: 3×3 maze with multiple paths
        // ====================================
        // Without lexicographic order (e.g., U,R,D,L):
        // Paths found: ["RDRD", "DRDR", "RRDD", "DDRR"] → NEED SORTING!
        //
        // With lexicographic order (D,L,R,U):
        // Paths found: ["DDRR", "DRDR", "RDRD", "RRDD"] → ALREADY SORTED!
        //
        // PERFORMANCE BENEFIT:
        // ====================
        // - No need for Collections.sort(paths) which is O(k log k)
        // - k = number of paths found
        // - Saves significant time when many paths exist
        //
        // CRITICAL INSIGHT:
        // =================
        // The natural ordering of DFS exploration combined with
        // ordered direction attempts produces sorted results!
        
      
        
        // UNMARK: Backtrack - unmark current cell
        // This allows other paths to use this cell
        vMat[rowId][colId] = false;
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Rat In A Maze (Backtracking)
================================================================================

PROBLEM: Find all paths from (0,0) to (N-1,N-1) in an N×N maze where:
         - 1 represents open path
         - 0 represents blocked cell
         - Can move in 4 directions: Up, Down, Left, Right
         - Paths must be returned in alphabetical order

APPROACH: Depth-First Search with Backtracking
-------------------------------------------------
1. Start from (0,0) with empty path string
2. At each cell, check if valid (in bounds, open, not visited)
3. Mark cell as visited to avoid cycles
4. Try all 4 directions in lexicographic order (D, L, R, U)
5. When destination reached, add path to results
6. Backtrack by unmarking cell before returning

KEY INSIGHT: Trying directions in lexicographic order (D, L, R, U)
automatically ensures sorted output without additional sorting!

TIME COMPLEXITY: O(4^(n^2)) in worst case
-----------------------
• For each cell: up to 4 choices (directions)
• Maximum path length: n^2 (visiting every cell)
• Total paths in worst case: 4^(n^2)
• Each path construction takes O(n^2) time

Detailed breakdown:
- Total recursive calls: O(4^(n^2)) in worst case
- Each call does O(1) work (boundary checks, marking)
- Path string concatenation: O(n^2) per complete path
- Overall: O(4^(n^2) + k×n^2) where k = number of paths

Practical performance:
- With many blocked cells: Much better than worst case
- With constraints N ≤ 5: Maximum 25 cells
- For N=5, worst case: 4^25 ≈ 10^15 operations (theoretical)
- Real mazes have far fewer valid paths due to blocks

SPACE COMPLEXITY: O(n^2)
------------------------
• Visited matrix: O(n^2) - boolean[n][n]
• Recursion stack: O(n^2) - maximum depth = path length
• Path string storage: O(k×n^2) where k = number of paths
• Total auxiliary space: O(n^2) (visited + stack dominates)

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Early pruning**
   - Check if destination is reachable before exploring
   - Use connectivity analysis to eliminate impossible paths

2. **Iterative solution**
   - Use explicit stack instead of recursion
   - Avoids stack overflow for large mazes

3. **Path storage optimization**
   - Use StringBuilder instead of String concatenation
   - Reduces memory allocation during path building

4. **Direction ordering optimization**
   - Try directions based on heuristic (e.g., towards destination)
   - May find solutions faster but requires sorting at end

5. **Memoization**
   - Cache results from specific positions
   - Not effective as paths are position-dependent

COMPARISON WITH OTHER PATH-FINDING ALGORITHMS:
-----------------------------------------------
| Algorithm | Completeness | Optimality | Space | Use Case |
|-----------|--------------|------------|-------|----------|
| Backtracking | Complete | Non-optimal | O(n^2) | Find ALL paths |
| DFS | Complete | Non-optimal | O(n^2) | Single path |
| BFS | Complete | Optimal | O(n^2) | Shortest path |
| A* | Complete | Optimal | O(n^2) | Weighted graphs |
| Dijkstra | Complete | Optimal | O(n^2) | Weighted graphs |

PRACTICAL APPLICATIONS:
-----------------------
• **Robot navigation** - Finding all possible routes
• **Game development** - AI pathfinding with multiple options
• **Circuit design** - Finding all valid connections
• **Network routing** - Alternative path discovery
• **Puzzle solving** - Maze games, escape rooms

WHY BACKTRACKING WORKS HERE:
---------------------------
1. **Exhaustive search** - Need ALL paths, not just one
2. **Small constraints** - N ≤ 5 keeps search space manageable
3. **No cycles** - Visited matrix prevents infinite loops
4. **Simple state** - Position + path sufficient for state
5. **Natural fit** - Recursive exploration mirrors problem structure

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Forgetting to mark cells as visited (causes infinite loops)
2. ❌ Not unmarking during backtrack (blocks other paths)
3. ❌ Wrong direction order (output won't be sorted)
4. ❌ Not checking start/end cells validity
5. ❌ Using String concatenation in loops (inefficient)

TESTING STRATEGIES:
------------------
• Empty maze (all 0s) → []
• Single cell maze → [""]
• Straight line maze → Single path
• Maze with cycles → Multiple paths
• No path to destination → []
• All open maze → Maximum paths

DEBUGGING TIPS:
--------------
1. Print path at each recursion level
2. Track visited matrix state
3. Count recursive calls to measure complexity
4. Visualize maze with current position
5. Check boundary conditions carefully

FUTURE ENHANCEMENTS:
-------------------
1. Support for rectangular mazes (M×N)
2. Weighted cells (cost-based pathfinding)
3. Obstacle avoidance with constraints
4. Multiple start/end points
5. Path length filtering (shortest/longest paths)
6. Animation of path discovery process

KEY LEARNING POINTS:
--------------------
• Backtracking pattern: Mark → Explore → Unmark
• Lexicographic ordering for automatic sorting
• Visited matrix prevents cycles in undirected graphs
• String concatenation vs StringBuilder performance
• Recursive vs iterative trade-offs in pathfinding
================================================================================
*/
