package com.mazegen.maze;

/**
 * The TileType enum represents the different types of tiles
 * which mazes can be made out of.
 * 
 * @author Stephen Czekalski
 *
 */
public enum TileType
{
	SQUARE,
	HEXAGON;
	
	/**
	 * Gets the number of tile sides corresponding to each enum
	 * @return Number of tile sides.
	 */
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
