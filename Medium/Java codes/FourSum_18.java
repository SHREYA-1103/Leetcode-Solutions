import java.util.*;

public class FourSum_18 {
    
    // optimal - O(n log n + n^3), O(1)
    public static List<List<Integer>> fourSum_optimal(int[] arr, int target) {
        int n = arr.length;

        Arrays.sort(arr);

        List<List<Integer>> res = new ArrayList<>();

        for(int i=0; i<n-3; i++){
            if(i > 0 && arr[i] == arr[i-1]){
                continue;
            }
            for(int j=i+1; j<n-2; j++){
                if(j > i+1 && arr[j] == arr[j-1]){
                    continue;
                }

                int k = j+1;
                int l = n-1;

                while(k < l){
                    long sum = (long) arr[i] + arr[j] + arr[k] + arr[l];

                    if(sum == target){
                        List<Integer> list = new ArrayList<>();
                        list.add(arr[i]);
                        list.add(arr[j]);
                        list.add(arr[k]);
                        list.add(arr[l]);
                        res.add(list);
                        k++;
                        l--;
                        while(k < l && arr[k] == arr[k-1]){
                            k++;
                        }
                        while(k < l && arr[l] == arr[l+1]){
                            l--;
                        }
                    }

                    else if(sum < target){
                        k++;
                    }

                    else{
                        l--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String args[]){
        int arr[] = {1,2,-1,-2,0,1};
        int target = 2;

        List<List<Integer>> res1 = fourSum_optimal(arr, target);
        for(int i=0; i<res1.size(); i++){
            List<Integer> l1 = res1.get(i);
            System.out.println(l1.get(0) + " " + l1.get(1) + " " + l1.get(2) + " " + l1.get(3));
        }    
    }
}
