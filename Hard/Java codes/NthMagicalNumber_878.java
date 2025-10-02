public class NthMagicalNumber_878 {
    
    public static int nthMagicalNumber_optimal(int n, int a, int b) {
        long mod = 1000000007;
        long lcm = lcm(a,b);
        
        long low = (long) Math.min(a,b); // min possible answer
        long high = (long) n*low; // max possible answer

        long ans = -1;
        
        while(low <= high){
            long mid = low + (high - low)/2;
            long count = mid/a + mid/b - mid/lcm; // no. of values before mid
            if(count >= n){
                ans = mid;
                high = mid-1; // search left for a smaller value 
            }
            else{
                low = mid+1;
            }
        }

        return (int) (ans % mod);
    }

    public static long lcm(long a, long b){
        return a/gcd(a,b)*b;
    }

    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a%b);
    }

    public static void main(String args[]){
        int n = 1;
        int a = 2;
        int b = 3;

        System.out.println(nthMagicalNumber_optimal(n, a, b));
    }
}
