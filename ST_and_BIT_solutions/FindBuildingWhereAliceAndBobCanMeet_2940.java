
public class FindBuildingWhereAliceAndBobCanMeet_2940 {

    static class SegmentTree {
        int[] ST;
        int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            ST = new int[4 * n];
            buildST(arr, 0, n - 1, 0);
        }

        private void buildST(int[] arr, int ss, int se, int stIdx) {
            if (ss == se) {
                ST[stIdx] = arr[ss];
                return;
            }

            int mid = ss + (se - ss) / 2;
            buildST(arr, ss, mid, 2 * stIdx + 1);
            buildST(arr, mid + 1, se, 2 * stIdx + 2);

            ST[stIdx] = Math.max(ST[2 * stIdx + 1], ST[2 * stIdx + 2]);
        }

        public int query(int qs, int qe) {
            return queryUtil(0, 0, n - 1, qs, qe);
        }

        private int queryUtil(int stIdx, int ss, int se, int qs, int qe) {
            if (qe < ss || se < qs) return Integer.MIN_VALUE;
            if (qs <= ss && se <= qe) return ST[stIdx];
            int mid = ss + (se - ss) / 2;
            return Math.max(
                queryUtil(2 * stIdx + 1, ss, mid, qs, qe),
                queryUtil(2 * stIdx + 2, mid + 1, se, qs, qe)
            );
        }
    }

    public static int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;

        SegmentTree st = new SegmentTree(heights);

        int[] ans = new int[m];

        for (int qi = 0; qi < m; qi++) {
            int a = queries[qi][0];
            int b = queries[qi][1];

            // Case 1: Alice and Bob start at the same building.
            if (a == b) {
                ans[qi] = a;
                continue;
            }

            // Case 2: One person can move directly to the other's location.
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (heights[a] < heights[b]) {
                ans[qi] = b;
                continue;
            }

            // Case 3: No direct meeting. Find a common building to their right.
            int start = b + 1;
            int target = Math.max(heights[a], heights[b]);

            if (start >= n) {
                ans[qi] = -1;
                continue;
            }

            int l = start, r = n - 1, res = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (st.query(l, mid) > target) {
                    res = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans[qi] = res;
        }
        return ans;
    }

    public static void main(String args[]){
        int heights[] = {6,4,8,5,2,7};
        int queries[][] = {{0,1},{0,3},{2,4},{3,4},{2,2}};

        int res[] = leftmostBuildingQueries(heights, queries);

        for(int val: res){
            System.out.print(val + " ");
        }
    }
}