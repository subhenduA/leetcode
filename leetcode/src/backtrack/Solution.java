package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	
	
	
	/**   22. Generate Parentheses **/
	public List<String> generateParenthesis(int n) {
        List<String> output =  new ArrayList<String>();
        Set<String> in_processing = new HashSet<String>();
        String cur_string = new String("()");
        if(n> 0) 
            call_func(n-1, cur_string, in_processing, output);
        else output.add("");
        
        return output;
    }
    
    public void call_func(int n, String s,  Set<String> in_processing, List<String> output) {
        // stores as output if this is a new combination
        if(n ==0) {
            //if(!output.contains(s)) 
            output.add(new String(s));
            return;
        }
        //introduce one parenthesis pair create different combination parenthesis 
        for(int i = 0; i <= s.length(); i++) {
            //String s1 = s.substring(0, i) + "(" + s.substring(i);
            s = s.substring(0, i) + "(" + s.substring(i);
            for(int j = i+1; j <= s.length(); j++) {
               // String s2 = s1.substring(0,j) + ")" + s1.substring(j);
                 s = s.substring(0,j) + ")" + s.substring(j);
                //recursively go down with s if this combination is not seen before
                /**
                if(!in_processing.contains(s2)) {
                    in_processing.add(new String(s2));
                    call_func(n-1, s2, in_processing, output);
                    //call_func(n-1, s2, output);
                }**/
                if(!in_processing.contains(s)) {
                    in_processing.add(new String(s));
                    call_func(n-1, s, in_processing, output);
                    //call_func(n-1, s2, output);
                }
                s = s.substring(0, j) + s.substring(j+1);
            }
            s = s.substring(0, i) + s.substring(i+1);    
        }
    }
    
    /** 20. Valid Parentheses **/
    public boolean isValid(String s) {
        List<String> stack = new ArrayList<String>();
        for(int i = 0; i < s.length(); i++) {
            String ch = new String(s.substring(i,i+1));
            if(ch.equals("(") || ch.equals("{") || ch.equals("[")) {
                stack.add(ch);
            } else if(ch.equals(")")) {
                if(stack.isEmpty()) return false;
                if(!stack.remove(stack.size()-1).equals("(")) return false;
            } else if(ch.equals("}")) {
                if(stack.isEmpty()) return false;
                if(!stack.remove(stack.size()-1).equals("{")) return false;
            } else if(ch.equals("]")) {
                if(stack.isEmpty()) return false;
                if(!stack.remove(stack.size()-1).equals("[")) return false;
            }
        }
        return stack.isEmpty();
    }
    
    

}
