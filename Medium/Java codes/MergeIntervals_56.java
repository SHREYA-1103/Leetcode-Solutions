import java.util.*;

public class MergeIntervals_56 {
    
    public static int[][] mergeIntervals(int[][] intervals) {
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList<>();

        int[] current = intervals[0]; // take first interval as current
        for (int i = 1; i < intervals.length; i++) {
            // If overlapping, merge by extending end time
            if (intervals[i][0] <= current[1]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                // No overlap, push current and move forward
                list.add(current);
                current = intervals[i];
            }
        }
        // Add last interval
        list.add(current);

        return list.toArray(new int[list.size()][]);
    }

    public static void main(String args[]){
        int intervals[][] = {{1,3},{2,6},{8,10},{15,18}};

        int res[][] = mergeIntervals(intervals);

        for (int[] re : res) {
            System.out.println(re[0] + " " + re[1]);
        }
    }
}
