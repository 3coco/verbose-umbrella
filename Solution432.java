class AllOne {
    private Node head;
    private Node tail;
    private Map<Integer, Node> countToNode;
    private Map<String, Integer> keyToCount;

    public AllOne() {
        this.head = new Node(Integer.MIN_VALUE);
        this.tail = new Node(Integer.MAX_VALUE);
        this.countToNode = new HashMap<>();
        this.keyToCount = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public void inc(String key) {
        if (keyToCount.containsKey(key)) {
            changeKey(key, 1);
        } else {
            keyToCount.put(key, 1);
            if (head.next.count != 1) {
                addNode(head, new Node(1));
            }
            head.next.keys.add(key);
            countToNode.put(1, head.next);
        }
    }
    
    public void dec(String key) {
        if (!keyToCount.containsKey(key)) {
            return;
        }
        if (keyToCount.get(key) == 1) {
            keyToCount.remove(key);
            removeKey(head.next, key);
        } else {
            changeKey(key, -1);
        }
    }
    
    private void changeKey(String key, int offset) {
        int cnt = keyToCount.get(key);
        Node curNode = countToNode.get(cnt);
        Node newNode = countToNode.get(cnt + offset);
        if (newNode == null) {
            newNode = new Node(cnt + offset);
            countToNode.put(cnt + offset, newNode);
            addNode(offset == 1 ? curNode : curNode.prev, newNode);
        }
        newNode.keys.add(key);
        keyToCount.put(key, cnt + offset);
        removeKey(curNode, key);
    }

    private void addNode(Node node, Node nextNode) {
        nextNode.prev = node;
        nextNode.next = node.next;
        node.next.prev = nextNode;
        node.next = nextNode;
    }

    private void removeKey(Node node, String key) {
        node.keys.remove(key);
        if (node.keys.isEmpty()) {
            countToNode.remove(node.count);
            removeNode(node);
        }
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    private class Node {
        Node prev;
        Node next;
        int count;
        Set<String> keys;

        public Node(int count) {
            this.count = count;
            this.keys = new LinkedHashSet<String>();
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
