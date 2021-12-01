class Solution332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String> > g = new HashMap<>();
        for (List<String> pair : tickets) {
           PriorityQueue<String> nbr = g.computeIfAbsent(pair.get(0), k -> new PriorityQueue<>());
           nbr.offer(pair.get(1));
        }
        List<String> ans = new LinkedList<>();
        dfs("JFK", g, ans);
        return ans;
    }

    private void dfs(String src, Map<String, PriorityQueue<String> > g, List<String> ans) {
        PriorityQueue<String> pq = g.get(src);
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll(), g, ans);
        }
        ans.add(0, src);
    }
}
