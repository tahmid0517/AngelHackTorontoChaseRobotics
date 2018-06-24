package robotics.chase.util;

import javafx.scene.shape.Line;

public class Vector 
{
	
	//vectorX and vectorY are vectors based on two sets of coordinates
	public double vectorX;
	public double vectorY;
	
	//Constructor that finds value of vector AB (does coordinate B - coordinate A)
	public Vector(Coordinate A, Coordinate B) 
	{
		vectorX = B.getCurrentX() - A.getCurrentX();
		vectorY = B.getCurrentY() - A.getCurrentY(); 
		
	}
	
	public Vector(Line line)
	{
		vectorX = line.getEndX() - line.getStartX();
		vectorY = line.getStartY() - line.getEndY();
	}
	
	public double getVectorX()
	{
		return vectorX;
	}
	
	public double getVectorY() 
	{
		return vectorY;
	}
	
	public void setVector(Coordinate A, Coordinate B) 
	{
		this.vectorX = B.getCurrentY() - A.getCurrentX();
		this.vectorY = B.getCurrentY() - A.getCurrentY(); 
	}
}