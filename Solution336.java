class Solution336 {
    private static class TrieNode {
        int index;
        List<Integer> l;
        TrieNode[] next;

        public TrieNode() {
            index = -1;
            l = new ArrayList<>();
            next = new TrieNode[26];
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode dict = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            addWord(dict, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words[i], i, dict, res);
        }
        return res;
    }

    private void addWord(TrieNode dict, String word, int index) {
        TrieNode cur = dict;
        for (int i = word.length() - 1; i >= 0; i--) {
            if (isPalindrome(word, 0, i)) {
                cur.l.add(index);
            }
            int j = word.charAt(i) - 'a';
            if (cur.next[j] == null) {
                cur.next[j] = new TrieNode();
            }
            cur = cur.next[j];
        }
        cur.index = index;
    }

    private void search(String word, int index, TrieNode dict, List<List<Integer>> res) {
        TrieNode cur = dict;
        for (int i = 0; i < word.length(); i++) {
            if (cur != null && cur.index != -1 && isPalindrome(word, i, word.length() - 1)) {
                res.add(List.of(index, cur.index));
            } else if (cur == null) {
                break;
            }
            char c = word.charAt(i);
            cur = cur.next[c - 'a'];
        }
        if (cur != null) {
            if (cur.index != -1 && cur.index != index) {
                res.add(List.of(index, cur.index));
            }
            for (int j : cur.l) {
                res.add(List.of(index, j));
            }
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
