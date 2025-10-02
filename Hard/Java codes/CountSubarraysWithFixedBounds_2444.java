public class CountSubarraysWithFixedBounds_2444 {
    
    // Optimal -- O(n), O(1)
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        
        int minIdx = -1;
        int maxIdx = -1;

        int outOfBound = -1;

        long ans = 0;

        for(int i=0; i<n; i++){
            if(nums[i] == minK){
                minIdx = i;
            }
            if(nums[i] == maxK){
                maxIdx = i;
            }

            if(nums[i] < minK || nums[i] > maxK){
                outOfBound = i;
            }

            ans += Math.max(0, (Math.min(minIdx, maxIdx)-outOfBound));
        }

        return ans;
    }

    public static void main(String args[]){
        int nums[] = {1,3,5,2,7,5};
        int minK = 1;
        int maxK = 5;

        System.out.println(countSubarrays(nums, minK, maxK));
    }
}
