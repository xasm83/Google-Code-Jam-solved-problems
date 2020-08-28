package leetcode;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/same-tree/submissions/
    class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode> stackP = new LinkedList<>();
        Deque<TreeNode> stackQ = new LinkedList<>();
        stackP.push(p);
        stackQ.push(q);

        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode pp = stackP.pop();
            TreeNode qq = stackQ.pop();
            if (pp == null && qq == null) continue;
            if (pp == null || qq == null) return false;
            if ((pp.val != qq.val)) return false;
            stackP.push(pp.left);
            stackP.push(pp.right);
            stackQ.push(qq.left);
            stackQ.push(qq.right);
        }
        return stackP.isEmpty() == stackQ.isEmpty();
    }
}
