package com.mazegen.entity;

import com.mazegen.maze.Maze;
import com.mazegen.maze.draw.MazeDrawer;
import com.mazegen.maze.draw.SquareMazeDrawer;

public abstract class MazeEntity extends Entity
{

	protected final Maze maze;
	
	protected final MazeDrawer drawer;
	
	protected int row;
	
	protected int column;
	
	public MazeEntity(Maze maze, MazeDrawer drawer, int row, int col, int width, int height)
	{		
		super(0f, 0f, width, height);
		
		this.maze = maze;
		this.drawer = drawer;
		this.row = row;
		this.column = col;
		
		this.x = this.calculateXPos();
		this.y = this.calculateYPos();
	}

	public Maze getMaze()
	{
		return maze;
	}

	public MazeDrawer getMazeDrawer()
	{
		return this.drawer;
	}
	
	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
		this.x = calculateXPos();
	}

	public int getColumn()
	{
		return column;
	}

	public void setColumn(int column)
	{
		this.column = column;
		this.y = calculateYPos();
	}
	
	protected float calculateXPos()
	{
		return (float) this.row * drawer.getTileSize();
	}
	
	protected float calculateYPos()
	{
		return (float) this.column * drawer.getTileSize();
	}
}
