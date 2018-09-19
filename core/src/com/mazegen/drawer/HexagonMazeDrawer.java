package com.mazegen.drawer;

import com.mazegen.maze.Maze;

public class HexagonMazeDrawer extends MazeDrawer
{

	public HexagonMazeDrawer(Maze maze)
	{
		super(maze);
	}

	@Override
	protected void draw(int i, int j)
	{
		
	}

	@Override
	protected boolean isOnScreen(int i, int j)
	{
		return false;
	}

}
