import java.util.*;

public class PascalTriangle_118{

    //bruteforce - recursion - O(n^3), O(1)
    public static List<List<Integer>> generatePascalTriangle_rec(int numRows){
        List<List<Integer>> list = new ArrayList<>();

        for(int i=0; i<numRows; i++){
            list.add(new ArrayList<>());
            for(int j=0; j<=i; j++){
                list.get(i).add(factorial(i)/(factorial(j) * factorial(i-j)));
            }
        }

        return list;
    }

    public static int factorial(int n){
        int arr[] = new int[n+1];

        arr[0] = 1;

        for(int i=1; i<=n; i++){
            arr[i] = i*arr[i-1];
        }

        return arr[n];
    }

    // better - DP memoization - O(n^2), O(1)
    public static List<List<Integer>> generatePascalTriangle_memo(int numRows){
        List<List<Integer>> list = new ArrayList<>();

        for(int i=0; i<numRows; i++){
            list.add(new ArrayList<>());
        }

        list.get(0).add(1);

        for(int i=1; i<numRows; i++){
            list.get(i).add(1);
            for(int j=1; j<i; j++){
                list.get(i).add(list.get(i-1).get(j) + list.get(i-1).get(j-1));
            }
            list.get(i).add(1);
        }

        return list;
    }

    // optimal - combination formula - O(n^2), O(1)
    public static List<List<Integer>> generatePascalTriangle_form(int numRows){
        List<List<Integer>> res = new ArrayList<>();

        for(int i=0; i<numRows;  i++){
            int val = 1;
            List<Integer> row = new ArrayList<>();
            row.add(val);
            for(int j=1; j<=i; j++){
                val = val * (i-j+1)/j;
                row.add(val);
            }
            res.add(row);
        }

        return res;
    }

    public static void main(String args[]){
        int numRows = 5;

        List<List<Integer>> list = generatePascalTriangle_form(numRows);

        for(int i=0; i<numRows; i++){
            for(int j=0; j<list.get(i).size(); j++){
                System.out.print(list.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}