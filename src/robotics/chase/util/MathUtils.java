package robotics.chase.util;
import java.lang.Math;

public class MathUtils
{	
	//Calculate the absolute length of the vector 
	public static double calculateVectorAbsoluteLength(Vector vector) 
	{
		double vectorAbsoluteLength =  Math.sqrt(Math.pow(vector.getVectorX(), 2) + Math.pow(vector.getVectorY(), 2));
		return vectorAbsoluteLength;
	}
	
	//Calculate the angle of the vector and which quadrant it belongs to
	public static double calculateVectorAngle(Vector vector)
	{
		double vectorAngle = Math.atan(vector.getVectorY() / vector.getVectorX());
		vectorAngle = Math.toDegrees(vectorAngle);
		if (vector.getVectorX() > 0 && vector.getVectorY() > 0) 
		{
			return vectorAngle; // For 0-90 degrees
		}
		else if (vector.getVectorX() < 0 && vector.getVectorY() > 0)
		{
			return vectorAngle + 180; 
			// For 90-180 degrees, add vectorAngle because vectorAngle is a negative angle
		}
		else if (vector.getVectorX() < 0 && vector.getVectorY() < 0) 
		{
			return vectorAngle - 180; // For 180-270 degrees
		}
		else if (vector.getVectorX() > 0 && vector.getVectorY() < 0) 
		{
			return -vectorAngle;
			//For 270-360 degrees, add vectorAngle because vectorAngle is a negative angle
		}
		return -1;
	}	
}