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
    
    /** 234. Palindrome Linked List  **/
    public boolean isPalindrome(ListNode head) {
        if( head == null || head.next == null) return true; // trivial case
        ListNode cur = head, mid_ptr = null;
        double mid_len = 0, list_len = 0;
        // traverse the list to decide the mid point 
        while(cur != null) {
             cur = cur.next;
             list_len++;
             if(mid_len < Math.ceil(list_len/2.0)) {
                 mid_ptr = mid_ptr == null ? head : mid_ptr.next;
                 mid_len++;
             }
        }
        //determine odd even list and fix the starting point of the right part of palindrome relative to mid_point
        ListNode prev  = (list_len%2 == 0) ? mid_ptr : mid_ptr.next;
        
        // reverse the right half of the list from mid point
        cur = prev.next;
        prev.next = null; // ground this list, we may not need it ..
        while( cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        
        // start comparing the nodes in the list to check if it's a palindrome or not
        ListNode cur_list1 = head, cur_list2 = prev; 
        for(int i = 0; i < Math.floor(list_len/2); i++) {
            if(cur_list1.val != cur_list2.val) return false;
            cur_list1 = cur_list1.next;
            cur_list2 = cur_list2.next;
        }
        return true;
    }
}
