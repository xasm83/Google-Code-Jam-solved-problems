package leetcode;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/diameter-of-binary-tree/
// no recursion solution with in-order traversal, need to peek and  check if parent or current then visit
//modifies tree, need extra array to track degree of a node
public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return 0;

        int r = 0;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        boolean childrenProcessed = false;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println("Popped node: " + node.val);
            if (childrenProcessed || (node.left == null && node.right == null)) {
                System.out.println("Visited: " + node.val);
                int left = (node.left != null) ? node.left.val + 1 : 0;
                int right = (node.right != null) ? node.right.val + 1 : 0;
                node.val = (node.left == null && node.right == null) ? 0 : Math.max(left, right);
                r = Math.max(r, left + right);
                System.out.println("R: " + r);
            } else {
                stack.push(node);
                if (node.right != null) {
                    stack.push(node.right);
                    System.out.println("Push node: " + node.right.val);
                }

                if (node.left != null) {
                    stack.push(node.left);
                    System.out.println("Push node: " + node.left.val);
                }
            }

            if (!stack.isEmpty()) {
                TreeNode nextNode = stack.peek();
                childrenProcessed = (nextNode.left == node || nextNode.right == node);
                System.out.println("Peek node: " + nextNode.val + " children processed: " + childrenProcessed);
            }
            System.out.println("========");
        }
        return r;
    }
}
