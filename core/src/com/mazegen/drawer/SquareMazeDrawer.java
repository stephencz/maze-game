package com.mazegen.drawer;

import com.badlogic.gdx.math.Plane.PlaneSide;
import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;

public class SquareMazeDrawer extends MazeDrawer
{
	protected int wallSize;
	
	protected int tileSize;
	
	public SquareMazeDrawer(Maze maze)
	{
		super(maze);
		
		this.wallSize = 2;
		this.tileSize = 8;
	}
	
	@Override
	protected void draw(int i, int j)
	{
		this.drawTiles(i, j);
		this.drawCorners(i, j);
		this.drawWalls(i, j);
	}
	
	@Override
	protected boolean isOnScreen(int i, int j)
	{
		float x = (i * this.tileSize);
		float y = (j * this.tileSize);
		
		/*
		if(x >= -10 && x <= Driver.viewport.getScreenWidth() + 10)
		{
			if(y >= -10 && y <= Driver.viewport.getScreenHeight() + 10)
			{
				return true;
			}
		}*/
		
		if(Driver.camera.frustum.pointInFrustum(x, y, 0f))
		{
			return true;
		}
		
		return false;
	}

	private void drawTiles(int i, int j)
	{
		if(this.maze.getMaze()[i][j].isEntrance())
		{
			this.drawEntrance(i, j);
		}
		else if(this.maze.getMaze()[i][j].isExit())
		{
			this.drawExit(i, j);
		}
		else
		{
			//Draw Tile Background
			if(this.maze.getMaze()[i][j].isVisited())
			{
				Driver.shape.setColor(this.visitedColor);
			}
			else
			{
				Driver.shape.setColor(this.tileColor);
			}
			
			this.drawTile(i, j);

		}
	}
	
	private void drawCorners(int i, int j)
	{
		Driver.shape.setColor(this.wallColor);
		this.drawUpperLeftCorners(i, j);
		this.drawLowerRightCorners(i, j);
	}
	
	private void drawWalls(int i, int j)
	{
		//Draw Tile Walls
		Driver.shape.setColor(this.wallColor);
		if(this.maze.getMaze()[i][j].getWalls()[0]) //North Wall
		{
			this.drawNorthWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[1]) //East Wall
		{
			this.drawEastWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[2]) //South Wall
		{
			this.drawSouthWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[3]) //West Wall
		{
			this.drawWestWall(i, j);
		}					
	}
	
	private void drawEntrance(int i, int j)
	{
		Driver.shape.setColor(this.tileColor);
		Driver.shape.rect(this.tileSize * i,
				this.tileSize * j, 
				this.tileSize, 
				this.tileSize + this.wallSize);
		
		Driver.shape.setColor(this.entranceColor);
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j, 
				this.tileSize, 
				this.wallSize);
	}
	
	private void drawExit(int i, int j)
	{
		Driver.shape.setColor(this.tileColor);
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j, 
				this.tileSize, 
				this.tileSize + this.wallSize);
		
		Driver.shape.setColor(this.exitColor);
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j + this.tileSize, 
				this.tileSize, 
				this.wallSize);
	}
	
	private void drawTile(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j, 
				this.tileSize, 
				this.tileSize + this.wallSize);
	}
	
	private void drawNorthWall(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j, 
				this.tileSize, 
				this.wallSize);
	}
	
	private void drawEastWall(int i, int j)
	{
		Driver.shape.rect((this.tileSize * i) + (this.tileSize), 
				this.tileSize * j, 
				this.wallSize, 
				this.tileSize);
	}
	
	private void drawSouthWall(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, 
				(this.tileSize * j) + (this.tileSize), 
				this.tileSize, 
				this.wallSize);
	}
	
	private void drawWestWall(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j, 
				this.wallSize, 
				this.tileSize + this.wallSize);
	}
	
	private void drawUpperLeftCorners(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, 
				this.tileSize * j, 
				this.wallSize, 
				this.wallSize); 
	}
	
	private void drawLowerRightCorners(int i, int j)
	{
		Driver.shape.rect((this.tileSize * i) + this.tileSize, 
				(this.tileSize * j) + (this.tileSize), 
				this.wallSize, 
				this.wallSize);
	}

	public int getWallSize()
	{
		return wallSize;
	}

	public void setWallSize(int wallSize)
	{
		this.wallSize = wallSize;
	}

	public int getTileSize()
	{
		return tileSize;
	}

	public void setTileSize(int tileSize)
	{
		this.tileSize = tileSize;
	}
}
