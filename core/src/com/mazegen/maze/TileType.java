package com.mazegen.maze;

/**
 * The types of Tiles that a Maze can be composed of.
 */
public enum TileType
{
	SQUARE,
	HEXAGON;
	
	public int getTypeSideNumber()
	{
		switch(this)
		{
			case SQUARE: return 4;
			case HEXAGON: return 6;
			default: return 0;
		}
	}
}
