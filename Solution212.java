class Solution212 {
    private int[][] DIRS = new int[][] {
        {0, -1}, {-1, 0}, {0, 1}, {1, 0}
    };
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || words == null || board[0] == null || board[0].length == 0) {
            return List.of();
        }
        int m = board.length;
        int n = board[0].length;
        TrieNode trie = buildTrie(words);
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, board, trie, m, n, res);
            }
        }
        return res;
    }
    
    private void dfs(int r, int c, char[][] board, TrieNode t, int m, int n, List<String> res) {
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] == '#' || t == null
                || t.children[board[r][c] - 'a'] == null) {
            return;
        }
        char tmp = board[r][c];
        TrieNode next = t.children[tmp - 'a'];
        board[r][c] = '#';
        if (next.word != null) {
            res.add(next.word);
            next.word = null;
        }
        for (int[] d : DIRS) {
            dfs(r + d[0], c + d[1], board, next, m, n, res);
        }
        board[r][c] = tmp;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode cur = root;
            for (char c : w.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = w;
        }
        return root;
    }
    
    class TrieNode {
        String word = null;
        TrieNode[] children = new TrieNode[26];
    }
}
