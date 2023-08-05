package datastructures.graphs;

public class GraphIMP {
    public static void main(String[] args) {

        /*//floodFill
        int screen[][] = {{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };
        int x = 4, y = 4, newC = 3;
        floodFill(screen, x, y, newC);
        Arrays.stream(screen).forEach(r -> System.out.println(Arrays.toString(r)));*/

        //
        int[][] M = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};
        System.out.println(numIslands(M));

    }


    /*
    Word search
    */
    public boolean wordSearch(char[][] board, String word) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if ((board[i][j] == word.charAt(0)) && dfsWordSearch(board, i, j, 0, word))
                    return true;
        return false;
    }

    private boolean dfsWordSearch(char[][] board, int i, int j, int count, String word) {
        if (count == word.length())
            return true;
        if (i == -1 || i == board.length || j == -1 || j == board[0].length || board[i][j] != word.charAt(count))
            return false;
        char temp = board[i][j];
        board[i][j] = '*';
        boolean found = dfsWordSearch(board, i + 1, j, count + 1, word) ||
                dfsWordSearch(board, i - 1, j, count + 1, word) ||
                dfsWordSearch(board, i, j + 1, count + 1, word) ||
                dfsWordSearch(board, i, j - 1, count + 1, word);
        board[i][j] = temp;
        return found;
    }

    /*
    */
    public static int numIslands(int[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    count++;
                    dfsFill(grid, i, j);
                }
            }
        }

        return count;
    }

    public static void dfsFill(int[][] grid, int i, int j){
        if(i>=0 && j>=0 && i<grid.length && j<grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;
            dfsFill(grid, i-1, j);
            dfsFill(grid, i+1, j);
            dfsFill(grid, i, j+1);
            dfsFill(grid, i, j-1);
        }


    }
    /*
    In tree if we use dfs then we go to or make recursive call first in left direction then right direction.
    So similarly in matrix problems also we can make four possible recursive call in  four direction to travers based on the condition.
    i.e. from [i][j] --> to [i+1][j] [i][j+1] [i-1][j] [i][j-1]

    We know recursive function will not stop without base least one base case.
    Here we have 6 base cases:-
    1. bottom corner i.e i > rows 2. right j > cols 3. left i < 0 4. top j < 0 5. old color   6. new color == new color


    sc and sc are starting point to fill
    */

    public static void floodFill(int[][] image, int sr, int sc, int newColor) {
        int prevColor = image[sr][sc];
        if(prevColor == newColor) return;

        dfsFloodFill(image, sr, sc, prevColor, newColor);
    }

    private static void dfsFloodFill(int[][] image, int i, int j, int prevColor, int newColor) {
        if(i >= image.length || i < 0 || j >= image[0].length || j < 0 || image[i][j] != prevColor){
            return;
        }

        image[i][j] = newColor;
        dfsFloodFill(image, i+1, j, prevColor, newColor);
        dfsFloodFill(image, i, j+1, prevColor, newColor);
        dfsFloodFill(image, i-1, j, prevColor, newColor);
        dfsFloodFill(image, i, j-1, prevColor, newColor);

    }
}
