package random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import search.Roman;

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
	
	/** String to Integer (atoi) **/
	
		public int myAtoi(String str) {
	        int index = 0, sign = 1, total = 0;
	    //1. Empty string
	    if(str.length() == 0) return 0;
	
	    //2. Remove Spaces
	    while(str.charAt(index) == ' ' && index < str.length())
	        index ++;
	
	    //3. Handle signs
	    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
	        sign = str.charAt(index) == '+' ? 1 : -1;
	        index ++;
	    }
	    
	    //4. Convert number and avoid overflow
	    while(index < str.length()){
	        int digit = str.charAt(index) - '0';
	        if(digit < 0 || digit > 9) break;
	
	        //check if total will be overflow after 10 times and add digit
	        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
	            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
	
	        total = 10 * total + digit;
	        index ++;
	    }
	    return total * sign;
	}
		
	/** 13. Roman to Integer **/
		public int romanToInt(String a) {
		    int running_sum = 0;
		    int current_sum = 0;
		    Map<Character, Integer> roman_char = new HashMap<Character, Integer>();
		    roman_char.put('I', 1);
		    roman_char.put('V', 5);
		    roman_char.put('X', 10);
		    roman_char.put('L', 50);
		    roman_char.put('C', 100);
		    roman_char.put('D', 500);
		    roman_char.put('M', 1000);
		    for(int i = 0; i < a.length(); i++) {
		        if(i == 0) {
		            current_sum = roman_char.get(a.charAt(i));
		            continue;
		        }
		        
		        if(a.charAt(i-1) == a.charAt(i)) {
		            current_sum += roman_char.get(a.charAt(i));
		        } else if(roman_char.get(a.charAt(i)) > roman_char.get(a.charAt(i-1))) {
		            running_sum = running_sum + roman_char.get(a.charAt(i)) - current_sum;
		            current_sum = 0;
		        } else if(roman_char.get(a.charAt(i)) < roman_char.get(a.charAt(i-1))) {
		            running_sum = running_sum + current_sum;
		            current_sum = roman_char.get(a.charAt(i));
		        }
		        //System.out.println(running_sum);
		    }
		    return running_sum + current_sum;
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
	
	
	
	
	/**  6. ZigZag Conversion **/
	public String convert(String s, int numRows) {
        // we know the formula that returns the char index list which belong 'i'th ZigZag row...
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
	
	/** 27. Remove Element **/
	public int removeElement(int[] nums, int val) {
        Arrays.sort(nums);
        int val_count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(val_count > 0) nums[i-val_count] = nums[i];
            if(nums[i] == val) val_count++;
        }
        return nums.length - val_count;
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
	
	
	/** 165. Compare Version Numbers **/
	public int compareVersion(String version1, String version2) {
        //return 1;
        int ptr1 = 0, ptr2 = 0;
        int digit1, digit2;
        while(ptr1 < version1.length() || ptr2 < version2.length()) {
            
            if(ptr1 >= version1.length()) digit1 = 0;
            else {
                int ptr1_next = version1.indexOf('.', ptr1);
                if(ptr1_next < 0) ptr1_next = version1.length();
                digit1 = Integer.parseInt(version1.substring(ptr1, ptr1_next));
                //System.out.println(digit1);
                ptr1 = ptr1_next+1;
            }
            
            if(ptr2 >= version2.length()) digit2 = 0;
            else {
                int ptr2_next = version2.indexOf('.', ptr2);
                if(ptr2_next < 0) ptr2_next = version2.length();
                digit2 = Integer.parseInt(version2.substring(ptr2, ptr2_next));
                //System.out.println(digit2);
                ptr2 = ptr2_next+1;
            }
            if( digit1 > digit2) return 1;
            if( digit1 < digit2) return -1;
        }
        return 0;
    }
		
	
	/** 147. Insertion Sort List **/
	
	/** 242. Valid Anagram **/
	
	/** 274. H-Index **/
	
	
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
	
	/** 299. Bulls and Cows **/
	public String getHint(String secret, String guess) {
        int a = 0, b = 0;
        Map<Character, Integer> secret_map = new HashMap<Character, Integer>();
        Map<Character, Integer> guess_map = new HashMap<Character, Integer>();
        for(int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) a++;
            else {
                // if s present in guess_map increment cow_count else insert s in secret_map
                if (guess_map.get(s) == null || guess_map.get(s) == 0) {
                    int ct = secret_map.containsKey(s) ? secret_map.get(s) + 1: 1;
                    secret_map.put(s, ct);
                } else {
                    b++;
                    guess_map.put(s, guess_map.get(s)-1);
                }
                // if g present in secret_map increment cow_count else insert s in guess_map
                if (secret_map.get(g) == null || secret_map.get(g) == 0) {
                    int ct = guess_map.containsKey(g) ? guess_map.get(g) + 1: 1;
                    guess_map.put(g, ct);
                } else {
                    b++;
                    secret_map.put(g, secret_map.get(g)-1);
                }
            }
        }
        return a + "A" + b + "B";
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
	
	/** 350. Intersection of Two Arrays II **/
	public int[] intersect(int[] nums1, int[] nums2) {
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
                out[ptr++] = nums1[ptr1];
                ptr1++;
                ptr2++;
            }
        }
        return Arrays.copyOf(out, ptr);
    }
	
	/**  155. Min Stack **/
	public class MinStack {
	    
	    long min;
	    Stack<Long> stack;

	    /** initialize your data structure here. */
	    public MinStack() {
	        stack=new Stack<Long>();
	    }
	    
	    public void push(int x) {
	        if (stack.isEmpty()){
	            stack.push(0L);
	            min=x;
	        }else{
	            stack.push(x-min);//Could be negative if min value needs to change
	            if (x<min) min=x;
	        }
	    }
	    
	    public void pop() {
	        if (stack.isEmpty()) return;
	        
	        long pop=stack.pop();
	        
	        if (pop<0)  min=min-pop;//If negative, increase the min value
	        
	    }
	    
	    public int top() {
	        long top=stack.peek();
	        if (top>0){
	            return (int)(top+min);
	        }else{
	           return (int)(min);
	        }
	    }
	    
	    public int getMin() {
	         return (int)min;
	    }
	}

	/**
	 * Your MinStack object will be instantiated and called as such:
	 * MinStack obj = new MinStack();
	 * obj.push(x);
	 * obj.pop();
	 * int param_3 = obj.top();
	 * int param_4 = obj.getMin();
	 */

	
	/** 258. Add Digits **/
	
    public int addDigits(int num) {
        if(num%9 == 0) return num == 0 ? 0 : 9;
        return num%9;
    }
	
	/** 278. First Bad Version **/
	
	public int firstBadVersion(int n) {
        long start = 1, stop = n; 
        if(isBadVersion((int)start)) return (int)start;
        while(start < stop) {
            long mid =(long)Math.ceil((start + stop)/2);
            if(isBadVersion((int)mid)) stop = mid;
            else start = mid+1;
        }
        return (int)stop;
    }
	
	public boolean isBadVersion(int n) {
		return n >= 1702766719;
		//return n >=40;
	}
	
	/** 292. Nim Game **/
    
	public boolean canWinNim(int n) {
        if(n%4 > 0) return true;
        return false;
    }
	
	
	public List<Long> good_num(int n) {
		List<Long> output = new ArrayList<Long>();
		Map<Long, Integer> sol_set = new HashMap<Long, Integer>(); 
		long max_bound = (long)Math.cbrt(n);
		for(long i = 1; i < max_bound-1; i++) {
			long i_cube = (long)Math.pow(i, 3);
			for(long j = i+1; j <= max_bound; j++) {
				long cur_result = i_cube + (long)Math.pow(j, 3);
				Integer freq_count = sol_set.get(cur_result);
				if (freq_count == null) sol_set.put(cur_result, 1);
				else {
					if(freq_count == 1) output.add(cur_result);
					sol_set.put(cur_result, freq_count+1);
				}
			}
		}
		return output;
	}
	
	public int reverseBits(int n) {
        int r = 0;
        for(int i = 0;i < 32; i++) {
        	r <<= 1;
            if((n & 1) == 1) r = r | 1;
            n >>= 1;
            System.out.println(r);
        }
        return r;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		//System.out.println(Math.cbrt(Long.MAX_VALUE));
		//System.out.println(Math.cbrt(Long.MAX_VALUE));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(s.reverseBits(1));
		//System.out.println(s.firstBadVersion(2126753390));
		//System.out.println(s.firstBadVersion(50));
		//System.out.println(s.good_num(2147483647));
		System.out.println("here");
		
	}
}
