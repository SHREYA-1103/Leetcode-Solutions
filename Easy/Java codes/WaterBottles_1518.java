public class WaterBottles_1518 {
    
    public static int numWaterBottles(int numBottles, int numExchange) {
        int res=numBottles;
        
        int exchange=numBottles/numExchange;
        int rem=numBottles%numExchange;
        res+=exchange;

        while(exchange+rem>=numExchange){
            int exchange1=(exchange+rem)/numExchange;
            int rem1=(exchange+rem)%numExchange;
            res+=exchange1;
            exchange=exchange1;
            rem=rem1;
        }

        return res;
    }

    public static void main(String args[]){
        int numBottles = 9;
        int numExchange = 3;

        System.out.println(numWaterBottles(numBottles, numExchange));
    }
}
