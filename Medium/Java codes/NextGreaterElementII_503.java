import java.util.*;

public class NextGreaterElementII_503 {
    
    // Optimal -- O(n), O(n)
    public static int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        
        int nge[] = new int[n];

        // monotonic stack to store the elements
        Stack<Integer> s = new Stack<>();

        int i = 2*n - 1;

        // double the array by manipulating index to get all the values into the stack
        while(i >= 0){
            while(!s.isEmpty() && s.peek() <= arr[i%n]){
                s.pop();
            }

            if(i < n){
                if(s.isEmpty()){
                    nge[i] = -1;
                }
                else{
                    nge[i] = s.peek();
                }
            }

            s.push(arr[i%n]);

            i--;
        }

        return nge;
    }

    public static void main(String args[]){
        int nums[] = {1,2,1};

        int nge[] = nextGreaterElements(nums);

        for(int i=0; i<nge.length; i++){
            System.out.print(nge[i] + " ");
        }
    }
}
