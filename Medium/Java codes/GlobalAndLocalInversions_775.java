public class GlobalAndLocalInversions_775 {
    
    public static boolean isIdealPermutation(int[] nums) {
        // for same number of global and local inversions, whenever the elements are at wrong place, they can at max be at an index i-1 or index i+1
        // this unique property can help us find if global and local inversions are equal in the case where array of length n has elements from o to n-1 only

        int n = nums.length;
        
        for(int i=0; i<n; i++){
            if(Math.abs(nums[i] - i) > 1){
                return false;
            }
        }

        return true;
    }

    public static void main(String args[]){
        int nums[] = {1,0,2};

        System.out.println(isIdealPermutation(nums));
    }
}
