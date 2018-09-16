package com.mazegen.screen;

import com.badlogic.gdx.Screen;
import com.mazegen.main.Driver;
import com.mazegen.util.RenderUtil;

public class MainMenuScreen implements Screen
{
	public MainMenuScreen()
	{
		
	}
	
	@Override
	public void render(float delta)
	{
		RenderUtil.clearScreen(1f, 1f, 1f, 1f);
		RenderUtil.updateCamera();
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
	public void hide()
	{
		
	}
	
	@Override
	public void show()
	{
		
	}
}
