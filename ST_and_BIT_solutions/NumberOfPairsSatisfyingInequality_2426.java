import java.util.*;

public class NumberOfPairsSatisfyingInequality_2426 {

    static class SegmentTree {
        long[] ST;
        int n;

        public SegmentTree(int size) {
            n = size;
            ST = new long[4 * n];
        }

        private void update(int ss, int se, int stIdx, int idx, long val) {
            if (ss == se) {
                ST[stIdx] += val;
                return;
            }
            int mid = ss + (se - ss) / 2;
            if (idx <= mid) update(ss, mid, 2 * stIdx, idx, val);
            else update(mid + 1, se, 2 * stIdx + 1, idx, val);
            ST[stIdx] = ST[2 * stIdx] + ST[2 * stIdx + 1];
        }

        public void update(int idx, long val) {
            update(1, n, 1, idx, val);
        }

        private long query(int ss, int se, int stIdx, int qs, int qe) {
            if (se < qs || ss > qe) return 0;
            if (qs <= ss && se <= qe) return ST[stIdx];
            int mid = ss + (se - ss) / 2;
            return query(ss, mid, 2 * stIdx, qs, qe) + query(mid + 1, se, 2 * stIdx + 1, qs, qe);
        }

        public long queryRange(int qs, int qe) {
            return query(1, n, 1, qs, qe);
        }
    }

    public static long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        long[] val = new long[n];
        TreeSet<Long> allValues = new TreeSet<>();

        // coordinate compression
        for (int i = 0; i < n; i++) {
            val[i] = (long) nums1[i] - (long) nums2[i];
            allValues.add(val[i]);
            allValues.add(val[i] - diff);
        }

        Map<Long, Integer> rank = new HashMap<>();
        int idx = 1;
        for (long v : allValues) rank.put(v, idx++);

        // Build Segment Tree
        SegmentTree ST = new SegmentTree(rank.size());
        long count = 0;

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            int qs = rank.get(val[i] - diff); 
            int qe = rank.size(); 
            count += ST.queryRange(qs, qe);
            ST.update(rank.get(val[i]), 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 5};
        int[] nums2 = {2, 2, 1};
        int diff = 1;
        System.out.println(numberOfPairs(nums1, nums2, diff)); // Output: 3
    }
}
