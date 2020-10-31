package leetcode;

import java.util.*;

//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
//you can use additional queue for the next level or use array representation of a tree
public class BinaryTreeZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new LinkedList<List<Integer>>();
        boolean zig = true;
        int currentLevelLength = 1;
        int nextLevelLength = 0;

        Deque<TreeNode> queue = new LinkedList();
        queue.addFirst(root);
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> levelResult = new LinkedList<>();

        while (!queue.isEmpty()) {
            TreeNode node = zig ? queue.pollLast() : queue.pollFirst();
            currentLevelLength--;
            levelResult.add(node.val);
            int added = addNode(node, queue, zig);
            nextLevelLength += added;

            if (currentLevelLength == 0) {
                zig = !zig;
                currentLevelLength = nextLevelLength;
                nextLevelLength = 0;
                result.add(levelResult);
                levelResult = new LinkedList<>();
            }
        }
        return result;
    }

    int addNode(TreeNode node, Deque<TreeNode> queue, boolean zig) {
        int added = 0;
        if (!zig) {
            if (node.right != null) {
                queue.addLast(node.right);
                added++;
            }
            if (node.left != null) {
                queue.addLast(node.left);
                added++;
            }
        } else {
            if (node.left != null) {
                queue.addFirst(node.left);
                added++;
            }
            if (node.right != null) {
                queue.addFirst(node.right);
                added++;
            }
        }
        return added;
    }

//    with list add(0,x) trick
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null)
            return output;
        int even = 0;
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int cnt = q.size();
            for(int i=0;i<cnt;i++){
                TreeNode node = q.poll();
                if(even%2==0){
                    list.add(node.val);
                }else{
                    list.add(0,node.val);
                }
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            even++;
            output.add(list);

        }
        return output;
    }
}