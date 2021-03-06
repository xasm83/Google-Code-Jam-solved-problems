package leetcode;

public class ReverseLinkedListRecursion {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listToReverse = head.next;
        head.next = null;
        return reverse(head, listToReverse);
    }

    private ListNode reverse(ListNode head, ListNode listToReverse) {
        if (listToReverse.next == null) {
            listToReverse.next = head;
            return listToReverse;
        }

        ListNode newListToReverse = listToReverse.next;
        listToReverse.next = head;

        return reverse(listToReverse, newListToReverse);
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tail = head.next;
        ListNode newHead = reverseList2(head.next);
        tail.next = head;
        head.next = null;
        return newHead;

    }
}
