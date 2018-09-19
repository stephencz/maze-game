package com.mazegen.gen;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.mazegen.maze.HexagonMaze;
import com.mazegen.maze.Maze;
import com.mazegen.maze.SquareMaze;
import com.mazegen.maze.Tile;

public class MazeFactory
{
	public SquareMaze generateSquareRecursiveBacktrackMaze(int width, int height)
	{
		SquareMaze maze = new SquareMaze(width, height);
		return recursiveBacktrackAlgorithm(maze);
	}
	
	public HexagonMaze generateHexagonRecursiveBacktrackMaze(int width, int height)
	{
		HexagonMaze maze = new HexagonMaze(width, height);
		return recursiveBacktrackAlgorithm(maze);
	}
	
	private <T extends Maze> T recursiveBacktrackAlgorithm(T maze)
	{
		//Create random generator.
		Random random = new Random();
		
		//Create a Stack of Tile objects.
		Stack<Tile> stack = new Stack<Tile>();

		//Get Random Tile and set to Visited
		int row = random.nextInt(maze.getRows());
		int col = random.nextInt(maze.getColumns());
		
		Tile active = maze.getTile(row, col);
		active.setVisited(true);
				
		//Calculate the number of remaining unvisited Tiles
		int remaining = maze.getRows() * maze.getColumns() - 1;
		
		while(remaining > 0)
		{
			ArrayList<Tile> unvisited = maze.getUnvisitedNeighbors(active);
			if(!unvisited.isEmpty())
			{
				//Choose a random neighbor
				Tile target = unvisited.get(random.nextInt(unvisited.size()));
				
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
	
	public SquareMaze generateSquareAldousBroderMaze(int width, int height)
	{
		SquareMaze maze = new SquareMaze(width, height);
		return aldousBroderAlgorithm(maze);
	}
	
	public HexagonMaze generateHexagonAldousBroderMaze(int width, int height)
	{
		HexagonMaze maze = new HexagonMaze(width, height);
		return aldousBroderAlgorithm(maze);
	}
	
	private <T extends Maze> T aldousBroderAlgorithm(T maze)
	{
		Random random = new Random();
		
		//Get Random Tile and set to Visited
		int row = random.nextInt(maze.getRows());
		int col = random.nextInt(maze.getColumns());
		
		Tile active = maze.getTile(row, col);
		active.setVisited(true);
				
		//Calculate the number of remaining unvisited Tiles
		int remaining = maze.getRows() * maze.getColumns() - 1;
		
		//Loop until every Tile has been visited.
		while(remaining > 0)
		{
			//Get a random neighbor of the active Tile.
			Tile target = maze.getRandomNeighbor(random, active);
			
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
