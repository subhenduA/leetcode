package math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import linkedlist.Solution.ListNode;

public class Solution {
	/** 171. Excel Sheet Column Number **/
	public int titleToNumber(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(s.length()-1-i) - 'A'+1;
            result += c*(int)Math.pow(26,i);
        }
        return result;
    }
	
	/** 168. Excel Sheet Column Title **/
	public String convertToTitle(int n) {
        String output = "";
        while(n > 0) {
            int k = n%26;
            int res = (k == 0) ? 26 : k;
            n = (k == 0) ? n/26-1 : n/26;
            output = String.valueOf((char)(res-1+'A')) + output;
        }
        return output;
    }
	
	/** 7. Reverse Integer **/
	public int reverse(int x) {
        int sign = (x < 0) ? -1 : 1;
        x = (sign == -1) ? -x : x;
        Stack<Integer> s = new Stack<Integer>();
        while(x > 0) {
            s.push(x%10);
            x/=10;
        }
        int base = 1, result = 0;
        while(!s.isEmpty()) {
            int digit = s.pop();
            //System.out.println(result + " : " + digit*base);
            if(digit != 0 && digit*base < base) return 0;
            result += digit*base;
            if(result < 0) return 0;
            if(base*10 < base) return 0;
            base *=10;
        }
        return sign*result;
    }
	
	/** 29. Divide Two Integers 
	 * Divide two integers without using multiplication, division and mod operator.
		If it is overflow, return MAX_INT.
	 * **/
	public int divide(int dividend, int divisor) {
        boolean negative_output = false;
        long my_dividend = dividend;
        long my_divisor = divisor;
        
        if(my_dividend < 0 ) {
            my_dividend *=-1;
            negative_output = !negative_output;
        }
        if(my_divisor < 0 ) {
            my_divisor *=-1;
            negative_output = !negative_output;
        }
        long result = my_divide(my_dividend, my_divisor);
        if(result > Integer.MAX_VALUE) {
            return negative_output ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else {
            return negative_output ? -1*(int)result : (int)result;
        }
    }
    
    public long my_divide(long my_dividend, long my_divisor) {
        //System.out.println(my_dividend + ":" + my_divisor);
        if(my_dividend < my_divisor) return 0; // base case 
        long result = 1;
        long temp = my_divisor;
        while(my_dividend > (temp + temp)) {
            temp += temp;
            result += result;
        }
        //System.out.println(result);
        return (result + my_divide(my_dividend - temp, my_divisor));
    }
    
    
	
	
	/** 202. Happy Number  **/
	
	public boolean isHappy(int n) {
        Set<Integer> visited_nums = new HashSet<Integer>();// keeps track of the numbers encounterdd in the while loop
        visited_nums.add(n);//inserts the starting numbers
        while(true) {
            int new_n = 0; // calculate the square sum of the digits
            while(n > 0) {
                new_n += (n%10)*(n%10);
                n /=10;
            }
            //System.out.println(new_n);
            if(new_n == 1) return true; // got the happy number
            if(visited_nums.contains(new_n)) return false; // encountered old number already visited , a loop returns false
            visited_nums.add(new_n); 
            n = new_n;
        }
    }
	
	
	/** 204. Count Primes **/
	
	public int countPrimes(int n) {
		   boolean[] isPrime = new boolean[n];
		   for (int i = 2; i < n; i++) {
		      isPrime[i] = true;
		   }
		   // Loop's ending condition is i * i < n instead of i < sqrt(n)
		   // to avoid repeatedly calling an expensive function sqrt().
		   for (int i = 2; i * i < n; i++) {
		      if (!isPrime[i]) continue;
		      for (int j = i * i; j < n; j += i) {
		         isPrime[j] = false;
		      }
		   }
		   int count = 0;
		   for (int i = 2; i < n; i++) {
		      if (isPrime[i]) count++;
		   }
		   return count;
	}
	
	/** 263. Ugly Number **/
	public boolean isUgly(int num) {
        if(num == 1) return true;
        if(num == 0) return false;
        if(num%2 == 0) return isUgly(num/2);
        if(num%3 == 0) return isUgly(num/3);
        if(num%5 == 0) return isUgly(num/5);
        return false;
    }
	
	/** 396. Rotate Function 
	 * Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).
	 * 
	 * **/
	
	public int maxRotateFunction(int[] A) {
        if(A.length == 0) return 0;
        int length = A.length, total = 0, max_so_far = 0;
        for(int i = 0; i < length; i++) {
            total += A[i];
            max_so_far += i*A[i];
        }
        int last_func_value = max_so_far;
        for(int i = 1; i < length; i++) {
            int cur_func_value = last_func_value + total - length*A[length-i];
            if(cur_func_value > max_so_far) max_so_far = cur_func_value;
            last_func_value = cur_func_value;
        }
        return max_so_far;
    }
	
	
	 /**
	  * 397. Integer Replacement 
	  * Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?
	  */
	
	public int integerReplacement(int n) {
        long N = n;
        int count = 0;
        while(N != 1) {
            if(N % 2 == 0) {
                N = N >> 1;
            }
            else {
                if(N == 3) {
                    count += 2;
                    break;
                }
                N = ((N+1)%4 == 0) ? N + 1 : N - 1;
            }
            count ++;
        }
        return count;
    }
	
	/** 401. Binary Watch 
	 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 
	 * 6 LEDs on the bottom represent the minutes (0-59).
	 * 
	 * Given a non-negative integer n which represents the number of LEDs that are currently 
	 * on, return all possible times the watch could represent.
	 * 
	 * **/
	
	public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<String>();
        for (int h=0; h<12; h++)
        	for (int m=0; m<60; m++)
        		if (Integer.bitCount(h * 64 + m) == num)
        			times.add(String.format("%d:%02d", h, m));
        return times;  
   }
	
	/** 382. Linked List Random Node 
	 * Reservoir Sampling**/
	
	public class Solution1 {

	    /** @param head The linked list's head.
	        Note that the head is guaranteed to be not null, so it contains at least one node. */
		//helper class
				public class ListNode {
					  int val;
					  ListNode next;
					  ListNode(int x) { val = x; }
					 }
	    ListNode head = null;
	    Random randomGenerator = null;
	    public Solution1(ListNode head) {
	        this.head = head;
	        this.randomGenerator = new Random();

	    }
	    
	    /** Returns a random node's value. */
	    public int getRandom() {
	         ListNode result = null;
	        ListNode current = head;
	        
	        for(int n = 1; current!=null; n++) {
	            if (randomGenerator.nextInt(n) == 0) {
	                result = current;
	            }
	            current = current.next;
	        }
	        
	        return result.val;
	    }
	}
	
	/** 398. Random Pick Index **/
	
	public class Solution2 {

	    Random randomGenerator = null;
	    int[] nums = null;
	    public Solution2(int[] nums) {
	        this.nums = nums;
	        this.randomGenerator = new Random();
	    }
	    
	    public int pick(int target) {
	        int target_match_count  = 0;
	        int result_index = -1;
	        for(int i = 0; i < nums.length; i++) {
	            if(nums[i] == target) {
	                target_match_count++;
	                if(randomGenerator.nextInt(target_match_count) == 0) result_index = i;
	            }
	        }
	        return result_index;
	    }
	}
	
	
	/** 400. Nth Digit 
	 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
	 * **/
	
	public int findNthDigit(int n) {
        long[] intv_array = {9,189, 2889, 38889, 488889, 5888889, 68888889, 788888889, 8888888889l};
        int k = 1;
        for(; k <= intv_array.length; k++) 
            if(intv_array[k-1] >= n) break;
        //System.out.println(k);
        int num = (int)(Math.pow(10, k) - 1 - Math.floor((intv_array[k-1] - n)/k));
        //System.out.println(num);
        long digit_pos = (long)(intv_array[k-1] - n)%k;
        //System.out.println(digit_pos);
        int output = 0;
        for(int j = 0; j <= digit_pos; j++) {
            output = num%10;
            num /= 10;
        }
        return output;
    }
}
