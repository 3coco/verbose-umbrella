/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution1932 {
    public TreeNode canMerge(List<TreeNode> trees) {
        Set<Integer> leaves = new HashSet<>();
        Map<Integer, TreeNode> valToRoot = new HashMap<>();
        for (TreeNode n : trees) {
            valToRoot.put(n.val, n);
            if (n.left != null) {
                if (leaves.contains(n.left.val)) {
                    return null;
                }
                leaves.add(n.left.val);
            }
            if (n.right != null) {
                if (leaves.contains(n.right.val)) {
                    return null;
                }
                leaves.add(n.right.val);
            }
        }
        Set<Integer> roots = new HashSet<>(valToRoot.keySet());
        roots.removeAll(leaves);
        if (roots.size() != 1) {
            return null;
        }
        int root = roots.iterator().next();
        TreeNode rootNode = valToRoot.get(root);
        valToRoot.remove(root);
        merge(rootNode, valToRoot);
        if (!valToRoot.isEmpty()) {
            return null;
        }
        List<Integer> l = new ArrayList<>();
        inOrderTraversal(rootNode, l);
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i - 1) >= l.get(i)) {
                return null;
            }
        }
        return rootNode;
    }

    private void merge(TreeNode root, Map<Integer, TreeNode> valToRoot) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            TreeNode mergingNode = valToRoot.get(root.val);
            if (mergingNode == null) {
                return;
            }
            root.left = mergingNode.left;
            root.right = mergingNode.right;
            valToRoot.remove(root.val);
        }
        merge(root.left, valToRoot);
        merge(root.right, valToRoot);
    }

    private void inOrderTraversal(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, l);
        l.add(root.val);
        inOrderTraversal(root.right, l);
    }
}
