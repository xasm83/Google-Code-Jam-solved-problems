package leetcode;

import apple.laf.JRSUIUtils;

import java.util.LinkedList;
import java.util.List;

public class PostOrderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> treeNode = new LinkedList<>();
        TreeNode p = root;
        List<Integer> result = new LinkedList<>();
        while (!treeNode.isEmpty() || p != null) {
            if (p != null && p.left != null) {
                treeNode.addFirst(p);
                p = p.left;
            } else {
                if (p == null) {
                    if (!treeNode.isEmpty()) {
                        TreeNode t = treeNode.peekFirst();
                        if (t.right == p) {
                            while (!treeNode.isEmpty() && t.right == p) {
                                result.add(t.val);
                                p = treeNode.removeFirst();
                                t = treeNode.peekFirst();
                            }
                            if (treeNode.isEmpty()) {
                                return result;
                            }
                        }
                        p = t.right;
                    }
                } else {
                    treeNode.push(p);
                    p = p.right;
                }
            }
        }
        return result;
    }


    /*  inorder traversal

   void inorder() {
        if (root == null) {
            return;
        }

        //keep the nodes in the path that are waiting to be visited
        Stack<Node> stack = new Stack<Node>();
        Node node = root;

        //first node to be visited will be the left one
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        // traverse the tree
        while (stack.size() > 0) {

            // visit the top node
            node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) {
                node = node.right;

                // the next node to be visited is the leftmost
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
    }
*/
}

