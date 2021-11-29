class Solution1473 {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m + 1][n + 1][target + 1];
        int INF = Integer.MAX_VALUE;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j][0] = INF;
            }
        }

        for (int i = 1; i <= m; i++) {
            int color = houses[i - 1];
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= target; k++) {
                    if (k > i || (color != 0 && j != color)) {
                        dp[i][j][k] = INF;
                        continue;
                    }
                    int c = (color != 0 ? 0 : cost[i - 1][j - 1]);
                    int tmp = INF;
                    for (int p = 1; p <= n; p++) {
                        if (p != j && dp[i - 1][p][k - 1] != INF) {
                            tmp = Math.min(tmp, dp[i - 1][p][k - 1] + c);
                        }
                    }
                    if (dp[i - 1][j][k] != INF) {
                        tmp = Math.min(tmp, dp[i - 1][j][k] + c);
                    }
                    dp[i][j][k] = tmp;
                }
            }
        }

        int res = dp[m][1][target];
        for (int i = 2; i <= n; i++) {
            res = Math.min(res, dp[m][i][target]);
        }
        return res == INF ? -1 : res;
    }
}
