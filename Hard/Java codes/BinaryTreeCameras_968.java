import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeCameras_968 {

    public static class TreeNode{
        @SuppressWarnings("unused")
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }
    
    static int count;
    
    public static int minCameraCover(TreeNode root) {
        if(root == null) return 0;

        count = 0;
        
        int res = cameraHelper(root);

        return res == -1 ? count + 1 : count;
    }

    public static int cameraHelper(TreeNode root){
        if(root == null){
            return 0;
        }  

        int left = cameraHelper(root.left);
        int right = cameraHelper(root.right);

        if(Math.min(left, right) == -1){
            count++;
            return 1;
        }

        else if(Math.max(left, right) == 1){
            return 0;
        }

        return -1;
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

    public static void main(String args[]){
        int arr[] = {0,0,-1,0,0};

        TreeNode root = buildTree(arr);

        System.out.println(minCameraCover(root));
    }
}
