public class BubbleGrid {
    private int[][] grid;
    private int rowNum;
    private int colNum;
    private int ceiling = 0;
    private int[][] dirs = new int[][]{{-1,0}, {1,0}, {0, -1},{0, 1}};


    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        UnionFind uf = new UnionFind(rowNum * colNum + 1);

        for(int [] dart : darts){
            if(grid[dart[0]][dart[1]] == 1)
                grid[dart[0]][dart[1]] = 2;
        }

        for (int row = 0; row < rowNum; row ++){
            for(int col = 0; col < colNum; col ++){
                if(grid[row][col] == 1) {
                    unionNeighbors(row, col, grid, uf);
                }
            }
        }

        int [] res = new int[darts.length];
        int count = uf.sizeOf(ceiling);

        for(int i = darts.length - 1; i >= 0 ; i --){
            int row = darts[i][0];
            int col = darts[i][1];
            if(grid[row][col] == 2){
                unionNeighbors(row,col,grid,uf);
                grid[row][col] = 1;
            }

            int newCount = uf.sizeOf(ceiling);

            res[i] = newCount > count ? newCount - count - 1 : 0;

            count = newCount;
        }
        return res ;
    }

    private void unionNeighbors(int row, int col, int [][] grid, UnionFind uf){
        if(row == 0){
            uf.union(xyTo1D(row, col), ceiling);
        }

        for(int []dir : dirs){
            int adjRow = row + dir[0];
            int adjCol = col + dir[1];

            if(adjRow < 0 || adjRow == rowNum || adjCol < 0 || adjCol == colNum || grid[adjRow][adjCol] != 1){
                continue;
            }

            uf.union(xyTo1D(row, col), xyTo1D(adjRow,adjCol));
        }

    }

    private int xyTo1D(int row, int col){
        return row * rowNum + col + 1;
    }
}
