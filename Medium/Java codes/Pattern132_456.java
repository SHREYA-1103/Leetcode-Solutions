import java.util.*;

public class Pattern132_456 {
    
    // Optimal - O(n), O(n)
    public static boolean find132pattern(int[] nums) {
        int n = nums.length;

        // to track niddle element (3)
        Stack<Integer> s = new Stack<>();

        // to track the last element (2)
        int third = Integer.MIN_VALUE;

        // iterate from right to left to find the first element (1)
        for(int i=n-1; i>=0; i--){
            
            if(nums[i] < third){ // perfect match found
                return true;
            }

            while(!s.isEmpty() && nums[i]>s.peek()){
                third = Math.max(s.pop(), third);
            }

            s.push(nums[i]);
        }
        
        return false;
    }

    public static void main(String args[]){
        int nums[] = {1,2,3,4};

        System.out.println(find132pattern(nums));
    }
}
