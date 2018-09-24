package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.mazegen.entity.EntityManager;
import com.mazegen.main.Driver;
import com.mazegen.maze.MazeHexagon;
import com.mazegen.maze.GenerationType;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeDrawer;
import com.mazegen.maze.MazeDrawerHexagon;
import com.mazegen.maze.MazeDrawerSquare;
import com.mazegen.maze.MazeFactory;
import com.mazegen.maze.MazeSquare;
import com.mazegen.maze.TileType;
import com.mazegen.util.RenderUtil;

public class GameScreen implements Screen
{
	private EntityManager manager;
	
	private final MazeFactory factory;

	private  Maze maze;
	
	public GameScreen()
	{	
		this.manager = new EntityManager();
		this.factory = new MazeFactory();
						
		Driver.camera.zoom = 2.5f;
	}

	@Override
	public void render(float delta)
	{
		RenderUtil.clearScreen(1f, 1f, 1f, 1f);
		RenderUtil.updateCamera();
				
		if(maze != null)
		{
						
			manager.render(delta);
			
			this.handleCameraZoom();
			this.handleCameraMovement();
						
			if(Gdx.input.isKeyJustPressed(Keys.R))
			{
				this.createMaze();
			}
		}
		else
		{
			this.createMaze();
		}
	}
	
	private void createMaze()
	{
		if(this.maze != null)
		{
			this.manager.getEntities().remove(this.maze);
			this.maze = null;
		}
		
		this.maze = factory.getMaze(TileType.SQUARE, GenerationType.RECURSIVE_BACKTRACK, 10, 5);
		this.manager.addEntity(this.maze);
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
