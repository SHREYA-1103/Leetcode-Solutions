import java.util.*;

public class LongestIncreasingSubsequenceII_2407 {

    static class SegmentTree {
        int[] ST;
        int n;

        public SegmentTree(int size) {
            n = size;
            ST = new int[4 * n]; 
        }

        public void update(int idx, int val) {
            updateUtil(0, n - 1, idx, val, 0);
        }

        private void updateUtil(int start, int end, int idx, int val, int node) {
            if (start == end) {
                ST[node] = Math.max(ST[node], val);
                return;
            }
            int mid = start + (end - start) / 2;
            if (idx <= mid) updateUtil(start, mid, idx, val, 2 * node + 1);
            else updateUtil(mid + 1, end, idx, val, 2 * node + 2);

            ST[node] = Math.max(ST[2 * node + 1], ST[2 * node + 2]);
        }

        public int query(int l, int r) {
            return queryUtil(0, n - 1, l, r, 0);
        }

        private int queryUtil(int start, int end, int l, int r, int node) {
            if (r < start || end < l) return 0; // no overlap
            if (l <= start && end <= r) return ST[node]; // complete overlap
            int mid = start + (end - start) / 2;
            return Math.max(queryUtil(start, mid, l, r, 2 * node + 1),
                            queryUtil(mid + 1, end, l, r, 2 * node + 2));
        }
    }

    public static int lengthOfLIS(int[] nums, int k) {
        // Coordinate compression
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            set.add(num - k); // include lower bound for queries
        }

        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int num : set) {
            map.put(num, idx++);
        }

        // Segment Tree
        SegmentTree st = new SegmentTree(set.size());
        int res = 0;

        for (int num : nums) {
            int left = map.get(num - k);
            int right = map.get(num) - 1; // strictly less than num
            int maxPrev = (left <= right) ? st.query(left, right) : 0;
            int currLen = maxPrev + 1;
            st.update(map.get(num), currLen);
            res = Math.max(res, currLen);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int k = 3;
        System.out.println(lengthOfLIS(nums, k)); // Output: 5
    }
}
