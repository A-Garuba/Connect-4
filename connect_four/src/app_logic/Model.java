package app_logic;

import java.util.Scanner;

/**
 * @author alexander.garuba
 * 
 * This class contains the ConnectFour application logic
 */
public class Model
{
    private boolean user_turn = false;

    private int[][] grid = new int[6][7];
    private int prev_row;
    private int prev_col;

    /**
     * The constructor determines who will go first (50-50) and then begins the game
     */
    public Model()
    {
            if (Math.random() < .5)
            {
                    user_turn = true;
            }

            run();
    }

    /**
     * This function executes the connect 4 game
     */
    private void run()
    {
            boolean finished = false;

            while (!finished)
            {
                    //alternate turns
                    user_turn = !user_turn;

                    if (user_turn)
                    {
                            userTurn();
                    }
                    else
                    {
                            cpuTurn();
                    }
                    
                    printGrid();
                    finished = checkWinner();
            }

            if (user_turn)
            {
                System.out.println("You won!");
            }
            else
            {
                System.out.println("You've lost.");
            }

    }
    
    /**
     * This function determines if someone has won the game by checking if any
     * links of 4 of the same type of tile have been made (horizontal, vertical,
     * and diagonal).
     * @return boolean regarding whether someone has won the game
     */
    private boolean checkWinner()
    {
            //get last placed tile
            int turn = grid[prev_row][prev_col];
            int link;

            //check vertical, start at placed tile and count down
            link = 1;
            for (int i = prev_row-1; i >= 0; i--)
            {
                    if (grid[i][prev_col] != turn)
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
            for (int i = prev_col-1; i >= 0; i--)
            {
                    if (grid[prev_row][i] != turn)
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

            for (int i = prev_col+1; i < grid[prev_row].length; i++)
            {
                    if (grid[prev_row][i] != turn)
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
            int row = prev_row - 1;
            int col = prev_col - 1;

            while (row >= 0 && col >= 0)
            {
                    if (grid[row][col] != turn)
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

            row = prev_row + 1;
            col = prev_col + 1;

            while (row < grid.length && col < grid[row].length)
            {
                    if (grid[row][col] != turn)
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
            row = prev_row + 1;
            col = prev_col - 1;

            while (row < grid.length && col >= 0)
            {
                    if (grid[row][col] != turn)
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

            if (link == 4)
            {
                    return true;
            }

            row = prev_row - 1;
            col = prev_col + 1;

            while (row >= 0 && col < grid[row].length)
            {
                    if (grid[row][col] != turn)
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

            if (link == 4)
            {
                    return true;
            }

            return false;
    }

    /**
     * Randomly chooses a column to place a tile in
     */
    private void cpuTurn()
    {
        int col = (int) (Math.random()*7);

        switch (col) {
            case 0:  place(col);
                     break;
            case 1:  place(col);
                     break;
            case 2:  place(col);
                     break;
            case 3:  place(col);
                     break;
            case 4:  place(col);
                     break;
            case 5:  place(col);
                     break;
            case 6:  place(col);
                     break;
        }

    }

    /**
     * Takes user input to place a tile
     */
    private void userTurn()
    {
        System.out.println("Enter a column (1-7): ");
        Scanner scanner = new Scanner(System.in);

        place(scanner.nextInt() - 1);
    }
    
    /**
     * This function allows the user or CPU to place a tile in a certain column.
     * Updates prev_row and prev_col
     * 
     * @param column the column in which the next tile will be placed
     */
    private void place(int column)
    {
        int row = 0;
        while (grid[row][column] != 0)
        {
            row++;
        }
        
        if (user_turn)
        {
            grid[row][column] = 1;
        }
        else
        {
            grid[row][column] = 2;
        }
        
        prev_row = row;
        prev_col = column;
    }
    
    /**
     * This method prints the grid
     */
    private void printGrid()
    {
        System.out.println("------------------\n");
        
        for (int row = grid.length-1; row >= 0; row--)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        
        System.out.println("------------------");
    }
    
    public static void main(String[] args)
    {
            new Model();

    }
}
