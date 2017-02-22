package leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

//preorder traverlas + binary tree property
//no nulls in the serialized string
public class SerializeBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode rootNode) {
        if (rootNode == null) {
            return "";
        }
        Collection<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode tempNode = rootNode;

        while (tempNode != null || !stack.isEmpty()) {
            if (tempNode == null) {
                tempNode = stack.pop();
            }
            result.add(tempNode.val);
            if (tempNode.right != null) {
                stack.push(tempNode.right);
            }

            tempNode = tempNode.left;
        }
        String resultString = result.stream().map(Object::toString).collect(Collectors.joining(","));
        return resultString;
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        Scanner scanner = new Scanner(data).useDelimiter(",");
        Integer val = scanner.nextInt();
        TreeNode rootNode = new TreeNode(val);
        TreeNode tempNode = rootNode;
        TreeNode parentNode = new TreeNode(Integer.MAX_VALUE);
        stack.push(parentNode);
        while (scanner.hasNextInt()) {
            val = scanner.nextInt();
            if (val > tempNode.val) {
                while (val.compareTo(parentNode.val) == 1) {
                    tempNode = stack.pop();
                    parentNode = stack.peek();
                }
                tempNode.right = new TreeNode(val);
                tempNode = tempNode.right;
            } else {
                tempNode.left = new TreeNode(val);
                stack.push(tempNode);
                parentNode = tempNode;
                tempNode = tempNode.left;
            }
        }
        return rootNode;
    }
}
