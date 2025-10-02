import java.util.*;

public class CountOfRangeSum_327 {

    static class SegmentTree{
        int n;
        int ST[];

        SegmentTree(int n){
            this.n = n;
            this.ST = new int[4*n];
        }

        void update(int stIdx, int ss, int se, int pos){
            if(ss == se){
                ST[stIdx]++;
                return; 
            }

            int mid = ss + (se - ss)/2;

            if(pos <= mid) update(2*stIdx + 1, ss, mid, pos);
            else update(2*stIdx + 2, mid + 1, se, pos);

            ST[stIdx] = ST[2*stIdx+1] + ST[2*stIdx+2];
        }

        int countRS(int stIdx, int ss, int se, int qs, int qe){
            if(qe < ss || qs > se){ // non-overlapping
                return 0;
            }
            if(qs <= ss && se <= qe){ // complete overlap
                return ST[stIdx];
            }
            else{
                int mid = ss + (se - ss)/2;
                int left = countRS(2*stIdx+1, ss, mid, qs, qe);
                int right = countRS(2*stIdx+2, mid+1, se, qs, qe);
                return left+right;
            }
        }
    }

    public static int countRangeSum(int nums[], int lower, int upper){
        int n = nums.length;

        // calculate prefix sums
        long prefix[] = new long[n+1];

        for(int i=0; i<n; i++){
            prefix[i+1] = prefix[i] + nums[i];
        }

        // perform coordinate compression - collect all values and compress values to indices
        TreeSet<Long> set = new TreeSet<>();

        for(long p: prefix){
            set.add(p);
            set.add(p - lower);
            set.add(p - upper);
        }

        HashMap<Long, Integer> map = new HashMap<>();
        int idx = 0;

        for(long val: set){
            map.put(val, idx++);
        }

        // segment tree
        SegmentTree st = new SegmentTree(map.size());

        int count = 0;

        for(long p: prefix){
            int left = map.get(p - upper);
            int right = map.get(p - lower);
            count += st.countRS(0, 0, st.n-1, left, right);

            st.update(0, 0, st.n-1, map.get(p));
        }

        return count;
    }
    
    public static void main(String args[]){
        int nums[] = {-2,5,-1};
        int lower = -2;
        int upper = 2;

        System.out.println(countRangeSum(nums, lower, upper));
    }
}
