import java.util.LinkedList;
import java.util.Queue;

public class LongestUnivaluePath_687 {
    
    public static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.data = val;
            this.left = this.right = null;
        }
    }
    
    static int maxLen;
    
    public static int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;

        maxLen = 0;
        
        univaluePathHelper(root);

        return maxLen;
    }

    public static TreeNode buildTree(int arr[]){
        if(arr.length == 0 || arr[0] == -1) return null;

        Queue<TreeNode> q = new LinkedList<>();

        TreeNode root = new TreeNode(arr[0]);

        q.add(root);

        int idx = 1;
        while(!q.isEmpty() && idx < arr.length){
            TreeNode curr = q.remove();

            // left child
            if(idx < arr.length && arr[idx] != -1){
                curr.left = new TreeNode(arr[idx]);
                q.add(curr.left);
            }
            idx++;

            // right child
            if(idx < arr.length && arr[idx] != -1){
                curr.right = new TreeNode(arr[idx]);
                q.add(curr.right);
            }
            idx++;
        }

        return root;
    }

    public static int univaluePathHelper(TreeNode root){
        if(root == null) return 0;

        int leftMax = univaluePathHelper(root.left);
        int rightMax = univaluePathHelper(root.right);

        int left = 0;
        int right = 0;
        
        left += root.left != null && root.data == root.left.data? leftMax + 1 : 0;
        right += root.right != null && root.data == root.right.data? rightMax + 1 : 0;

        maxLen = Math.max(maxLen, left+right);
        
        return Math.max(left, right);
    }

    public static void main(String args[]){
        int arr[] = {5,4,5,1,1,-1,5};

        TreeNode root = buildTree(arr);

        System.out.println(longestUnivaluePath(root));
    }
}
