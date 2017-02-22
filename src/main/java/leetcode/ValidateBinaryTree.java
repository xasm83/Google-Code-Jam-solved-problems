package leetcode;

import java.util.LinkedList;

//https://leetcode.com/submissions/detail/91524712/
//inorder traversal should be sorted

public class ValidateBinaryTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> nodeStack = new LinkedList<TreeNode>();
        TreeNode node = root;
        long lastVal = Integer.MIN_VALUE - 1L;
        while (node != null || nodeStack.size() > 0) {
            if (node != null) {
                nodeStack.push(node);
                node = node.left;

            } else {
                node = nodeStack.pop();
                if (lastVal >= node.val) {
                    return false;
                }
                lastVal = node.val;
                node = node.right;
            }
        }
        return true;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
