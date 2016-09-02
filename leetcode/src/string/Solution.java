package string;

public class Solution {

		/** 383. Ransom Note **/
		public boolean canConstruct(String ransomNote, String magazine) {
	        if(ransomNote.length() > magazine.length()) return false; // trivial case
	        ransomNote = ransomNote.toLowerCase();
	        magazine = magazine.toLowerCase();
	        int[] char_map = new int[26];
	        // build a frequency map
	        for(int i = 0; i < magazine.length(); i++) {
	            int c = magazine.charAt(i) - 'a';
	            char_map[c] = char_map[c] +1;
	        }
	        //slides through each char and check if all the chars. present in frequency map or not 
	        for(int i = 0; i < ransomNote.length(); i++) {
	            int c = ransomNote.charAt(i) - 'a';
	            if(char_map[c] < 1) return false;
	            char_map[c] = char_map[c] -1;
	        }
	        return true;
	    }
		
		
	
	
}
