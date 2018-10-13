package com.mazegen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.mazegen.entity.EntityManager;
import com.mazegen.entity.EntityPlayer;
import com.mazegen.main.Driver;
import com.mazegen.maze.GenerationType;
import com.mazegen.maze.Maze;
import com.mazegen.maze.MazeFactory;
import com.mazegen.maze.TileType;
import com.mazegen.ui.GameUI;
import com.mazegen.util.RenderUtil;

public class GameScreen implements Screen
{	
	private MazeFactory factory;
	
	private Maze maze;	

	private TileType tileType;
	
	private GenerationType genType;
	
	private int mazeHeight;
	
	private int mazeWidth;
	
	private EntityManager manager;
	
	private EntityPlayer player;
	
	private GameUI ui;
	
	public GameScreen()
	{	
		this.initMaze();
		this.initEntities();
		
		this.ui = new GameUI(this);
	}

	@Override
	public void render(float delta)
	{		
		if(maze != null)
		{
			RenderUtil.clearScreen(this.maze.getDrawer().getBgColor());
			RenderUtil.updateCamera();
			
			//Update Entities
			manager.update(delta);
			ui.act(delta);
			
			//Draw Maze and Entities
			maze.render();
			manager.render(delta);
			
			//Draw UI
			ui.getViewport().apply();
			ui.draw();
			
			//Check if player has won
			this.checkAndHandlePlayerWin();
			
			//Regenerate the Maze
			if(Gdx.input.isKeyJustPressed(Keys.R))
			{
				this.createMaze(this.tileType, this.genType, this.mazeWidth, this.mazeHeight);
			}
		}
		else
		{
			this.createMaze(this.tileType, this.genType, this.mazeWidth, this.mazeHeight);
		}
	}
	
	private void initMaze()
	{
		this.factory = new MazeFactory();
		this.maze = null;	
		
		this.tileType = TileType.SQUARE;
		this.genType = GenerationType.RECURSIVE_BACKTRACK;
		this.mazeWidth = 10;
		this.mazeHeight = 10;
	}
	
	private void initEntities()
	{
		this.manager = new EntityManager();
		this.player = null;
	}
	
	public void createMaze(TileType tileType, GenerationType genType, int width, int height)
	{
		if(this.maze != null)
		{
			this.manager.getEntities().remove(this.player);
			this.maze = null;
			this.player = null;
		}
		else
		{
			this.maze = factory.getMaze(tileType, genType, width, height);
			this.player = new EntityPlayer(this.maze, this.maze.getEntrance());
			
			this.manager.addEntity(player);
		}
	}
	
	private void checkAndHandlePlayerWin()
	{
		if(this.player.getTile().getRow() == this.maze.getExit().getRow()
				&& this.player.getTile().getColumn() == this.maze.getExit().getColumn())
		{
			this.createMaze(this.tileType, this.genType, this.mazeWidth, this.mazeHeight);
		}
	}
	
	@Override
	public void resize(int width, int height)
	{
		this.ui.getViewport().update(width, height);
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

	public Maze getMaze()
	{
		return maze;
	}

	public void setMaze(Maze maze)
	{
		this.maze = maze;
	}

	public TileType getTileType()
	{
		return tileType;
	}

	public void setTileType(TileType tileType)
	{
		this.tileType = tileType;
	}

	public GenerationType getGenType()
	{
		return genType;
	}

	public void setGenType(GenerationType genType)
	{
		this.genType = genType;
	}

	public int getMazeHeight()
	{
		return mazeHeight;
	}

	public void setMazeHeight(int mazeHeight)
	{
		this.mazeHeight = mazeHeight;
	}

	public int getMazeWidth()
	{
		return mazeWidth;
	}

	public void setMazeWidth(int mazeWidth)
	{
		this.mazeWidth = mazeWidth;
	}
}
