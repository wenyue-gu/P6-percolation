public class PercolationDFSFast extends PercolationDFS {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    /**
     *
     * @param row
     * @param col
     * checks the four neighbors of the cell
     * if one of them is full or if the cell is on top row
     * perform dfs on it (which fills the cell and fill its neighbors as well)
     */
    @Override
    protected void updateOnOpen(int row, int col) {
        boolean a = false;
        if(row==0){a = true;}
        else if(col-1>-1 && isFull(row, col-1)){
            a = true;
        }
        else if(col+1<myGrid[0].length && isFull(row, col+1)){
            a = true;
        }
        else if(isFull(row-1,col)){
            a = true;
        }
        else if(row+1<myGrid.length && isFull(row+1,col)){
            a = true;

        }

        if(a){dfs(row,col);}
    }
}
