package com.mazegen.entity;

import com.mazegen.maze.Maze;

public abstract class Entity
{
	protected final Maze maze;
	
	protected float x;
	
	protected float y;
	
	protected int width;
	
	protected int height;
	
	public Entity(Maze maze, float x, float y, int width, int height)
	{
		this.maze = maze;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	abstract public void render(float delta);
	
	abstract public void dispose();
	
	public Maze getMaze()
	{
		return maze;
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
