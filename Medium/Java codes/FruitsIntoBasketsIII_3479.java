public class FruitsIntoBasketsIII_3479 {
    
    public static class SegmentTree {
        int[] ST;
        @SuppressWarnings("unused")
        int n;

        public void init(int n){
            this.n = n;
            ST = new int[4*n];
        }

        public void build(int arr[], int stIdx, int ss, int se){
            if(ss == se){
                ST[stIdx] = arr[ss];
                return;
            }
            int mid = (ss + se)/2;
            build(arr, 2*stIdx+1, ss, mid);
            build(arr, 2*stIdx+2, mid+1, se);
            ST[stIdx] = Math.max(ST[2*stIdx+1], ST[2*stIdx+2]);
        }

        public void update(int stIdx, int ss, int se, int idx, int val){
            if(ss == se){
                ST[stIdx] = val;
                return;
            }
            int mid = (ss + se)/2;
            if(idx <= mid){
                update(2*stIdx+1, ss, mid, idx, val);
            } else {
                update(2*stIdx+2, mid+1, se, idx, val);
            }
            ST[stIdx] = Math.max(ST[2*stIdx+1], ST[2*stIdx+2]);
        }

        // find leftmost index with value >= target
        public int queryFirst(int stIdx, int ss, int se, int target){
            if(ST[stIdx] < target) return -1; // no valid basket in this segment
            if(ss == se) return ss; // found basket
            int mid = (ss + se)/2;
            if(ST[2*stIdx+1] >= target){
                return queryFirst(2*stIdx+1, ss, mid, target);
            } else {
                return queryFirst(2*stIdx+2, mid+1, se, target);
            }
        }
    }

    public static int numOfUnplacedFruits(int[] fruits, int[] baskets){
        int n = fruits.length;
        SegmentTree st = new SegmentTree();
        st.init(n);
        st.build(baskets, 0, 0, n-1);

        int unplaced = 0;
        for(int f: fruits){
            int idx = st.queryFirst(0, 0, n-1, f);
            if(idx == -1){
                unplaced++;
            } else {
                st.update(0, 0, n-1, idx, 0); // mark basket used
            }
        }
        return unplaced;
    }

    public static void main(String[] args){
        int[] fruits = {3,7,5,6};
        int[] baskets = {4,6,8,3};
        System.out.println(numOfUnplacedFruits(fruits, baskets));
    }
}

