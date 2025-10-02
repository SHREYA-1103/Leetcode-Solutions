import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class MaximumSumQueries_2736 {
   
    public static class SegmentTree {
        int[] ST;
        int n;

        public void init(int n) {
            this.n = n;
            ST = new int[4 * n];
            Arrays.fill(ST, -1); // since sums can be negative
        }

        private void update(int stIdx, int ss, int se, int idx, int val) {
            if (ss == se) {
                ST[stIdx] = Math.max(ST[stIdx], val);
                return;
            }
            int mid = (ss + se) / 2;
            if (idx <= mid) update(2 * stIdx + 1, ss, mid, idx, val);
            else update(2 * stIdx + 2, mid + 1, se, idx, val);
            ST[stIdx] = Math.max(ST[2 * stIdx + 1], ST[2 * stIdx + 2]);
        }

        public void update(int idx, int val) {
            update(0, 0, n - 1, idx, val);
        }

        private int query(int stIdx, int ss, int se, int qs, int qe) {
            if (ss > qe || se < qs) return -1;
            if (ss >= qs && se <= qe) return ST[stIdx];
            int mid = (ss + se) / 2;
            return Math.max(query(2 * stIdx + 1, ss, mid, qs, qe),
                            query(2 * stIdx + 2, mid + 1, se, qs, qe));
        }

        public int query(int l, int r) {
            if (l > r) return -1;
            return query(0, 0, n - 1, l, r);
        }
    }

    public static int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int q = queries.length;

        // compress nums2 values coordinate compression
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : nums2) set.add(x);
        for (int[] qr : queries) set.add(qr[1]);

        List<Integer> vals = new ArrayList<>(set);
        Map<Integer, Integer> comp = new HashMap<>();
        for (int i = 0; i < vals.size(); i++) comp.put(vals.get(i), i);

        // pair nums
        int[][] arr = new int[n][3]; 
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums1[i];
            arr[i][1] = nums2[i];
            arr[i][2] = nums1[i] + nums2[i];
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);

        int[][] Q = new int[q][4]; 
        for (int i = 0; i < q; i++) {
            Q[i][0] = queries[i][0];
            Q[i][1] = queries[i][1];
            Q[i][2] = i;
        }
        Arrays.sort(Q, (a, b) -> b[0] - a[0]); 
        
        SegmentTree st = new SegmentTree();
        st.init(vals.size());

        int[] res = new int[q];
        int ptr = 0;

        for (int[] query : Q) {
            int x = query[0], y = query[1], idx = query[2];

            // insert all arr with nums1 >= x
            while (ptr < n && arr[ptr][0] >= x) {
                int c2 = comp.get(arr[ptr][1]);
                st.update(c2, arr[ptr][2]);
                ptr++;
            }

            // query segment tree for nums2 >= y
            Integer start = set.ceiling(y);
            if (start == null) {
                res[idx] = -1;
            } else {
                int cY = comp.get(start);
                res[idx] = st.query(cY, vals.size() - 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,3,1,2};
        int[] nums2 = {2,4,9,5};
        int[][] queries = {{4,1},{1,3},{2,5}};

        int[] ans = maximumSumQueries(nums1, nums2, queries);
        System.out.println(Arrays.toString(ans)); // [6, 10, 7]
    }
}
