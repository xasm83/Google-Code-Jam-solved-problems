//http://www.practice.geeksforgeeks.org/problem-page.php?pid=700179
//recursive solution


public class IfTreeIsSumTree {
    int isLeaf(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return 0;
    }

    int isSumTree(Node node) {
        int ls;
        int rs;


        if (node == null || isLeaf(node) == 1)
            return 1;

        if (isSumTree(node.left) != 0 && isSumTree(node.right) != 0) {
            if (node.left == null)
                ls = 0;
            else if (isLeaf(node.left) != 0)
                ls = node.left.data;
            else
                //sum tree property holds here
                ls = 2 * (node.left.data);

            if (node.right == null)
                rs = 0;
            else if (isLeaf(node.right) != 0)
                rs = node.right.data;
            else
                rs = 2 * (node.right.data);


            if ((node.data == rs + ls))
                return 1;
            else
                return 0;
        }

        return 0;
    }

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }
}



