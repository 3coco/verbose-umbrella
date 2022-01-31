class Solution37  {
    private int m = 9;

    public void solveSudoku(char[][] board) {
        List<int[]> spaces = new ArrayList<>();
        Set<Integer> filled = new HashSet<>();
        int[] rows = new int[m];
        int[] cols = new int[m];
        int[][] boxes = new int[m / 3][m / 3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    fill(i, j, board[i][j] - '1', false, rows, cols, boxes);
                }
            }
        }
        solve(board, spaces, filled, rows, cols, boxes);
    }

    private void fill(int r, int c, int n, boolean undo, int[] rows, int[] cols, int[][] boxes) {
        rows[r] = undo ? rows[r] & ~(1 << n) : rows[r] | (1 << n);
        cols[c] = undo ? cols[c] & ~(1 << n) : cols[c] | (1 << n);
        boxes[r / 3][c / 3] = undo ? boxes[r / 3][c / 3] & ~(1 << n) : boxes[r / 3][c / 3] | (1 << n);
    }

    private List<Character> getPossibleValues(int r, int c, int[] rows, int[] cols, int[][] boxes) {
        int mask = getValueMask(r, c, rows, cols, boxes);
        List<Character> vals = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if ((mask & (1 << i)) != 0) {
                vals.add((char)('1' + i));
            }
        }
        return vals;
    }

    private int getValueMask(int r, int c, int[] rows, int[] cols, int[][] boxes) {
        return ~(rows[r] | cols[c] | boxes[r / 3][c / 3]);
    }

    private Candidate pickNext(List<int[]> spaces, Set<Integer> filled, int[] rows, int[] cols, int[][] boxes) {
        int idx = 0;
        List<Character> vals = List.of();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < spaces.size(); i++) {
            if (filled.contains(i)) {
                continue;
            }
            List<Character> ret = getPossibleValues(spaces.get(i)[0], spaces.get(i)[1], rows, cols, boxes);
            if (ret.size() < min) {
                min = ret.size();
                idx = i;
                vals = ret;
            }
        }
        return new Candidate(idx, vals);
    }

        private boolean solve(char[][] board, List<int[]> spaces, Set<Integer> filled, int[] rows, int[] cols, int[][] boxes) {
        if (filled.size() == spaces.size()) {
            return true;
        }
        Candidate can = pickNext(spaces, filled, rows, cols, boxes);
        int[] nextPos = spaces.get(can.idx);
        filled.add(can.idx);
        int r = nextPos[0];
        int c = nextPos[1];
        for (char v : can.vals) {
            board[r][c] = v;
            fill(r, c, v - '1', false, rows, cols, boxes);
            if (solve(board, spaces, filled, rows, cols, boxes)) {
                return true;
            }
            fill(r, c, v - '1', true, rows, cols, boxes);
            board[r][c] = '.';
        }
        filled.remove(can.idx);
        return false;
    }

    class Candidate {
        int idx;
        List<Character> vals;

        public Candidate(int idx, List<Character> vals) {
            this.idx = idx;
            this.vals = vals;
        }
    }
}
