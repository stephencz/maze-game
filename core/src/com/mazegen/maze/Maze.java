package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.mazegen.entity.Entity;

/**
 * 
 */
public abstract class Maze extends Entity
{		
	/**The type of Tile that composes the maze.*/
	protected final TileType type;
	
	/**A 2D array of Tile objects which represents the structure of the Maze.*/
	protected Tile[][] maze;
	
	/**The number of rows in the Maze.*/
	protected final int rows;
	
	/**The number of columns in the Maze.*/
	protected final int columns;
	
	/**The Tile containing the entrance to the Maze.*/
	protected Tile entrance;
	
	/**The Tile containing the exit from the Maze.*/
	protected Tile exit;
	
	/**The MazeDrawer which contains the rendering code for the maze.*/
	protected final MazeDrawer drawer;
	
	/**
	 * Creates a new Maze object.
	 * @param rows The number of row (width) of the Maze.
	 * @param columns The number of columns (height) of the Maze.
	 * @param sides The
	 */
	public Maze(TileType type, int rows, int columns)
	{		
		this.type = type;
		this.rows = rows;
		this.columns = columns;
		
		this.maze = new Tile[rows][columns];
		this.drawer = this.resolveMazeDrawer();
			
		this.entrance = null;
		this.exit = null;
		
		this.populate();
	}

	private MazeDrawer resolveMazeDrawer()
	{
		if(this instanceof MazeHexagon)
		{
			return new MazeDrawerHexagon(this);
		}
		else
		{
			return new MazeDrawerSquare(this);
		}
	}
	
	private void populate()
	{
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.columns; j++)
			{
				this.maze[i][j] = new Tile(this, i, j, type.getTypeSideNumber());
			}
		}
	}
	
	/**
	 * Carves a path between two Tiles. The Tiles must be neighbors for
	 * the method to work correctly.
	 * @param origin The origin Tile.
	 * @param target The target Tile.
	 */
	abstract public void carvePath(Tile origin, Tile target);
	
	/**
	 * Randomly sets the entrance and exit to the maze.
	 * @param random A random generator.
	 */
	abstract public void setEntranceAndExit(Random random);
	
	/**
	 * Gets the Tiles to the North, East, South, and West of the passed in Tile.
	 * @param tile The Tile to get the neighbors of.
	 * @return An ArrayList<Tile> containing all the found neighbors.
	 */
	abstract public ArrayList<Tile> getNeighbors(Tile tile);
	
	/**
	 * Checks if the passed in row and column position is within the bounds
	 * of the maze.
	 * @param maze The maze to check the bounds of.
	 * @param row The row position of the Tile.
	 * @param column The column position of the Tile.
	 * @return True if it is in bounds. Otherwise false.
	 */
	public boolean isWithinBounds(int row, int column)
	{
		if((row >= 0 && column >= 0) && (row < this.getRows() && column < this.getColumns()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Tile getRandomNeighbor(Random random, Tile tile)
	{
		ArrayList<Tile> neighbors = this.getNeighbors(tile);

		neighbors.removeAll(Collections.singleton(null));
		
		return neighbors.get(random.nextInt(neighbors.size()));
	}
	
	public ArrayList<Tile> getUnvisitedNeighbors(Tile origin)
	{
		ArrayList<Tile> unvisited = new ArrayList<Tile>();
		
		for(Tile neighbor : this.getNeighbors(origin))
		{
			if(neighbor != null && !neighbor.isVisited())
			{
				unvisited.add(neighbor);
			}
		}
		
		return unvisited;
	}
	
	public Tile findEntranceTile()
	{
		for(int i = 0; i < this.getRows(); i++)
		{
			for(int j = 0; j < this.getColumns(); j++)
			{
				if(this.getMaze()[i][j].isEntrance())
				{
					return this.getMaze()[i][j];
				}
			}
		}
		
		return null;
	}
	
	public boolean isTileEven(Tile tile)
	{
		if((tile.getRow() + 1) % 2 == 0)
		{
			return true;
		}
		else 
		{
			return false;
		}		
	}
	
	public boolean isTileOdd(Tile tile)
	{
		if((tile.getRow() + 1) % 2 == 0)
		{
			return false;
		}
		else 
		{
			return true;
		}	
	}
	
	public TileType getType()
	{
		return type;
	}

	public Tile[][] getMaze()
	{
		return this.maze;
	}

	public void setMaze(Tile[][] maze)
	{
		this.maze = maze;
	}
	
	public int getRows()
	{
		return this.rows;
	}

	public int getColumns()
	{
		return this.columns;
	}
	
	public Tile getTile(int row, int col)
	{
		return this.maze[row][col];
	}
	
	public Tile getEntrance()
	{
		return this.entrance;
	}
	
	public Tile getExit()
	{
		return this.exit;
	}

	public MazeDrawer getDrawer()
	{
		return drawer;
	}
}
