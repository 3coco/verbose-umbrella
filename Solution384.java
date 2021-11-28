class Solution384 {
    private int[] nums;
    private Random rand;
    private int n;

    public Solution(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.rand = new Random();
    }
    
    public int[] reset() {
        return nums;
    }
    
    public int[] shuffle() {
        int[] ans = nums.clone();
        for (int i = 0; i < n - 1; i++) {
            swap(ans, i, i + rand.nextInt(n - i));
        }
        return ans;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
