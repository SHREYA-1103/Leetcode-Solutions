import java.util.*;

public class PathSum_112 {
    
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static TreeNode buildTree(int[] arr){
        if(arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        int idx = 1;
        while(!q.isEmpty() && idx<arr.length){
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

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        int currSum = root.val;
        
        return pathSumHelper(root, currSum, targetSum);
    }

    public static boolean pathSumHelper(TreeNode root, int curr, int target){
        if(root.left == null && root.right == null) return target == curr;

        if(root.left != null){
            int currLeft = curr+root.left.val;
            if(pathSumHelper(root.left, currLeft, target)) return true;
        }

        if(root.right != null){
            int currRight = curr+root.right.val;
            if(pathSumHelper(root.right, currRight, target)) return true;
        }

        return false;
    }

    public static void main(String args[]){
        int arr[] = {1,2,3};
        int target = 5;

        TreeNode root = buildTree(arr);

        System.out.println(hasPathSum(root, target));
    }
}
