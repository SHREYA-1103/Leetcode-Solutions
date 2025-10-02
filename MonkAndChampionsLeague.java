import java.util.*;

public class MonkAndChampionsLeague {

    public static int maxSales(PriorityQueue<Integer> pq, int people){
        int sales = 0;

        while(people-- > 0){
            int ticketPrice = pq.remove();
            sales += ticketPrice;
            pq.add(ticketPrice-1);
        }

        return sales;
    }
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); // no. of rows
        int n = sc.nextInt(); // no. of people in queue

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int i=0; i<m; i++){
            pq.add(sc.nextInt());
        }

        System.out.println(maxSales(pq, n));
    }
}
