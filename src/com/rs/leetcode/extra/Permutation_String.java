package com.rs.leetcode.extra;

import java.util.Arrays;


/* @LeetcodeMeta
 * @Title: Permutation String
 * @TimeComplexity: O(n! * n)
 * @SpaceComplexity: O(n)
 * @Algorithm: Backtracking
 */

/**
 * ================================================================================
 * STRING PERMUTATIONS USING BACKTRACKING
 * ================================================================================
 * 
 * Problem: Generate all permutations of characters in a string
 * Example: Input: "ABC" → Output: [ABC, ACB, BAC, BCA, CAB, CBA]
 * 
 * Algorithm: Backtracking with in-place character swapping
 * 
 * @Author: Ratul Sarkar
 * @Date: 4/24/26
 */
public class Permutation_String {
    /**
     * Public entry point - starts the permutation generation
     * @param str Input string to permute
     * Time: O(1) - just converts to char array
     * Space: O(n) - char array storage
     */
    public void printPermutationOfString (String str) {
        char[] chars = str.toCharArray ();  // Convert to mutable char array
        printString (chars, 0);             // Start backtracking from index 0
    }

    /**
     * RECURSIVE BACKTRACKING METHOD
     * Generates all permutations by fixing characters one by one
     * 
     * @param chars Character array being permuted
     * @param idx Current position to fix (0-based)
     * 
     * BACKTRACKING PATTERN:
     * 1. CHOOSE: Swap char at idx with each position i ≥ idx
     * 2. EXPLORE: Recurse for next position (idx + 1)
     * 3. UNCHOOSE: Swap back to restore original state
     * 
     * BASE CASE: When idx == chars.length, we have a complete permutation
     */
    private void printString (char[] chars, int idx) {
        // Base case: All positions fixed - print the permutation
        if (idx == chars.length) {
            System.out.println (Arrays.toString (chars));
            return;
        }
        
        // Recursive case: Try each character at position idx
        for(int i=idx; i<chars.length; i++){
            // CHOOSE: Place chars[i] at position idx
            swap (chars, idx, i);
            
            // EXPLORE: Generate permutations for remaining positions
            printString (chars, idx+1);
            
            // UNCHOOSE: Restore original order (backtrack!)
            swap (chars, idx, i);
        }
    }

    /**
     * Swaps two characters in the array
     * Uses temporary variable approach for clarity
     * 
     * @param chars Character array
     * @param idx First index
     * @param i Second index
     * 
     * Time: O(1) - Simple 3-step operation
     * Space: O(1) - Only uses temporary variables
     */
    private void swap(char[] chars, int idx, int i){
        // Uncomment debug lines to see array state before/after swap
        // System.out.println ("Before swap: " + Arrays.toString (chars));
        
        char temp = chars[idx];  // Store first character
        chars[idx] = chars[i];   // Move second character to first position
        chars[i] = temp;         // Move stored character to second position
        
        // System.out.println ("After swap: [" + idx + "," + i + "] " + Arrays.toString (chars));
    }
}

/*
================================================================================
COMPLEXITY ANALYSIS & EXECUTION TRACE
================================================================================

TIME COMPLEXITY: O(n! × n)
---------------------------
• For a string of length n, there are n! permutations
• Each permutation requires:
  - n recursive calls (one for each character position)
  - n-1 swap operations (2 swaps per level except last)
  - O(n) time for printing the final permutation
• Total: n! × O(n) = O(n! × n)

Example for n=3: 3! = 6 permutations
Each requires 3 levels of recursion → 6 × 3 = 18 operations minimum

SPACE COMPLEXITY: O(n)
-----------------------
• Char array: O(n) - stores the current permutation
• Recursion stack: O(n) - maximum depth = string length
• Output: O(1) if just printing, O(n! × n) if storing all permutations
• Total auxiliary space: O(n)

EXECUTION TRACE for "AB":
-------------------------

printPermutationOfString("AB")
└─ chars = ['A', 'B'], call printString(chars, 0)
   
   printString(['A', 'B'], idx=0)
   ├─ i=0: swap(0,0) → ['A', 'B'] (no change)
   │  └─ printString(['A', 'B'], idx=1)
   │     └─ idx == 2: PRINT [A, B]
   │  swap(0,0) → ['A', 'B'] (backtrack)
   │
   └─ i=1: swap(0,1) → ['B', 'A']
      └─ printString(['B', 'A'], idx=1)
         └─ idx == 2: PRINT [B, A]
      swap(0,1) → ['A', 'B'] (backtrack)

Output:
[A, B]
[B, A]

EXECUTION TRACE for "ABC" (first branch):
-----------------------------------------

printString(['A', 'B', 'C'], idx=0)
├─ i=0: swap(0,0) → ['A', 'B', 'C']
│  └─ printString(['A', 'B', 'C'], idx=1)
│     ├─ i=1: swap(1,1) → ['A', 'B', 'C']
│     │  └─ printString(['A', 'B', 'C'], idx=2)
│     │     └─ idx == 3: PRINT [A, B, C] ← First complete permutation
│     │  swap(1,1) → ['A', 'B', 'C'] (backtrack)
│     │
│     └─ i=2: swap(1,2) → ['A', 'C', 'B']
│        └─ printString(['A', 'C', 'B'], idx=2)
│           └─ idx == 3: PRINT [A, C, B] ← Second permutation
│        swap(1,2) → ['A', 'B', 'C'] (backtrack)
│  swap(0,0) → ['A', 'B', 'C'] (backtrack)
│
├─ i=1: swap(0,1) → ['B', 'A', 'C'] (continues...)
└─ i=2: swap(0,2) → ['C', 'B', 'A'] (continues...)

KEY INSIGHTS:
-----------
• The algorithm generates permutations in lexicographic order when input is sorted
• Each level of recursion fixes one character position permanently
• Backtracking (second swap) is ESSENTIAL - without it, we'd corrupt the array
• Character array is modified in-place, saving space compared to creating new strings
• The for loop at each level ensures every character gets a chance at each position

COMPARISON WITH ARRAY PERMUTATIONS (Leetcode_46):
-----------------------------------------------
• Same algorithmic approach (swap-based backtracking)
• Arrays use Collections.swap(), strings use manual char swapping
• String version prints directly, array version stores in List
• Both have identical time/space complexity: O(n! × n) time, O(n) space

PRACTICAL APPLICATIONS:
-----------------------
• Generating all possible passwords of given characters
• Solving anagram puzzles
• Testing all possible arrangements in combinatorial problems
• Generating test cases for algorithms that depend on input order

OPTIMIZATION NOTES:
------------------
• For very large strings (n > 10), consider iterative approaches to avoid stack overflow
• If only need k permutations, add early termination condition
• For duplicate characters, need additional checks to avoid duplicate permutations
• Can modify to return List<String> instead of printing for programmatic use
================================================================================
*/
