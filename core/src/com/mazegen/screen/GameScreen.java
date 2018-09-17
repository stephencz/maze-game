package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.mazegen.main.Driver;
import com.mazegen.maze.GenerationType;
import com.mazegen.maze.HexagonMaze;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeFactory;
import com.mazegen.maze.TileType;
import com.mazegen.maze.draw.HexagonMazeDrawer;
import com.mazegen.maze.draw.MazeDrawer;
import com.mazegen.maze.draw.SquareMazeDrawer;
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
				
		Driver.camera.zoom = 1.5f;
	}
	
	private void createMazeAndDrawer()
	{
		if(tileType == TileType.TRIANGLE)
		{
			
		}
		else if(tileType == TileType.SQUARE)
		{
			switch(genType)
			{
			case ALDOUS_BRODER: this.maze = factory.generateSquareAldousBroderMaze(this.width, this.height); break;
			case RECURSIVE_BACKTRACK: this.maze = factory.generateSquareRecursiveBacktrackMaze(this.width, this.height); break;
			default: this.maze = factory.generateSquareRecursiveBacktrackMaze(this.width, this.height); break;
			}
			this.drawer = new SquareMazeDrawer(this.maze);
		}
		else
		{
			switch(genType)
			{
			case ALDOUS_BRODER: this.maze = factory.generateHexagonAldousBroderMaze(this.width, this.height); break;
			case RECURSIVE_BACKTRACK: this.maze = new HexagonMaze(width, height); break;
			default: this.maze = factory.generateHexagonRecursiveBacktrackMaze(this.width, this.height); break;
			}
			this.drawer = new HexagonMazeDrawer(this.maze);
		}
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
				this.createMazeAndDrawer();
			}
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
