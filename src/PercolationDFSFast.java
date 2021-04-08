public class PercolationDFSFast extends PercolationDFS{
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }
    @Override
    protected void updateOnOpen(int row, int col) {
        if(inBounds(row, col)){
            if (row == 0){
                dfs(row, col);
            }
            if (inBounds(row + 1, col) && myGrid[row + 1][col] == FULL
                    || inBounds(row - 1 , col) && myGrid[row -1][col] == FULL
                    || inBounds(row , col+1) &&myGrid[row][col+1] == FULL
                    || inBounds(row , col -1 ) && myGrid[row][col - 1] == FULL) {
                dfs(row, col);

            }

        }


    }

}
