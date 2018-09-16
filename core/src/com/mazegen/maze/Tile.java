package com.mazegen.maze;

public abstract class Tile
{	
	private final Maze maze;
	
	private final int row;
	
	private final int column;
	
	private boolean visited;

	public Tile(Maze maze, int row, int column)
	{		
		this.maze = maze;

		this.row = row;
		this.column = column;
		
		this.visited = false;
	}

	public Maze getMaze()
	{
		return maze;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public boolean isVisited()
	{
		return this.visited;
	}
	
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
}
