package tree;

public class Solution {
	
	//Helper class
	public class TreeNode {
		     int val;
		    TreeNode left;
		    TreeNode right;
		    TreeNode(int x) { val = x; }
	}
	
	//100. Same Tree
	public boolean isSameTree(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null && right != null || left != null && right == null) return false;
        if(left.val != right.val) return false; 
        //check symmetry of left of left and right of right 
        if(!isSameTree(left.left , right.left)) return false; 
        //check symmetry of right of left and left of right 
        if(!isSameTree(left.right , right.right)) return false;
        return true;
    }
	
	//101. Symmetric Tree
	public boolean is_symmetric(TreeNode left, TreeNode right) {
        // check if left, right have identical values
        if(left == null && right == null) return true;
        if(left == null && right != null || left != null && right == null) return false;
        if(left.val != right.val) return false; 
        //check symmetry of left of left and right of right 
        if(!is_symmetric(left.left , right.right)) return false; 
        //check symmetry of right of left and left of right 
        if(!is_symmetric(left.right , right.left)) return false;
        return true;
    }
	
	//104. Maximum Depth of Binary Tree
	public int maxDepth(TreeNode root) {
        if(root == null) return 0; // special first case 
        int ldepth = 0, rdepth = 0;
        //left subtree 
        if(root.left != null) ldepth = maxDepth(root.left);
        //right subtree
        if(root.right != null) rdepth = maxDepth(root.right);
        return Math.max(ldepth, rdepth) +1;
    }
	
	
	//110. Balanced Binary Tree
	public boolean isBalanced(TreeNode root) {
        return call_dfs(root) != -1;
        //return (ht != -1);
        
    }
    
    public int call_dfs(TreeNode cur) {
        if(cur == null) return 0; // special first case 
        int ldepth = 0, rdepth = 0;
        //left subtree 
        if(cur.left != null) {
            ldepth = call_dfs(cur.left);
            if(ldepth == -1) return -1;
        }
        
        //right subtree
        if(cur.right != null) {
            rdepth = call_dfs(cur.right);
            if(rdepth == -1) return -1;
        }
        
        if(Math.abs(ldepth - rdepth) > 1) return -1;
        return Math.max(ldepth, rdepth) +1;
        
    }
    
    
	
	//111. Minimum Depth of Binary Tree   
	public int minDepth(TreeNode root) {
	        if(root == null) return 0; // special first case 
	        if(root.left == null && root.right == null) return 1;
	        if(root.right  == null) return minDepth(root.left) +1;
	        if(root.left  == null) return minDepth(root.right) +1;
	        int ldepth = minDepth(root.left), rdepth = minDepth(root.right);
	        return ldepth <= rdepth ? ldepth +1 : rdepth + 1;
	    }
	
	/** 112. Path Sum **/
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) return true;//stopping criteria
        if(root.left != null && hasPathSum(root.left , sum - root.val)) return true;
        if(root.right != null && hasPathSum(root.right , sum - root.val)) return true;
        return false;
    }
	

}
