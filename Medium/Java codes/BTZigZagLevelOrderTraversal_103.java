import java.util.*;

public class BTZigZagLevelOrderTraversal_103{

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

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(root == null) return res;
        
        boolean leftToRight = true;
        
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        q.add(null);

        List<Integer> l = new ArrayList<>();

        while(!q.isEmpty()){
            TreeNode curr = q.remove();
            if(curr == null){
                res.add(l);
                if(!q.isEmpty()){
                    leftToRight = !leftToRight;
                    l = new ArrayList<>();
                    q.add(null);
                }
            }
            else{
                if(leftToRight){
                    l.add(curr.val);
                }
                else{
                    l.add(0, curr.val);
                }
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }

        return res;
    }

    public static void main(String args[]){
        int arr[] = {3,9,20,-1,-1,15,7};

        TreeNode root = buildTree(arr);

        List<List<Integer>> zigZagTraversal = zigzagLevelOrder(root);

        for(List<Integer> l: zigZagTraversal){
            for(int val: l) System.out.print(val + " ");
        }
    }
}