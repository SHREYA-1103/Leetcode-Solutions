import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfTwoArraysII_350{
    
    public static int[] intersect_optimal(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;

        List<Integer> list = new ArrayList<>();
        
        while(i < n && j < m){
            if(nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j]){
                i++;
            }
            else{
                j++;
            }
        }

        int res[] = new int[list.size()];

        int idx = 0;
        
        for(int val : list){
            res[idx++] = val;
        }

        return res;
    }

    public static void main(String args[]){
        int nums1[] = {1,2,2,1};
        int nums2[] = {2,2};

        int res1[] = intersect_optimal(nums1, nums2);

        for(int i=0; i<res1.length; i++){
            System.out.print(res1[i] + " ");
        }
    }
}