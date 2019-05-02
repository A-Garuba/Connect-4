
import java.util.Arrays;

/**
 * @author alexander.garuba
 *
 * This class contains the CPU interface for the easy, medium, and hard algorithms
 */
public abstract class CPU
{

    public CPU(){}
    
    /**
     * This function determines if any links of 3 of a given type of tile have 
     * been made (horizontal, vertical, and diagonal).
     * 
     * @param grid the ConnectFour 6x7 integer grid
     * @param side the tile to search for links (1 = user, 2 = CPU)
     * @return the column in which to lengthen the link to 4 or block a 3-link
     */
    public int findLink(int[][] grid, int side)
    {
        int[] empty_tiles = new int[grid[0].length];
        
        //find possible tile placement for each column
        for (int curr_col = 0; curr_col < empty_tiles.length; curr_col++)
        {
            int curr_row;
            //start at top of column, count down until placed tile found or 0
            for (curr_row = grid.length - 1; curr_row >= 0; curr_row--)
            {
                if (grid[curr_row][curr_col] != 0)
                {
                    curr_row++;
                    break;
                }
            }
            
            //if column is empty, put 0 in empty_tiles
            if (curr_row == -1)
            {
                curr_row++;
            }
            //if column is full, put -1 in empty_tiles
            else if (curr_row == grid.length)
            {
                curr_row = -1;
            }
            
            empty_tiles[curr_col] = curr_row;
        } 
        return -1;
        /*
        int link, row, col;

        //check vertical, start at highest tile and count down for each column
        link = 1;

        for (int i = row - 1; i >= 0; i--)
        {
            if (grid[i][col] != side)
            {
                break;
            }
            else
            {
                link++;
            }

            if (link == 4)
            {
                return true;
            }
        }

        //check horizontal, start at placed tile and count left then return to tile and count right
        link = 1;
        for (int i = col - 1; i >= 0; i--)
        {
            if (grid[row][i] != side)
            {
                break;
            }
            else
            {
                link++;
            }
        }

        if (link == 4)
        {
            return true;
        }

        for (int i = col + 1; i < grid[row].length; i++)
        {
            if (grid[row][i] != side)
            {
                break;
            }
            else
            {
                link++;
            }
        }

        if (link == 4)
        {
            return true;
        }

        //check diagonal, start at placed tile and count to bottom-left
        //				 return to placed tile and count to top-right
        link = 1;
        int row = row - 1;
        int col = col - 1;

        while (row >= 0 && col >= 0)
        {
            if (grid[row][col] != side)
            {
                break;
            }
            else
            {
                link++;
                row--;
                col--;
            }
        }

        if (link == 4)
        {
            return true;
        }

        row = row + 1;
        col = col + 1;

        while (row < grid.length && col < grid[row].length)
        {
            if (grid[row][col] != side)
            {
                break;
            }
            else
            {
                link++;
                row++;
                col++;
            }
        }

        if (link == 4)
        {
            return true;
        }

        //check diagonal, start at placed tile and count to top-left
        //				 return to placed tile and count to bottom-right
        link = 1;
        row = row + 1;
        col = col - 1;

        while (row < grid.length && col >= 0)
        {
            if (grid[row][col] != side)
            {
                break;
            }
            else
            {
                link++;
                row++;
                col--;
            }
        }

        row = row - 1;
        col = col + 1;

        while (row >= 0 && col < grid[row].length)
        {
            if (grid[row][col] != side)
            {
                break;
            }
            else
            {
                link++;
                row--;
                col++;
            }
        }

        return link == 4;*/
    }
    
    /**
     * Each CPU concrete class will implement a turn method that determines how
     * it will place the next tile given the current ConnectFour grid (int[][])
     * 
     * @param grid the ConnectFour 6x7 integer grid
     * @return the column chosen to place the tile into
     */
    public abstract int turn(int[][] grid);
}
