import java.util.ArrayList;

/**
 * @author alexander.garuba
 *
 * This class contains the Hard CPU algorithm which uses a Connect Four heuristic to begin the game.
 * It also will anticipate 2 moves in advance to prevent setting up the user to block winning moves
 * or win the game.
 */
public class HardCPU extends CPU
{

    private final double BLOCK = 1;
    private final double WIN = 1;

    private ArrayList<Integer> avoid;
    
    public HardCPU()
    {
    }

    /**
     * This function determines which column to place the tile into.
     * This function determines which column to place the tile into.
     * Always takes winning moves
     * Always blocks opponent links of 3
     * Avoids setting opponent up to win
     * Attempts to create following pattern in center 3 columns to start game
     * -----
     * --X--
     * --X--
     * --X--
     * -XOX-
     * --X--
     *
     * @param grid the ConnectFour 6x7 integer grid
     * @return the column chosen to place the tile into
     */
    @Override
    public int turn(int[][] grid)
    {
        int col;
        int win = findLink(grid, 2, false);
        int block = findLink(grid, 1, false);

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
        else
        {
            avoid = new ArrayList<>();
            int win_next = findLink(grid, 2, true);
            int lose_next = findLink(grid, 1, true);
            
            //avoid columns that set up opponent to block winning move
            if (win_next != -1)
            {
                avoid.add(win_next);
            }
            //avoid columns that set up opponent to make winning move
            if (lose_next != -1)
            {
                avoid.add(lose_next);
            }
            //avoid columns that are full
            for (int j = 0; j < grid[0].length; j++)
            {
                if (grid[grid.length - 1][j] != 0 && !avoid.contains(j))
                {
                    avoid.add(j);
                }
            }
            
            /* Pattern to begin game
             * -----
             * --X--
             * --X--
             * --X--
             * -XOX-
             * --X--
             */
            if (grid[0][3] == 0 & !avoid.contains(3))
            {
                col = 3;
            }
            else if (grid[1][2] == 0 & !avoid.contains(2))
            {
                col = 2;
            }
            else if (grid[1][4] == 0 & !avoid.contains(4))
            {
                col = 4;
            }
            else if (grid[2][3] == 0 & !avoid.contains(3))
            {
                col = 3;
            }
            else if (grid[3][3] == 0 & !avoid.contains(3))
            {
                col = 3;
            }
            else if (grid[4][3] == 0 & !avoid.contains(3))
            {
                col = 3;
            }
            //if no columns are optimal, sacrifice winning move blocked if possible
            //else, accept loss and put tile anywhere
            else if (avoid.size() >= grid[0].length)
            {
                if (win_next != -1)
                {
                    return win_next;
                }
                else
                {
                    do
                    {
                        col = (int) (Math.random() * 7);
                    } while (grid[grid.length-1][col] != 0);
                }
                return col;
            }
            //if central columns are not all to be avoided
            else if( (!avoid.contains(2) || !avoid.contains(3) || !avoid.contains(4)) )
            {
                do
                {
                    col = (int) (Math.random() * 3) + 2;
                } while (avoid.contains(col));
            }
            else
            {   
                do
                {
                    col = (int) (Math.random() * 7);
                } while (avoid.contains(col));
            }
        }

        return col;
    }
}
