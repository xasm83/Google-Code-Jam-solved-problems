package leetcode;


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/binary-tree-right-side-view/submissions/
//you track amount of nodes per this and next level, add result for the last node in this level
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> tree = new LinkedList<>();
        tree.add(root);

        List<Integer> result = new LinkedList<>();
        int currentLevel = 1;
        int nextLevel = 0;

        while (!tree.isEmpty()) {
            TreeNode current = tree.poll();
            if (current == null) continue;
            currentLevel--;
            System.out.println("val "+ current.val + " cur "+ currentLevel +" next "+ nextLevel );

            if (current.left != null) {
                nextLevel++;
                tree.add(current.left);
            }

            if (current.right != null) {
                nextLevel++;
                tree.add(current.right);
            }

            if (currentLevel == 0) {
                result.add(current.val);
                currentLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return result;
    }
}
