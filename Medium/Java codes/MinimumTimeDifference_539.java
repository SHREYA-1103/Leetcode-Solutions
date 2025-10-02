import java.util.*;

public class MinimumTimeDifference_539 {
    
    public static int findMinDifference(List<String> timePoints) {
        int minutes[] = calcMinutes(timePoints);

        Arrays.sort(minutes);

        int prev = minutes.length-1;
        int curr = 0;

        int minDiff = Integer.MAX_VALUE;

        while(curr < minutes.length){
            int currDiff = Math.abs(minutes[prev] - minutes[curr]);
            int currMin = Math.min(currDiff, 1440 - currDiff);
            minDiff = Math.min(currMin, minDiff);
            prev = curr;
            curr++;
        }

        return minDiff;
    }

    public static int[] calcMinutes(List<String> timePoints){
        int minutes[] = new int[timePoints.size()];

        int idx = 0;
        
        for(String s: timePoints){
            int h = Integer.parseInt(s.substring(0,2));
            int m = Integer.parseInt(s.substring(3,5));
            minutes[idx++] = h*60 + m;
        }

        return minutes;
    }

    public static void main(String args[]){
        List<String> timePoints = Arrays.asList("23:59","00:00");

        System.out.println(findMinDifference(timePoints));
    }
}
