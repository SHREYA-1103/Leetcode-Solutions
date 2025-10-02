import java.util.*;

public class BuildBTFromPreorderAndInorder_105 {
    
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
            this.left = this.right = null;
        }
    }
    
    static int preidx = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) return null;

        HashMap<Integer, Integer> map = new HashMap<>();

        int idx = 0;
        
        for(int i: inorder){
            map.put(i, idx++);
        }
        
        return buildTreeHelper(preorder, map, 0, preorder.length-1);
    }

    public static TreeNode buildTreeHelper(int preorder[], HashMap<Integer, Integer> map, int start, int end){
        if(start > end) return null;
        
        TreeNode root = new TreeNode(preorder[preidx++]);

        int inidx = map.get(root.val);
        
        root.left = buildTreeHelper(preorder, map, start, inidx-1);
        root.right = buildTreeHelper(preorder, map, inidx+1, end);

        return root;
    }

    public static void printTree(TreeNode root){
        if (root == null) {
            System.out.println("[]");
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        List<String> result = new ArrayList<>();

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(curr.val));
                q.add(curr.left);
                q.add(curr.right);
            }
        }

        // remove trailing "null"s
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i--);
        }

        System.out.println(result);
    }

    public static void main(String args[]){
        int preorder[] = {3,9,20,15,7};
        int inorder[] = {9,3,15,20,7};

        TreeNode root = buildTree(preorder, inorder);

        printTree(root);
    }
}
