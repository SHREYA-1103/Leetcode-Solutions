public class WaterBottlesII_3100{

    public static int maxBottlesDrunk(int numBottles, int numExchange) {
        int filled = numBottles;
        int empty = 0;
        int res = 0;
        
        while(filled > 0 || empty >= numExchange){
            if(empty >= numExchange){
                empty -= numExchange;
                filled++;
                numExchange++;
            }
            else if(filled > 0){
                res+=filled;
                empty += filled;
                filled = 0;
            }
        }

        return res;
    }

    public static void main(String args[]){
        int numBottles = 10;
        int numExchange = 3;

        System.out.println(maxBottlesDrunk(numBottles, numExchange));
    }
}