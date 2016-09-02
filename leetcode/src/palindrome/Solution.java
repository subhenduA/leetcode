package palindrome;

import linkedlist.Solution.ListNode;

public class Solution {
	//5. Longest Palindromic Substring 
		public String longestPalindrome(String s) {
		        int slen = s.length();
		        boolean[][] pal_table = new boolean[slen][slen];
		        //initialize all pal. with length 1
		        for(int i = 0; i < slen; i++) pal_table[i][i]= true;
		        int max_pal_len = 1, pal_index = 0;
		        //initialize all pal. table with length 2
		        for(int i = 0; i < slen-1; i++) {
		            if(s.charAt(i) == s.charAt(i+1))  {
		                pal_table[i][i+1]=  true;
		                max_pal_len = 2;
		                pal_index = i;
		            }
		        }
		        // build up solution for larger palindromes
		        for( int plen = 3; plen <= slen; plen++) {
		            if(plen - max_pal_len > 2) break; // avoid unnecessary computation
		            for(int i = 0; i < slen - plen + 1; i++) {
		                int j = i+plen-1;
		                if(pal_table[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
		                    pal_table[i][j] = true;
		                    max_pal_len = plen;
		                    pal_index = i;
		                }
		            }
		        }
		        return s.substring(pal_index, pal_index+max_pal_len);
		    }
		
		/** 125. Valid Palindrome : considering string , there is equivalent code for linked list **/
		
		

		
		
		//helper class
		public class ListNode {
			  int val;
			  ListNode next;
			  ListNode(int x) { val = x; }
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
    
    	/** 214. Shortest Palindrome **/
 		public String shortestPalindrome(String s) {
 	        int len = s.length(), start, end , longest = s.isEmpty() ? 0 : 1;// initialize longest to 0 or 1
 	        if(len <= 1) return s;
 			for (int begin = 0; begin <= len/2;) {
 				start = end = begin;
 				while (end < len - 1 && s.charAt(end + 1) == s.charAt(end)) ++end; // end stops the index where neighbor elements differ
 				begin = end + 1;
 				while(end < len - 1 && start > 0 && s.charAt(end + 1) == s.charAt(start - 1)) {
 					++end;
 					--start;
 				}
 				// start == 0 means the palindromic substring is also prefix string.
 				if (start == 0 && longest < end - start + 1)
 					longest = end - start + 1;
 			}
 			
 	        // build the prefix to add..
 	        StringBuilder prefix = new StringBuilder((s.substring(longest)));
 	        return new String(prefix.reverse().toString() + s);
 	    }
 		
 		
 		/** 9. Palindrome Number **/
 		public boolean isPalindrome(int x) {
 	        if (x<0 || (x!=0 && x%10==0)) return false;
 	    int rev = 0;
 	    while (x>rev){
 	    	rev = rev*10 + x%10;
 	    	x = x/10;
 	    }
 	    return (x==rev || x==rev/10);
 	    }
}
