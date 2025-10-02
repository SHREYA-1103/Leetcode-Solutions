public class RemoveKDigits_402 {
    
    // Optimal -- O(n), O(n)
    public static String removeKdigits(String num, int k) {
        // stack to store the digits - string builder as stack to help easy conversion to string
        StringBuilder s = new StringBuilder();

        // remove digits which have further digits smaller than the current
        int i = 1; // iterator for num string
        
        s.append(num.charAt(0));

        while(i < num.length()){
            while(k>0 && !s.isEmpty() && s.charAt(s.length()-1) > num.charAt(i)){
                s.deleteCharAt(s.length()-1);
                k--;
            } 
            s.append(num.charAt(i));
            i++;
        }

        // remove digits from the end if not k digits removed
        while(k>0){
            s.deleteCharAt(s.length()-1);
            k--;
        }

        // remove leading zeroes
        while(!s.isEmpty() && s.charAt(0) == '0'){
            s.deleteCharAt(0);
        }

        // return final string
        if(s.isEmpty()){
            return "0";
        }

        else{
            return s.toString();
        }

    }

    public static void main(String args[]){
        String num = "14322191";
        int k = 4;

        System.out.println(removeKdigits(num, k));
    }
}
