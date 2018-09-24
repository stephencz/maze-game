package com.mazegen.drawer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;

public class MazeDrawerHexagon extends MazeDrawer
{
	public static final int LENGTH_FACTOR = 4;
		
	protected float tileWidth;
	
	protected float tileHeight;
	
	private float tbLeft;
	
	private float tbRight;
			
	public MazeDrawerHexagon(Maze maze)
	{
		super(maze, 2);
				
		this.tileWidth = 32;
		this.tileHeight = 38;
		
		this.tbLeft = this.tileWidth / LENGTH_FACTOR;
		this.tbRight = (this.tileWidth / LENGTH_FACTOR) + this.tbLeft * 2;
		
		this.tileColor = new Color(1f, 0f, 0f, 1f);
	}

	@Override
	protected boolean isOnScreen(int i, int j)
	{
		return true;
	}
	
	@Override
	protected void draw(int i, int j)
	{
		if(this.maze.getMaze()[i][j] != null)
		{
			this.drawTiles(i, j);
			this.drawWalls(i, j);
			this.drawEntrance(i, j);
			this.drawExit(i, j);
		}
	}
	
	private void drawTiles(int i, int j)
	{
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
	
	private void drawTile(int i, int j)
	{
		
	}
	
	private void drawEntrance(int i, int j)
	{
		
	}
	
	private void drawExit(int i, int j)
	{
		
	}
	
	private void drawWalls(int i, int j)
	{	
		Driver.shape.setColor(this.wallColor);
		if((i + 1) % 2 == 0)
		{
			this.drawEvenWalls(i, j);
		}
		else
		{
			this.drawOddWalls(i, j);
		}
	}
	
	private void drawEvenWalls(int i, int j)
	{
		if(this.maze.getMaze()[i][j].getWalls()[0])
		{
			this.drawEvenNorthWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[1])
		{
			this.drawEvenNorthEastWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[2])
		{
			this.drawEvenSouthEastWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[3])
		{
			this.drawEvenSouthWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[4])
		{
			this.drawEvenSouthWestWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[5])
		{
			this.drawEvenNorthWestWall(i, j);
		}
	}
	
	private void drawOddWalls(int i, int j)
	{
		if(this.maze.getMaze()[i][j].getWalls()[0])
		{
			this.drawOddNorthWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[1])
		{
			this.drawOddNorthEastWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[2])
		{
			this.drawOddSouthEastWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[3])
		{
			this.drawOddSouthWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[4])
		{
			this.drawOddSouthWestWall(i, j);
		}
		
		if(this.maze.getMaze()[i][j].getWalls()[5])
		{
			this.drawOddNorthWestWall(i, j);
		}
	}
	
	private void drawEvenNorthWall(int i, int j)
	{
		Driver.shape.rect(this.tbLeft + (this.tileWidth * i),
				this.tileHeight / 2 + (this.tileHeight * j), 
				this.tbRight, 
				this.wallSize);
	}
	
	private void drawEvenNorthEastWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth) + this.tileWidth - this.wallSize / 2, 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.wallSize / 2, 
				(i * this.tileWidth) + this.tileWidth + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + this.tileHeight + this.wallSize / 2, 
				this.wallSize);
		
	}
	
	private void drawEvenSouthEastWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth) + this.tileWidth + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + this.tileHeight + this.wallSize / 2, 
				(i * this.tileWidth) + this.tileWidth - this.wallSize / 2, 
				(j * this.tileHeight) + (this.tileHeight + (this.tileHeight / 2)) + this.wallSize / 2,
				this.wallSize);
	}
	
	private void drawEvenSouthWall(int i, int j)
	{
		Driver.shape.rect(this.tbLeft + (this.tileWidth * i),
				(this.tileHeight / 2) + (this.tileHeight) + (this.tileHeight * j), 
				this.tbRight, 
				this.wallSize);
	}
	
	private void drawEvenSouthWestWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth)  - (this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight) + this.wallSize / 2, 
				(i * this.tileWidth) + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.tileHeight + this.wallSize / 2,
				this.wallSize);
	}
	
	private void drawEvenNorthWestWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth) + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.wallSize / 2, 
				(i * this.tileWidth)  - (this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight) + this.wallSize / 2, 
				this.wallSize);
	}
	
	private void drawOddNorthWall(int i, int j)
	{
		Driver.shape.rect(this.tbLeft + (this.tileWidth * i),
				this.tileHeight * j, 
				this.tbRight, 
				this.wallSize);
	}
	
	private void drawOddNorthEastWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth) + this.tileWidth - this.wallSize / 2, 
				(j * this.tileHeight) + this.wallSize / 2, 
				(i * this.tileWidth) + this.tileWidth + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.wallSize / 2, 
				this.wallSize);
	}
	
	private void drawOddSouthEastWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth) + this.tileWidth + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.wallSize / 2, 
				(i * this.tileWidth) + this.tileWidth - this.wallSize / 2, 
				(j * this.tileHeight) + (this.tileHeight) + this.wallSize / 2,
				this.wallSize);
	}
	
	private void drawOddSouthWall(int i, int j)
	{
		Driver.shape.rect(this.tbLeft + (this.tileWidth * i),
				this.tileHeight + (this.tileHeight * j), 
				this.tbRight, 
				this.wallSize);
	}
	
	private void drawOddSouthWestWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth)  - (this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.wallSize / 2, 
				(i * this.tileWidth) + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight) + this.wallSize / 2,
				this.wallSize);
	}
	
	private void drawOddNorthWestWall(int i, int j)
	{
		Driver.shape.rectLine((i * this.tileWidth) + (this.tbLeft + this.wallSize / 2), 
				(j * this.tileHeight) + this.wallSize / 2, 
				(i * this.tileWidth)  - (this.wallSize / 2), 
				(j * this.tileHeight) + (this.tileHeight / 2) + this.wallSize / 2, 
				this.wallSize);
	}
}
