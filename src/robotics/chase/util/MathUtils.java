package robotics.chase.util;
import java.lang.Math;

public class MathUtils
{
	public static double vectorAbsoluteLength; 
	public static double vectorAngle;
	
	public MathUtils() {
		
	}
	
	//Calculate the absolute length of the vector 
	public static double calculateVectorAbsoluteLength(Vector vector) 
	{
		vectorAbsoluteLength =  Math.sqrt(Math.pow(vector.getVectorX(), 2) + Math.pow(vector.getVectorY(), 2));
		return vectorAbsoluteLength;
	}
	
	//Calculate the angle of the vector and which quadrant it belongs to
	public static double calculateVectorAngle(Vector vector)
	{
		vectorAngle = Math.atan(vector.getVectorY() / vector.getVectorX());
		vectorAngle = Math.toDegrees(vectorAngle);
		if (vector.getVectorX() > 0 && vector.getVectorY() > 0) 
		{
			return vectorAngle; // For 0-90 degrees
		}
		else if (vector.getVectorX() < 0 && vector.getVectorY() > 0)
		{
			return 180 + vectorAngle; 
			// For 90-180 degrees, add vectorAngle because vectorAngle is a negative angle
		}
		else if (vector.getVectorX() < 0 && vector.getVectorY() < 0) 
		{
			return 180 + vectorAngle; // For 180-270 degrees
		}
		else if (vector.getVectorX() > 0 && vector.getVectorY() < 0) 
		{
			return 360 + vectorAngle;
			//For 270-360 degrees, add vectorAngle because vectorAngle is a negative angle
		}
		return -1;
	}	
}