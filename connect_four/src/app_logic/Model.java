package app_logic;

/**
 * @author alexander.garuba
 * 
 * This class contains the ConnectFour application logic
 */
public class Model
{
	private boolean user_turn = false;
	
	private int[][] grid = new int[6][7];
	private int prevRow;
	private int prevCol;
	
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
			
			finished = checkWinner();
		}
		
		System.out.println("winner found? --> " + finished);
	}

	private boolean checkWinner()
	{
		//get last placed tile
		int turn = grid[prevRow][prevCol];
		int link;
		
		//check vertical, start at placed tile and count down
		link = 1;
		for (int i = prevRow-1; i >= 0; i--)
		{
			if (grid[i][prevCol] != turn)
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
		for (int i = prevCol-1; i >= 0; i--)
		{
			if (grid[prevRow][i] != turn)
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
		
		for (int i = prevCol+1; i < grid[prevRow].length; i++)
		{
			if (grid[prevRow][i] != turn)
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
		int row = prevRow - 1;
		int col = prevCol - 1;
		
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
		
		row = prevRow + 1;
		col = prevCol + 1;
		
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
		row = prevRow + 1;
		col = prevCol - 1;

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

		row = prevRow - 1;
		col = prevCol + 1;

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

	private void cpuTurn()
	{
		// TODO Auto-generated method stub
		
	}

	private void userTurn()
	{
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args)
	{
		new Model();

	}

}
