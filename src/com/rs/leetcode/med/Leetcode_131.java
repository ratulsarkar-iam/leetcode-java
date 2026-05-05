package com.rs.leetcode.med;

import java.util.ArrayList;
import java.util.List;

/* @LeetcodeMeta
 * @Title: Palindrome Partitioning
 * @TimeComplexity: O(n × 2^n)
 * @SpaceComplexity: O(n^2)
 * @Algorithm: Backtracking
 */

/**
 * ================================================================================
 * PALINDROME PARTITIONING - BACKTRACKING SOLUTION
 * ================================================================================
 * 
 * Author: Ratul Sarkar
 * Date: May 5, 2026
 * <p>
 * <p>
 * <p>
 * PROBLEM: Given a string s, partition s such that every substring of the 
 * partition is a palindrome. Return all possible palindrome partitioning of s.
 * <p>
 * A palindrome is a string that reads the same forward and backward.
 * We need to find all ways to split the string into substrings where each 
 * substring is a palindrome.
 * <p>
 * <p>
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Explanation:
 * - Partition 1: "a" | "a" | "b" (all single letters are palindromes)
 * - Partition 2: "aa" | "b" ("aa" is a palindrome)
 * <p>
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 * Explanation: Only one way to partition a single character
 * <p>
 * Example 3:
 * Input: s = "racecar"
 * Output: [["r","a","c","e","c","a","r"],
 *          ["r","a","cec","a","r"],
 *          ["r","aceca","r"],
 *          ["racecar"]]
 * <p>
 * <p>
 * Constraints:
 * - 1 <= s.length <= 16
 * - s contains only lowercase English letters
 * <p>
 * ================================================================================
 * ALGORITHM FLOW DIAGRAM
 * ================================================================================
 * 
 *                    partition(s)
 *                           |
 *                           v
 *                    Initialize retString = []
 *                    Initialize strings = []
 *                           |
 *                           v
 *                    partCheck(s, strings, retString)
 *                           |
 *            ┌──────────────┴───────────────┐
 *            |                              |
 *            v                              v
 *      s.isEmpty()?                For i = 0 to s.length-1
 *            |                          |
 *      YES ──→ Add copy of           Extract substring
 *      strings to                    s[0:i+1]
 *      retString                     |
 *      return                        v
 *           EXIT                   isPalindrome?
 *                                  |
 *                         ┌────────┴────────┐
 *                         |                 |
 *                         v                 v
 *                      YES               NO
 *                         |                 |
 *                         v                 v
 *                Add substring         Continue to
 *                to strings            next i
 *                         |
 *                         v
 *                Recurse with           ┌────────┘
 *                remaining string      │
 *                s[i+1:]                │
 *                         |              │
 *                         v              │
 *                Backtrack:             │
 *                Remove last            │
 *                from strings           │
 *                         |              │
 *                         └──────────────┘
 *                            |
 *                            v
 *                          return
 * 
 * ================================================================================
 * PALINDROME CHECKING
 * ================================================================================
 * 
 * Two approaches implemented:
 * 
 * 1. StringBuilder Reverse (O(n) time, O(n) space):
 *    String reversed = new StringBuilder(s).reverse().toString();
 *    return s.equals(reversed);
 * 
 * 2. Two-Pointer Approach (O(n) time, O(1) space):
 *    - Compare characters from both ends moving inward
 *    - More efficient as it avoids creating new strings
 *    - Used in the main algorithm
 * 
 * Two-pointer visualization for "racecar":
 * 
 *    r a c e c a r
 *    ^           ^
 *    |           |
 *   left=0     right=6
 * 
 *    r a c e c a r
 *      ^       ^
 *      |       |
 *    left=1   right=5
 * 
 *    r a c e c a r
 *        ^   ^
 *        |   |
 *      left=2 right=4
 * 
 *    r a c e c a r
 *          ^
 *          |
 *        left=3=right=3 (middle reached)
 * 
 * ================================================================================
 * BACKTRACKING PATTERN
 * ================================================================================
 * 
 * The algorithm follows the classic backtracking pattern:
 * 
 * 1. CHOOSE: Select a prefix that is a palindrome
 *    strings.add(substring)
 * 
 * 2. EXPLORE: Recurse with the remaining string
 *    partCheck(remaining, strings, retString)
 * 
 * 3. UNCHOOSE: Remove the last choice (backtrack)
 *    strings.remove(strings.size() - 1)
 * 
 * KEY INSIGHT: We try all possible palindrome prefixes at each step,
 * ensuring we explore every valid partition!
 * 
 * ================================================================================
 * DECISION TREE VISUALIZATION
 * ================================================================================
 * 
 * For s = "aab":
 * 
 *                    partCheck("aab", [], [])
 *                           |
 *              ┌──────┬──────┬──────┐
 *              |      |      |      |
 *              v      v      v      v
 *           "a"     "aa"   "aab"  (continue)
 *          (pal)   (pal)  (not pal)
 *              |      |      |
 *              v      v      v
 *       partCheck  partCheck  return
 *       ("ab")    ("b")
 *           |          |
 *      ┌────┐        |
 *      |    |        |
 *      v    v        v
 *    "a"  "aa"     "b"
 *   (pal) (not)    (pal)
 *      |    |        |
 *      v    v        v
 *  partCheck return  partCheck
 *  ("b")            ("")
 *      |                |
 *      v                v
 *    "b"              Add ["aa","b"]
 *   (pal)             to results
 *      |
 *      v
 *  partCheck("")
 *      |
 *      v
 *   Add ["a","a","b"]
 *   to results
 * 
 * Final result: [["a","a","b"], ["aa","b"]]
 * 
 * ================================================================================
 * EXECUTION TRACE EXAMPLE
 * ================================================================================
 * 
 * Tracing s = "aab":
 * 
 * Step 1: partCheck("aab", [], [])
 * - Try i=0: substring="a" (palindrome)
 * - strings=["a"], recurse with "ab"
 * 
 * Step 2: partCheck("ab", ["a"], [])
 * - Try i=0: substring="a" (palindrome)
 * - strings=["a","a"], recurse with "b"
 * 
 * Step 3: partCheck("b", ["a","a"], [])
 * - Try i=0: substring="b" (palindrome)
 * - strings=["a","a","b"], recurse with ""
 * 
 * Step 4: partCheck("", ["a","a","b"], [])
 * - Empty string! Add ["a","a","b"] to results
 * - Return
 * 
 * Step 5: Backtrack to Step 3
 * - strings=["a","a"]
 * - No more substrings to try in "b"
 * - Return
 * 
 * Step 6: Backtrack to Step 2
 * - strings=["a"]
 * - Try i=1: substring="ab" (not palindrome)
 * - Return
 * 
 * Step 7: Backtrack to Step 1
 * - strings=[]
 * - Try i=1: substring="aa" (palindrome)
 * - strings=["aa"], recurse with "b"
 * - ... continues until all partitions found
 * 
 * ================================================================================
 */
public class Leetcode_131 {
    /**
     * Main entry point - finds all palindrome partitions of the input string
     * 
     * @param s Input string to be partitioned
     * @return List of all valid palindrome partitions
     * 
     * Time: O(n × 2^n) - n for palindrome check, 2^n partitions in worst case
     * Space: O(n^2) - recursion stack + string storage
     * 
     * KEY: Every character is a palindrome, so at least one solution exists!
     */
    public List<List<String>> partition(String s) {
        // List to store all valid partitions found
        List<List<String>> retString = new ArrayList<>();
        
        // Temporary list to build current partition
        List<String> strings = new ArrayList<>();
        
        // Start backtracking with the entire string
        partCheck(s, strings, retString);
        
        return retString;
    }

        
    /**
     * Checks if a string is a palindrome using two-pointer technique
     * 
     * @param s String to check
     * @return true if s is a palindrome, false otherwise
     * 
     * Time: O(n) - compares characters from both ends
     * Space: O(1) - no extra space needed
     * 
     * ALGORITHM:
     * 1. Initialize left = 0, right = s.length() - 1
     * 2. While left < right:
     *    - If s[left] != s[right], return false
     *    - Move left++, right--
     * 3. If loop completes, return true
     */
    private boolean isPalindrome_two_Pointer(String s) {
        boolean isPalindrome = true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
    /**
     * RECURSIVE BACKTRACKING METHOD
     * Explores all possible palindrome partitions by trying all palindrome prefixes
     * 
     * @param s Remaining string to partition
     * @param strings Current partition being built
     * @param retString List to store all valid partitions
     * 
     * BACKTRACKING PATTERN:
     * 1. BASE CASE: If string is empty, we've found a valid partition
     * 2. FOR EACH prefix: Try all possible prefixes
     * 3. CHECK: If prefix is palindrome
     * 4. CHOOSE: Add prefix to current partition
     * 5. EXPLORE: Recurse with remaining string
     * 6. BACKTRACK: Remove prefix to try other possibilities
     * 
     * KEY: substring(0, i+1) extracts prefix, substring(i+1) gets remainder
     */
    private void partCheck(String s, List<String> strings, List<List<String>> retString) {
        
        // BASE CASE: Empty string means we've successfully partitioned entire string
        if (s.isEmpty()) {
            // IMPORTANT: Create new ArrayList to avoid reference issues
            retString.add(new ArrayList<>(strings));
            return;
        }
        
        // TRY ALL POSSIBLE PREFIXES
        // For each possible cut point from 0 to s.length()-1
        for (int i = 0; i < s.length(); i++) {
            // Extract prefix s[0:i+1] (inclusive)
            String subString = s.substring(0, i + 1);
            
            // CHECK if prefix is palindrome
            if (isPalindrome_two_Pointer(subString)) {
                // CHOOSE: Add palindrome prefix to current partition
                strings.add(subString);
                
                // EXPLORE: Recurse with remaining string s[i+1:]
                partCheck(s.substring(i + 1), strings, retString);
                
                // BACKTRACK: Remove last choice to try other possibilities
                strings.remove(strings.size() - 1);
            }
            // If not palindrome, skip this prefix and try next
        }
    }
}

/*
================================================================================
ALGORITHM ANALYSIS - Palindrome Partitioning (Backtracking)
================================================================================

PROBLEM: Find all ways to partition a string into substrings where each 
         substring is a palindrome.

APPROACH: Backtracking with Prefix Exploration
--------------------------------------------
1. At each step, try all possible prefixes of the remaining string
2. Check if prefix is palindrome
3. If yes, add to current partition and recurse with remainder
4. Backtrack by removing the prefix
5. Continue until all partitions found

KEY INSIGHT: Every character is a palindrome, guaranteeing at least one solution!

TIME COMPLEXITY: O(n × 2^n)
---------------------------
• For a string of length n, there are 2^(n-1) possible partition points
• Each partition requires checking palindromes (O(n) total per partition)
• Total: O(n × 2^n) in worst case (e.g., string of all same characters)

Detailed breakdown:
- Number of recursive calls: 2^(n-1) (each position can be a cut or not)
- Palindrome check: O(k) for substring of length k
- String operations: substring() creates new strings (O(k) each)
- Overall: O(n × 2^n) dominates

For n = 16 (maximum constraint):
- Worst case: 16 × 2^15 ≈ 524,288 operations
- Very manageable for modern computers

SPACE COMPLEXITY: O(n^2)
------------------------
• Recursion stack depth: O(n) (maximum string length)
• Current partition list: O(n) (at most n single characters)
• Output storage: O(k × n) where k = number of partitions
• String substring overhead: O(n^2) total across all recursive calls

Detailed breakdown:
- Stack frames: O(n)
- Each frame holds substring reference: O(1)
- Total auxiliary: O(n)
- Output dominates: O(k × n) where k can be up to 2^(n-1)

WHY THIS WORKS:
--------------
The algorithm systematically explores all partition possibilities:
1. **Prefix enumeration** ensures we try every possible first cut
2. **Palindrome check** guarantees validity of each partition
3. **Recursion** handles the remainder independently
4. **Backtracking** returns to explore other cut positions
5. **Exhaustive search** guarantees all valid partitions found

OPTIMIZATION OPPORTUNITIES:
--------------------------
1. **Palindrome DP Table**
   - Precompute palindrome status for all substrings
   - O(n^2) preprocessing, O(1) palindrome checks
   - Total: O(n^2 + n × 2^n) → O(n × 2^n) still dominates

2. **Memoization**
   - Cache results for substrings: partition(s)
   - Avoid recomputing same subproblems
   - Space: O(n^2) for cache

3. **Iterative DP**
   - Build solutions bottom-up
   - Better for counting partitions (not listing them)

4. **String Optimization**
   - Use indices instead of substring()
   - Pass start index instead of creating new strings
   - Reduces memory allocation significantly

COMPARISON WITH OTHER APPROACHES:
--------------------------------
| Approach | Time | Space | Lists All | Implementation |
|----------|------|-------|-----------|----------------|
| Backtracking | O(n × 2^n) | O(n^2) | Yes | Simple |
| DP + Backtracking | O(n × 2^n) | O(n^2) | Yes | Faster checks |
| Pure DP | O(n^2) | O(n^2) | No (count only) | Complex |
| BFS | O(n × 2^n) | O(n^2) | Yes | More memory |

PRACTICAL APPLICATIONS:
-----------------------
• **Text processing** - Finding word boundaries in pangrams
• **DNA sequencing** - Identifying palindromic sequences
• **Cryptography** - Analyzing symmetric patterns
• **Linguistics** - Studying palindrome structures
• **Puzzle solving** - Word partitioning games

COMMON MISTAKES TO AVOID:
------------------------
1. ❌ Not copying the partition list when adding to results
2. ❌ Using inefficient palindrome check (StringBuilder instead of two-pointer)
3. ❌ Forgetting to backtrack (remove from strings)
4. ❌ Wrong substring indices (off-by-one errors)
5. ❌ Not handling empty string base case

TESTING STRATEGIES:
------------------
• Empty string: "" → [[]] (if allowed)
• Single character: "a" → [["a"]]
• All same: "aaa" → [["a","a","a"],["a","aa"],["aa","a"],["aaa"]]
• No palindromes > 1: "abc" → [["a","b","c"]]
• Mixed: "aab" → [["a","a","b"],["aa","b"]]
• Full palindrome: "racecar" → 4 partitions including whole string

DEBUGGING TIPS:
--------------
1. Print current partition at each recursion level
2. Track substring being checked for palindrome
3. Count total recursive calls to measure complexity
4. Verify all partitions sum to original string
5. Check that all substrings in results are palindromes

FUTURE ENHANCEMENTS:
-------------------
1. Support for Unicode characters
2. Minimum/maximum partition size constraints
3. Return partitions in lexicographic order
4. Parallel processing for large strings
5. Streaming output for huge result sets

KEY LEARNING POINTS:
--------------------
• Backtracking pattern: Choose → Explore → Unchoose
• String manipulation can be expensive (substring creates new strings)
• Two-pointer technique is optimal for palindrome checking
• Every character is a palindrome - useful base case insight
• Space complexity often dominated by output storage

VARIATIONS OF THIS PROBLEM:
--------------------------
1. **Palindrome Partitioning II** - Minimum cuts needed
2. **Palindromic Substrings** - Count all palindromic substrings
3. **Longest Palindromic Substring** - Find single longest
4. **Palindromic Subsequence** - Non-contiguous palindrome
5. **Word Break** - Similar partitioning with dictionary

================================================================================
*/
