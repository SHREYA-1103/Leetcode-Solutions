public class CountIncreasingQuadruplets_2552 {
    
    public static class BIT {
        int n;
        int[] tree;

        public BIT(int size) {
            n = size + 1;
            tree = new int[n];
        }

        private int lowbit(int i) {
            return i & -i;
        }

        public void update(int idx) {
            while (idx < n) {
                tree[idx] += 1;
                idx += lowbit(idx);
            }
        }

        public int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= lowbit(idx);
            }
            return sum;
        }

        public int rangeQuery(int target) {
            return query(n - 1) - query(target);
        }
    }

    public static long countQuadruplets(int[] nums) {
        long ans = 0;
        int n = nums.length;

        BIT bit = new BIT(n);
        bit.update(nums[n - 1]);

        for (int k = n - 2; k >= 2; k--) {
            BIT temp = new BIT(n);
            temp.update(nums[0]);

            for (int j = 1; j < k; j++) {
                if (nums[j] > nums[k]) {
                    int icount = temp.query(nums[k]);
                    int lcount = bit.rangeQuery(nums[j]);
                    ans += (long) icount * lcount;
                }
                temp.update(nums[j]);
            }
            bit.update(nums[k]);
        }
        return ans;
    }

    public static void main(String args[]){
        int nums[] = {1,3,2,4,5};

        System.out.println(countQuadruplets(nums));
    }
}
