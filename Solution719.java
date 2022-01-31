class Solution719 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int h = nums[n - 1] - nums[0];

        while (l < h) {
            int m = l + (h - l) / 2;
            if (check(nums, m, k)) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int dist, int k) {
        int cnt = 0;
        int j = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            while (j < nums.length && nums[j] - nums[i] <= dist) {
                j++;
            }
            cnt += (j - i - 1);
        }
        return cnt >= k;
    }
}
