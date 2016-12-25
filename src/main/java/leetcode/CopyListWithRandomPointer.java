package leetcode;

//https://leetcode.com/problems/copy-list-with-random-pointer/
public class CopyListWithRandomPointer {
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode tempHead = head;
        while (tempHead != null) {
            RandomListNode tempNode = new RandomListNode(tempHead.label);
            tempNode.next = tempHead.next;
            tempHead.next = tempNode;
            tempHead = tempNode.next;
        }

        tempHead = head;
        while (tempHead != null) {
            RandomListNode tempNode = tempHead.next;
            tempNode.random = tempHead.random != null ? tempHead.random.next : null;
            tempHead = tempNode.next;
        }


        RandomListNode newHead = head.next;
        RandomListNode tempNewHead = newHead;
        tempHead = head;
        while (tempHead != null) {
            tempHead.next = tempHead.next.next;
            tempHead = tempHead.next;
            if (tempHead != null) {
                tempNewHead.next = tempHead.next;
                tempNewHead = tempNewHead.next;
            }


        }
        return newHead;
    }
}
