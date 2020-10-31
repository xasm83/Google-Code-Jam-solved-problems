package leetcode;

//https://leetcode.com/problems/binary-tree-maximum-path-sum/
//mind negative nodes and leafs
public class BinaryTreeMaxPathSize {
    static int maxPathSum = 0;

    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        if (root != null && root.left == null && root.right == null) return root.val;
        maxPath(root);
        return maxPathSum;
    }

    public int maxPath(TreeNode node) {
        if (node == null) return 0;
        int ml = maxPath(node.left);
        int mr = maxPath(node.right);
        int maxChild = Math.max(ml, mr);
        if (maxChild < 0) maxChild = 0;
        if ((ml + mr + node.val) > maxPathSum) {
            maxPathSum = ml + mr + node.val;
        }
        return ((node.val + maxChild) > 0 ? node.val + maxChild : +0);
    }

}
