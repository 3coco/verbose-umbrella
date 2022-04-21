class Solution287 {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int res = 0;
        while (res != slow) {
            res = nums[res];
            slow = nums[slow];
        }
        return res;
    }
}
