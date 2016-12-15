package leetcode;
//https://leetcode.com/problems/remove-nth-node-from-end-of-list/

public class RemoveNthElementFromLinkedList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nodeToRemove = head;
        ListNode currentNode = head;
        ListNode previousNode = null;
        for (int i = 0; i < n - 1; i++) {
            currentNode = currentNode.next;
            if (currentNode == null) {
                return null;
            }
        }
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            previousNode = nodeToRemove;
            nodeToRemove = nodeToRemove.next;

        }
        if (previousNode == null) {
            return head.next;
        } else {
            previousNode.next = nodeToRemove.next;
            return head;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
