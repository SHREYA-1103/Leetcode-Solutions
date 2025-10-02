public class CountGoodTripletsInAnArray_2179 {

    static class BIT {
        long[] ST;
        int n;

        public BIT(int size) {
            n = size;
            ST = new long[n + 2];
        }

        private void update(int idx, long val) {
            while (idx <= n) {
                ST[idx] += val;
                idx += idx & -idx;
            }
        }

        private long query(int idx) {
            long sum = 0;
            while (idx > 0) {
                sum += ST[idx];
                idx -= idx & -idx;
            }
            return sum;
        }

        public void updateBIT(int idx, long val) {
            update(idx, val);
        }

        public long queryBIT(int qs, int qe) {
            return query(qe) - query(qs - 1);
        }
    }

    public static long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos2 = new int[n];
        for (int i = 0; i < n; i++) pos2[nums2[i]] = i;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = pos2[nums1[i]] + 1;

        BIT bit1 = new BIT(n);
        BIT bit2 = new BIT(n);
        long res = 0;

        for (int i = 0; i < n; i++) {
            long countPairs = bit1.queryBIT(1, arr[i] - 1); 
            res += bit2.queryBIT(1, arr[i] - 1);    
            bit1.updateBIT(arr[i], 1);  
            bit2.updateBIT(arr[i], countPairs); 
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 0, 1, 3};
        int[] nums2 = {0, 1, 2, 3};
        System.out.println(goodTriplets(nums1, nums2)); // 1

        int[] nums3 = {4,0,1,3,2};
        int[] nums4 = {4,1,0,2,3};
        System.out.println(goodTriplets(nums3, nums4)); // 4
    }
}
