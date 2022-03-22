class Solution2071 {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int l = 0, h = Math.min(tasks.length, workers.length);
        while (l < h) {
            int m = l + (h - l + 1) / 2;
            if (canAssign(tasks, workers, m, pills, strength)) {
                l = m;
            } else {
                h = m - 1;
            }
        }
        return l;
    }

    private boolean canAssign(int[] tasks, int[] workers, int k, int p, int s) {
        Deque<Integer> dq = new ArrayDeque<>();
        int ptr = workers.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            int t = tasks[i];
            while (ptr >= workers.length - k && workers[ptr] >= t - s) {
                dq.addFirst(workers[ptr]);
                ptr--;
            }
            if (!dq.isEmpty() && dq.peekLast() >= t) {
                dq.pollLast();
            } else if (!dq.isEmpty() && p > 0) {
                dq.pollFirst();
                p--;
            } else {
                return false;
            }
        }
        return true;
    }
}
