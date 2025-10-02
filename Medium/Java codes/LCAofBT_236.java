import java.util.LinkedList;
import java.util.Queue;

public class LCAofBT_236 {
    
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
    
    public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        if(root.val == p.val) return p;
        if(root.val == q.val) return q;

        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);

        if(left != null && right != null) return root;
        
        if(left == null) return right;
        return left;
    }

    public static void main(String args[]){
        int arr[] = {3,5,1,6,2,0,8,-1,-1,7,4};
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);

        TreeNode root = buildTree(arr);

        System.out.println(LCA(root, p, q).val);
    }
}
