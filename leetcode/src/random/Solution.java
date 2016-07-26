package random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	
	//1. Two Sum
	public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> res_map = new java.util.Hashtable<Integer, Integer>();
        int[] out = new int[2];
        for(int i = 0; i < nums.length; i++) {
            if(res_map.containsKey(nums[i])) {
                 out[0] = res_map.get(nums[i]);
                 out[1] = i;
                return out;
            } else res_map.put(target - nums[i], i);
        }
        return out;
    }
	
	//15. 3Sum
	public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < nums.length-2; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
                int start = i+1;
                int end = nums.length-1;
                while(start < end) {
                    int res = nums[i] + nums[start] + nums[end];
                    if(res == 0) {
                        out.add(Arrays.asList(nums[i], nums[start], nums[end]));
                        end--;
                        while(start < end && nums[end] == nums[end+1]) end--;
                    } else if( res > 0) {end--; while(start < end && nums[end] == nums[end+1] ) end--;}
                    else {start ++; while(start < end && nums[start] == nums[start-1]) start++;}
                }
            }
            
        }
        
        return out;
    }
	
	// 16. 3Sum Closest
	public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest_so_far = 0;
        boolean initialized = false;
        for(int i = 0; i < nums.length-2; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
                int start = i+1;
                int end = nums.length-1;
                while(start < end) {
                    int res = nums[i] + nums[start] + nums[end];
                    if(res == target) {
                        return res;
                    } else if( res > target) {end--; while(start < end && nums[end] == nums[end+1] ) end--;}
                    else {start ++; while(start < end && nums[start] == nums[start-1]) start++;}
                    // assess the closest value so far and stores it
                    if(!initialized) {
                        closest_so_far = res;
                        initialized = true;
                    } else {
                        closest_so_far = Math.abs(target - res) < Math.abs(target - closest_so_far) ? res : closest_so_far;
                    }
                }
            }
        }
        return closest_so_far;
    }
	
	/** 36. Valid Sudoku **/
	public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set> row_sets_map = new HashMap<Integer, Set>();
        Map<Integer, Set> col_sets_map = new HashMap<Integer, Set>();
        Map<Integer, Set> square_sets_map = new HashMap<Integer, Set>();
        // checks each char. in the sudoku cell and compare it with corresponding sets to validate it 
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
              if(board[i][j] == '.') continue; // empty cell skip checking  
              int digit = board[i][j] - '0';
              Set<Integer> cell_set = row_sets_map.get(i);
              //checks in with row set 
              if(cell_set == null) {
                  cell_set = new HashSet<Integer>();
                  cell_set.add(digit);
                  row_sets_map.put(i, cell_set);
              } else if(!cell_set.contains(digit)) cell_set.add(digit);
                else return false;  // duplicate digit in the set invalid sudoku cell
              
              //checks in with col set
              cell_set = col_sets_map.get(j);
              //checks in with row set 
              if(cell_set == null) {
                  cell_set = new HashSet<Integer>();
                  cell_set.add(digit);
                  col_sets_map.put(j, cell_set);
              } else if(!cell_set.contains(digit)) cell_set.add(digit);
                else return false;  // duplicate digit in the set invalid sudoku cell
              
              // checks in with square set
              int square_key = 5*(i/3) + 7*(j/3);
              cell_set = square_sets_map.get(square_key);
              //checks in with row set 
              if(cell_set == null) {
                  cell_set = new HashSet<Integer>();
                  cell_set.add(digit);
                  square_sets_map.put(square_key, cell_set);
              } else if(!cell_set.contains(digit)) cell_set.add(digit);
                else return false;  // duplicate digit in the set invalid sudoku cell
            }
        }
        return true;
    }
	
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
	
	// 214. Shortest Palindrome
	public String shortestPalindrome(String s) {
        int len = s.length(), start, end , longest = s.isEmpty() ? 0 : 1;// initialize longest to 0 or 1
        if(len <= 1) return s;
		for (int begin = 0; begin <= len/2;) {
			start = end = begin;
			while (end < len - 1 && s.charAt(end + 1) == s.charAt(end)) ++end;
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
	
	/**  6. ZigZag Conversion **/
	public String convert(String s, int numRows) {
        // we know the formula that returns the char index list which belown 'i'th ZigZag row...
        StringBuilder outString = new StringBuilder();
        if(numRows == 1) return s;
        for(int i = 1; i <= numRows; i++) {
            int index = i, offset = 2*numRows-2;
            // for border rows
            if( i == 1 || i == numRows) {
                while( index <= s.length()) {
                    outString.append(s.charAt(index-1));
                    index += offset; 
                }
            } else {
                // for internal rows
                int index2 = 2*numRows-i;
                while( index <= s.length()) {
                    outString.append(s.charAt(index-1));
                    if(index2 <= s.length()) outString.append(s.charAt(index2-1));
                    index += offset;
                    index2 += offset;
                }
            }
        }
        return outString.toString();
    }
	
	/** 290. Word Pattern  **/
	public boolean wordPattern(String pattern, String str) {
        String[] word_arr = str.split(" ");
        if(pattern.length() != word_arr.length) return false;
        String[] char_map = new String[26];
        Set<String> unique_word_set = new HashSet<String>();
        
        for(int i= 0; i < word_arr.length; i++) {
            int c =  pattern.charAt(i) - 'a';
            if(char_map[c] != null) {
                if(!char_map[c].equals(word_arr[i])) return false;
            } else {
                if(unique_word_set.contains(word_arr[i])) return false;
                char_map[c] = word_arr[i];
                unique_word_set.add(word_arr[i]);
            }
        }
        return true;
    }
	
	/** 349. Intersection of Two Arrays **/
	public int[] intersection(int[] nums1, int[] nums2) {
        //sort both the arrays 
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        //traverse the sorted arrays to find the intersection and output it in a another array
        int[] out = new int[Math.min(nums1.length, nums2.length)];
        int ptr1 = 0, ptr2 = 0, ptr = 0;
        while(ptr1 < nums1.length && ptr2 < nums2.length) {
            if(nums1[ptr1] < nums2[ptr2]) ptr1++;
            else if(nums1[ptr1] > nums2[ptr2]) ptr2++;
            else {
                if(ptr == 0 || (ptr > 0 && out[ptr-1] != nums1[ptr1])) out[ptr++] = nums1[ptr1];
                ptr1++;
                ptr2++;
            }
        }
        return Arrays.copyOf(out, ptr);
    }

}
