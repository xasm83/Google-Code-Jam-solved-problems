package leetcode;

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

    /* simpler version in c

    {
    // Check for empty tree
    if (root == NULL)
        return;

    struct Stack* stack = createStack(MAX_SIZE);
    do
    {
        // Move to leftmost node
        while (root)
        {
            // Push root's right child and then root to stack.
            if (root->right)
                push(stack, root->right);
            push(stack, root);

            // Set root as root's left child
            root = root->left;
        }

        // Pop an item from stack and set it as root
        root = pop(stack);

        // If the popped item has a right child and the right child is not
        // processed yet, then make sure right child is processed before root
        if (root->right && peek(stack) == root->right)
        {
            pop(stack);  // remove right child from stack
            push(stack, root);  // push root back to stack
            root = root->right; // change root so that the right
                                // child is processed next
        }
        else  // Else print root's data and set root as NULL
        {
            printf("%d ", root->data);
            root = NULL;
        }
    } while (!isEmpty(stack));
}

     */









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

