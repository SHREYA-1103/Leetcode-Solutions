import java.util.*;

public class OnlineStockSpan_901 {

    // Optimal - O(1), O(n)
    static class StockSpanner{
        List<Integer> stock;
        Stack<Integer> s;

        public StockSpanner() {
            stock = new ArrayList<>();
            s = new Stack<>();
        }

        public int next(int price) {
            // update stocks list
            stock.add(price);

            int span = 1;

            // find next greater left
            while (!s.isEmpty() && stock.get(s.peek()) <= price) {
                s.pop();
            }

            if (s.isEmpty()) {
                span = stock.size();
            } else {
                span = stock.size() - s.peek() - 1;
            }

            s.push(stock.size() - 1);

            return span;
        }
    }

    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();

        // Example test case
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Stock Prices: " + Arrays.toString(prices));
        System.out.print("Spans: [");

        for (int i = 0; i < prices.length; i++) {
            int span = obj.next(prices[i]);
            System.out.print(span);
            if (i < prices.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
