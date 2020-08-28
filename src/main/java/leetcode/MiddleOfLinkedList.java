package leetcode;



class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        boolean  found = false;
        ListNode single = head;
        ListNode dbl = head;
        while (dbl.next !=null && !found ){
            if (dbl.next.next ==null) {
                single = single.next;
                found = true;
            } else {
                single = single.next;
                dbl = dbl.next.next;
            }
        }
        return single;
    }
}
