import java.util.*;

public class ThreeSum_15 {

    // bruteforce - O(n log n + n^3), O(1)
    public static List<int[]> threeSum_brute(int arr[]){
        int n = arr.length;

        Arrays.sort(arr);
        
        List<int[]> list = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            if(i > 0 && arr[i] == arr[i-1]){
                continue;
            }
            for(int j=i+1; j<n; j++){
                if(j > 0 && arr[j] == arr[j-1]){
                    continue;
                }
                for(int k=j+1; k<n; k++){
                    if(k > 0 && arr[k] == arr[k-1]){
                        continue;
                    }
                    if(arr[i] + arr[j] + arr[k] == 0){
                       list.add(new int[] {arr[i], arr[j], arr[k]});
                    }
                }
            }
        }

        return list;
    }
    
    // optimal - O(n log n + n^2), O(1)
    public static List<List<Integer>> threeSum_optimal(int[] arr) {
        Arrays.sort(arr);

        int n = arr.length;

        List<List<Integer>> res = new ArrayList<>();

        for(int i=0; i<n-2; i++){
            if(i>0 && arr[i] == arr[i-1]){
                continue; // avoid duplicates
            }

            int low = i+1; 
            int high = n-1;

            while(low < high){
                int sum = arr[i] + arr[low] + arr[high];
                if(sum == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[low]);
                    list.add(arr[high]);
                    res.add(list);
                    low++;
                    high--;
                    while(low < high && arr[low] == arr[low-1]){
                        low++;
                    }
                    while(high > 0 && arr[high]==arr[high+1]){
                        high--;
                    }
                }
                else if(sum < 0){
                    low++;
                }
                else{
                    high--;
                }
            }
        }

        return res;
    }

    public static void main(String args[]){
        int arr[] = {1,2,-1,-2,0,1};

        List<List<Integer>> res1 = threeSum_optimal(arr);
        for(int i=0; i<res1.size(); i++){
            List<Integer> l1 = res1.get(i);
            System.out.println(l1.get(0) + " " + l1.get(1) + " " + l1.get(2));
        }    
    }
}
