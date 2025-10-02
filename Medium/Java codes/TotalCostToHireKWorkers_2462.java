import java.util.PriorityQueue;

public class TotalCostToHireKWorkers_2462 {
    
    static class Info implements Comparable<Info>{
        int cost;
        int idx;

        public Info(int c, int i){
            this.cost = c;
            this.idx = i;
        }

        @Override
        public int compareTo(Info i2){
            return this.cost - i2.cost;
        }
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        
        PriorityQueue<Info> pq1 = new PriorityQueue<>();
        PriorityQueue<Info> pq2 = new PriorityQueue<>();
        
        boolean vis[] = new boolean[n];

        int idx1 = 0;
        int idx2 = n;
        
        for(idx1=0; idx1<candidates; idx1++){
            pq1.add(new Info(costs[idx1], idx1));
            vis[idx1] = true;
        }

        for(idx2=n-1; idx2>=n-candidates; idx2--){
            if(!vis[idx2]){
                vis[idx2] = true;
                pq2.add(new Info(costs[idx2], idx2));
            }
        }

        long cost = 0;

        while(k-- > 0 && (!pq1.isEmpty() || !pq2.isEmpty())){
            if(!pq1.isEmpty() && !pq2.isEmpty()){
                if(pq1.peek().cost <= pq2.peek().cost){
                    cost += pq1.remove().cost;
                    if(idx1 < n-1 && !vis[idx1]){
                        vis[idx1] = true;
                        pq1.add(new Info(costs[idx1], idx1++));
                    }
                }
                else{
                    cost += pq2.remove().cost;
                    if(idx2 > 0 && !vis[idx2]){
                        vis[idx2] = true;
                        pq2.add(new Info(costs[idx2], idx2--));
                    }
                }
            }
            else if(!pq1.isEmpty()){
                cost += pq1.remove().cost;
            }
            else{
                cost += pq2.remove().cost;
            }
        }

        return cost;
    }

    public static void main(String args[]){
        int costs[] = {17,12,10,2,7,2,11,20,8};
        int k = 3;
        int candidates = 4;

        System.out.println(totalCost(costs, k, candidates));
    }
}
