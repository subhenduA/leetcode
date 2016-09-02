package math;

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
}
