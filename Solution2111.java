class Solution2111 {
    public int kIncreasing(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int total = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> incs = new ArrayList<>();
            for (int j = i; j < arr.length; j += k) {
                if (incs.isEmpty() || arr[j] >= incs.get(incs.size() - 1)) {
                    incs.add(arr[j]);
                } else {
                    incs.set(upperBound(incs, arr[j]), arr[j]);
                }
            }
            total += incs.size();
        }
        return arr.length - total;
    }
    
    private int upperBound(List<Integer> list, int t) {
        int l = 0;
        int h = list.size() - 1;
        
        while (l < h) {
            int m = l + (h - l) / 2;
            if (t >= list.get(m)) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l;
    }
}
