class Solution212 {
    private final int[][] DIRS = new int[][] {
        {0, -1}, {-1, 0}, {0, 1}, {1, 0}  
    };
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return List.of();
        }
        TrieNode root = buildTrie(words);
        int m = board.length, n = board[0].length;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, res, root);
            }
        }
        return res;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode cur = root;
            for (char c : w.toCharArray()) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }
                cur = cur.next[c - 'a'];
            }
            cur.word = w;
        }
        return root;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res, TrieNode root) {
        int m = board.length, n = board[0].length;
        char c = board[i][j];
        TrieNode nn = node.next[c - 'a'];
        
        if (nn == null) {
            return;
        }

        if (nn != null && nn.word != null) {
            res.add(nn.word);
            deleteWordFromTrie(root, nn.word.toCharArray(), 0);
        }

        board[i][j] = '#';
        for (int[] d : DIRS) {
            int nr = i + d[0];
            int nc = j + d[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || board[nr][nc] == '#') {
                continue;
            }
            dfs(board, nr, nc, nn, res, root);
        }
        board[i][j] = c;
    }

    private boolean deleteWordFromTrie(TrieNode node, char[] word, int i) {
        if (word.length == i) {
            node.word = null;
            return !hasNext(node);
        }
        char c = word[i];
        TrieNode n = node.next[c - 'a'];
        boolean ret = deleteWordFromTrie(n, word, i + 1);
        if (ret) {
            node.next[c - 'a'] = null;
            return node.word == null && !hasNext(node);
        }
        return false;
    }

    private boolean hasNext(TrieNode node) {
        for (int i = 0; i < node.next.length; i++) {
            if (node.next[i] != null) {
                return true;
            }
        }
        return false;
    }

    private class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
