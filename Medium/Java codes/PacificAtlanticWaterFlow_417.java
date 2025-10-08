import java.util.*;

public class PacificAtlanticWaterFlow_417 {

    public static void dfs(int[][] heights, boolean[][] ocean, int prevHt, int r, int c){
        int n=heights.length;
        int m=heights[0].length;

        if(r<0 || c<0 || r>=n || c>=m || ocean[r][c] || heights[r][c]<prevHt){
            return;
        }

        ocean[r][c]=true;

        dfs(heights, ocean, heights[r][c], r+1, c);
        dfs(heights, ocean, heights[r][c], r-1, c);
        dfs(heights, ocean, heights[r][c], r, c+1);
        dfs(heights, ocean, heights[r][c], r, c-1);
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        int n = heights.length;
        int m = heights[0].length;

        if(n==0 || m==0){
            return res;
        }
        
        boolean pacific[][] = new boolean[n][m];
        boolean atlantic[][] = new boolean[n][m];

        // Initialize pacific and atlantic borders
        for (int i = 0; i < n; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i,0);
            dfs(heights, atlantic, Integer.MIN_VALUE, i, m-1);
        }

        for (int j = 0; j < m; j++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0,j);
            dfs(heights, atlantic, Integer.MIN_VALUE, n-1, j);
        }

        // Collect results
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }

        return res;
    }

    public static void main(String args[]){
        int heights[][] = {{1,2,2,3,5},
                            {3,2,3,4,4},
                            {2,4,5,3,1},
                            {6,7,1,4,5},
                            {5,1,1,2,4}};

        System.out.println(pacificAtlantic(heights));
    }
}