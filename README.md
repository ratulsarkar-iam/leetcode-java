# LeetCode Solutions in Java

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=openjdk)
![LeetCode](https://img.shields.io/badge/LeetCode-Problems-green?style=flat&logo=leetcode)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat)

A comprehensive collection of LeetCode problem solutions implemented in Java 17. This repository serves as a personal learning archive and reference for algorithmic problem-solving techniques.

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
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
│   │           │   ├── Leetcode_169.java
│   │           │   ├── Leetcode_2965.java
│   │           │   ├── Leetcode_344.java
│   │           │   ├── Leetcode_509.java
│   │           │   ├── Leetcode_704.java
│   │           │   └── Leetcode_9.java
│   │           ├── extra/          # Extra implementations
│   │           │   └── Permutation_String.java
│   │           ├── hard/          # Hard difficulty problems
│   │           │   ├── Leetcode_37.java
│   │           │   └── Leetcode_51.java
│   │           ├── med/          # Medium difficulty problems
│   │           │   ├── Leetcode_15.java
│   │           │   ├── Leetcode_151.java
│   │           │   ├── Leetcode_18.java
│   │           │   ├── Leetcode_204.java
│   │           │   ├── Leetcode_240.java
│   │           │   ├── Leetcode_46.java
│   │           │   ├── Leetcode_50.java
│   │           │   ├── Leetcode_53.java
│   │           │   ├── Leetcode_7.java
│   │           │   ├── Leetcode_74.java
│   │           │   ├── Leetcode_78.java
│   │           │   └── Leetcode_90.java
│   │           ├── prc/          # Prc implementations
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

## 📚 Problem Categories

### Easy Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| 1 | Two Sum | [Leetcode_1.java](src/com/rs/leetcode/easy/Leetcode_1.java) | O(n) | O(n) |
| 9 | Palindrome Number | [Leetcode_9.java](src/com/rs/leetcode/easy/Leetcode_9.java) | O(log n) | O(1) |
| 169 | Majority Element | [Leetcode_169.java](src/com/rs/leetcode/easy/Leetcode_169.java) | O(n) | O(n) |
| 344 | Reverse String | [Leetcode_344.java](src/com/rs/leetcode/easy/Leetcode_344.java) | O(n) | O(1) |
| 509 | Fibonacci Number | [Leetcode_509.java](src/com/rs/leetcode/easy/Leetcode_509.java) | Golden Ratio (O(1.618)^n) | - |
| 704 | Binary Search | [Leetcode_704.java](src/com/rs/leetcode/easy/Leetcode_704.java) | O(log n) | O(1) |
| 2965 | Find Missing and Repeated Values | [Leetcode_2965.java](src/com/rs/leetcode/easy/Leetcode_2965.java) | O(n^2) | O(n) |

### Medium Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| 7 | Reverse Integer | [Leetcode_7.java](src/com/rs/leetcode/med/Leetcode_7.java) | O(log n) | O(1) |
| 15 | 3Sum | [Leetcode_15.java](src/com/rs/leetcode/med/Leetcode_15.java) | O(n^3) | O(n) |
| 18 | 4Sum | [Leetcode_18.java](src/com/rs/leetcode/med/Leetcode_18.java) | O(n^4) | O(n) |
| 46 | Permutations – Backtracking | [Leetcode_46.java](src/com/rs/leetcode/med/Leetcode_46.java) | O(n! * n) | O(n! + n) |
| 50 | Pow(x, n) | [Leetcode_50.java](src/com/rs/leetcode/med/Leetcode_50.java) | O(log n) | O(1) |
| 53 | Maximum Subarray | [Leetcode_53.java](src/com/rs/leetcode/med/Leetcode_53.java) | O(n) | O(1) |
| 74 | Search a 2D Matrix | [Leetcode_74.java](src/com/rs/leetcode/med/Leetcode_74.java) | O(log(m * n)) | O(1) |
| 78 | Subsets | [Leetcode_78.java](src/com/rs/leetcode/med/Leetcode_78.java) | O(n × 2^n) | O(n) |
| 90 | Subsets II | [Leetcode_90.java](src/com/rs/leetcode/med/Leetcode_90.java) | O(n × 2^n) | O(n) auxiliary |
| 151 | Reverse Words in a String | [Leetcode_151.java](src/com/rs/leetcode/med/Leetcode_151.java) | O(n) | O(n) |
| 204 | Count Primes | [Leetcode_204.java](src/com/rs/leetcode/med/Leetcode_204.java) | O(n log log n) | O(n) |
| 240 | Search a 2D Matrix II | [Leetcode_240.java](src/com/rs/leetcode/med/Leetcode_240.java) | O(n log n) | O(1) |

### Hard Problems
| Problem # | Title | Solution | Time Complexity | Space Complexity |
|-----------|-------|----------|-----------------|------------------|
| 37 | Sudoku Solver | [Leetcode_37.java](src/com/rs/leetcode/hard/Leetcode_37.java) | O(9^m) | O(m) |
| 51 | N-Queens | [Leetcode_51.java](src/com/rs/leetcode/hard/Leetcode_51.java) | O(n!) | O(n^2) |

## ✨ Solution Highlights

### Featured Algorithms

- **Hash Map** - Used in Two Sum problem
- **Mathematical Operations** - Used in Palindrome Number problem
- **Two Pointers** - Used in Reverse String problem
- **TODO** - Used in Binary Search problem
- **Sorting** - Used in 3Sum problem
- **Backtracking** - Used in Permutations – Backtracking problem
- **Kadan's Algorithm** - Used in Maximum Subarray problem
- **Binary Search** - Used in Search a 2D Matrix problem
- **String Manipulation** - Used in Reverse Words in a String problem
- **Sieve of Eratosthenes** - Used in Count Primes problem

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

- **Total Problems Solved**: 21
- **Easy**: 7 ✅
- **Medium**: 12 ✅
- **Hard**: 2 ✅

---

**Happy Coding!** 🎉

If you find this repository helpful, consider giving it a ⭐ star!
