public class MedianOfTwoSortedArrays_4 {
    
    // bruteforce - O(n+m), O(n+m)
    public static int findMedian_brute(int arr1[], int arr2[]){
        int n = arr1.length;
        int m = arr2.length;

        int arr3[] = new int[n+m];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < n && j < m){
            if(arr1[i] <= arr2[j]){
                arr3[k++] = arr1[i++];
            }
            else{
                arr3[k++] = arr2[j++];
            }
        }

        while(i < n){
            arr3[k++] = arr1[i++];
        }

        while(j < m){
            arr3[k++] = arr2[j++];
        }

        int mid = (n+m)/2;

        if((n+m) % 2 == 0){
            return (arr3[mid] + arr3[mid-1])/2;
        }

        return arr3[mid];
    }

    // better - O(n+m), O(1)
    public static int findMedian_better(int arr1[], int arr2[]){
        int n = arr1.length;
        int m = arr2.length;

        int idx1 = (n+m)/2;
        int idx2 = idx1-1;

        int ele1 = -1;
        int ele2 = -1;

        int i = 0;
        int j = 0;

        int count = -1; // index of element in the sorted array

        while(i < n && j < m){
            if(arr1[i] <= arr2[j]){
                count++;
                ele1 = count == idx1 ? arr1[i] : ele1;
                ele2 = count == idx2 ? arr1[i] : ele2;
                i++;
            }
            else{
                count++;
                ele1 = count == idx1 ? arr2[j] : ele1;
                ele2 = count == idx2 ? arr2[j] : ele2;
                j++;
            }
            if(count > idx2){
                break;
            }
        }

        while(i < n){
            count++;
            ele1 = count == idx1 ? arr1[i] : ele1;
            ele2 = count == idx2 ? arr1[i] : ele2;
            i++;
            if(count > idx2){
                break;
            }
        }

        while(j < m){
            count++;
            ele1 = count == idx1 ? arr2[j] : ele1;
            ele2 = count == idx2 ? arr2[j] : ele2;
            j++;
            if(count > idx2){
                break;
            }
        }

        return (n+m)%2 == 0 ? (ele1+ele2)/2 : ele1;
    }

    // optimal - O(log(m+n)), O(1)
    public static int findMedian_optimal(int arr1[], int arr2[]){
        int m = arr1.length;
        int n = arr2.length;

        if(m > n){ // we want arr1 to be of smaller length
            return findMedian_optimal(arr2, arr1);
        }

        // binary search on number of elements to be chosen from arr1
        int low = 0;
        int high = m;

        int left = (m+n+1)/2; // no. of elements on left of median - equal in case of even and more in case of odd

        while(low <= high){
            int mid1 = low + (high - low)/2; // no. of elements from arr1
            int mid2 = left - mid1; // no. of elements from arr2
            
            int l1 = Integer.MIN_VALUE; // right most element from arr1 in left subarray
            int l2 = Integer.MIN_VALUE;// left most element from arr1 in right subarray

            int r1 = Integer.MAX_VALUE; // right most element from arr1 in left subarray
            int r2 = Integer.MAX_VALUE; // left most element from arr2 in right subarray

            if(mid1 < m){
                r1 = arr1[mid1];
            }

            if(mid2 < n){
                r2 = arr2[mid2];
            }

            if(mid1 > 0){
                l1 = arr1[mid1-1];
            }

            if(mid2 > 0){
                l2 = arr2[mid2-1];
            }

            if(l1 <= r2 && l2 <= r1){
                return (m+n) %2 == 0 ? (Math.max(l1, l2) + Math.min(r1, r2)) / 2 : Math.max(l1, l2);
            }

            else if(l1 > r2){
                high = mid1-1;
            }

            else{
                low = mid1+1;
            }
        }

        return -1;
    }
    
    public static void main(String args[]){
        int arr1[] = {1,3,4,7,10,12};
        int arr2[] = {1,3,5};

        System.out.println(findMedian_optimal(arr1, arr2));
    }
}
