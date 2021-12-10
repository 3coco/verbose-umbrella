class Solution1392 {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] next = new int[n + 1];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i + 1] = j;
        }
        
        return s.substring(0, next[n]);
    }
}
