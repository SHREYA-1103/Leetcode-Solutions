import java.util.*;

public class AdjacentIncreasingSubarraysDetectionII_3349 {
   
    public static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();

        int inc = 1;
        int prevInc = 0;

        int res = 0;

        for(int i=1; i<n; i++){
            if(nums.get(i) > nums.get(i-1)){
                inc++;
            }
            else{
                prevInc = inc;
                inc = 1;
            }
            res = Math.max(res, Math.min(prevInc, inc));
            res = Math.max(res, inc/2);
        }

        return res >= k;
    }

    public static void main(String args[]){
        List<Integer> arr = Arrays.asList(2,5,7,8,9,2,3,4,3,1);
        int k = 3;

        System.out.println(hasIncreasingSubarrays(arr, k));
    }
}
