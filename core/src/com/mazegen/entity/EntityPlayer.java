package com.mazegen.entity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeDrawerHexagon;
import com.mazegen.maze.MazeDrawerSquare;
import com.mazegen.maze.Tile;
import com.mazegen.maze.TileType;

public class EntityPlayer extends Entity
{
	
	private static final float MAX_ZOOM = 10.0f;
	
	private static final float INITIAL_ZOOM = 5.0f;
	
	private static final float MIN_ZOOM = 1.0f;
		
	public EntityPlayer(Maze maze, Tile tile)
	{
		super(maze, tile);
		
		Driver.camera.zoom = INITIAL_ZOOM;
	}

	@Override
	public void update(float delta)
	{
		this.handlePlayerMovement();
		this.trackPlayerMovement();
		this.handleCameraZoom();
	}

	@Override
	public void render(float delta)
	{
		this.drawPlayer();
	}
	
	private void handlePlayerMovement()
	{
		if(this.maze.getType() == TileType.SQUARE)
		{
			this.handleSquarePlayerMovement();
		}
		else
		{
			this.handleHexagonPlayerMovement();
		}
	}
	
	private void handleSquarePlayerMovement()
	{
		ArrayList<Tile> neighbors = this.maze.getNeighbors(tile);
		if(Gdx.input.isKeyJustPressed(Keys.W)) // NORTH MOVEMENT
		{
			if(neighbors.get(0) != null && this.maze.isPathClear(tile, neighbors.get(0)))
			{
				this.setTile(neighbors.get(0));
			}		
		}
		else if(Gdx.input.isKeyJustPressed(Keys.A)) // WEST MOVEMENT
		{
			if(neighbors.get(3) != null && this.maze.isPathClear(tile, neighbors.get(3)))
			{
				this.setTile(neighbors.get(3));
			}		
		}
		else if(Gdx.input.isKeyJustPressed(Keys.S)) // SOUTH MOVEMENT
		{
			if(neighbors.get(2) != null && this.maze.isPathClear(tile, neighbors.get(2)))
			{
				this.setTile(neighbors.get(2));
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.D)) // EAST MOVEMENT
		{
			if(neighbors.get(1) != null && this.maze.isPathClear(tile, neighbors.get(1)))
			{
				this.setTile(neighbors.get(1));
			}
		}
	}
	
	private void handleHexagonPlayerMovement()
	{
		ArrayList<Tile> neighbors = this.maze.getNeighbors(tile);
		if(Gdx.input.isKeyJustPressed(Keys.Q))
		{
			if(neighbors.get(5) != null && this.maze.isPathClear(tile, neighbors.get(5)))
			{
				this.setTile(neighbors.get(5));
			}		
		}
		else if(Gdx.input.isKeyJustPressed(Keys.W))
		{
			if(neighbors.get(0) != null && this.maze.isPathClear(tile, neighbors.get(0)))
			{
				this.setTile(neighbors.get(0));
			}		
		}
		else if(Gdx.input.isKeyJustPressed(Keys.E))
		{
			if(neighbors.get(1) != null && this.maze.isPathClear(tile, neighbors.get(1)))
			{
				this.setTile(neighbors.get(1));
			}		
		}
		else if(Gdx.input.isKeyJustPressed(Keys.A))
		{
			if(neighbors.get(4) != null && this.maze.isPathClear(tile, neighbors.get(4)))
			{
				this.setTile(neighbors.get(4));
			}		
		}
		else if(Gdx.input.isKeyJustPressed(Keys.S))
		{
			if(neighbors.get(3) != null && this.maze.isPathClear(tile, neighbors.get(3)))
			{
				this.setTile(neighbors.get(3));
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.D))
		{
			if(neighbors.get(2) != null && this.maze.isPathClear(tile, neighbors.get(2)))
			{
				this.setTile(neighbors.get(2));
			}
		}
	}	
		
	private void trackPlayerMovement()
	{	
		float x = this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn());
		float y = this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn());
		
		Driver.camera.position.lerp(new Vector3((int) x, (int) y, 0), 0.05f);
	}
	
	private void handleCameraZoom()
	{
		if(Gdx.input.isKeyJustPressed(Keys.U))
		{
			if(Driver.camera.zoom > MIN_ZOOM)
			{
				Driver.camera.zoom -=  1.0f;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.I))
		{
			if(Driver.camera.zoom < MAX_ZOOM)
			{
				Driver.camera.zoom += 1.0f;
			}
		}
	}
	
	private void drawPlayer()
	{
		if(this.maze.getType() == TileType.SQUARE)
		{
			this.drawSquareMazePlayer();
		}
		else
		{
			this.drawHexagonMazePlayer();
		}
	}
	
	private void drawSquareMazePlayer()
	{
		MazeDrawerSquare drawer = (MazeDrawerSquare) this.maze.getDrawer();
					
		Driver.shape.begin(ShapeType.Filled);
		Driver.shape.setColor(Color.RED);
		Driver.shape.rect(this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6,
				this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6, 
				drawer.getTileSize() / 2, 
				drawer.getTileSize() / 2);
		Driver.shape.end();
	}
	
	private void drawHexagonMazePlayer()
	{
		MazeDrawerHexagon drawer = (MazeDrawerHexagon) this.maze.getDrawer();
		
		Driver.shape.begin(ShapeType.Filled);
		Driver.shape.setColor(Color.RED);
		Driver.shape.rect(this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileWidth() / 8,
				this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileHeight() / 8, 
				drawer.getTileWidth() / 4, 
				drawer.getTileWidth() / 4);
		Driver.shape.end();
	}

	@Override
	public void dispose()
	{
		
	}
	
}
