import java.util.*;
public class PercolationBFS extends PercolationDFSFast {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }

    /**
     *
     * @param row
     *            is the row coordinate of the cell being checked/marked
     * @param col
     * first add the integer representing row,col to the queue
     * while queue not empty, removes an element from the queue
     * checks the neighbor of the removed element
     * if they should be filled, fill it and add it to the queue
     * repeat until there's no more element in queue
     */
    @Override
    protected void dfs(int row, int col) {
        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col))
            return;

        Queue<Integer> qp = new LinkedList<>();
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};
        myGrid[row][col] = FULL;
        qp.add(h(row,col));
        while (qp.size() != 0){
            Integer p = qp.remove();
            for(int k=0; k < rowDelta.length; k++){
                row = p/myGrid.length + rowDelta[k];
                col = p%myGrid.length + colDelta[k];
                if (inRange(row,col) && myGrid[row][col] == OPEN){
                    myGrid[row][col] = FULL;
                    qp.add(h(row,col));
                }
            }
        }
    }

    /**
     *
     * @param row
     * @param col
     * @return the index of the grid, which is calculated with row*n+col
     */
    public int h(int row, int col){
        return row*myGrid.length + col;
    }
    /**
     *
     * @param row
     * @param col
     * @return if the index is valid
     */
    public boolean inRange(int row, int col){
        return row>-1 && row < myGrid.length && col > -1 && col < myGrid[0].length;
    }
}
