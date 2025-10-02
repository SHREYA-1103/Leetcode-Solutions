public class SingleElementInSortedArray_540 {

    // bruteforce - O(n), O(1)
    public static int findElement_brute(int arr[]){
        int n = arr.length;

        for(int i=0; i<n-1; i+=2){
            if(arr[i] != arr[i+1]){
                return arr[i];
            }
        }

        return -1;
    }
    
    // optimal - O(log n), O(1)
    public static int findElement_optimal(int arr[]){
        int n = arr.length;

        if(n == 1){
            return arr[0];
        }

        else if(arr[0] != arr[1]){
            return arr[0];
        }

        else if(arr[n-1] != arr[n-2]){
            return arr[n-1];
        }

        int low = 1;
        int high = n-2;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1]){
                return arr[mid];
            }
            else if((mid % 2 == 1 && arr[mid] == arr[mid-1]) || (mid % 2 == 0 && arr[mid] == arr[mid+1])){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        return -1;
    }

    public static void main(String args[]){
        int arr[] = {1,1,2,3,3,4,4,8,8};

        System.out.println("Single element in the given sorted array: " + findElement_optimal(arr));
    }
}
