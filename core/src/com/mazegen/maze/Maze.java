package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Maze
{		
	protected final int rows;
	
	protected final int columns;
	
	protected final int sides;
	
	protected Tile[][] maze;
	
	protected Tile entrance;
	
	protected Tile exit;
	
	public Maze(int rows, int columns, int sides)
	{
		this.rows = rows;
		this.columns = columns;
		this.sides = sides;
		
		this.maze = new Tile[rows][columns];
			
		this.entrance = null;
		this.exit = null;
		
		this.populate();
	}

	private void populate()
	{
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.columns; j++)
			{
				this.maze[i][j] = new Tile(this, i, j, this.sides);
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
	 * Returns a random Tile which is a neighbor to the passed in Tile.
	 * @param random A Random generator.
	 * @param tile The Tile to get a neighbor of.
	 * @return A random neighbor Tile.
	 */
	abstract public Tile getRandomNeighbor(Random random, Tile tile);
	
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
	
	public ArrayList<Tile> getUnvisitedNeighbors(Tile origin)
	{
		ArrayList<Tile> unvisited = new ArrayList<Tile>();
		
		for(Tile neighbor : this.getNeighbors(origin))
		{
			if(!neighbor.isVisited())
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
	
	
	public Tile[][] getMaze()
	{
		return this.maze;
	}

	public void setMaze(Tile[][] maze)
	{
		this.maze = maze;
	}
	
	public Tile getTile(int row, int col)
	{
		return this.maze[row][col];
	}

	public int getRows()
	{
		return this.rows;
	}

	public int getColumns()
	{
		return this.columns;
	}
	
	public int getSides()
	{
		return this.sides;
	}
	
	public Tile getEntrance()
	{
		return this.entrance;
	}
	
	public Tile getExit()
	{
		return this.exit;
	}
}
