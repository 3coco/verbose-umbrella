class Solution282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        addOpHelper(0, new StringBuilder(), num, 0, 0, target, res);
        return res;
    }
    
    private void addOpHelper(int pos, StringBuilder path, String num, long eval, long multi,
                             int target, List<String> ans) {
        if (pos == num.length()) {
            if (eval == target) {
                ans.add(path.toString());
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i > pos && num.charAt(pos) == '0') {
                break;
            }
            long cur = Long.parseLong(num.substring(pos, i + 1));
            int beforeLength = path.length();
            if (pos == 0) {
                path.append(cur);
                addOpHelper(i + 1, path, num, cur, cur, target, ans);
                path.setLength(beforeLength);
            } else {    
                path.append('+').append(cur);
                addOpHelper(i + 1, path, num, eval + cur, cur, target, ans);
                
                path.setLength(beforeLength);
                path.append('-').append(cur);
                addOpHelper(i + 1, path, num, eval - cur, -cur, target, ans);
                
                path.setLength(beforeLength);
                path.append('*').append(cur);
                long newMulti = multi * cur;
                addOpHelper(i + 1, path, num, eval - multi + newMulti, newMulti, target, ans);
                path.setLength(beforeLength);
            }
        }
    }
}
