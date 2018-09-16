package com.mazegen.maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeFactory
{

	public static Maze generateDepthFirstSearchMaze(int width, int height)
	{
		Maze maze = new Maze(width, height);
		
		return maze;
	}
	
	public static Maze generateRandomizedPrimMaze(int width, int height)
	{
		//Create maze and wall list
		Maze maze = new Maze(width, height);
		
		
		return maze;
	}
	
	public static Maze generateRecursiveBacktrackMaze(int width, int height)
	{
		//Create Maze with all walls filled in
		Maze maze = new Maze(width, height);
		Stack<TileWalled> stack = new Stack<TileWalled>();
		Random random = new Random();
		
		//Get Random Tile and set to Visited
		int row = random.nextInt(maze.getRows());
		int col = random.nextInt(maze.getColumns());
		
		TileWalled active = (TileWalled) maze.getTile(row, col);
		active.setVisited(true);
				
		//Calculate the number of remaining unvisited Tiles
		int remaining = maze.getRows() * maze.getColumns() - 1;
		
		while(remaining > 0)
		{
			ArrayList<Tile> unvisited = maze.getUnvisitedNeighbors(active);
			if(!unvisited.isEmpty())
			{
				//Choose a random neighbor
				TileWalled target = (TileWalled) unvisited.get(random.nextInt(unvisited.size()));
				
				//Push current cell to stack
				stack.push(active);
				
				//Move wall between current and random and set target to visited.
				maze.carvePath(active, target);
				target.setVisited(true);
								
				//Make random cell current
				active = target;
				
				remaining--;
			}
			else
			{
				active = stack.pop();
			}
		}
		
		//Add entrance and exit.
		maze.setEntranceAndExit(random);
		
		return maze;
	}
	
	public static Maze generateAldousBroderMaze(int width, int height)
	{
		//Create Maze with all walls filled in
		Maze maze = new Maze(width, height);
		Random random = new Random();
		
		//Get Random Tile and set to Visited
		int row = random.nextInt(maze.getRows());
		int col = random.nextInt(maze.getColumns());
		
		TileWalled active = (TileWalled) maze.getTile(row, col);
		active.setVisited(true);
				
		//Calculate the number of remaining unvisited Tiles
		int remaining = maze.getRows() * maze.getColumns() - 1;
		
		//Loop until every Tile has been visited.
		while(remaining > 0)
		{
			//Get a random neighbor of the active Tile.
			TileWalled target = (TileWalled) maze.getRandomNeighbor(random, active);
			
			//If that Tile has not been visited, carve a path between
			//the active and the target. Set the target to visited
			if(!target.isVisited())
			{
				maze.carvePath(active, target);
				target.setVisited(true);
				remaining -= 1;
			}
			
			//Update active Tile
			active = target;
		}
				
		//Add entrance and exit.
		maze.setEntranceAndExit(random);
				
		return maze;
	}
	
}
