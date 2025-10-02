import java.util.*;

public class BeautifulTowersI_2865 {
    
    // Better -- O(3n), O(3n)
    public static long maximumSumOfHeights(int[] heights) {
        int n = heights.length;
        
        Stack<Integer> s = new Stack<>();

        long prefixSum[] = new long[n];
        long suffixSum[] = new long[n];
        
        // calculate prefix sum
        prefixSum[0] = heights[0];
        s.push(0);
        
        for(int i=1; i<n; i++){
            while(!s.isEmpty() && heights[s.peek()]>heights[i]){
                s.pop();
            }
            
            if(s.isEmpty()){
                prefixSum[i] = (long) heights[i] * (i+1);
            }
            else{
                int j = s.peek();
                prefixSum[i] = prefixSum[j] + (long) heights[i] * (i - j);
            }
            s.push(i);
        }

        // calculate suffix sum
        s = new Stack<>();

        suffixSum[n-1] = heights[n-1];
        s.push(n-1);
        
        for(int i=n-2; i>=0; i--){
            while(!s.isEmpty() && heights[s.peek()]>heights[i]){
                s.pop();
            }
            if(s.isEmpty()){
                suffixSum[i] = (long) heights[i] * (n-i);
            }
            else{
                int j = s.peek();
                suffixSum[i] = suffixSum[j] + (long) heights[i] * (j - i);
            }
            s.push(i);
        }

        // calculate max possible heights
        long maxHeight = 0;
    
        for(int i=0; i<n; i++){
            maxHeight = Math.max(maxHeight, prefixSum[i] + suffixSum[i] - heights[i]);
        }

        return maxHeight;
    }

    public static void main(String args[]){
        int heights[] = {5,3,4,1,1};

        System.out.println(maximumSumOfHeights(heights));
    }
}
