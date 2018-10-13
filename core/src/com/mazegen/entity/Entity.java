package com.mazegen.entity;

import com.mazegen.maze.Maze;
import com.mazegen.maze.Tile;

/**
 * The Entity class presents an entity which can exist on
 * a Maze. Each Entity requires a reference to the Maze it
 * exits on. Its location is tracked via {@link Tile} objects.
 * 
 * <p>
 * Every Entity must implement these three abstract methods:
 * <ul>
 * <li>{@link #update(float)}</li>
 * <li>{@link #render(float)}</li>
 * <li>{@link #dispose()}</li>
 * </ul>
 * </p>
 * 
 * @author Stephen Czekalski
 *
 */
public abstract class Entity
{		
	
	/**The Maze object that the Entity exists within.*/
	protected final Maze maze;
	
	/**The Tile that the Entity exists at.*/
	protected Tile tile;
	
	/**
	 * Creates a new Entity object.
	 * @param maze The maze object the Entity exists within.
	 */
	public Entity(final Maze maze)
	{				
		this.maze = maze;
		this.tile = this.maze.getTile(0, 0);
	}
	
	/**
	 * Creates a new Entity object.
	 * @param maze The maze object the Entity exists within.
	 * @param tile The tile object the Entity exists at.
	 */
	public Entity(final Maze maze, Tile tile)
	{
		this.maze = maze;
		this.tile = tile;
	}
	
	/**
	 * Creates a new Entity object.
	 * @param maze The maze object the Entity exists within.
	 * @param row The row position of the Tile the entity exists at.
	 * @param col The column position of the Tile the entity exists at.
	 */
	public Entity(final Maze maze, int row, int col)
	{		
		this.maze = maze;
		this.tile = this.maze.getTile(row, col);
	}
	
	/**Updates the internal logic of the Entity.*/
	abstract public void update(float delta);
	
	/**Renders the Entity on the screen.*/
	abstract public void render(float delta);
	
	/**Disposes of resources allocated by the Entity.*/
	abstract public void dispose();

	/*** @return Gets the Maze the Entity exists within. */
	public Maze getMaze()
	{
		return this.maze;
	}
	
	/*** @return Gets the Tile the Entity exists at.*/
	public Tile getTile()
	{
		return this.tile;
	}
	
	/*** @param tile The Tile the Entity exists at.*/
	public void setTile(Tile tile)
	{
		this.tile = tile;
	}
	
	/**
	 * @param row The row position of the Tile the Entity exists at.
	 * @param col The column  position of the Tile the Entity exists at.
	 */
	public void setTile(int row, int col)
	{
		if(this.maze.isWithinBounds(row, col))
		{
			this.tile = this.maze.getTile(row, col);
		}
	}
	
}
