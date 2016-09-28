package math;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
}
