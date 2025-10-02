public class SearchA2DMatrix_74 {
    
    // bruteforce - O(m*n), O(1)
    public static boolean search2D_brute(int mat[][], int target){
        int m = mat.length; // no. of rows
        int n = mat[0].length; // no. of columns;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == target){
                    return true;
                }
            }
        }

        return false;
    }

    // better - O(m + log n), O(1)
    public static boolean search2D_better(int mat[][], int target){
        int m = mat.length; // no. of rows
        int n = mat[0].length; // no. of columns;

        for(int i=0; i<m; i++){
            if(target >= mat[i][0] && target <= mat[i][n-1]){
                return binarySearch(mat[i], target);
            }
        }

        return false;
    }

    public static boolean binarySearch(int arr[], int target){
        int n = arr.length;

        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(arr[mid] == target){
                return true;
            }
            else if(arr[mid] < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        return false;
    }

    // optimal - O(log(m*n)), O(1)
    public static boolean search2D_optimal(int mat[][], int target){
        int m = mat.length;
        int n = mat[0].length;

        int low = 0;
        int high = m*n-1;

        while(low <= high){
            int mid = low + (high-low)/2;

            int row = mid/n;
            int col = mid%n;

            if(mat[row][col] == target){
                return true;
            }
            else if(mat[row][col] < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        return false;
    }
    
    public static void main(String args[]){
        int matrix[][] = {{1,3,5,7},
                        {10,11,16,20},
                        {23,30,34,60}};
        int target = 3;
        
        System.out.println(search2D_optimal(matrix, target));
    }
}
