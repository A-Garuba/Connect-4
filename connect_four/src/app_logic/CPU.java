/**
 * @author alexander.garuba
 *
 * This class contains the CPU interface for the easy, medium, and hard
 * algorithms
 */
public abstract class CPU
{

    public CPU()
    {
    }

    /**
     * This function determines if any links of 3 of a given type of tile have
     * been made (horizontal, vertical, and diagonal). Also can determine if the
     * opponent will win on next turn through last parameter.
     *
     * @param grid the ConnectFour 6x7 integer grid
     * @param side the tile to search for links (1 = user, 2 = CPU)
     * @param avoid used to increment possible tile placement by 1 (predict opponent win next move)
     * @return the column in which to lengthen the link to 4 or block a 3-link
     */
    public int findLink(int[][] grid, int side, boolean avoid)
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
            //increment to detect avoided columns
            if (curr_row != -1 && avoid)
            {
                curr_row++;
            }
            //if column only has one space left, cannot check above it for future moves
            if (curr_row == grid.length && avoid)
            {
                curr_row = -1;
            }

            empty_tiles[curr_col] = curr_row;
        }

        int link, row, col;

        //for the to-be-placed tile in each column
        for (col = 0; col < empty_tiles.length; col++)
        {
            row = empty_tiles[col];
            //if the column is full, skip iteration
            if (row == -1)
            {
                continue;
            }

            //check vertical, start at to-be-placed tile and count down
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

                //return if link would be 4
                if (link > 3)
                {
                    return col;
                }
            }

            //check horizontal, start at to-be-placed tile and count left
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

            //return if link would be 4
            if (link > 3)
            {
                return col;
            }

            //start at to-be-placed tile and count right
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

            //return if link would be 4
            if (link > 3)
            {
                return col;
            }

            //check 1st diagonal, start at to-be-placed tile and count to bottom-left
            link = 1;
            int curr_row = row - 1;
            int curr_col = col - 1;

            while (curr_row >= 0 && curr_col >= 0)
            {
                if (grid[curr_row][curr_col] != side)
                {
                    break;
                }
                else
                {
                    link++;
                    curr_row--;
                    curr_col--;
                }
            }

            //return if link would be 4
            if (link > 3)
            {
                return col;
            }

            //start at to-be-placed tile and count to top-right
            curr_row = row + 1;
            curr_col = col + 1;

            while (curr_row < grid.length && curr_col < grid[curr_row].length)
            {
                if (grid[curr_row][curr_col] != side)
                {
                    break;
                }
                else
                {
                    link++;
                    curr_row++;
                    curr_col++;
                }
            }

            //return if link would be 4
            if (link > 3)
            {
                return col;
            }

            //check 2nd diagonal, start at to-be-placed tile and count to top-left
            link = 1;
            curr_row = row + 1;
            curr_col = col - 1;

            while (curr_row < grid.length && curr_col >= 0)
            {
                if (grid[curr_row][curr_col] != side)
                {
                    break;
                }
                else
                {
                    link++;
                    curr_row++;
                    curr_col--;
                }
            }

            //start at to-be-placed tile and count to bottom-right
            curr_row = row - 1;
            curr_col = col + 1;

            while (curr_row >= 0 && curr_col < grid[curr_row].length)
            {
                if (grid[curr_row][curr_col] != side)
                {
                    break;
                }
                else
                {
                    link++;
                    curr_row--;
                    curr_col++;
                }
            }

            //return if link would be 4
            if (link > 3)
            {
                return col;
            }

        }
        return -1;
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
