public class MinDaysToMakeMBouquets_1482 {
    
    // bruteforce - O(n + n*(max-min)), O(1)
    public static int minDays_brute(int bloom[], int m, int k){
        int n = bloom.length;

        if(n < m*k){
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            max = Math.max(max, bloom[i]);
            min = Math.min(min, bloom[i]);
        }

        for(int i=min; i<=max; i++){
            if(isPossible(bloom, m, k, i)){
                return i;
            }
        }

        return -1;
    }

    // O(n)
    public static boolean isPossible(int[] bloom, int bouquets, int flowers, int currDay){
        int n = bloom.length;

        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (bloom[i] <= currDay) {
                count++;
                if (count == flowers) {
                    bouquets--;
                    count = 0;
                }
            } 
            else {
                count = 0;
            }
            if (bouquets == 0) return true; // early exit
        }

        return bouquets == 0;
    }

    // optimal - O(n + n*log max-min), O(1)
    public static int minDays_optimal(int bloom[], int m, int k){
        int n = bloom.length;

        if(n < m*k){
            return -1;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            max = Math.max(max, bloom[i]);
            min = Math.min(min, bloom[i]);
        }

        int low = min;
        int high = max;

        int minDays = -1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(isPossible(bloom, m, k, mid)){ // if mid is the possible answer
                minDays = mid;
                high = mid-1; // search left for even min
            }
            else{
                low = mid+1;
            }
        }

        return minDays;
    }
    
    public static void main(String args[]){
        int bloom[] = {7,7,7,7,13,12,11,7};
        int m = 2;
        int k = 3;

        System.out.println(minDays_optimal(bloom, m, k));
    }
}
