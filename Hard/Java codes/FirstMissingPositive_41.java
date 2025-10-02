public class FirstMissingPositive_41 {
    
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Place numbers in their correct positions
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // swap nums[i] and nums[nums[i] - 1]
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        // Identify the first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all 1..n are present, answer is n+1
        return n + 1;
    }

    public static void main(String args[]){
        int nums[] = {1,2,0};

        System.out.println(firstMissingPositive(nums));
    }
}
