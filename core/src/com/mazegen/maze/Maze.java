package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * <p>The Maze class represents the structure of a 2D maze composed
 * of {@link Tile} objects. The Maze can be composed of any tile type
 * found in the {@link TileType} enumeration. Maze objects are primarily
 * concerned with representing the structure of the maze and implementing
 * various abstract methods which contain logic useful to the {@link MazeFactory} 
 * for generation.</p> 
 * 
 * <p>Every Maze object keeps track of various attributes of the maze such as
 * the dimensions of the Maze and the entrance and exit Tiles. Drawing of the
 * Maze is delegated to a {@link MazeDrawer} object which is resolved based
 * on the Maze's tile {@link #type}.</p>
 * 
 * Abstract methods:
 * <ul>
 * <li>{@link #carvePath(Tile, Tile)}</li>
 * <li>{@link #isPathClear(Tile, Tile)}</li>
 * <li>{@link #setEntranceAndExit(Random)}</li>
 * <li>{@link #getNeighbors(Tile)}</li>
 * <ul>
 */
public abstract class Maze
{		
	
	/**The type of Tile that composes the maze.*/
	protected final TileType type;
	
	/**The MazeDrawer which contains the rendering code for the maze.*/
	protected final MazeDrawer drawer;
	
	/**The number of rows in the Maze.*/
	protected final int rows;
	
	/**The number of columns in the Maze.*/
	protected final int columns;
	
	/**A 2D array of Tile objects which represents the structure of the Maze.*/
	protected Tile[][] maze;
	
	/**The Tile containing the entrance to the Maze.*/
	protected Tile entrance;
	
	/**The Tile containing the exit from the Maze.*/
	protected Tile exit;
	
	/**
	 * Creates a new Maze object.
	 * @param rows The number of row (width) of the Maze.
	 * @param columns The number of columns (height) of the Maze.
	 * @param sides The
	 */
	public Maze(TileType type, int rows, int columns)
	{		
		this.type = type;
		this.drawer = this.resolveMazeDrawer();
		
		this.rows = rows;
		this.columns = columns;
		
		this.maze = new Tile[rows][columns];
			
		this.entrance = null;
		this.exit = null;
		
		this.populate();
	}
	
	/**
	 * Carves a path between two Tiles. The Tiles should be neighbors for
	 * the method to work correctly.
	 * @param origin The origin Tile.
	 * @param target The target Tile.
	 */
	abstract public void carvePath(Tile origin, Tile target);
	
	/**
	 * Determines if the path is clear between two Tiles. True if the
	 * path is clear. Otherwise false.
	 * @param origin The first Tile.
	 * @param target The second Tile.
	 * @return True if the path is clear. Otherwise false.
	 */
	abstract public boolean isPathClear(Tile origin, Tile target);
	
	/**
	 * Randomly sets the entrance and exit to the maze.
	 * @param random A random generator.
	 */
	abstract public void setEntranceAndExit(Random random);
	
	/**
	 * Gets the Tiles which can be considered to be neighbors of the
	 * passed in Tile.
	 * @param tile The Tile to get the neighbors of.
	 * @return An ArrayList<Tile> containing all the found neighbors.
	 */
	abstract public ArrayList<Tile> getNeighbors(Tile tile);
	
	/**A convenience method that calls the Maze's MazeDrawer's render method.*/
	public void render()
	{
		this.drawer.render();
	}
	
	/**
	 * Resolves what kind of MazeDrawer object to use based on
	 * the kind of Maze object this is. 
	 * @return The correct kind of MazeDrawer.
	 */
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
	
	/**
	 * Populates the Maze's 2D Array of Tile objects with the tile that
	 * matches the Maze's {@link #type}
	 */
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
	
	/**
	 * Gets a random neighbor from around one of the Maze's Tile objects.
	 * Relies on the implementation of {@link #getNeighbors(Tile)}.
	 * 
	 * @param random A random number generator.
	 * @param tile The Tile object to get the neighbors of.
	 * @return A random Tile object that neighbors the passed in Tile object.
	 */
	public Tile getRandomNeighbor(Random random, Tile tile)
	{
		ArrayList<Tile> neighbors = this.getNeighbors(tile);

		neighbors.removeAll(Collections.singleton(null));
		
		return neighbors.get(random.nextInt(neighbors.size()));
	}
	
	/**
	 * Gets any neighbors of the passed in Tile object which have
	 * their visited flag set to false. Relies on the implementation
	 * of {@link #getNeighbors(Tile)}.
	 * @param origin The Tile object to get the neighbors of.
	 * @return An ArrayList<Tile> of neighbors marked as unvisited.
	 */
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
	
	/**
	 * Determines if the passed in Tile is on an even row.
	 * @param tile The Tile to check.
	 * @return True if on an even row. Otherwise false.
	 */
	public boolean isTileRowEven(Tile tile)
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
	
	/**
	 * Determines if the passed in Tile is on an even column.
	 * @param tile The Tile to check.
	 * @return True if on an even column. Otherwise false.
	 */
	public boolean isTileColumnEven(Tile tile)
	{
		if((tile.getColumn() + 1) % 2 == 0)
		{
			return true;
		}
		else 
		{
			return false;
		}	
	}
	
	/**@return The {@link TileType} that the Maze is composed of.*/
	public TileType getType()
	{
		return type;
	}

	/**@return A 2D array of Tile objects which holds the Maze's tiles.*/
	public Tile[][] getMaze()
	{
		return this.maze;
	}

	/**@param maze A 2D array of Tile objects.*/
	public void setMaze(Tile[][] maze)
	{
		this.maze = maze;
	}
	
	/**@return The number of rows in the Maze.*/
	public int getRows()
	{
		return this.rows;
	}

	/**@return The number of columns in the Maze.*/
	public int getColumns()
	{
		return this.columns;
	}
	
	/**
	 * @param row The row position of a Tile.
	 * @param col The column position of a Tile.
	 * @return The Tile found at the passed in row and column positions.
	 */
	public Tile getTile(int row, int col)
	{
		return this.maze[row][col];
	}
	
	/**@return The Tile marked as the Maze's entrance.*/
	public Tile getEntrance()
	{
		return this.entrance;
	}
	
	/**@return The Tile marked as the Maze's exit.*/
	public Tile getExit()
	{
		return this.exit;
	}

	/**@return The Maze's {@link MazeDrawer} object.*/
	public MazeDrawer getDrawer()
	{
		return drawer;
	}
	
}
