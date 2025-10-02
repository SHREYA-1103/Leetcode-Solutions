import java.util.*;

public class MaximalRectangle_85 {
    
    // Better - O(mn), O(mn)
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        // find next smaller left
        Stack<Integer> s1 = new Stack<>();
        int nsl[] = new int[n];

        nsl[0] = -1;

        s1.push(0);

        for(int i=1; i<n; i++){
            while(!s1.isEmpty() && heights[s1.peek()]>=heights[i]){
                s1.pop();
            }
            nsl[i] = s1.isEmpty() ? -1 : s1.peek();
            s1.push(i);
        }

        // find next smaller right
        Stack<Integer> s2 = new Stack<>();
        int nsr[] = new int[n];

        nsr[n-1] = n;

        s2.push(n-1);

        for(int i=n-2; i>=0; i--){
            while(!s2.isEmpty() && heights[s2.peek()]>=heights[i]){
                s2.pop();
            }
            nsr[i] = s2.isEmpty() ? n:s2.peek();
            s2.push(i);
        }

        //find max possible area
        int max = 0;

        for(int i=0; i<n; i++){
            int len = heights[i];
            int width = nsr[i] - nsl[i] - 1;
            max = Math.max(max, len*width);
        }

        return max;
    }
    
    public static int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int prefix[][] = new int[m][n];

        for(int i=0; i<n; i++){ // column
            for(int j=0; j<m; j++){ // row
                if(j==0){ // first row of the matrix
                    if(matrix[j][i] == '1'){
                        prefix[j][i] = 1;
                    }
                }
                else{
                    if(matrix[j][i] == '1'){
                        prefix[j][i] = prefix[j-1][i]+1;
                    }
                    else{
                        prefix[j][i] = 0;
                    }
                }
            }
        }

        int maxArea = 0;

        for(int i=0; i<m; i++){
            maxArea = Math.max(maxArea, largestRectangleArea(prefix[i]));
        }

        return maxArea;
    }

    public static void main(String args[]){
        char matrix[][] = {{'1','0','1','0','0'},
                             {'1','0','1','1','1'},
                             {'1','1','1','1','1'},
                             {'1','0','0','1','0'}};
        
        System.out.println(maximalRectangle(matrix));
        
    }
}
