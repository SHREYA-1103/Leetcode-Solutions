import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RevealCardsInIncreasingOrder_950 {
    
    public static ArrayList<Integer> deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<deck.length; i++){
            q.add(i);
        }

        ArrayList<Integer> res = new ArrayList<>();

        int idx = 0;

        while(!q.isEmpty()){
            q.remove();
            res.add(deck[idx++]);
            if(!q.isEmpty()){
                q.add(q.remove());
            }
        }

        return res;
    }

    public static void main(String args[]){
        int deck[] = {17,13,11,2,3,5,7};

        System.out.println(deckRevealedIncreasing(deck));
    }
}
