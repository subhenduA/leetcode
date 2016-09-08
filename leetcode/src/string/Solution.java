package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

		/** 14. Longest Common Prefix **/
		public String longestCommonPrefix(String[] strs) {
	        if(strs == null || strs.length == 0)    return "";
	        String pre = strs[0];
	        int i = 1;
	        while(i < strs.length){
	            while(strs[i].indexOf(pre) != 0)
	                pre = pre.substring(0,pre.length()-1);
	            i++;
	        }
	        return pre;
	    }
		
		/** 67. Add Binary **/
		public String addBinary(String a, String b) {
	        StringBuilder output = new StringBuilder();
	        int carry = 0;
	        int i = 0;
	        while(i < a.length() || i < b.length()) {
	            char b1 = (i < a.length()) ? a.charAt(a.length() - i-1) : '0';
	            char b2 = (i < b.length()) ? b.charAt(b.length() - i-1) : '0';
	            int count = (b1-'0') + (b2 - '0')  + carry;
	            switch(count) {
	                case 0:
	                    output.insert(0, '0');
	                    carry = 0;
	                    break;
	                case 1:
	                    output.insert(0, '1');
	                    carry = 0;
	                    break;
	                case 2:
	                    output.insert(0, '0');
	                    carry = 1;
	                    break;
	                case 3 :
	                    output.insert(0, '1');
	                    carry = 1;
	                    break;
	            }
	            i++;
	        }
	        if(carry == 1) output.insert(0, '1');
	        // adjust the output if left padded with '0'
	        if(output.toString().indexOf("1") == 0) return output.toString();
	        else if(output.toString().indexOf("1") == -1) return "0";
	        else return output.substring(output.indexOf("1"));
	        
	    }
		
		/** 205. Isomorphic Strings **/
		public boolean isIsomorphic(String s, String t) {
	        int i = 0;
	        //s = s.toLowerCase();
	        //t = t.toLowerCase();
	        Map<Character, Character> replace_map = new HashMap<Character, Character>();
	        //boolean[] t_array = new boolean[26];
	        Set<Character> t_set = new HashSet<Character>();
	        while(i < s.length()) {
	            char c = s.charAt(i);
	            if(replace_map.containsKey(c)) {
	                if(t.charAt(i) != replace_map.get(c)) return false;
	            } else {
	                if(t_set.contains(t.charAt(i))) return false;
	                replace_map.put(c, t.charAt(i));
	                t_set.add(t.charAt(i));
	            }
	            i++;
	        }
	        return true;
	    }
	
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
