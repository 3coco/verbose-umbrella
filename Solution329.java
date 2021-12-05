class Solution329 {
    private int[][] DIRS = {
        {0, -1}, {1, 0}, {0, 1}, {-1, 0}
    };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j, memo));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 1;
        for (int[] D : DIRS) {
            int r = i + D[0];
            int c = j + D[1];
            if (r >= 0 && r < m && c >= 0 && c < n && matrix[r][c] > matrix[i][j]) {
                res = Math.max(res, dfs(matrix, r, c, memo) + 1);
            }
        }
        memo[i][j] = res;
        return res;
    }
}
