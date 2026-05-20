# LeetCode Solutions in Java

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=openjdk)
![LeetCode](https://img.shields.io/badge/LeetCode-Problems-green?style=flat&logo=leetcode)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat)

A comprehensive collection of LeetCode problem solutions implemented in Java 17. This repository serves as a personal learning archive and reference for algorithmic problem-solving techniques.

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Binary Reference](#-binary-reference)
- [Bitwise Operations for DSA](#-bitwise-operations-for-dsa)
- [Problem Categories](#-problem-categories)
- [Solution Highlights](#-solution-highlights)
- [Contributing](#-contributing)
- [License](#-license)
- [Disclaimer](#-disclaimer)

## 🎯 Project Overview

This repository contains well-documented Java solutions to various LeetCode problems, organized by difficulty level. Each solution includes:

- Clean, readable code following Java best practices
- Time and space complexity analysis
- Detailed comments explaining the approach
- Test cases demonstrating the solution

## 📁 Project Structure

```
leetcode-java/
├── src/
│   ├── com/
│   │   └── rs/
│   │       └── leetcode/
│   │           ├── easy/          # Easy difficulty problems
│   │           │   ├── Leetcode_1.java
│   │           │   ├── Leetcode_136.java
│   │           │   ├── Leetcode_141.java
│   │           │   ├── Leetcode_169.java
│   │           │   ├── Leetcode_206.java
│   │           │   ├── Leetcode_21.java
│   │           │   ├── Leetcode_2965.java
│   │           │   ├── Leetcode_344.java
│   │           │   ├── Leetcode_509.java
│   │           │   ├── Leetcode_704.java
│   │           │   ├── Leetcode_876.java
│   │           │   └── Leetcode_9.java
│   │           ├── extra/          # Extra difficulty problems
│   │           │   ├── InversionsCount.java
│   │           │   ├── ListNode.java
│   │           │   ├── MergeSort.java
│   │           │   ├── Permutation_String.java
│   │           │   └── QuickSort.java
│   │           ├── hard/          # Hard difficulty problems
│   │           │   ├── Code360_RatInAMaze.java
│   │           │   ├── Code360_RatInAMaze_Optimized.java
│   │           │   ├── Leetcode_37.java
│   │           │   └── Leetcode_51.java
│   │           ├── med/          # Medium difficulty problems
│   │           │   ├── Leetcode_131.java
│   │           │   ├── Leetcode_142.java
│   │           │   ├── Leetcode_15.java
│   │           │   ├── Leetcode_151.java
│   │           │   ├── Leetcode_18.java
│   │           │   ├── Leetcode_204.java
│   │           │   ├── Leetcode_240.java
│   │           │   ├── Leetcode_2596.java
│   │           │   ├── Leetcode_39.java
│   │           │   ├── Leetcode_46.java
│   │           │   ├── Leetcode_50.java
│   │           │   ├── Leetcode_53.java
│   │           │   ├── Leetcode_7.java
│   │           │   ├── Leetcode_74.java
│   │           │   ├── Leetcode_78.java
│   │           │   └── Leetcode_90.java
│   │           ├── prc/          # Prc implementations
│   │           │   ├── L169_D1.java
│   │           │   ├── L37_D1.java
│   │           │   └── L51_D1.java
│   │           ├── Leetcode_Main.java  # Main test runner
│   │           ├── MetaGenerator.java  # Main test runner
│   │           ├── ProgressTracker.java  # Main test runner
│   │           └── ReadmeUpdater.java  # README auto-updater
│   └── module-info.java
├── README.md
└── LICENSE
```

### Directory Descriptions

- **`easy/`** - Solutions for problems marked as Easy difficulty
- **`med/`** - Solutions for problems marked as Medium difficulty
- **`hard/`** - Solutions for problems marked as Hard difficulty (to be added)
- **`Leetcode_Main.java`** - Entry point for running and testing solutions

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17** or later
- A Java IDE such as:
  - IntelliJ IDEA
  - Eclipse
  - Visual Studio Code with Java Extension Pack

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/ratulsarkar-iam/leetcode-java.git
   cd leetcode-java
   ```

2. **Import in your IDE**
   - For IntelliJ IDEA: File → Open → Select the project directory
   - For Eclipse: File → Import → Maven → Existing Maven Projects
   - For VS Code: Open folder and install the Java Extension Pack

3. **Run the solutions**
   ```bash
   # Compile and run using command line
   javac src/com/rs/leetcode/Leetcode_Main.java
   java -cp src com.rs.leetcode.Leetcode_Main
   ```
<!-- README_UPDATER_SKIP_START -->
## � Binary Reference

| Decimal | Binary | Hex |
|---------|--------|-----|
| `0` | `0000` | `0x0` |
| `1` | `0001` | `0x1` |
| `2` | `0010` | `0x2` |
| `3` | `0011` | `0x3` |
| `4` | `0100` | `0x4` |
| `5` | `0101` | `0x5` |
| `6` | `0110` | `0x6` |
| `7` | `0111` | `0x7` |
| `8` | `1000` | `0x8` |
| `9` | `1001` | `0x9` |
| `10` | `1010` | `0xA` |
<!-- README_UPDATER_SKIP_END -->

<!-- README_UPDATER_SKIP_START -->
## 🧮 Bitwise Operations for DSA

### Operators

| Operator | Symbol | Description | Example |
|----------|--------|-------------|---------|
| **AND** | `&` | 1 if both bits are 1 | `5 & 3` → `0101 & 0011` = `0001` = `1` |
| **OR** | `\|` | 1 if at least one bit is 1 | `5 \| 3` → `0101 \| 0011` = `0111` = `7` |
| **XOR** | `^` | 1 if bits are different | `5 ^ 3` → `0101 ^ 0011` = `0110` = `6` |
| **NOT** | `~` | Flips all bits | `~5` → `~0101` = `1010` = `-6` (2's complement) |
| **Left Shift** | `<<` | Shifts bits left (× 2) | `5 << 1` → `0101 << 1` = `1010` = `10` |
| **Right Shift** | `>>` | Shifts bits right (÷ 2) | `10 >> 1` → `1010 >> 1` = `0101` = `5` |
| **Unsigned Right Shift** | `>>>` | Shifts right, fills 0 | `-1 >>> 1` = `2147483647` |

### Common DSA Tricks

| Trick | Code | Description |
|-------|------|-------------|
| Check if even/odd | `n & 1 == 0` | Even if last bit is 0 |
| Multiply by 2 | `n << 1` | Left shift by 1 |
| Divide by 2 | `n >> 1` | Right shift by 1 |
| Check k-th bit | `(n >> k) & 1` | Isolate bit at position k |
| Set k-th bit | `n \| (1 << k)` | Turn on bit at position k |
| Clear k-th bit | `n & ~(1 << k)` | Turn off bit at position k |
| Toggle k-th bit | `n ^ (1 << k)` | Flip bit at position k |
| Clear lowest set bit | `n & (n - 1)` | Removes rightmost 1-bit |
| Isolate lowest set bit | `n & (-n)` | Keeps only rightmost 1-bit |
| Check power of 2 | `n > 0 && (n & (n-1)) == 0` | Powers of 2 have one set bit |
| Count set bits | `Integer.bitCount(n)` | Count 1-bits (Java) |
| XOR duplicate finder | `a ^ a == 0` | Same value XOR cancels out |
| Swap without temp | `a^=b; b^=a; a^=b;` | XOR swap trick |

### Key Properties

- `x ^ x = 0` — XOR of a number with itself is 0
- `x ^ 0 = x` — XOR with 0 keeps value unchanged
- `x & (x-1)` — clears the lowest set bit
- `n & (-n)` — isolates the lowest set bit (used in Fenwick Trees)
- Left shift `n << k` = multiply by `2^k`
- Right shift `n >> k` = divide by `2^k`
<!-- README_UPDATER_SKIP_END -->

<!-- README_UPDATER_SKIP_START -->
## �📊 Algorithm Comparison

| Algorithm | Best Case | Average Case | Worst Case | Space Complexity | Stable | When to Use | Key Trait |
|-----------|-----------|--------------|------------|------------------|--------|-------------|-----------|
| **Merge Sort** | `O(n log n)` | `O(n log n)` | `O(n log n)` | `O(n)` | ✅ Yes | Linked lists, external sorting, need stable sort | Divide & conquer; guaranteed `O(n log n)` always |
| **Quick Sort** | `O(n log n)` | `O(n log n)` | `O(n²)` | `O(log n)` | ❌ No | General-purpose in-memory sort, large arrays | Fastest in practice; pivot choice is critical |
| **Heap Sort** | `O(n log n)` | `O(n log n)` | `O(n log n)` | `O(1)` | ❌ No | Memory-constrained, need guaranteed `O(n log n)` | In-place; uses max-heap; not cache-friendly |
| **Insertion Sort** | `O(n)` | `O(n²)` | `O(n²)` | `O(1)` | ✅ Yes | Small / nearly-sorted arrays, online sorting | Simple & fast for tiny `n`; adaptive |
| **Bubble Sort** | `O(n)` | `O(n²)` | `O(n²)` | `O(1)` | ✅ Yes | Educational purposes only | Rarely used in practice; easy to understand |
<!-- README_UPDATER_SKIP_END -->
## 📚 Problem Categories

### Easy Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| 1 | Two Sum | [Leetcode_1.java](src/com/rs/leetcode/easy/Leetcode_1.java) | O(n) | O(n) |
| 9 | Palindrome Number | [Leetcode_9.java](src/com/rs/leetcode/easy/Leetcode_9.java) | O(log n) | O(1) |
| 21 | Merge Two Sorted Lists | [Leetcode_21.java](src/com/rs/leetcode/easy/Leetcode_21.java) | O(n + m) | O(n + m) |
| 136 | Single Number | [Leetcode_136.java](src/com/rs/leetcode/easy/Leetcode_136.java) | O(n) | O(1) |
| 141 | Linked List Cycle | [Leetcode_141.java](src/com/rs/leetcode/easy/Leetcode_141.java) | O(n) | O(1) |
| 169 | Majority Element | [Leetcode_169.java](src/com/rs/leetcode/easy/Leetcode_169.java) | O(n) | O(n) |
| 206 | Reverse Linked List | [Leetcode_206.java](src/com/rs/leetcode/easy/Leetcode_206.java) | O(n) | O(1) |
| 344 | Reverse String | [Leetcode_344.java](src/com/rs/leetcode/easy/Leetcode_344.java) | O(n) | O(1) |
| 509 | Fibonacci Number | [Leetcode_509.java](src/com/rs/leetcode/easy/Leetcode_509.java) | Golden Ratio (O(1.618)^n) | - |
| 704 | Binary Search | [Leetcode_704.java](src/com/rs/leetcode/easy/Leetcode_704.java) | O(log n) | O(1) |
| 876 | Middle of the Linked List | [Leetcode_876.java](src/com/rs/leetcode/easy/Leetcode_876.java) | O(n) | O(1) |
| 2965 | Find Missing and Repeated Values | [Leetcode_2965.java](src/com/rs/leetcode/easy/Leetcode_2965.java) | O(n^2) | O(n) |

### Medium Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| 7 | Reverse Integer | [Leetcode_7.java](src/com/rs/leetcode/med/Leetcode_7.java) | O(log n) | O(1) |
| 15 | 3Sum | [Leetcode_15.java](src/com/rs/leetcode/med/Leetcode_15.java) | O(n^3) | O(n) |
| 18 | 4Sum | [Leetcode_18.java](src/com/rs/leetcode/med/Leetcode_18.java) | O(n^4) | O(n) |
| 39 | Combination Sum | [Leetcode_39.java](src/com/rs/leetcode/med/Leetcode_39.java) | O(2^t) | O(t/min(candidates)) |
| 46 | Permutations – Backtracking | [Leetcode_46.java](src/com/rs/leetcode/med/Leetcode_46.java) | O(n! * n) | O(n! + n) |
| 50 | Pow(x, n) | [Leetcode_50.java](src/com/rs/leetcode/med/Leetcode_50.java) | O(log n) | O(1) |
| 53 | Maximum Subarray | [Leetcode_53.java](src/com/rs/leetcode/med/Leetcode_53.java) | O(n) | O(1) |
| 74 | Search a 2D Matrix | [Leetcode_74.java](src/com/rs/leetcode/med/Leetcode_74.java) | O(log(m * n)) | O(1) |
| 78 | Subsets | [Leetcode_78.java](src/com/rs/leetcode/med/Leetcode_78.java) | O(n × 2^n) | O(n) |
| 90 | Subsets II | [Leetcode_90.java](src/com/rs/leetcode/med/Leetcode_90.java) | O(n × 2^n) | O(n) auxiliary |
| 131 | Palindrome Partitioning | [Leetcode_131.java](src/com/rs/leetcode/med/Leetcode_131.java) | O(n × 2^n) | O(n^2) |
| 142 | Linked List Cycle II | [Leetcode_142.java](src/com/rs/leetcode/med/Leetcode_142.java) | O(n) | O(1) |
| 151 | Reverse Words in a String | [Leetcode_151.java](src/com/rs/leetcode/med/Leetcode_151.java) | O(n) | O(n) |
| 204 | Count Primes | [Leetcode_204.java](src/com/rs/leetcode/med/Leetcode_204.java) | O(n log log n) | O(n) |
| 240 | Search a 2D Matrix II | [Leetcode_240.java](src/com/rs/leetcode/med/Leetcode_240.java) | O(n log n) | O(1) |
| 2596 | Check Knight Tour Configuration | [Leetcode_2596.java](src/com/rs/leetcode/med/Leetcode_2596.java) | O(8^(n^2)) | O(n^2) |

### Hard Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| 37 | Sudoku Solver | [Leetcode_37.java](src/com/rs/leetcode/hard/Leetcode_37.java) | O(9^m) | O(m) |
| 51 | N-Queens | [Leetcode_51.java](src/com/rs/leetcode/hard/Leetcode_51.java) | O(n!) | O(n^2) |
| - | Rat In A Maze | [Code360_RatInAMaze.java](src/com/rs/leetcode/hard/Code360_RatInAMaze.java) | O(4^(n^2)) | O(n^2) |
| - | Rat In A Maze (Optimized) | [Code360_RatInAMaze_Optimized.java](src/com/rs/leetcode/hard/Code360_RatInAMaze_Optimized.java) | O(4^(n^2)) | O(n^2) |

### Extra Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| - | Count Inversions | [InversionsCount.java](src/com/rs/leetcode/extra/InversionsCount.java) | O(n log n) | O(n) |
| - | Leetcode -1 | [ListNode.java](src/com/rs/leetcode/extra/ListNode.java) | - | - |
| - | Merge Sort | [MergeSort.java](src/com/rs/leetcode/extra/MergeSort.java) | O(n log n) | O(n) |
| - | Permutation String | [Permutation_String.java](src/com/rs/leetcode/extra/Permutation_String.java) | O(n! * n) | O(n) |
| - | Quick Sort | [QuickSort.java](src/com/rs/leetcode/extra/QuickSort.java) | O(n log n) avg, O(n^2) worst | O(log n) |

## ✨ Solution Highlights

### Featured Algorithms

- **Hash Map** - Used in Two Sum problem
- **Mathematical Operations** - Used in Palindrome Number problem
- **Recursion** - Used in Merge Two Sorted Lists problem
- **Bit Manipulation (XOR)** - Used in Single Number problem
- **Two Pointers (Floyd's Tortoise and Hare)** - Used in Linked List Cycle problem
- **Iterative Three Pointers** - Used in Reverse Linked List problem
- **Two Pointers** - Used in Reverse String problem
- **TODO** - Used in Binary Search problem
- **Two Pointers (Slow-Fast)** - Used in Middle of the Linked List problem
- **Sorting** - Used in 3Sum problem
- **Backtracking** - Used in Combination Sum problem
- **Kadan's Algorithm** - Used in Maximum Subarray problem
- **Binary Search** - Used in Search a 2D Matrix problem
- **String Manipulation** - Used in Reverse Words in a String problem
- **Sieve of Eratosthenes** - Used in Count Primes problem
- **Backtracking/DFS** - Used in Check Knight Tour Configuration problem
- **Backtracking with In-place Marking** - Used in Rat In A Maze (Optimized) problem
- **Modified Merge Sort** - Used in Count Inversions problem
- **Divide and Conquer** - Used in Merge Sort problem

### Key Learning Points

- Understanding edge cases and constraints
- Optimizing solutions for better time/space complexity
- Implementing clean, maintainable code
- Proper error handling and input validation

## 🤝 Contributing

While this is primarily a personal learning repository, contributions are welcome in the form of:

1. **Bug Reports** - If you find any errors in the solutions
2. **Optimization Suggestions** - Better approaches to solved problems
3. **New Solutions** - Additional LeetCode problems not yet implemented

To contribute:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request with a clear description

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## ⚠️ Disclaimer

This repository is intended for **educational purposes only**. The solutions provided are meant to help understand algorithms and improve problem-solving skills. Please:

- Use these solutions as a learning reference, not for submission
- Understand the logic before using any code
- Respect LeetCode's terms of service
- Try solving problems independently first

## 📈 Progress Tracking

- **Total Problems Solved**: 32
- **Easy**: 12 ✅
- **Medium**: 16 ✅
- **Hard**: 4 ✅

---

**Happy Coding!** 🎉

If you find this repository helpful, consider giving it a ⭐ star!
