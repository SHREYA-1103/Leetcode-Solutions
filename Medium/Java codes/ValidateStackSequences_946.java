import java.util.Stack;

public class ValidateStackSequences_946 {

    // bruteforce -- O(n), O(n)
    public static boolean isValid_brute(int pushed[], int popped[]){
        Stack<Integer> s = new Stack<>();
        int j = 0;

        for(int val: pushed){
            s.push(val);
            // pop whenever there is a match
            while(!s.isEmpty() && s.peek() == popped[j]){
                s.pop();
                j++;
            }
        }

        return s.isEmpty();
    }

    // optimal -- O(n), O(1)
    public static boolean isValid_optimal(int pushed[], int popped[]){
        int i = 0;
        int j = 0;

        for(int val: pushed){
            // use pushed array as stack - i is the pointer to track the top element
            pushed[i++] = val;
            while(i > 0 && pushed[i-1] == popped[j]){
                i--;
                j++;
            }
        }

        return i == 0;
    }
    
    public static void main(String args[]){
        int pushed[] = {1,2,3,4,5};
        int popped[] = {4,5,3,2,1};

        System.out.println(isValid_brute(pushed, popped));

        System.out.println(isValid_optimal(pushed, popped));
    }
}
