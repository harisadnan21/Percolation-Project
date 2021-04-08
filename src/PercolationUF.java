import java.util.Arrays;

public class PercolationUF implements IPercolate{
    private boolean[][] myGrid;
    private int myOpenCount;
    private IUnionFind myFinder;
    private final int VTOP;
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size) {
        finder.initialize((size*size)+2);
        myFinder = finder;
        myGrid = new boolean[size][size];
        for(boolean[] row : myGrid){
            Arrays.fill(row, false);
        }

        myOpenCount = 0;
        VTOP = (size * size);
        VBOTTOM = (size *size) + 1;

    }
    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)){
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)){
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }
        int q= row*myGrid.length + col;

        return myFinder.connected(VTOP,q);

    }

    @Override
    public boolean percolates() {

        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    @Override
    public void open(int row, int col) {
        int p = row*myGrid.length + col;
        if (!inBounds(row, col)){
            throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
        }
        if (myGrid[row][col] == false){
            myGrid[row][col] = true;
            myOpenCount++;
            int[] rowDelta = {-1,1,0,0};
            int[] colDelta = {0,0,-1,1};
            for(int k=0; k < rowDelta.length; k++) {
                int r = row + rowDelta[k];
                int c = col + colDelta[k];
                int index = r * myGrid.length + c;
                if (inBounds(r, c) && myGrid[r][c] == true) {
                    myFinder.union(index, p);

                }
            }
            if (row == 0){
                myFinder.union(VTOP, p);
            }
            if (row == myGrid.length-1){
                myFinder.union(VBOTTOM, p);

            }



        }
    }

}
