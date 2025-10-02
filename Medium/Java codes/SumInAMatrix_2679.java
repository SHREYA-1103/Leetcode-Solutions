import java.util.Arrays;

public class SumInAMatrix_2679 {
    
    public static int matrixSum(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        
        for(int i=0; i<m; i++){
            Arrays.sort(nums[i]);
        }

        int score = 0;
        
        for(int j=n-1; j>=0; j--){
            int max = Integer.MIN_VALUE;
            for(int i=0; i<m; i++){
                max = Math.max(max, nums[i][j]);
            }
            score+=max;
        }

        return score;
    }

    public static void main(String args[]){
        int nums[][] = {{7,2,1},{6,4,2},{6,5,3},{3,2,1}};

        System.out.println(matrixSum(nums));
    }
}
