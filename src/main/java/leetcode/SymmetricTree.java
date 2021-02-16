package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;

            Queue<TreeNode> qq = new LinkedList<TreeNode>();
            qq.add(root.left);
            qq.add(root.right);

            while(!qq.isEmpty()){
                TreeNode one = qq.poll();
                TreeNode another = qq.poll();

                if (one==null && another!=null || one!=null && another ==null ) return false;
                if (one!=null && another!=null && one.val!=another.val) return false;
                if (one ==null && another==null) continue;

                if (one!=null) {
                    qq.add(one.left);
                } else {
                    qq.add(null);
                }

                if (another!=null) {
                    qq.add(another.right);
                } else {
                    qq.add(null);
                }


                if (one!=null) {
                    qq.add(one.right);
                } else {
                    qq.add(null);
                }

                if (another!=null) {
                    qq.add(another.left);
                } else {
                    qq.add(null);
                }

            }
            return true;
        }
    }