#!/bin/bash

# LeetCode Progress Tracker Update Script
# This script compiles and runs the ProgressTracker to update your Excel file

set -e  # Exit on error

echo "🚀 LeetCode Progress Tracker Update Script"
echo "=========================================="
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven first."
    echo "   Install via: brew install maven"
    exit 1
fi

# Navigate to project root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

echo "📂 Project directory: $SCRIPT_DIR"
echo ""

# Check if pom.xml exists
if [ ! -f "pom.xml" ]; then
    echo "❌ pom.xml not found. Please ensure you're in the project root."
    exit 1
fi

# Install dependencies if needed
if [ ! -d "target" ]; then
    echo "📦 Installing dependencies (first time setup)..."
    mvn clean install -q
    echo "✅ Dependencies installed"
    echo ""
fi

# Compile the project
echo "🔨 Compiling project..."
mvn compile -q
echo "✅ Compilation successful"
echo ""

# Run the Progress Tracker
echo "📊 Running Progress Tracker..."
echo "----------------------------------------"
mvn exec:java -Dexec.mainClass="com.rs.leetcode.ProgressTracker" -q
echo "----------------------------------------"
echo ""

# Check if Excel file was created/updated
if [ -f "FAANG_DSA_Tracker.xlsx" ]; then
    echo "✅ Excel file updated: FAANG_DSA_Tracker.xlsx"
    echo "📅 Last modified: $(date -r FAANG_DSA_Tracker.xlsx '+%Y-%m-%d %H:%M:%S')"
else
    echo "⚠️  Excel file not found. It should have been created."
fi

echo ""
echo "🎉 Update complete! Open FAANG_DSA_Tracker.xlsx to view your progress."
