package com.mazegen.maze.draw;

import com.badlogic.gdx.graphics.Color;
import com.mazegen.maze.Maze;

public class MazeDrawerSettings
{

	public int wallSize;
	
	public int tileSize;
	
	public int sightRange;
	
	public static Color bgColor;
	
	public Color tileColor;
	
	public Color visitedColor;
	
	public Color wallColor;
	
	public Color entranceColor;
	
	public Color exitColor;
	
	public Color playerColor;
	
	public MazeDrawerSettings()
	{
		this.tileSize = 16;
		this.wallSize = 2;
		this.sightRange = 200;
		
		this.bgColor = new Color(0.99f, 0.99f, 0.99f, 1.0f);
		this.tileColor = new Color(1f, 1f, 1f, 1.0f);
		this.visitedColor = new Color(1f, 1f, 1f, 1.0f);
		this.wallColor =  new Color(0f, 0f, 0f, 1.0f);
		this.entranceColor = new Color(0f, 1f, 0f, 1.0f);
		this.exitColor = new Color(1f, 0f, 0f, 1.0f);
		this.playerColor  = new Color(0f, 0f, 1f, 1.0f);
	}
}
