package random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodNumber {
	
	/**
	 * It's kind of brute force approach. This code tried to store all the solutions in a map 'sol_map' and every time it encounters 
	 * a solution number it checks how many times so far this solution number was encountered..  a solution number encountered more than once 
	 * are separately stored into 'output' list for return 
	 * @param n
	 * @return
	 */
	public List<Long> good_num(long n) {
		List<Long> output = new ArrayList<Long>();
		Map<Long, Integer> sol_map = new HashMap<Long, Integer>(); 
		long max_bound = (long)Math.cbrt(n); // only iterate till cbrt(n)
		long counter = 0;
		for(long i = 1; i < max_bound-1; i++) {
			long i_cube = (long)Math.pow(i, 3);
			//do cleanup if needed... 
			sol_map = do_space_cleanup(sol_map, ++counter, i_cube);
			for(long j = i+1; j <= max_bound; j++) {
				long cur_result = i_cube + (long)Math.pow(j, 3);
				Integer freq_count = sol_map.get(cur_result);
				if (freq_count == null) sol_map.put(cur_result, 1);
				else {
					if(freq_count == 1) output.add(cur_result);
					sol_map.put(cur_result, freq_count+1);
				}
			}
		}
		return output;
	}
	
	
	private Map<Long, Integer> do_space_cleanup(Map<Long, Integer> sol_map, long counter, long min_cube_cutoff) {
		
		if(counter%2 == 0) {
			Map<Long, Integer> new_sol_map = new HashMap<Long, Integer>();
			System.out.println("Before cleanup : " + sol_map.size());
			Iterator<Long> key_itr =  sol_map.keySet().iterator();
			while(key_itr.hasNext()) {
				Long key = key_itr.next();
				if(key > min_cube_cutoff) new_sol_map.put(key, sol_map.get(key));
			}
			System.out.println("After cleanup : " + new_sol_map.size());
			return new_sol_map;
		}
		return sol_map;
	}
	
	// this is not a good solution it's time complexity is O(cbrt(n)*cbrt(n)) and it has similar space complexity O(cbrt(n)*cbrt(n)). I'm storing unnecessary solutions , i can definitely 
	// improve on space complexity . I tried to run it for n = 2 pow 63 ( java long ) ... It went into out of memory error in my laptop.. 
	public static void main(String[] args) {
		GoodNumber s = new GoodNumber();
		System.out.println(Long.MAX_VALUE); // here is a run for n = 2 pow 31 -1 , returns 2302 solutions.. 
		List<Long> output = s.good_num(Long.MAX_VALUE);
		System.out.println(output.size());
	}
}
