package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Random;

public class HexagonMaze extends Maze
{

	public HexagonMaze(int rows, int columns)
	{
		super(rows, columns, 6);
	}

	@Override
	public void carvePath(Tile origin, Tile target)
	{
		if((origin.getColumn() + 1) % 2 == 0) // Even
		{
			
		}
		else // Odd
		{
			
		}
		
		//Carving North
		if(origin.getColumn() > target.getColumn() && origin.getRow() == target.getRow())
		{
			
		}
		
		//Carving South
		if(origin.getColumn() < target.getColumn() && origin.getRow() == target.getRow())
		{
			
		}
	}

	@Override
	public void setEntranceAndExit(Random random)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tile getRandomNeighbor(Random random, Tile tile)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> getNeighbors(Tile tile)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
