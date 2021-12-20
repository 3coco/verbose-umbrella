class Solution149 {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int res = 1;
        int n = points.length;
        Map<String, Integer> counts = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int dy = points[j][1] - points[i][1];
                int dx = points[j][0] - points[i][0];
                int d = gcd(dx, dy);
                String k = sb.append(dy / d).append('_').append(dx / d).toString();
                sb.setLength(0);
                counts.put(k, counts.getOrDefault(k, 0) + 1);
                max = Math.max(max, counts.get(k));
            }
            res = Math.max(res, max + 1);
            counts.clear();
        }
        return res;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
