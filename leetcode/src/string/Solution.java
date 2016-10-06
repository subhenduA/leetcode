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
		
		/** 28. Implement strStr()
		 * Implement strStr().
			Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
		 */
		public int strStr(String haystack, String needle) {
	        /*
	        an integer, m ← 0 (the beginning of the current match in S)
	        an integer, i ← 0 (the position of the current character in W)
	        an array of integers, T (the table, computed elsewhere)
	        */
	        int m = 0, i = 0;
	        // empty needle
	        if(needle.isEmpty()) {
	             return 0;
	           
	        }
	        
	        int[] kmp_table = build_kmp_table(needle);
	        //System.out.println("kmp_table built");
	        while ((m + i) < haystack.length()) {
	            if (needle.charAt(i) == haystack.charAt(m + i)) {
	                if (i == needle.length() - 1) return m;
	                 i++;
	            }
	            else {
	                if (kmp_table[i] > -1) {
	                     m = m + i - kmp_table[i];
	                     i = kmp_table[i];
	                } else {
	                     m++;
	                     i = 0;
	                }
	            }
	        }        
	        //(if we reach here, we have searched all of S unsuccessfully)
	        return -1;
	    }
	    
	    public int[]  build_kmp_table(String needle) {
	        int[] kmp_table = new int[needle.length()];        
	        int pos = 2, i = 0;
	         // handle trivial cases
	         kmp_table[0] = -1;
	         if(needle.length() <= 1) return kmp_table;
	         kmp_table[1] = 0;
	         
	        while(pos < kmp_table.length) {
	            //first case: the substring continues
	            if (needle.charAt(pos-1) == needle.charAt(i)) {
	                kmp_table[pos] = i + 1;
	                i++; 
	                pos++;
	            }
	            //second case: it doesn't, but we can fall back
	            else if (i > 0)
	                i = kmp_table[i];
	            //third case: we have run out of candidates.  Note cnd = 0)
	            else {
	                kmp_table[pos] = 0; 
	                pos++;
	            }
	        }
	        //for(int j = 0; j < kmp_table.length; j++) System.out.print(kmp_table[j] + ":");
	        return kmp_table;
	    }
	    
	    
		
		
		
		/** 38. Count and Say **/
		
		public String countAndSay(int n) {
	        String int_string = String.valueOf(1);
	        for(int i = 1; i < n ; i++) {
	            StringBuffer cur_string = new StringBuffer();
	            int ptr = 1, ctr = 1;
	            char c = int_string.charAt(0);
	            while(ptr < int_string.length()) {
	                if(int_string.charAt(ptr) == c) ctr++;
	                else {
	                    cur_string.append(ctr);
	                    cur_string.append(c);
	                    ctr = 1;
	                    c = int_string.charAt(ptr);
	                }
	                ptr++;
	            }
	            cur_string.append(ctr);
	            cur_string.append(c);
	            int_string = cur_string.toString();
	        }
	        return int_string;
	    }
		
		
		/** 58. Length of Last Word
		 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
		 * return the length of last word in the string.
		 * If the last word does not exist, return 0.
			Note: A word is defined as a character sequence consists of non-space characters only.
		
			For example, 
			Given s = "Hello World",
			return 5.
		 * 
		 */
		
		
		public int lengthOfLastWord(String s) {
	        if(s.trim().length() == 0) return 0;
	        int x = s.trim().lastIndexOf(' ');
	        return x < 0 ? s.trim().length() : s.trim().length() -1 -x;
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
		
		/** 151. Reverse Words in a String  **/
		
		public String reverseWords(String s) {
	        String [] words = s.split(" ");
	        StringBuilder sb = new StringBuilder();
	        int end = words.length - 1;
	        for(int i = 0; i<= end; i++){
	            if(!words[i].isEmpty()) {
	                sb.insert(0, words[i]);
	                if(i < end) sb.insert(0, " ");
	            }
	        }
	        return sb.toString();
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
