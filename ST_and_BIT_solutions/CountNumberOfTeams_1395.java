import java.util.*;

public class CountNumberOfTeams_1395 {
    
    static class BIT {
        int[] tree;
        int n;
        
        public BIT(int size) {
            n = size;
            tree = new int[n + 2];
        }
        
        public void update(int idx, int val) {
            while (idx <= n) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }
        
        public int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
        
        public int queryRange(int l, int r) {
            return query(r) - query(l - 1);
        }
    }
    
    public static int numTeams(int[] rating) {
        int n = rating.length;
        int[] sorted = rating.clone();
        Arrays.sort(sorted);
        
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rank.put(sorted[i], i + 1);
        }
        
        int[] leftSmaller = new int[n];
        int[] leftLarger = new int[n];
        int[] rightSmaller = new int[n];
        int[] rightLarger = new int[n];
        
        BIT bit = new BIT(n);
        
        // Count leftSmaller and leftLarger
        for (int i = 0; i < n; i++) {
            int r = rank.get(rating[i]);
            leftSmaller[i] = bit.query(r - 1);
            leftLarger[i] = i - bit.query(r);
            bit.update(r, 1);
        }
        
        // Reset BIT for right counts
        bit = new BIT(n);
        for (int i = n - 1; i >= 0; i--) {
            int r = rank.get(rating[i]);
            rightSmaller[i] = bit.query(r - 1);
            rightLarger[i] = (n - 1 - i) - bit.query(r);
            bit.update(r, 1);
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += leftSmaller[i] * rightLarger[i] + leftLarger[i] * rightSmaller[i];
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[] ratings = {2, 5, 3, 4, 1};
        
        System.out.println(numTeams(ratings));
    }
}
