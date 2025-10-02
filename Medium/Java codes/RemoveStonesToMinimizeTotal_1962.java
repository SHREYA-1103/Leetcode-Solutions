import java.util.Comparator;
import java.util.PriorityQueue;

public class RemoveStonesToMinimizeTotal_1962 {
    
    public static int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<piles.length; i++){
            pq.add(piles[i]);
        }

        int sum = 0;

        while(!pq.isEmpty() && k-- > 0){
            int p = pq.remove();
            p -= p/2;
            pq.add(p);
        }

        while(!pq.isEmpty()){
            sum += pq.remove();
        } 

        return sum;
    }

    public static void main(String args[]){
        int piles[] = {5,4,9};
        int k = 2;

        System.out.println(minStoneSum(piles, k));
    }
}
