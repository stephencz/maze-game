package com.mazegen.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;
import com.mazegen.maze.draw.MazeDrawer;

public class EntityPlayer extends MazeEntity
{	
	
	public EntityPlayer(Maze maze, MazeDrawer drawer, int row, int col)
	{
		super(maze, drawer, row, col, 0, 0);
		
		this.width = drawer.getTileSize() - drawer.getTileSize() / 2;
		this.height = drawer.getTileSize() - drawer.getTileSize() / 2;
		this.x = this.calculateXPos() + drawer.getTileSize() / 4 + drawer.getWallSize() / 2;
		this.y = this.calculateYPos() + drawer.getTileSize() / 4 + drawer.getWallSize() / 2;		
	}

	@Override
	public void render(float delta)
	{
		this.drawPlayer();
		this.handlePlayerMovement();
		this.cameraTrackPlayer();
	}

	@Override
	public void dispose()
	{
		
	}
	
	private void drawPlayer()
	{
		Driver.shape.begin(ShapeType.Filled);
		Driver.shape.setColor(drawer.getPlayerColor());
		Driver.shape.rect(x, y, width, height);
		Driver.shape.end();
	}
	
	private void handlePlayerMovement()
	{	
		if(Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP))
		{
			if(this.maze.isWithinBounds(this.row, this.column - 1))
			{	
				if(!maze.getTile(row, column).getWalls()[0] && !maze.getTile(row, column - 1).getWalls()[2])
				{
					this.setColumn(this.column - 1);
				}
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN))
		{
			if(this.maze.isWithinBounds(this.row, this.column + 1))
			{
				if(!maze.getTile(row, column).getWalls()[2] && !maze.getTile(row, column + 1).getWalls()[0])
				{					
					this.setColumn(this.column + 1);
				}	
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.RIGHT))
		{
			if(this.maze.isWithinBounds(this.row + 1, this.column))
			{
				if(!maze.getTile(row, column).getWalls()[1] && !maze.getTile(row + 1, column).getWalls()[3])
				{				
					this.setRow(this.row + 1);
				}
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.LEFT))
		{
			if(this.maze.isWithinBounds(this.row - 1, this.column))
			{
				if(!maze.getTile(row, column).getWalls()[3] && !maze.getTile(row - 1, column).getWalls()[1])
				{
					this.setRow(this.row - 1);
				}
			}
		}
	}
	
	private void cameraTrackPlayer()
	{
		Driver.camera.position.lerp(new Vector3(this.x + drawer.getTileSize() / 4, this.y + drawer.getTileSize() / 4, 0), 0.2f);	
	}
	
	@Override
	public void setRow(int row)
	{
		this.row = row;
		this.x = this.calculateXPos() + drawer.getTileSize() / 4 + drawer.getWallSize() / 2;
	}
	
	@Override
	public void setColumn(int column)
	{
		this.column = column;
		this.y = this.calculateYPos() + drawer.getTileSize() / 4 + drawer.getWallSize() / 2;
	}
}
