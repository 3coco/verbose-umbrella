class Solution330 {
    public int minPatches(int[] nums, int n) {
        int len = nums.length;
        long x = 1;
        int index = 0;
        int numPatches = 0;
        while (x <= n) {
            if (index < len && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                numPatches++;
            }
        }

        return numPatches;
    }
}
