# P6-percolation

Goal: "explore trade-offs in several approaches to estimate the percolation threshold in an NxN system."
(PercolationDFS is provided)


Specific tasks:

1. PercolationDFSFast	
method updateOnOpen is overrided/rewritten to be faster/more efficient
instead of clearing and checking all cells, the new method only checks if the newly opened cell is
adjacent to any FULL cell, or is in the top row

2. PercolationBFS	
the dfs method is overrided/rewritten 
instead of using recursion, the new dfs method marks the cell at input row,col FULL, and put it on a queue. 
then the following steps are repeated: dequeue cell in queue, check the dequeued cell's four neighbours,
if any neighbour is OPEN and not FULL, make it full and add it to queue.

3. PercolationUF
uses IUnionFinder to keep track of open and full cells. If Row 0 (VTOP) and row N+1 (VBOTTOM) are connected
the system percolates; if any cell is connected to VTOP the cell should be full. a 2D boolean array is 
created to keep track of opened cells. When a cell is opened, the open method checks if any of its four 
neighbours are opened as well: connect the newly opened cell to its open neighbour. If the newly opened cell
is on the top row, connect it to VTOP as well, and if the newly opened cell is on the bottom, connect it to 
VBOTTOM.
