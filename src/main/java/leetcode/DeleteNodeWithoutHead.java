package leetcode;

//https://leetcode.com/problems/delete-node-in-a-linked-list/submissions/
//replace values and shoft instead of delete
public class DeleteNodeWithoutHead {
    public void deleteNode(ListNode node) {
        ListNode current= node;
        while (current.next!=null) {
            ListNode temp = current.next;
            current.val = temp.val;
            if (temp.next==null)  {
                current.next=null;
            }
            current=temp;
        }
    }
}
