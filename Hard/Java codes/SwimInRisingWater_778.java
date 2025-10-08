import java.util.*;

;public class SwimInRisingWater_778{

    public static int swimInWater(int[][] grid) {
        int n = grid.length;

        boolean[][] vis = new boolean[n][n];

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{grid[0][0], 0, 0});
        vis[0][0] = true;

        int max = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int h = cur[0], r = cur[1], c = cur[2];
            max = Math.max(max, h);

            if (r == n - 1 && c == n - 1)
                return max;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nc >= 0 && nr < n && nc < n && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    pq.add(new int[]{grid[nr][nc], nr, nc});
                }
            }
        }
        return -1;
    }

    public static void main(String args[]){
        int grid[][] = {{0,2}, {1,3}};

        System.out.println(swimInWater(grid));
    }
}