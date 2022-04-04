class LFUCache {
    private Map<Integer, Node> cache;
    private Map<Integer, Set<Node>> freqMap;
    private int minFreq;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node n = cache.get(key);
        incFreq(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (!cache.containsKey(key) && cache.size() == capacity) {
            Node evict = freqMap.get(minFreq).iterator().next();
            cache.remove(evict.key);
            freqMap.get(minFreq).remove(evict);
            if (freqMap.get(minFreq).isEmpty()) {
                freqMap.remove(minFreq);
            }
        }
        Node n = cache.computeIfAbsent(key, k -> new Node(key, value));
        n.value = value;
        incFreq(n);
    }

    private void incFreq(Node node) {
        Set<Node> curS = freqMap.getOrDefault(node.freq, Set.of());
        if (node.freq <= minFreq && curS.size() <= 1) {
            minFreq = node.freq + 1;
            freqMap.remove(node.freq); 
        }
        if (!curS.isEmpty()) {
            curS.remove(node);
        }
        node.freq++;
        Set<Node> s = freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>());
        s.add(node);
    }
    
    private class Node {
        int key;
        int value;
        int freq;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 0;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
