import java.util.*;

public class KPairsWithSmallestSums_373 {
    
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> list = new ArrayList<>();

        HashSet<String> vis = new HashSet<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0]));

        pq.add(new int[] {nums1[0]+nums2[0], 0, 0});
        vis.add("0#0");

        while(k-- > 0 && !pq.isEmpty()){
            int top[] = pq.remove();
            
            int i = top[1];
            int j = top[2];
            
            list.add(List.of(nums1[i], nums2[j]));
            
            if(i < m-1 && !vis.contains(i+1+"#"+j)){
                vis.add((i+1) + "#" + j);
                pq.add(new int[] {nums1[i+1]+nums2[j], i+1, j});
            }
            if(j < n-1 && !vis.contains(i+"#"+j+1)){
                vis.add(i+"#"+j+1);
                pq.add(new int[] {nums1[i]+nums2[j+1], i, j+1});
            }
        }

        return list;
    }

    public static void main(String args[]){
        int nums1[] = {1,7,11};
        int nums2[] = {2,4,6};
        int k = 2;

        List<List<Integer>> list = kSmallestPairs(nums1, nums2, k);

        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).get(0) + " " + list.get(i).get(1));
        }
    }
}
