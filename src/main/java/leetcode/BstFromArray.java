package leetcode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class BstFromArray {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        Queue<TreeNodeWithIndexChildren> q = new LinkedList<>();
        int rootIndex = nums.length / 2;
        TreeNodeWithIndexChildren root = new TreeNodeWithIndexChildren(nums[rootIndex], 0, nums.length - 1, rootIndex);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNodeWithIndexChildren node = q.remove();
            int leftChildIndex = node.from + (node.root - node.from) / 2;
            if (leftChildIndex >= node.from && leftChildIndex < node.root) {
                TreeNodeWithIndexChildren leftChild = new TreeNodeWithIndexChildren(nums[leftChildIndex], node.from, node.root - 1, leftChildIndex);
                node.left = leftChild;
                q.add(leftChild);
            }

            int rightChildIndex = node.root + (int) Math.ceil((double) (node.to - node.root) / 2);
            if (rightChildIndex <= node.to && rightChildIndex > node.root) {
                TreeNodeWithIndexChildren rightChild = new TreeNodeWithIndexChildren(nums[rightChildIndex], node.root + 1, node.to, rightChildIndex);
                node.right = rightChild;
                q.add(rightChild);
            }
        }
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private static class TreeNodeWithIndexChildren extends TreeNode {
        int from;
        int to;
        int root;

        TreeNodeWithIndexChildren(int x, int from, int to, int root) {
            super(x);
            this.from = from;
            this.to = to;
            this.root = root;
        }
    }
}

