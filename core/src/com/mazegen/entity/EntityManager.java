package com.mazegen.entity;

import java.util.ArrayList;

public class EntityManager
{

	private ArrayList<Entity> entities;
	
	public EntityManager()
	{
		this.entities = new ArrayList<Entity>();
	}
	
	public void render(float delta)
	{
		for(Entity entity : this.entities)
		{
			entity.render(delta);
		}	
	}
	
	public void dispose()
	{
		for(Entity entity : this.entities)
		{
			entity.dispose();
		}
	}
	
	public ArrayList<Entity> getEntities()
	{
		return this.entities;
	}
	
	public void setEntity(ArrayList<Entity> entities)
	{
		this.entities = entities;
	}
	
	public Entity getEntity(int index)
	{
		return this.entities.get(index);
	}
	
	public void addEntity(Entity entity)
	{
		this.entities.add(entity);
	}	
}
