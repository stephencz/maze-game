package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeHexagon extends Maze
{

	public MazeHexagon(int rows, int columns)
	{
		super(TileType.HEXAGON, rows, columns);
	}

	@Override
	public void carvePath(Tile origin, Tile target)
	{
		ArrayList<Tile> neighbors = this.getNeighbors(origin);
		for(int i = 0; i < neighbors.size(); i++)
		{
			if(neighbors.get(i) != null && neighbors.get(i).equals(target))
			{				
				origin.setWall(i, false);
				switch(i)
				{
					case 0: target.setWall(3, false); ; break;
					case 1: target.setWall(4, false); break;
					case 2: target.setWall(5, false); break;
					case 3: target.setWall(0, false); break;
					case 4: target.setWall(1, false); break;
					case 5: target.setWall(2, false); break;
					default: break;
				}
			}
		}
		
	}

	@Override
	public void setEntranceAndExit(Random random)
	{
		
	}
	
	@Override
	public ArrayList<Tile> getNeighbors(Tile tile)
	{
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		
		neighbors.add(this.getNorthNeighbor(tile));
		neighbors.add(this.getNorthEastNeighbor(tile));
		neighbors.add(this.getSouthEastNeighbor(tile));
		neighbors.add(this.getSouthNeighbor(tile));
		neighbors.add(this.getSouthWestNeighbor(tile));
		neighbors.add(this.getNorthWestNeighbor(tile));
				
		//neighbors.removeAll(Collections.singleton(null));
				
		return neighbors;
	}
	
	private Tile getNorthNeighbor(Tile tile)
	{		
		if(this.isWithinBounds(tile.getRow(), tile.getColumn() - 1))
		{
			return this.maze[tile.getRow()][tile.getColumn() - 1];
		}
		
		return null;
	}
	
	private Tile getNorthEastNeighbor(Tile tile)
	{
		if(this.isTileEven(tile))
		{
			if(this.isWithinBounds(tile.getRow() + 1, tile.getColumn()))
			{
				return this.maze[tile.getRow() + 1][tile.getColumn()];
			}
		}
		else
		{
			if(this.isWithinBounds(tile.getRow() + 1, tile.getColumn() - 1))
			{
				return this.maze[tile.getRow() + 1][tile.getColumn() - 1];
			}
		}
		
		return null;
	}
	
	private Tile getSouthEastNeighbor(Tile tile)
	{
		if(this.isTileEven(tile))
		{
			if(this.isWithinBounds(tile.getRow() + 1, tile.getColumn() + 1))
			{
				return this.maze[tile.getRow() + 1][tile.getColumn() + 1];
			}
		}
		else
		{
			if(this.isWithinBounds(tile.getRow() + 1, tile.getColumn()))
			{
				return this.maze[tile.getRow() + 1][tile.getColumn()];
			}
		}
		
		return null;
	}
	
	private Tile getSouthNeighbor(Tile tile)
	{
		if(this.isWithinBounds(tile.getRow(), tile.getColumn() + 1))
		{
			return this.maze[tile.getRow()][tile.getColumn() + 1];
		}
		
		return null;
	}
	
	private Tile getSouthWestNeighbor(Tile tile)
	{
		if(this.isTileEven(tile))
		{
			if(this.isWithinBounds(tile.getRow() - 1, tile.getColumn() + 1))
			{
				return this.maze[tile.getRow() - 1][tile.getColumn() + 1];
			}
		}
		else
		{
			if(this.isWithinBounds(tile.getRow() - 1, tile.getColumn()))
			{
				return this.maze[tile.getRow() - 1][tile.getColumn()];
			}
		}
		
		return null;
	}
	
	private Tile getNorthWestNeighbor(Tile tile)
	{
		if(this.isTileEven(tile))
		{
			if(this.isWithinBounds(tile.getRow() - 1, tile.getColumn()))
			{
				return this.maze[tile.getRow() - 1][tile.getColumn()];
			}
		}
		else
		{
			if(this.isWithinBounds(tile.getRow() - 1, tile.getColumn() - 1))
			{
				return this.maze[tile.getRow() - 1][tile.getColumn() - 1];
			}
		}
		
		return null;
	}
	
}
