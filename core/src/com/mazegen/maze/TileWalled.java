package com.mazegen.maze;

public class TileWalled extends Tile
{	
	private boolean[] walls;
	
	private boolean isEntrance;
	
	private boolean isExit;
		
	public TileWalled(Maze maze, int row, int column)
	{		
		super(maze, row, column);
	
		this.walls = new boolean[] { true, true, true, true};
		
		this.isEntrance = false;
		this.isExit = false;
	}
	
	public TileWalled(Maze maze, int row, int column, boolean[] walls)
	{
		super(maze, row, column);
		
		this.walls = new boolean[] { true, true, true, true};	
	}
	
	public boolean[] getWalls()
	{
		return this.walls;
	}
	
	public void setWalls(boolean[] walls)
	{
		this.walls = walls;
	}
	
	public void setWall(int index, boolean wall)
	{
		this.walls[index] = wall;
	}
	
	public void setWall(Direction direction, boolean wall)
	{
		this.walls[direction.ordinal()] = wall;
	}

	public boolean isEntrance()
	{
		return isEntrance;
	}

	public void setEntrance(boolean isEntrance)
	{
		this.isEntrance = isEntrance;
		this.isExit = false;
	}

	public boolean isExit()
	{
		return isExit;
	}

	public void setExit(boolean isExit)
	{
		this.isExit = isExit;
		this.isEntrance = false;
	}
}
