package com.mazegen.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mazegen.gen.GenerationType;
import com.mazegen.maze.TileType;
import com.mazegen.screen.GameScreen;

public class Driver extends Game 
{		
	public static SpriteBatch batch;
	
	public static ShapeRenderer shape;
	
	public static OrthographicCamera camera;
	
	public static FillViewport viewport;
			
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT);
		viewport = new FillViewport(GameInfo.WORLD_WIDTH, GameInfo.WORLD_HEIGHT, camera);
		
		this.setScreen(new GameScreen(TileType.SQUARE, GenerationType.RECURSIVE_BACKTRACK, 500, 500));
	}

	@Override
	public void render () 
	{
		super.render();
	}
	
	@Override
	public void resize(int width, int height) 
	{
		super.resize(width, height);
		
	}

	@Override
	public void dispose () 
	{
		shape.dispose();
	}
}
