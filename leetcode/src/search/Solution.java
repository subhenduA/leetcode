package search;

import java.util.Arrays;

public class Solution {
	

	/** 374. Guess Number Higher or Lower **/
	public int guessNumber(int n) {
        long start = 1, stop = n;
        while(start <= stop) {
            long guess_num = (start + stop)/2;
            //System.out.println( start + " " + stop + " " + guess_num);
            int outcome = guess((int)guess_num);
            switch(outcome) {
            case 0 : 
                return (int)guess_num;
            case 1: 
                start = guess_num+1;
                break;
            case -1:
                stop = guess_num-1;
                break;
            default: 
                break;
            }
        }
        return (int)(start+stop)/2;
    }
	
	/** 136. Single Number **/
	public int singleNumber(int[] nums) {
        int result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }
	
	/** 389. Find the Difference **/
	public char findTheDifference(String s, String t) {
        int res = t.charAt(0);
        for(int i = 1; i < t.length(); i++) res = res ^ (int)t.charAt(i);
        for(int i = 0; i < s.length(); i++) res = res ^ (int)s.charAt(i);
        return (char)res;
    }
	
	/** 387. First Unique Character in a String **/
	public int firstUniqChar(String s) {
        char[] a = s.toCharArray();
		
		for(int i=0; i<a.length;i++){
			if(s.indexOf(a[i])==s.lastIndexOf(a[i])){return i;}
		}
		return -1;
    }
	
	/** 217. Contains Duplicate **/
	public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int ind = 1; ind < nums.length; ind++) {
            if(nums[ind] == nums[ind - 1]) {
                return true;
            }
        }
        return false;
    }
	
	
}
