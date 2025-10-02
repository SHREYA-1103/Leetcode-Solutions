import java.util.*;

public class FindAllDuplicatesInArray_442 {
    
    public static List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> list=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            int ind=Math.abs(nums[i])-1;
            if(nums[ind]<0){
                list.add(Math.abs(nums[i]));

            }
            else{
                nums[ind]=-nums[ind];
            }
            
        }

        return list;
    }

    public static void main(String args[]){
        int nums[] = {4,3,2,7,8,2,3,1};

        List<Integer> list = findDuplicates(nums);

        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
    }
}
