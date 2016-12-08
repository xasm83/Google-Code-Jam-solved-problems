package leetcode;
//leetcode sort linked list
public class SortLinkedList {
    public ListNode sortList(ListNode head) {
        ListNode headRef = new ListNode(0);
        headRef.next = head;
        int step = 1;

        ListNode headSecond = seekTo(headRef.next, step);
        while (headSecond != null) {
            ListNode headFirst = headRef.next;
            ListNode tmpHead = headRef;
            while (headFirst != null) {
                tmpHead = merge(headFirst, headSecond, step, tmpHead);
                headFirst = tmpHead != null ? tmpHead.next : null;
                headSecond = seekTo(headFirst, step);
            }
            step *= 2;
            headSecond = seekTo(headRef.next, step);
        }
        return headRef.next;
    }

    private ListNode seekTo(ListNode node, int distance) {
        for (int i = 0; i < distance && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    private ListNode merge(ListNode headFirst, ListNode headSecond, int step, ListNode headRef) {
        int firstCounter = 0;
        int secondCounter = 0;
        ListNode currentNode;
        if (headSecond == null) {
            return null;
        }
        if (headFirst.val > headSecond.val) {
            currentNode = headSecond;
            headSecond = headSecond.next;
            secondCounter++;
        } else {
            currentNode = headFirst;
            headFirst = headFirst.next;
            firstCounter++;
        }
        headRef.next = currentNode;
        while (firstCounter < step || (headSecond != null && secondCounter < step)) {
            if (headSecond != null && secondCounter < step &&
                    ((headFirst != null && firstCounter < step && headFirst.val > headSecond.val) ||
                            (firstCounter >= step || headFirst == null))) {
                currentNode.next = headSecond;
                currentNode = currentNode.next;
                headSecond = headSecond.next;
                secondCounter++;
            } else if (headFirst != null && firstCounter < step) {
                currentNode.next = headFirst;
                currentNode = currentNode.next;
                headFirst = headFirst.next;
                firstCounter++;
            }
        }
        currentNode.next = headSecond;
        return currentNode;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
