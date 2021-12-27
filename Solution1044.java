class Solution1044 {
    private int R = 26;
    private int Q = 1572869;
    
    public String longestDupSubstring(String s) {
        if (s == null || s.length() <= 1) {
            return "";
        }
        int l = 1, h = s.length() - 1;
        String res = "";
        while (l <= h) {
            int m = l + (h - l + 1) / 2;
            String ret = getDupSubstringOfLen(s, m);
            if ("".equals(ret)) {
                h = m - 1;
            } else {
                l = m + 1;
                res = ret;
            }
        }
        return res;
    }
    
    private String getDupSubstringOfLen(String s, int len) {
        Map<Long, List<Integer>> map = new HashMap<>();
        long hash = 0;
        int powerOfR = 1;
        for (int i = 0; i < len; i++) {
            powerOfR = (powerOfR * R) % Q;
            hash = ((hash * R) % Q + (s.charAt(i) - 'a')) % Q;
        }
        map.put(hash, new ArrayList<>());
        map.get(hash).add(len - 1);
        for (int i = len; i < s.length(); i++) {
            hash = ((hash * R) % Q - ((s.charAt(i - len) - 'a') * powerOfR) % Q + Q) % Q;
            hash = (hash + (s.charAt(i) - 'a')) % Q;
            if (map.containsKey(hash)) {
                String sub = s.substring(i + 1 - len, i + 1);
                if (hasDup(s, map.get(hash), sub)) {
                    return sub;
                }
            }
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<>());
            }
            map.get(hash).add(i);
        }
        return "";
    }
    
    private boolean hasDup(String s, List<Integer> endList, String sub) {
        if (endList == null || endList.isEmpty()) {
            return false;
        }
        int len = sub.length();
        for (int e : endList) {
            if (sub.equals(s.substring(e + 1 - len, e + 1))) {
                return true;
            }
        }
        return false;
    }
}
