package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze
{	
	private final int rows;
	
	private final int columns;
	
	private TileWalled[][] maze;
	
	private Tile entrance;
	
	private Tile exit;
	
	public Maze(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		this.maze = new TileWalled[rows][columns];
		
		this.populate();
		
		this.entrance = null;
		this.exit = null;
	}

	/**
	 * Carves a path between two Tiles. The Tiles must be neighbors for
	 * the method to work correctly.
	 * @param origin The origin Tile.
	 * @param target The target Tile.
	 */
	public void carvePath(TileWalled origin, TileWalled target)
	{		
		if(origin.getColumn() < target.getColumn())
		{
			origin.setWall(Direction.SOUTH, false);
			target.setWall(Direction.NORTH, false);
		}
		
		if(origin.getColumn() > target.getColumn())
		{
			origin.setWall(Direction.NORTH, false);
			target.setWall(Direction.SOUTH, false);
		}
		
		if(origin.getRow() < target.getRow())
		{
			origin.setWall(Direction.EAST, false);
			target.setWall(Direction.WEST, false);
		}
		
		if(origin.getRow() > target.getRow())
		{
			origin.setWall(Direction.WEST, false);
			target.setWall(Direction.EAST, false);	
		}
	}
	
	public void setEntranceAndExit(Random random)
	{
		TileWalled entrance = this.getTile(random.nextInt(this.getRows()), 0);
		TileWalled exit = this.getTile(random.nextInt(this.getRows()), this.getColumns() - 1);
		
		this.entrance = entrance;
		entrance.setWall(Direction.NORTH, false);
		entrance.setEntrance(true);
		
		this.exit = exit;
		exit.setWall(Direction.SOUTH, false);
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
	 * Gets the Tile to the North of the passed in Tile.
	 * @param tile The origin Tile.
	 * @return The Tile to the North of the origin Tile. Null if no Tile is found.
	 */
	public Tile getNorthNeighbor(Tile tile)
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
	public Tile getSouthNeighbor(Tile tile)
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
	public Tile getEastNeighbor(Tile tile)
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
	public Tile getWestNeighbor(Tile tile)
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
	
	public TileWalled findEntranceTile()
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
	
	private void populate()
	{
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.columns; j++)
			{
				this.maze[i][j] = new TileWalled(this, i, j);
			}
		}
	}
	
	public TileWalled[][] getMaze()
	{
		return maze;
	}

	public void setMaze(TileWalled[][] maze)
	{
		this.maze = maze;
	}
	
	public TileWalled getTile(int row, int col)
	{
		return this.maze[row][col];
	}

	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
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
