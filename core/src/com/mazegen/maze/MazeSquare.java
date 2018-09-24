package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeSquare extends Maze
{		
	public MazeSquare(int rows, int columns)
	{
		super(TileType.SQUARE, rows, columns);
	}
	
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
