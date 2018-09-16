package com.mazegen.maze;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mazegen.main.Driver;

public class MazeDrawer
{

	private Maze maze;
	
	private int wallSize;
	
	private int tileSize;
	
	private int mazeWidth;
	
	private int mazeHeight;
	
	private int sightRange;
	
	private Color bgColor;
	
	private Color tileColor;
	
	private Color visitedColor;
	
	private Color wallColor;
	
	private Color entranceColor;
	
	private Color exitColor;
	
	private Color playerColor;
		
	public MazeDrawer(Maze maze, int tSize, int wSize, int sight)
	{
		this.maze = maze;
		
		this.mazeWidth = maze.getRows();
		this.mazeHeight = maze.getColumns();
		this.tileSize = tSize;
		this.wallSize = wSize;
		this.sightRange = sight;
		
		this.bgColor = new Color(0.99f, 0.99f, 0.99f, 1.0f);
		this.tileColor = new Color(1f, 1f, 1f, 1.0f);
		this.visitedColor = new Color(1f, 1f, 1f, 1.0f);
		this.wallColor =  new Color(0f, 0f, 0f, 1.0f);
		this.entranceColor = new Color(0f, 1f, 0f, 1.0f);
		this.exitColor = new Color(1f, 0f, 0f, 1.0f);
		this.playerColor = new Color(0f, 0f, 1f, 1.0f);;
	}

	public void drawMaze()
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
						this.drawBackground();
						this.drawTiles(i, j);
						this.drawCorners(i, j);
						this.drawWalls(i, j);
					}
				}
			}			
			Driver.shape.end();
		}
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
	
	private void drawBackground()
	{
		
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
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.tileSize, this.tileSize + this.wallSize);
		
		Driver.shape.setColor(this.entranceColor);
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.tileSize, this.wallSize);
	}
	
	private void drawExit(int i, int j)
	{
		Driver.shape.setColor(this.tileColor);
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.tileSize, this.tileSize + this.wallSize);
		
		Driver.shape.setColor(this.exitColor);
		Driver.shape.rect(this.tileSize * i, this.tileSize * j + this.tileSize, this.tileSize, this.wallSize);
	}
	
	private void drawTile(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.tileSize, this.tileSize + this.wallSize);
	}
	
	private void drawNorthWall(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.tileSize, this.wallSize);
	}
	
	private void drawEastWall(int i, int j)
	{
		Driver.shape.rect((this.tileSize * i) + (this.tileSize), this.tileSize * j, this.wallSize, this.tileSize);
	}
	
	private void drawSouthWall(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, (this.tileSize * j) + (this.tileSize), this.tileSize, this.wallSize);
	}
	
	private void drawWestWall(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.wallSize, this.tileSize + this.wallSize);
	}
	
	private void drawUpperLeftCorners(int i, int j)
	{
		Driver.shape.rect(this.tileSize * i, this.tileSize * j, this.wallSize, this.wallSize); 
	}
	
	private void drawLowerRightCorners(int i, int j)
	{
		Driver.shape.rect((this.tileSize * i) + this.tileSize, (this.tileSize * j) + (this.tileSize), this.wallSize, this.wallSize);
	}
	
	private boolean canPlayerSee(int i, int j)
	{
		if(this.tileSize * i > Driver.camera.position.x - this.sightRange && tileSize * i < Driver.camera.position.x + this.sightRange)
		{
			if(this.tileSize * j > Driver.camera.position.y - this.sightRange && tileSize * j < Driver.camera.position.y + this.sightRange)
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
	
	public int getSightRange()
	{
		return sightRange;
	}

	public void setSightRange(int sightRange)
	{
		this.sightRange = sightRange;
	}

	public Color getBgColor()
	{
		return bgColor;
	}

	public void setBgColor(Color bgColor)
	{
		this.bgColor = bgColor;
	}

	public Color getTileColor()
	{
		return tileColor;
	}

	public void setTileColor(Color tileColor)
	{
		this.tileColor = tileColor;
	}

	public Color getVisitedColor()
	{
		return visitedColor;
	}

	public void setVisitedColor(Color visitedColor)
	{
		this.visitedColor = visitedColor;
	}

	public Color getWallColor()
	{
		return wallColor;
	}

	public void setWallColor(Color wallColor)
	{
		this.wallColor = wallColor;
	}

	public Color getEntranceColor()
	{
		return entranceColor;
	}

	public void setEntranceColor(Color entranceColor)
	{
		this.entranceColor = entranceColor;
	}

	public Color getExitColor()
	{
		return exitColor;
	}

	public void setExitColor(Color exitColor)
	{
		this.exitColor = exitColor;
	}

	public Color getPlayerColor()
	{
		return playerColor;
	}

	public void setPlayerColor(Color playerColor)
	{
		this.playerColor = playerColor;
	}
}
