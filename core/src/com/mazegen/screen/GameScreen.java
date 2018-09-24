package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.mazegen.drawer.MazeDrawerHexagon;
import com.mazegen.drawer.MazeDrawer;
import com.mazegen.drawer.MazeDrawerSquare;
import com.mazegen.gen.GenerationType;
import com.mazegen.gen.MazeFactory;
import com.mazegen.main.Driver;
import com.mazegen.maze.MazeHexagon;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeSquare;
import com.mazegen.maze.TileType;
import com.mazegen.util.RenderUtil;

public class GameScreen implements Screen
{
	private final TileType tileType;
	
	private final GenerationType genType;
	
	private final MazeFactory factory;
	
	private int width;
	
	private int height;
		
	private  Maze maze;

	private MazeDrawer drawer;
	
	public GameScreen(TileType tileType, GenerationType genType, int width, int height)
	{	
		this.tileType = tileType;
		this.genType = genType;
		
		this.factory = new MazeFactory();
		
		this.width = width;
		this.height = height;
		
		this.createMazeAndDrawer();
				
		Driver.camera.zoom = 2.5f;
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
			this.handleCameraMovement();
						
			if(Gdx.input.isKeyJustPressed(Keys.R))
			{
				this.createMazeAndDrawer();
			}
		}
	}
	
	private void createMazeAndDrawer()
	{
		this.maze = factory.getMaze(tileType, genType, width, height);
		
		if(maze instanceof MazeSquare)
		{
			this.drawer = new MazeDrawerSquare(this.maze);
		}
		else
		{
			this.drawer = new MazeDrawerHexagon(this.maze);
		}
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
	
	private void handleCameraMovement()
	{
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			Driver.camera.position.y -= 100f * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			Driver.camera.position.x -= 100f * Gdx.graphics.getDeltaTime();	
		}
		
		if(Gdx.input.isKeyPressed(Keys.S))
		{
			Driver.camera.position.y += 100f * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			Driver.camera.position.x += 100f * Gdx.graphics.getDeltaTime();	
		}
	}
	
	@Override
	public void resize(int width, int height)
	{
		Driver.viewport.update(width, height, true);
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
