package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.mazegen.main.Driver;
import com.mazegen.maze.GenerationType;
import com.mazegen.maze.MazeFactory;
import com.mazegen.maze.SquareMaze;
import com.mazegen.maze.TileType;
import com.mazegen.maze.draw.SquareMazeDrawer;
import com.mazegen.util.RenderUtil;

public class GameScreen implements Screen
{
	private TileType tileType;
	
	private GenerationType genType;
	
	private int width;
	
	private int height;
	
	private MazeFactory factory;
	
	private  SquareMaze maze;
	
	private SquareMazeDrawer drawer;
					
	public GameScreen(TileType tileType, GenerationType genType, int width, int height)
	{	
		this.tileType = tileType;
		this.genType = genType;
		
		this.width = width;
		this.height = height;
		
		this.factory = new MazeFactory();
		
		this.maze = null;
		this.drawer = null;
				
		Driver.camera.zoom = 1.5f;
	}

	@Override
	public void render(float delta)
	{
		RenderUtil.clearScreen(1f, 1f, 1f, 1f);
		RenderUtil.updateCamera();
		
		if((maze != null && drawer != null))
		{
			drawer.render();
						
			this.handleCameraZoom();
			
			if(Gdx.input.isKeyJustPressed(Keys.R))
			{
				this.handleMazeGeneration();
			}
		}
		else
		{
			this.handleMazeGeneration();
		}
	}

	private void handleMazeGeneration()
	{	
		maze = factory.generateSquareRecursiveBacktrackMaze(width, height);
		drawer = new SquareMazeDrawer(this.maze);				
	}
	
	private void handleCameraZoom()
	{
		if(Gdx.input.isKeyPressed(Keys.Z))
		{
			Driver.camera.zoom -= 1f * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.X))
		{
			Driver.camera.zoom += 1f * Gdx.graphics.getDeltaTime();
		}
	}
	
	@Override
	public void resize(int width, int height)
	{
		Driver.viewport.update(width, height);
		Driver.camera.update();
	}
	
	@Override
	public void dispose()
	{
		
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void resume()
	{
		
	}

	@Override
	public void show()
	{
		
	}
	
	@Override
	public void hide()
	{
		
	}
}
