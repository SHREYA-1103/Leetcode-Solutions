import java.util.*;

public class MonkAndMultiplication {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        PriorityQueue<Long> pq = new PriorityQueue<>();

        long prod = 1;
        
        for(int i=0; i<n; i++){
            long num = sc.nextLong();
            prod *= num;
            pq.add(num);

            if(pq.size() < 3){
                System.out.println("-1");
            }

            else if(pq.size() > 3){
                long x = pq.remove();
                prod /= x;
            }

            if(pq.size() == 3){
                System.out.println(prod);
            }
        }
    }
}
