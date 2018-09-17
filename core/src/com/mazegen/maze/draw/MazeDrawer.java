package com.mazegen.maze.draw;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;

public abstract class MazeDrawer
{

	protected Maze maze;
	
	protected MazeDrawerSettings settings;
	
	protected int mazeWidth;
	
	protected int mazeHeight;
	
	public MazeDrawer(Maze maze)
	{
		this.maze = maze;
		this.settings = new MazeDrawerSettings();
		
		this.mazeWidth = maze.getRows();
		this.mazeHeight = maze.getColumns();
	}

	public void render()
	{	
		if(maze != null)
		{
			Driver.shape.begin(ShapeType.Filled);
			for(int i = 0; i < maze.getRows(); i++)
			{
				for(int j = 0; j < maze.getColumns(); j++)
				{	
					if(this.canPlayerSee(i, j))
					{
						this.draw(i, j);
					}
				}
			}			
			Driver.shape.end();
		}
	}

	abstract protected void draw(int i, int j);
	
	protected boolean canPlayerSee(int i, int j)
	{
		if(this.settings.tileSize * i > Driver.camera.position.x - this.settings.sightRange && settings.tileSize * i < Driver.camera.position.x + this.settings.sightRange)
		{
			if(this.settings.tileSize * j > Driver.camera.position.y - this.settings.sightRange && settings.tileSize * j < Driver.camera.position.y + this.settings.sightRange)
			{
				return true;
			}	
		}
		
		return false;
	}
	
	public Maze getMaze()
	{
		return maze;
	}

	public void setMaze(Maze maze)
	{
		this.maze = maze;
		this.mazeWidth = maze.getRows();
		this.mazeHeight = maze.getColumns();
	}

	public MazeDrawerSettings getSettings()
	{
		return this.settings;
	}
	
	public void setSettings(MazeDrawerSettings settings)
	{
		this.settings = settings;
	}
	
	public int getMazeWidth()
	{
		return mazeWidth;
	}

	public void setMazeWidth(int mazeWidth)
	{
		this.mazeWidth = mazeWidth;
	}

	public int getMazeHeight()
	{
		return mazeHeight;
	}

	public void setMazeHeight(int mazeHeight)
	{
		this.mazeHeight = mazeHeight;
	}
}
