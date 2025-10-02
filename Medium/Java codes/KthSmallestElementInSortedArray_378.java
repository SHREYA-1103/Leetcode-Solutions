import java.util.*;

public class KthSmallestElementInSortedArray_378 {

    static class Info implements Comparable<Info>{
        int num;
        int row;
        int col;

        public Info(int n, int r, int c){
            this.num = n;
            this.row = r;
            this.col = c;
        }

        @Override
        public int compareTo(Info i2){
            return this.num - i2.num;
        }
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(int i=0; i<m; i++){
            pq.add(new Info(matrix[i][0], i, 0));
        }

        int ans = -1;

        while(k-- > 0 && !pq.isEmpty()){
            Info curr = pq.remove();
            ans = curr.num;
            if(curr.col < n-1) pq.add(new Info(matrix[curr.row][curr.col+1], curr.row, curr.col+1));
        }

        return ans;
    }

    public static void main(String args[]){
        int matrix[][] = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;

        System.out.println(kthSmallest(matrix, k));
    }
}