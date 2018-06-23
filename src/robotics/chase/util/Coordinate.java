package robotics.chase.util;
public class Coordinate {
	
	//currentX and currentY are robot coordinates
	public double currentX; 
	public double currentY;
	
	public Coordinate(double currentX, double currentY) 
	{ 
		this.currentX = currentX;
		this.currentY = currentY; 
	}
	
	public void setCoordinates(double newCurrentX, double newCurrentY) 
	{
		this.currentX = newCurrentX; 
		this.currentY = newCurrentY; 
	}
	
	public double getCurrentX()
	{
		return this.currentX;
	}

	public double getCurrentY() 
	{
		return this.currentY; 
	}
}