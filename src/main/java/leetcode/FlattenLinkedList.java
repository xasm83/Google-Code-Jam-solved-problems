package leetcode;

public class FlattenLinkedList {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

//https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/submissions/

    public Node flatten(Node head) {
        flattenInt(head);
        return head;
    }

    public Node flattenInt(Node node) {
        if (node == null) return null;
        if (node.child == null && node.next == null) return node;
        Node nodeNext = node.next;
        Node last = null;
        if (node.child != null) {
            last = flattenInt(node.child);
            node.next = node.child;
            node.child.prev = node;
            node.child = null;
            last.next = nodeNext;
            if (nodeNext != null) nodeNext.prev = last;
        }
//        if child has no next you need to return last(the child itself)
        return nodeNext == null ? last : flattenInt(nodeNext);
    }
}