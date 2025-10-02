import java.util.*;

public class MaxSlidingWindow_239 {
    
    // bruteforce -- O(n^2), O(1)
    public static int[] maxSlidingWindow_brute(int[] arr, int k) {
        int res[] = new int[arr.length-k+1];

        // check individually every window and calculate the max
        for(int i=0; i<=arr.length-k; i++){
            int max = Integer.MIN_VALUE;
            for(int j=i; j<i+k; j++){
                max = Math.max(max, arr[j]);
            }
            res[i] = max;
        }

        return res;
    }

    // optimal -- O(n), O(k)
    public static int[] maxSlidingWindow_optimal(int arr[], int k){
        Deque<Integer> dq = new ArrayDeque<>();

        int res[] = new int[arr.length-k+1];

        for(int i=0; i<arr.length; i++){
            // remove indices out of the sliding window
            while(!dq.isEmpty() && dq.getFirst()<i-k){
                dq.removeFirst();
            }

            // remove indices that have elements smaller than the current
            while(!dq.isEmpty() && arr[dq.getLast()] <= arr[i]){
                dq.removeLast();
            }

            dq.addLast(i);

            if(i>=k-1){
                res[i-k+1] = arr[dq.getFirst()];
            }
        }

        return res;
    }

    public static void main(String args[]){
        int arr[] = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int res1[] = maxSlidingWindow_brute(arr, k);

        for(int i=0; i<res1.length; i++){
            System.out.print(res1[i] + " ");
        }
        System.out.println();

        int res2[] = maxSlidingWindow_optimal(arr, k);

        for(int i=0; i<res2.length; i++){
            System.out.print(res2[i] + " ");
        }
        System.out.println();
    }
}
