class Solution2060 {
    public boolean possiblyEquals(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        boolean[][][] memo = new boolean[50][50][2000];
        return dfs(0, 0, 0, s1.toCharArray(), s2.toCharArray(), memo);
    }

    private boolean dfs(int i1, int i2, int diff, char[] s1, char[] s2, boolean[][][] memo) {
        if (i1 == s1.length && i2 == s2.length) {
            if (diff == 0) {
                return true;
            }
            memo[i1][i2][diff + 1000] = true;
            return false;
        }
        if (memo[i1][i2][diff + 1000]) {
            return false;
        }
        memo[i1][i2][diff + 1000] = true;
        int n1 = s1.length;
        int n2 = s2.length;
        int p1 = i1;
        int num = 0;
        while (p1 < n1 && Character.isDigit(s1[p1])) {
            num = 10 * num + (s1[p1] - '0'); 
            p1++;
            if (dfs(p1, i2, diff + num, s1, s2, memo)) {
                return true;
            }
        }
        if (i1 < n1 && !Character.isDigit(s1[i1])) {
            if (diff < 0 && dfs(i1 + 1, i2, diff + 1, s1, s2, memo)) {
                return true;
            }
        }
        int p2 = i2;
        num = 0;
        while (p2 < n2 && Character.isDigit(s2[p2])) {
            num = 10 * num + (s2[p2] - '0');
            p2++;
            if (dfs(i1, p2, diff - num, s1, s2, memo)) {
                return true;
            }
        }
        if (i2 < n2 && !Character.isDigit(s2[i2])) {
            if (diff > 0 && dfs(i1, i2 + 1, diff - 1, s1, s2, memo)) {
                return true;
            }
        }
        if (diff == 0 && i1 < n1 && !Character.isDigit(s1[i1]) 
                && i2 < n2 && !Character.isDigit(s2[i2]) && s1[i1] == s2[i2]) {
            return dfs(i1 + 1, i2 + 1, 0, s1, s2, memo);
        }
        return false;
    }
}
