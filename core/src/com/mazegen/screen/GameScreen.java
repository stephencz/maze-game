package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Input.Keys;
import com.mazegen.entity.EntityManager;
import com.mazegen.entity.EntityPlayer;
import com.mazegen.main.Driver;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeFactory;
import com.mazegen.maze.MazeType;
import com.mazegen.maze.SquareMaze;
import com.mazegen.maze.draw.MazeDrawer;
import com.mazegen.maze.draw.SquareMazeDrawer;
import com.mazegen.util.RenderUtil;

public class GameScreen implements Screen
{

	private MazeType type;
	
	private int width;
	
	private int height;
	
	private MazeFactory factory;
	
	private SquareMaze maze;
	
	private MazeDrawer drawer;
	
	private EntityManager manager;
	
	private EntityPlayer player;
			
	public GameScreen(MazeType type, int width, int height)
	{	
		this.type = type;
		this.width = width;
		this.height = height;
		
		this.factory = new MazeFactory();
		
		this.maze = null;
		this.drawer = null;
		
		this.manager = new EntityManager();
		this.player = null;
		
		Driver.camera.zoom = 1.5f;
	}

	@Override
	public void render(float delta)
	{
		RenderUtil.clearScreen(1f, 1f, 1f, 1f);
		RenderUtil.updateCamera();
		
		if((maze != null && drawer != null))
		{
			drawer.drawMaze();
			manager.render(delta);
						
			this.handleCameraZoom();
			
			if(Gdx.input.isKeyJustPressed(Keys.R))
			{
				this.handleMazeGeneration();
			}
			
			if(player.getRow() == maze.getExit().getRow())
			{
				if(player.getColumn() == maze.getExit().getColumn())
				{
					this.handleMazeGeneration();
				}
			}
		}
		else
		{
			this.handleMazeGeneration();
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
		manager.dispose();
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
	
	private void handleMazeGeneration()
	{
		//If a player already exists murder him or her.
		if(player != null)
		{
			manager.getEntities().remove(player);
		}
		
		maze = factory.generateSquareRecursiveBacktrackMaze(width, height);
		
		//Create the Maze Drawer
		drawer = new MazeDrawer(this.maze, 8, 2, 200);
		
		//Figure out where the hell the entrance is and put the player there.
		if(maze.getEntrance() != null)
		{
			player = new EntityPlayer(maze, drawer, maze.getEntrance().getRow(), maze.getEntrance().getColumn());
			manager.addEntity(player);
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
}
