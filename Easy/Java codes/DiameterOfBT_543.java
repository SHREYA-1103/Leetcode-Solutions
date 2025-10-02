import java.util.LinkedList;
import java.util.Queue;

public class DiameterOfBT_543 {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
            this.left = this.right = null;
        }
    }
    
    public static class Info{
        int diam;
        int ht;

        public Info(int d, int h){
            this.diam = d;
            this.ht = h;
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

    public static int diameterOfBinaryTree(TreeNode root) {
        return diameterHelper(root).diam;
    }

    public static Info diameterHelper(TreeNode root){
        if(root == null) return new Info(0,0);

        Info leftInfo = diameterHelper(root.left);
        Info rightInfo = diameterHelper(root.right);

        int diam = Math.max(Math.max(leftInfo.diam, rightInfo.diam), leftInfo.ht+rightInfo.ht);
        int ht = Math.max(leftInfo.ht, rightInfo.ht)+1;

        return new Info(diam, ht);
    }

    public static void main(String args[]){
        int arr[] = {1,2,3,4,5};

        TreeNode root = buildTree(arr);

        System.out.println(diameterOfBinaryTree(root));
    }
}
