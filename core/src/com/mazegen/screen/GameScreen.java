package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.mazegen.entity.EntityManager;
import com.mazegen.entity.EntityPlayer;
import com.mazegen.main.Driver;
import com.mazegen.maze.GenerationType;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeFactory;
import com.mazegen.maze.TileType;
import com.mazegen.util.RenderUtil;

public class GameScreen implements Screen
{
	private EntityManager manager;
	
	private final MazeFactory factory;

	private Maze maze;	

	private EntityPlayer player;
	
	public GameScreen()
	{	
		this.manager = new EntityManager();
		this.factory = new MazeFactory();
						
		this.maze = null;
		this.player = null;
	}

	@Override
	public void render(float delta)
	{
		RenderUtil.clearScreen(1f, 1f, 1f, 1f);
		RenderUtil.updateCamera();
				
		if(maze != null)
		{						
			maze.render();

			manager.update(delta);			
			manager.render(delta);
						
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
			this.manager.getEntities().remove(this.player);
			this.maze = null;
			this.player = null;
		}
		else
		{
			this.maze = factory.getMaze(TileType.SQUARE, GenerationType.RECURSIVE_BACKTRACK, 100, 50);
			this.player = new EntityPlayer(this.maze, this.maze.getEntrance());
			
			this.manager.addEntity(player);
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
