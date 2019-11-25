import java.util.*;

public class PercolationUF implements IPercolate {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        myOpenCount = 0;
        for (boolean[] row : myGrid)
            Arrays.fill(row, false);
        int j = size * size + 2;
        finder.initialize(j);
        myFinder = finder;
        VTOP = size*size;
        VBOTTOM = size*size+1;
    }

    @Override
    public void open(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        if (myGrid[row][col])
            return;
        myOpenCount += 1;
        myGrid[row][col] = true;
        if(row==0){
            myFinder.union(VTOP, h(row,col));
        }
        if(row-1>-1 && isOpen(row-1, col)){
            myFinder.union(h(row-1,col), h(row,col));
        }
        if(row+1<myGrid.length && isOpen(row+1, col)){
            myFinder.union(h(row+1,col), h(row,col));
        }
        if(col+1<myGrid.length && isOpen(row, col+1)){
            myFinder.union(h(row,col+1), h(row,col));
        }
        if(col-1>-1 && isOpen(row, col-1)){
            myFinder.union(h(row,col-1), h(row,col));
        }
        if(row == myGrid.length-1){
            myFinder.union(VBOTTOM, h(row,col));
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        if(inBounds(row, col)) {
            return (myGrid[row][col]==true);
        }
        else{
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
    }

    @Override
    public boolean isFull(int row, int col) {
        if(inBounds(row, col)) {
            return myFinder.connected(VTOP,h(row,col));
        }
        else{
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP,VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    public int h(int row, int col){
        return row*myGrid.length+col;
    }


    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }
}
