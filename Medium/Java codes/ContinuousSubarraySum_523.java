import java.util.*;

public class ContinuousSubarraySum_523 {
    
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;
            if (remainder < 0) {
                remainder += k;
            }

            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) > 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }

        return false;
    }

    public static void main(String args[]){
        int nums[] = {23,2,4,6,7};
        int k = 6;

        System.out.println(checkSubarraySum(nums, k));
    }
}
