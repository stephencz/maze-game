package com.mazegen.maze.draw;

import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;
import com.mazegen.maze.SquareMaze;

public class SquareMazeDrawer extends MazeDrawer
{
	public SquareMazeDrawer(Maze maze)
	{
		super(maze);
	}
	
	@Override
	protected void draw(int i, int j)
	{
		this.drawTiles(i, j);
		this.drawCorners(i, j);
		this.drawWalls(i, j);
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
				Driver.shape.setColor(this.settings.visitedColor);
			}
			else
			{
				Driver.shape.setColor(this.settings.tileColor);
			}
			
			this.drawTile(i, j);

		}
	}
	
	private void drawCorners(int i, int j)
	{
		Driver.shape.setColor(this.settings.wallColor);
		this.drawUpperLeftCorners(i, j);
		this.drawLowerRightCorners(i, j);
	}
	
	private void drawWalls(int i, int j)
	{
		//Draw Tile Walls
		Driver.shape.setColor(this.settings.wallColor);
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
		Driver.shape.setColor(this.settings.tileColor);
		Driver.shape.rect(this.settings.tileSize * i,
				this.settings.tileSize * j, 
				this.settings.tileSize, 
				this.settings.tileSize + this.settings.wallSize);
		
		Driver.shape.setColor(this.settings.entranceColor);
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j, 
				this.settings.tileSize, 
				this.settings.wallSize);
	}
	
	private void drawExit(int i, int j)
	{
		Driver.shape.setColor(this.settings.tileColor);
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j, 
				this.settings.tileSize, 
				this.settings.tileSize + this.settings.wallSize);
		
		Driver.shape.setColor(this.settings.exitColor);
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j + this.settings.tileSize, 
				this.settings.tileSize, 
				this.settings.wallSize);
	}
	
	private void drawTile(int i, int j)
	{
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j, 
				this.settings.tileSize, 
				this.settings.tileSize + this.settings.wallSize);
	}
	
	private void drawNorthWall(int i, int j)
	{
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j, 
				this.settings.tileSize, 
				this.settings.wallSize);
	}
	
	private void drawEastWall(int i, int j)
	{
		Driver.shape.rect((this.settings.tileSize * i) + (this.settings.tileSize), 
				this.settings.tileSize * j, 
				this.settings.wallSize, 
				this.settings.tileSize);
	}
	
	private void drawSouthWall(int i, int j)
	{
		Driver.shape.rect(this.settings.tileSize * i, 
				(this.settings.tileSize * j) + (this.settings.tileSize), 
				this.settings.tileSize, 
				this.settings.wallSize);
	}
	
	private void drawWestWall(int i, int j)
	{
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j, 
				this.settings.wallSize, 
				this.settings.tileSize + this.settings.wallSize);
	}
	
	private void drawUpperLeftCorners(int i, int j)
	{
		Driver.shape.rect(this.settings.tileSize * i, 
				this.settings.tileSize * j, 
				this.settings.wallSize, 
				this.settings.wallSize); 
	}
	
	private void drawLowerRightCorners(int i, int j)
	{
		Driver.shape.rect((this.settings.tileSize * i) + this.settings.tileSize, 
				(this.settings.tileSize * j) + (this.settings.tileSize), 
				this.settings.wallSize, 
				this.settings.wallSize);
	}
}
