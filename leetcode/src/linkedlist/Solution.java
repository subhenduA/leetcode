package linkedlist;


public class Solution {
	
		//helper class
		public class ListNode {
			  int val;
			  ListNode next;
			  ListNode(int x) { val = x; }
			 }

	/** 237. Delete Node in a Linked List **/
    public void deleteNode(ListNode node) {
        if(node.next != null) {
        node.val = node.next.val;
        node.next = node.next.next;
        }
    }
    
   
}
