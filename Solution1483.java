class TreeAncestor {
    private int[][] up;
    private int LOG;

    public TreeAncestor(int n, int[] parent) {
        LOG = (int)(Math.ceil(Math.log(n) / Math.log(2))) + 1;
        up = new int[n][LOG];

        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int tmp = up[i][j - 1] != -1 ? up[up[i][j - 1]][j - 1] : -1;
                up[i][j] = up[i][j - 1] != -1 ? up[up[i][j - 1]][j - 1] : -1;
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        if (k == 0 || node == -1) {
            return node;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if ((k & (1 << i)) != 0) {
                node = up[node][i];
                if (node == -1) {
                    return -1;
                }
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
