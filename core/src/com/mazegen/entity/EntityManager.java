package com.mazegen.entity;

import java.util.ArrayList;

/**
 * The EntityManager class manages game entities by providing methods
 * for perform general entity tasks. These tasks including storing,
 * adding, and removing entities, and calling entity methods which
 * perform important functions such as updating logic, rendering the
 * entity on the screen, and disposing of resources allocated by the
 * entity.
 * 
 * @author Stephen Czekalski
 *
 */
public class EntityManager
{

	/**An ArrayList of Entity objects used to keep track of entities in the game.*/
	private ArrayList<Entity> entities;
	
	/**Creates a new EntityManager object.*/
	public EntityManager()
	{
		this.entities = new ArrayList<Entity>();
	}

	/**
	 * Updates every Entity stored in the manager.
	 * @param delta 
	 */
	public void update(float delta)
	{
		for(Entity entity : this.entities)
		{
			entity.update(delta);
		}	
	}
	
	/**
	 * Renders every Entity stored in the manager.
	 * @param delta
	 */
	public void render(float delta)
	{
		for(Entity entity : this.entities)
		{
			entity.render(delta);
		}	
	}
	
	/**Disposes of every Entity in the manager.*/
	public void dispose()
	{
		for(Entity entity : this.entities)
		{
			entity.dispose();
			this.entities.remove(entity);
		}
	}
	
	/*** @return An ArrayList of Entity objects.*/
	public ArrayList<Entity> getEntities()
	{
		return this.entities;
	}
	
	/** @param entities An ArrayList of Entity objects.*/
	public void setEntity(ArrayList<Entity> entities)
	{
		this.entities = entities;
	}
	
	/**
	 * @param index The index of the Entity to retrieve from the manager.
	 * @return The Entity at the passed in index position.
	 */
	public Entity getEntity(int index)
	{
		return this.entities.get(index);
	}
	
	/*** @param entity The entity to add to the manager.*/
	public void addEntity(Entity entity)
	{
		this.entities.add(entity);
	}	
	
	/*** @param index Removes the Entity corresponding to the passed in index value.*/
	public void removeEntity(int index)
	{
		this.entities.remove(index);
	}
	
	/*** @param entity Removes the passed in Entity object from the manager.*/
	public void removeEntity(Entity entity)
	{
		this.entities.remove(entity);
	}
	
}
