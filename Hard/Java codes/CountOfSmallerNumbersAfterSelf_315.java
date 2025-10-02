import java.util.*;

public class CountOfSmallerNumbersAfterSelf_315{

    // bruteforce -- O(n^2), O(1)
    public static List<Integer> countSmaller_brute(int nums[]){
        int n = nums.length;
        
        List<Integer> res = new ArrayList<>();

        for(int i=0; i<n; i++){
            int count = 0;
            for(int j=i+1; j<n; j++){
                if(nums[j] < nums[i]){
                    count++;
                }
            }
            res.add(count);
        }

        return res;
    }
    
    // Optimal -- O(n log n), O(n)
    public static List<Integer> countSmaller_optimal(int[] nums) {
        int n = nums.length;

        List<Integer> res = new ArrayList<>();

        int indexes[] = new int[n];

        for(int i=0; i<n; i++){
            indexes[i] = i;
            res.add(0);
        }
        
        mergeSort(nums, indexes, 0, n-1, res);

        return res;
    }

    public static void mergeSort(int arr[], int indexes[], int start, int end, List<Integer> res){
        // one element is already sorted
        if(start >= end){
            return;
        }

        int mid = start + (end-start)/2;

        mergeSort(arr, indexes, start, mid, res);
        mergeSort(arr, indexes, mid+1, end, res);

        // merge and append the count of smaller elements on the right for each element
        merge(arr, indexes, start, mid, end, res);
    }

    public static void merge(int arr[], int indexes[], int start, int mid, int end, List<Integer> res){
        int i = start; // pointer for left array
        int j = mid+1; // pointer for right array
        
        int newIndexes[] = new int[end-start+1];
        int k = 0; // pointer for newIndexes array

        int rtCount = 0; // no. of smaller elements from right half 

        while(i <= mid && j <= end){
            if(arr[indexes[j]] < arr[indexes[i]]){
                rtCount++;
                newIndexes[k++] = indexes[j++];
            }
            else{
                res.set(indexes[i], res.get(indexes[i])+rtCount);
                newIndexes[k++] = indexes[i++];
            }
        }

        while(i <= mid){
            res.set(indexes[i], res.get(indexes[i])+rtCount);
            newIndexes[k++] = indexes[i++];
        }

        while(j <= end){
            newIndexes[k++] = indexes[j++];
        }

        // copy back to indexes original array
        for(i=0; i<newIndexes.length; i++){
            indexes[start+i] = newIndexes[i];
        }
    }

    public static void main(String args[]){
        int nums[] = {5,2,6,1};

        System.out.println("Bruteforce solution: ");
        List<Integer> list1 = countSmaller_brute(nums);
        for(int val: list1){
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println("Optimal solution: ");
        List<Integer> list2 = countSmaller_optimal(nums);
        for(int val: list2){
            System.out.print(val + " ");
        }
    }
}
