/**
 * @author alexander.garuba
 *
 * This class contains the Medium CPU algorithm which prioritizes placing tiles
 * in the 3 central columns as well as blocks 85% of user links of 3 tiles
 */
public class MedCPU extends CPU
{
    
    private final double BLOCK = .85;
    private final double WIN = 1;
    
    public MedCPU(){}

    /**
     * This function determines which column to place the tile into.
     * Prioritizes central 3 columns
     * Blocks 85% of user links of 3 tiles
     * Places 4th tile in a winning link 100% of the time
     * 
     * @param grid the ConnectFour 6x7 integer grid
     * @return the column chosen to place the tile into
     */
    @Override
    public int turn(int[][] grid)
    {
        int col;
        int win = findLink(grid,2);
        int block = findLink(grid,1);
        
        //if there is a winning move, take it with WIN %
        if (win != -1 && Math.random() < WIN)
        {
            return win;
        }
        //if there is a block to be made, take it with BLOCK %
        else if (block != -1 && Math.random() < BLOCK)
        {
            return block;
        }
        //if neither, randomly place in middle 3 columns (unless those are full)
        else
        {
            if ( (grid[grid.length-1][2] == 0) || 
                 (grid[grid.length-1][3] == 0) ||
                 (grid[grid.length-1][4] == 0)  )
            {
                do
                {
                    col = (int) (Math.random() * 3) + 2;
                } while (grid[grid.length-1][col] != 0);
            }
            else
            {
                do
                {
                    col = (int) (Math.random() * 7);
                } while (grid[grid.length-1][col] != 0);
            }
        }

        return col;
    }
    
}
