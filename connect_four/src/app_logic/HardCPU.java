/**
 * @author alexander.garuba
 *
 * This class contains the Hard CPU algorithm which 
 */
public class HardCPU extends CPU
{
    
    public HardCPU(){}

    /**
     * This function 
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
