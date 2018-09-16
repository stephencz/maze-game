package com.mazegen.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mazegen.main.Driver;

public class RenderUtil
{
	
	public static void clearScreen(float r, float g, float b, float a)
	{
		Gdx.gl.glClearColor(r, g, b, a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public static void updateCamera()
	{
		Driver.shape.setProjectionMatrix(Driver.camera.combined);
		Driver.camera.update();
	}
	
}
