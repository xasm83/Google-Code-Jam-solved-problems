package leetcode;

//https://leetcode.com/problems/intersection-of-two-linked-lists/
public class LinkedListIntersection {
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            int sizeA = 0, sizeB = 0;
            ListNode currentHeadA = headA;
            ListNode currentHeadB = headB;
            while (currentHeadA.next != null || currentHeadB.next != null) {
                if (currentHeadA.next != null) {
                    currentHeadA = currentHeadA.next;
                    sizeA++;
                } else {
                    currentHeadB = currentHeadB.next;
                    sizeB++;
                }
            }

            currentHeadA = headA;
            currentHeadB = headB;
            int diff = sizeB - sizeA;

            while (diff > 0) {
                currentHeadB = currentHeadB.next;
                diff--;
            }

            while (diff < 0) {
                currentHeadA = currentHeadA.next;
                diff++;
            }

            do {
                if (currentHeadA == currentHeadB) {
                    return currentHeadB;
                }

                currentHeadB = currentHeadB.next;
                currentHeadA = currentHeadA.next;
            } while (currentHeadA != null);
            return null;
        }
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
