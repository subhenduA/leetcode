package dynamic;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	/** 70. Climbing Stairs **/
	public int climbStairs(int n) {
        Map<Integer, Integer> memo_map = new HashMap<Integer, Integer>();
        return climb(n, memo_map);
        
    }
    
    public int climb(int n, Map<Integer, Integer> memo_map) {
        if(n==1) return 1;
        if(n ==2) return 2;
        if(memo_map.containsKey(n)) return memo_map.get(n);
        else {
            int result = climb(n-1, memo_map) + climb(n-2, memo_map);
            memo_map.put(n, result);
            return result;
        }
    }
    
    
    
}
