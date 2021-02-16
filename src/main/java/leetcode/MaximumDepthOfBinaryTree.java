package leetcode;

import java.util.*;

//https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/
public class MaximumDepthOfBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public int maxDepth(TreeNode root) {
        TreeNode oldRoot = root;
        if (root == null) return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, Integer> map = new HashMap<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                if (root.right != null) stack.push(root.right);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == stack.peek() && root.right != null) {
                stack.pop();
                stack.push(root);
                root = root.right;
            } else {
                Integer left = map.get(root.left);
                if (left == null) left = 0;
                Integer right = map.get(root.right);
                if (right == null) right = 0;
                map.put(root, Math.max(left, right) + 1);
                root = null;
            }
        }
        return map.get(oldRoot);
    }

}