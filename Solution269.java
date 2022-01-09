class Solution269 {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> g = new HashMap<>();
        Map<Character, Integer> inDegrees = new HashMap<>();
        int n = words.length;
        
        for (String w : words) {
            for (char c : w.toCharArray()) {
                inDegrees.put(c, 0);
            }
        }
        
        for (int i = 1; i < n; i++) {
            if (words[i - 1].equals(words[i]) || words[i].startsWith(words[i - 1])) {
                continue;
            }
            int j = 0;
            int len = Math.min(words[i - 1].length(), words[i].length());
            for (j = 0; j < len; j++) {
                char pc = words[i - 1].charAt(j);
                char c = words[i].charAt(j);
                g.putIfAbsent(pc, new HashSet<>());
                if (pc != c) {
                    g.putIfAbsent(c, new HashSet<>());
                    if (!g.get(pc).contains(c)) {
                        g.get(pc).add(c);
                        inDegrees.put(c, inDegrees.get(c) + 1);
                    }
                    break;
                }
            }
            if (j == len) {
                return "";
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        
        for (var entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }
        
        while (!q.isEmpty()) {
            int tmp = q.size();
            for (int i = 0; i < tmp; i++) {
                char c = q.poll();
                sb.append(c);
                for (char next : g.getOrDefault(c, Set.of())) {
                    int in = inDegrees.get(next) - 1;
                    inDegrees.put(next, in);
                    if (in == 0) {
                        q.add(next);
                    }
                }
            }
        }
        
        return sb.length() == inDegrees.size() ? sb.toString() : "";
    }
}
