class Solution84 {
    public int largestRectangleArea(int[] heights) {
        int[] left = getBoundaries(heights, true);
        int[] right = getBoundaries(heights, false);
        
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }
    
    private int[] getBoundaries(int[] heights, boolean isLeft) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;
        int[] arr = new int[n];
        
        for (int i = (isLeft ? 0 : n - 1); i >= 0 && i < heights.length; i += (isLeft ? 1 : -1)) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                arr[i] = isLeft ? -1 : n;
            } else {
                arr[i] = stack.peek();
            }
            stack.push(i);
        }
        return arr;
    }
}
