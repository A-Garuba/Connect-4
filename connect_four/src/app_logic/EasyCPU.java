/**
 * @author alexander.garuba
 *
 * This class contains the Easy CPU algorithm which randomly chooses a column to
 * place a tile in.
 */
public class EasyCPU extends CPU
{
    
    public EasyCPU(){}

    /**
     * Randomly chooses a column to place a tile in
     * 
     * @param grid the ConnectFour 6x7 integer grid
     * @return the column chosen to place the tile into
     */
    @Override
    public int turn(int[][] grid)
    {
        int col;
        do
        {
            col = (int) (Math.random() * 7);
        } while (grid[grid.length-1][col] != 0);

        return col;
    }
    
}
