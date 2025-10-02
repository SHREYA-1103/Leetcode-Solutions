import java.util.*;

class BalancedBT_110 {
    
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
            this.left = this.right = null;
        }
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

    public static int isBalanced(TreeNode root){
        if(root == null) return 0;

        int leftHt = isBalanced(root.left);
        int rightHt = isBalanced(root.right);

        if(leftHt == -1 || rightHt == -1 || Math.abs(leftHt - rightHt) > 1) return -1;

        return Math.max(leftHt, rightHt)+1;

    }

    public static void main(String args[]){
        int arr[] = {1,2,2,3,3,-1,-1,4,4};

        TreeNode root = buildTree(arr);

        System.out.println(isBalanced(root) == -1 ? "false" : "true");
    }
}
