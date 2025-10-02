import java.util.*;

public class LargestRectangleInHistogram_84 {
    
    // Better -- O(3n), O(4n)
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        // find next smaller left
        Stack<Integer> s1 = new Stack<>(); // O(n)
        int nsl[] = new int[n]; // O(n)

        nsl[0] = -1;

        s1.push(0);

        // O(n)
        for(int i=1; i<n; i++){
            while(!s1.isEmpty() && heights[s1.peek()]>=heights[i]){
                s1.pop();
            }
            nsl[i] = s1.isEmpty() ? -1 : s1.peek();
            s1.push(i);
        }

        // find next smaller right
        Stack<Integer> s2 = new Stack<>(); // O(n)
        int nsr[] = new int[n]; // O(n)

        nsr[n-1] = n;

        s2.push(n-1);

        // O(n)
        for(int i=n-2; i>=0; i--){
            while(!s2.isEmpty() && heights[s2.peek()]>=heights[i]){
                s2.pop();
            }
            nsr[i] = s2.isEmpty() ? n:s2.peek();
            s2.push(i);
        }

        //find max possible area
        int max = 0;

        // O(n)
        for(int i=0; i<n; i++){
            int len = heights[i];
            int width = nsr[i] - nsl[i] - 1;
            max = Math.max(max, len*width);
        }

        return max;
    }

    public static void main(String args[]){
        int heights[] = {2,1,5,6,2,3};

        System.out.println(largestRectangleArea(heights));
    }
}
