package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTopOnly;
    private int numOpenSites = 0;

//    create N-by-N grid with all sites initially blocked
    public Percolation(int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }
        grid = new boolean[n][n];
        size = n;
        top = 0;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufTopOnly = new WeightedQuickUnionUF(n * n + 1);
    }
//    open the site(row, col) if it is not open already
    public void open(int row, int col){
        if(!grid[row][col]){
            grid[row][col] = true;
            numOpenSites++;
        }

        if(row == 0){
            uf.union(xyTo1D(row,col), top);
            ufTopOnly.union(xyTo1D(row,col), top);
        }

        if(row == size - 1){
            uf.union(xyTo1D(row, col), bottom);
        }

        if(row > 0 && isOpen(row - 1, col)){
            uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }

        if(row < size - 1 && isOpen(row + 1, col)){
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }

        if(col > 0 && isOpen(row, col - 1)){
            uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

        if(col < size - 1 && isOpen(row, col + 1)){
            uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }

    }
// is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row, col);
        return grid[row][col];
    }
// is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);
        return ufTopOnly.connected(xyTo1D(row, col), top);
    }
// number of open sites
    public int numberOfOpenSites(){
        return numOpenSites;
    }
// does the system percolate?
    public boolean percolates(){
        return uf.connected(bottom,top);
    }
    private void validate(int row, int col){
        if(row < 0 || col < 0|| row >= size || col >= size){
            throw new IndexOutOfBoundsException();
        }
    }

    private int xyTo1D(int row, int col){
        return row * size + col + 1;
    }

// use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

    }

}
