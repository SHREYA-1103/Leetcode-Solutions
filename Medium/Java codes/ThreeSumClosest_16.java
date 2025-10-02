import java.util.*;

public class ThreeSumClosest_16 {
    
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        int ans = 0;

        int minDiff = Integer.MAX_VALUE;
        
        for(int i=0; i<n; i++){
            int low = i+1;
            int high = n-1;

            while(low < high){
                int sum = nums[i]+nums[low]+nums[high];
                int diff = Math.abs(sum-target);
                if(diff < minDiff){
                    ans = sum;
                    minDiff = diff;
                }
                if(sum == target){
                    return sum;
                }
                if(sum < target){
                    low++;
                }
                else{
                    high--;
                }
            }
        }

        return ans;
    }

    public static void main(String args[]){
        int nums[] = {-1,2,1,-4};
        int target = 1;

        System.out.println(threeSumClosest(nums, target));
    }
}
