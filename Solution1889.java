class Solution1889 {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        int mod = (int)1e9 + 7;
        long sum = 0;
        int pn = packages.length;
        for (int p : packages) {
            sum += p;
        }
        long res = Long.MAX_VALUE;
        for (int[] bs : boxes) {
            Arrays.sort(bs);
            if (bs[bs.length - 1] < packages[pn - 1]) {
                continue;
            }
            int prev = 0;
            long tmp = 0;
            for (int b : bs) {
                int idx = search(packages, b + 1, prev);
                tmp += (idx - prev) * (long)b;
                if (idx == pn) {
                    break;
                }
                prev = idx;
            }
            res = Math.min(res, tmp);
        }
        return res == Long.MAX_VALUE ? -1 : (int)((res - sum) % mod);
    }

    private int search(int[] packages, int target, int l) {
        int r = packages.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (packages[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
