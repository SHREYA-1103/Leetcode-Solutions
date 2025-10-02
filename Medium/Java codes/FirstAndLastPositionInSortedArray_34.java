public class FirstAndLastPositionInSortedArray_34{

    // bruteforce - O(n), O(1)
    public static int[] findFirstAndLastPos_brute(int arr[], int m){
        int n = arr.length;

        int res[] = {-1, -1};

        for(int i=0; i<n; i++){
            if(arr[i] == m){
                res[0] = res[0] == -1 ? i : res[0];
                res[1] = i;
            }
        }

        return res;
    }
    
    // optimal - O(2 log n), O(1)
    public static int[] findFirstAndLastPos_optimal(int arr[], int target){
        int n = arr.length;

        int res[] = {-1, -1};

        // find first position
        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(arr[mid] == target){
                res[0] = res[0] == -1 ? mid : Math.min(res[0], mid);
                high = mid-1;
            }
            else if(arr[mid] < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        // find last position
        low = 0;
        high = n-1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(arr[mid] == target){
                res[1] = res[1] == -1 ? mid : Math.max(mid, res[1]);
                low = mid+1;
            }
            else if(arr[mid] < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        return res;
    }

    public static void main(String args[]){
        int nums[] = {5,7,7,8,8,10};
        int target = 8;

        int res[] = findFirstAndLastPos_optimal(nums, target);

        System.out.println("First position: " + res[0]);
        System.out.println("Last position: " + res[1]);
    }
}