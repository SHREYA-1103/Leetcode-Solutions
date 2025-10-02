import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReversePairs_493 {

    static class BIT {
        int[] tree;
        int n;
        
        public BIT(int n){
            this.n = n;
            tree = new int[n+1];
        }
        
        // add value at index
        public void update(int idx, int val){
            while(idx <= n){
                tree[idx] += val;
                idx += idx & -idx;
            }
        }
        
        // prefix sum query
        public int query(int idx){
            int sum = 0;
            while(idx > 0){
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
    
    public static int countReversePairs_BIT(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        
        // collect values for coordinate compression
        List<Long> all = new ArrayList<>();
        for(int x: nums){
            all.add((long)x);
            all.add(2L * x);
        }
        
        Collections.sort(all);
        Map<Long, Integer> mp = new HashMap<>();
        int idx = 1;
        for(long v: all){
            if(!mp.containsKey(v)){
                mp.put(v, idx++);
            }
        }
        
        BIT bit = new BIT(idx+2);
        int ans = 0;
        
        for(int i = n-1; i >= 0; i--){
            long val = nums[i];
            
            // count how many elements < val (since nums[i] > 2*nums[j])
            ans += bit.query(mp.get(val) - 1);
            
            // insert 2*val in BIT
            bit.update(mp.get(2L * val), 1);
        }
        
        return ans;
    }
    
    public static int countReversePairs_MS(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }

    public static int mergeSort(int nums[], int start, int end){
        if(end <= start){
            return 0;
        }
        
        int mid = start + (end - start)/2;
        
        int count = mergeSort(nums, start, mid) + mergeSort(nums, mid+1, end);

        // count reverse pairs
        int j = mid + 1;
        for (int i = start; i <= mid; i++) {
            while (j <= end && nums[i] > nums[j] * 2L)
                j++;
            count += j - (mid + 1);
        }
        
        merge(nums, start, mid, end);

        return count;
    }

    public static void merge(int nums[], int start, int mid, int end){
        int i = start; //ptr for left array
        int j = mid+1; // pointer for right array
        
        int temp[] = new int[end-start+1]; // temp array to store merged elements
        int k = 0; // ptr for temp array
        
        while(i <= mid && j <= end){
            if(nums[i] <= nums[j]){
                temp[k++] = nums[i++];
            }
            else{
                temp[k++] = nums[j++];
            }
        }

        while(i <= mid){
            temp[k++] = nums[i++];
        }

        while(j <= end){
            temp[k++] = nums[j++];
        }

        for(i=start, k=0; k<temp.length; i++, k++){
            nums[i] = temp[k];
        }
    }

    public static void main(String args[]){
        int nums[] = {1,3,2,3,1};

        // System.out.println(countReversePairs_MS(nums));

        System.out.println(countReversePairs_BIT(nums));
    }
}
