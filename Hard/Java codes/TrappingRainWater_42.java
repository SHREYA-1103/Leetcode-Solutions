public class TrappingRainWater_42 {
    
    // Better -- O(3n), O(2n)
    public static int trappedWater(int[] height) {
        int n = height.length;
        
        // find the left boundary for each bar
        int maxLeft[] = new int[n];

        maxLeft[0] = height[0];

        for(int i=1; i<n; i++){
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }

        // find the right boundary for each bar
        int maxRight[] = new int[n];

        maxRight[n-1] = height[n-1];

        for(int i=n-2; i>=0; i--){
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }

        int trappedWater = 0;
        
        // calculate the max possible rainwater tthat can be trapped above each bar and sum all
        for(int i=0; i<n; i++){
            int waterHt = Math.max(Math.min(maxLeft[i], maxRight[i]), 0);
            trappedWater += waterHt - height[i];
        }

        return trappedWater;
    }

    public static void main(String args[]){
        int height[] = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println(trappedWater(height));
    }
}
