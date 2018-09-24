package com.mazegen.entity;

public abstract class Entity
{	
	protected float x;
	
	protected float y;
	
	public Entity()
	{		
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	public Entity(float x, float y)
	{		
		this.x = x;
		this.y = y;
	}
	
	abstract public void render(float delta);
	
	abstract public void dispose();

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
}
