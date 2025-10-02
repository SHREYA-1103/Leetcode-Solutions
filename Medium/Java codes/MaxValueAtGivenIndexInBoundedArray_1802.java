public class MaxValueAtGivenIndexInBoundedArray_1802 {
    
    public static int maxValue_optimal(int n, int index, int maxSum) {
        // perform binary search on the value that can be placed at nums[index]
        // for that to be max and sum to be under maxSum, we want nums[index] to be as close to masSum and others as close to 0 as possible

        int low = 1; // min possible nums[index]
        int high = maxSum; // max possible nums[index]

        int ans = 1;
        
        while(low <= high){
            int mid = low + (high - low)/2;
            long sum = findSum(n, index, mid);
            if(sum <= maxSum){ // valid case
                ans = Math.max(ans, mid);
                low = mid+1; // search right for even bigger number if possible
            }
            else{
                high = mid-1; // search left for a smaller value
            }
        }

        return ans;
    }

    public static long findSum(int n, int index, int val){
        // to find if val at nums[index] is a possible answer
        
        long sum = val; // value at nums[index]
        
        sum+=sideSum(val-1, index); // left side sum
        sum+=sideSum(val-1, n-index-1); // right side sum

        return sum;
    }

    public static long sideSum(int val, int x){
        // val = currValue to start from
        // x = no. of elements to be placed

        long sum1 = (long) val*(val+1)/2;
        
        if(x > val){
            long y = x-val;
            return sum1+y;
        }
        else{
            long sum2 = (long) (val-x)*(val-x+1)/2;
            return sum1-sum2;
        }
    }

    public static void main(String args[]){
        int n = 4;
        int index = 2;
        int maxSum = 6;

        System.out.println(maxValue_optimal(n, index, maxSum));
    }
}
