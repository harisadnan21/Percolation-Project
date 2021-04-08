import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }
    @Override
    protected void dfs(int row, int col) {
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        if (isFull(row, col) || !isOpen(row, col))
            return;
        Queue<int[]> q = new LinkedList<>();
        myGrid[row][col] = FULL;
        q.add(new int[]{row, col});
        while(q.size() != 0 ){
            int[] p = q.remove();
            for(int k=0; k < rowDelta.length; k++){
                row = p[0] + rowDelta[k];
                col = p[1] + colDelta[k];
                if (inBounds(row,col) && myGrid[row][col] == OPEN){
                    q.add(new int[]{row,col});
                    myGrid[row][col] = FULL;
                }
            }

        }





    }
}

