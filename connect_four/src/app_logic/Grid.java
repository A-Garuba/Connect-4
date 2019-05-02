import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author alexander.garuba
 *
 * This class contains the ConnectFour application logic
 */
public class Grid
{

    private boolean user_turn = false;

    private int[][] grid = new int[6][7];
    private int prev_row;
    private int prev_col;

    private CPU cpu;

    /**
     * The constructor determines who will go first (50-50) and then begins the
     * game
     */
    public Grid()
    {
        getDifficulty();

        if (Math.random() < .5)
        {
            printGrid();
            user_turn = true;
            System.out.println("Your turn is first .. run it");

        }

        run();
    }

    /**
     * This function executes the connect 4 game
     */
    private void run()
    {
        while (true)
        {
            if (user_turn)
            {
                place(userTurn());
            }
            else
            {
                place(cpu.turn(grid));
                //make CPU 'take some time' before executing 
                try
                {
                    sleep(500);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            printGrid();

            if (checkWinner())
            {
                break;
            }

            //alternate turns
            user_turn = !user_turn;
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
     *
     * @return boolean regarding whether someone has won the game
     */
    private boolean checkWinner()
    {
        //get last placed tile
        int turn = grid[prev_row][prev_col];
        int link;

        //check vertical, start at placed tile and count down
        link = 1;
        for (int i = prev_row - 1; i >= 0; i--)
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
        for (int i = prev_col - 1; i >= 0; i--)
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

        for (int i = prev_col + 1; i < grid[prev_row].length; i++)
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

        return link == 4;
    }

    /**
     * Takes user input to place a tile, prompts again if column is full
     * 
     * @return the column chosen to place the tile into
     */
    private int userTurn()
    {
        System.out.println("Enter a column (1-7): ");
        Scanner scanner = new Scanner(System.in);
        
        int col = scanner.nextInt() - 1;
        
        while (grid[grid.length-1][col] != 0)
        {
            System.out.println("That column is full. Please try another: ");
            col = scanner.nextInt() - 1;
        }
        
        return col;
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
        String out = "\n-------------\n";

        int curr;

        for (int row = grid.length - 1; row >= 0; row--)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                curr = grid[row][col];

                switch (curr)
                {
                    case 0:
                        out += "- ";
                        break;
                    case 1:
                        out += "O ";
                        break;
                    case 2:
                        out += "X ";
                        break;
                }
            }
            out += "\n";
        }

        out += "-------------\n";

        System.out.println(out);
    }

    /**
     * This function allows the user to enter a CPU difficulty and instantiates
     * the proper CPU object to take the CPU's turns
     */
    private void getDifficulty()
    {
        System.out.println("Enter a CPU Difficulty (Easy, Medium, Hard): ");
        Scanner scanner = new Scanner(System.in);
        String diff = scanner.next();

        while (!(diff.equalsIgnoreCase("easy")
                 || diff.equalsIgnoreCase("medium")
                 || diff.equalsIgnoreCase("hard")))
        {
            System.out.println("Invalid difficulty. Enter again: ");
            diff = scanner.next();
        }

        if (diff.equalsIgnoreCase("easy"))
        {
            cpu = new EasyCPU();
        }
        else if (diff.equalsIgnoreCase("medium"))
        {
            cpu = new MedCPU();
        }
        else if (diff.equalsIgnoreCase("hard"))
        {
            cpu = new HardCPU();
        }
    }

    public static void main(String[] args)
    {
        new Grid();
    }

}
