public class ContainerWithMostWater_11{

    public static int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;

        int left = 0;
        int right = height.length-1;

        while(left<height.length && right>=0){
            int curr = Math.min(height[left],height[right])*(right-left);
            max=Math.max(max,curr);
            
            if(height[left]<height[right]){
                left++;
            }
            else{
                right--;
            }
        }

        return max;
    }

    public static void main(String args[]){
        int heights[] = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxArea(heights));
    }
}