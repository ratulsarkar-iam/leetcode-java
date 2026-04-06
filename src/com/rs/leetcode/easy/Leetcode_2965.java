Leetcode_2965

class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] retArray = new int[2];
        Set<Integer> s = new HashSet<>();
        int n = grid.length;
        int actualSum = 0;
        int expectedSum = (n * n) * (n * n + 1) / 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                actualSum += grid[i][j];
                if (s.contains(grid[i][j])) {
                    retArray[0] = grid[i][j];
                }
                s.add(grid[i][j]);
            }
        }
       // System.out.println(actualSum + " " + expectedSum);
        retArray[1] = expectedSum + retArray[0] - actualSum;
        return retArray;
    }
}
