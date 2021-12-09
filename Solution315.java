class Solution315 {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return List.of();
        }
        int n = nums.length;
        int[] indexes = new int[n];
        int[] tmp = new int[n];
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, indexes, tmp, res, 0, n - 1);

        List<Integer> ans = new ArrayList<>();
        for (int r : res) {
            ans.add(r);
        }
        return ans;
    }

    private void mergeSort(int[] nums, int[] indexes, int[] tmp, int[] res, int start, int end) {
        if (start >= end) {
            return;
        }
        int m = start + (end - start) / 2;
        mergeSort(nums, indexes, tmp, res, start, m);
        mergeSort(nums, indexes, tmp, res, m + 1, end);
        System.arraycopy(indexes, start, tmp, start, end - start + 1);
        int i = start;
        int j = m + 1;
        int k = start;
        while (i <= m && j <= end) {
            if (nums[tmp[i]] <= nums[tmp[j]]) {
                indexes[k++] = tmp[i];
                res[tmp[i]] += (j - m - 1);
                i++;
            } else {
                indexes[k++] = tmp[j++];
            }
        }
        while (i <= m) {
            indexes[k++] = tmp[i];
            res[tmp[i]] += (end - m);
            i++; 
        }
        while (j <= end) {
            indexes[k++] = tmp[j++];
        }
    }
}
