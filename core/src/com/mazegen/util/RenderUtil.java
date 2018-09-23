package com.mazegen.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
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
		Driver.camera.update(true);
		Driver.batch.setProjectionMatrix(Driver.camera.combined);
		Driver.shape.setProjectionMatrix(Driver.camera.combined);
	}
}
