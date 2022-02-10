class RandomizedCollection {
    private Map<Integer, List<Integer>> m;
    private List<Pair<Integer, Integer>> l;

    public RandomizedCollection() {
        m = new HashMap<>();
        l = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        boolean present = m.containsKey(val);
        List<Integer> indices = m.computeIfAbsent(val, k -> new ArrayList<>());
        indices.add(l.size());
        l.add(new Pair<>(val, indices.size() - 1));
        return !present;
    }
    
    public boolean remove(int val) {
        List<Integer> indices = m.get(val);
        if (indices == null) {
            return false;
        }
        int idx = indices.get(indices.size() - 1);
        Pair<Integer, Integer> p = l.get(l.size() - 1);
        l.set(idx, p);
        l.remove(l.size() - 1);
        m.get(p.getKey()).set(p.getValue(), idx);
        indices.remove(indices.size() - 1);
        if (indices.isEmpty()) {
            m.remove(val);
        }
        return true;
    }
    
    public int getRandom() {
        int r = (int)(Math.floor(l.size() * Math.random()));
        return l.get(r).getKey();
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
