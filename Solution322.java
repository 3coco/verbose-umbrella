class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int INF = Integer.MAX_VALUE;
        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = INF;
        }

        for (int i = 1; i <= n; i++) {
            int c = coins[i - 1];
            for (int j = c; j <= amount; j++) {
                if (dp[j - c] != INF) {
                    dp[j] = Math.min(dp[j], dp[j - c] + 1);
                }
            }
        }
        return dp[amount] == INF ? -1 : dp[amount];
    }
}
