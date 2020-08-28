package leetcode;

//https://leetcode.com/problems/rotate-list/submissions/
//the idea is to have one iterator lagging for k to find rotation point
public class RotateLinkedList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode current = head;
        int length = 1;
        while (current.next != null) {
            current = current.next;
            length++;
        }
        k = k % length;
        System.out.println(k);
        System.out.println(length);

        if (k == 0) {
            return head;
        }

        current = head;
        for (int i = 0; i < k - 1; i++) {
            current = current.next;
        }

        ListNode rotationPoint = head;
        ListNode newTail = null;
        while (current.next != null) {
            current = current.next;
            newTail = rotationPoint;
            rotationPoint = rotationPoint.next;
        }

        current.next = head;
        newTail.next = null;
        return rotationPoint;

    }
}
