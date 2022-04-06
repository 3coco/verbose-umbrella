class Solution218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> ps = new ArrayList<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>((a, b) -> (b - a));
        int prev = 0;

        for(int[] b: buildings) {
            ps.add(new int[] {b[0], -b[2]});
            ps.add(new int[] {b[1], b[2]});
        }
        Collections.sort(ps, (a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });

        tm.put(prev, 1);
        for (int[] p : ps) {
            if(p[1] <= 0) {
                tm.put(-p[1], tm.getOrDefault(-p[1], 0) + 1);
            } else {
                int cnt = tm.get(p[1]) - 1;
                tm.put(p[1], cnt);
                if(cnt == 0) {
                    tm.remove(p[1]);
                }
            }
            int maxH = tm.keySet().iterator().next();
            if(maxH != prev) {
                res.add(List.of(p[0], maxH));
                prev = maxH;
            }
        }
        return res;
    }
}
