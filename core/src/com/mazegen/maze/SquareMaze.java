package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SquareMaze extends Maze
{		
	public SquareMaze(int rows, int columns)
	{
		super(rows, columns, 4);
	}

	protected void populate()
	{
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.columns; j++)
			{
				this.maze[i][j] = new Tile(this, i, j, 4);
			}
		}
	}
	
	/**
	 * Carves a path between two Tiles. The Tiles must be neighbors for
	 * the method to work correctly.
	 * @param origin The origin Tile.
	 * @param target The target Tile.
	 */
	public void carvePath(Tile origin, Tile target)
	{			
		if(origin.getColumn() < target.getColumn())
		{
			origin.setWall(2, false);
			target.setWall(0, false);
		}
		
		if(origin.getColumn() > target.getColumn())
		{
			origin.setWall(0, false);
			target.setWall(2, false);
		}
		
		if(origin.getRow() < target.getRow())
		{
			origin.setWall(1, false);
			target.setWall(3, false);
		}
		
		if(origin.getRow() > target.getRow())
		{
			origin.setWall(3, false);
			target.setWall(1, false);	
		}
	}
	
	public void setEntranceAndExit(Random random)
	{
		Tile entrance = this.getTile(random.nextInt(this.getRows()), 0);
		Tile exit = this.getTile(random.nextInt(this.getRows()), this.getColumns() - 1);
		
		this.entrance = entrance;
		entrance.setWall(0, false);
		entrance.setEntrance(true);
		
		this.exit = exit;
		exit.setWall(2, false);
		exit.setExit(true);
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
	 * Returns a random Tile which is a neighbor to the passed in Tile.
	 * @param random A Random generator.
	 * @param tile The Tile to get a neighbor of.
	 * @return A random neighbor Tile.
	 */
	public Tile getRandomNeighbor(Random random, Tile tile)
	{
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		
		neighbors.add(this.getNorthNeighbor(tile));
		neighbors.add(this.getEastNeighbor(tile));
		neighbors.add(this.getSouthNeighbor(tile));
		neighbors.add(this.getWestNeighbor(tile));

		neighbors.removeAll(Collections.singleton(null));
		
		return neighbors.get(random.nextInt(neighbors.size()));
	}
	
	/**
	 * Gets the Tiles to the North, East, South, and West of the passed in Tile.
	 * @param tile The Tile to get the neighbors of.
	 * @return An ArrayList<Tile> containing all the found neighbors.
	 */
	public ArrayList<Tile> getNeighbors(Tile tile)
	{
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		
		neighbors.add(this.getNorthNeighbor(tile));
		neighbors.add(this.getEastNeighbor(tile));
		neighbors.add(this.getSouthNeighbor(tile));
		neighbors.add(this.getWestNeighbor(tile));

		neighbors.removeAll(Collections.singleton(null));
		
		return neighbors;
	}
	
	/**
	 * Gets the Tile to the North of the passed in Tile.
	 * @param tile The origin Tile.
	 * @return The Tile to the North of the origin Tile. Null if no Tile is found.
	 */
	private Tile getNorthNeighbor(Tile tile)
	{
		if(this.isWithinBounds(tile.getRow() - 1, tile.getColumn()))
		{
			if(this.getTile(tile.getRow() - 1, tile.getColumn()) != null)
			{
				return this.getTile(tile.getRow() - 1, tile.getColumn());
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the Tile to the South of the passed in Tile.
	 * @param tile The origin Tile.
	 * @return The Tile to the South of the origin Tile. Null if no Tile is found.
	 */
	private Tile getSouthNeighbor(Tile tile)
	{
		if(this.isWithinBounds(tile.getRow() + 1, tile.getColumn()))
		{
			if(this.getTile(tile.getRow() + 1, tile.getColumn()) != null)
			{
				return this.getTile(tile.getRow() + 1, tile.getColumn());
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the Tile to the East of the passed in Tile.
	 * @param tile The origin Tile.
	 * @return The Tile to the East of the origin Tile. Null if no Tile is found.
	 */
	private Tile getEastNeighbor(Tile tile)
	{
		if(this.isWithinBounds(tile.getRow(), tile.getColumn() + 1))
		{
			if(this.getTile(tile.getRow(), tile.getColumn() + 1) != null)
			{
				return this.getTile(tile.getRow(), tile.getColumn() + 1);
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the Tile to the West of the passed in Tile.
	 * @param tile The origin Tile.
	 * @return The Tile to the West of the origin Tile. Null if no Tile is found.
	 */
	private Tile getWestNeighbor(Tile tile)
	{
		if(this.isWithinBounds(tile.getRow(), tile.getColumn() - 1))
		{
			if(this.getTile(tile.getRow(), tile.getColumn() - 1) != null)
			{
				return this.getTile(tile.getRow(), tile.getColumn() - 1);
			}
		}
		
		return null;
	}
}
