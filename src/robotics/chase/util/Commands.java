package robotics.chase.util;

public class Commands {
	public static final int DRIVE = 0;
	public static final int TURN = 1;
	public double value;
	public double type;
	
	public Commands() {
		
	}
	public double getValue() {
		return value;
	}
	
	public double getType() {
		return type;
	}
	
	public void setValue(double newValue) {
		value = newValue;
	}
	
	public void setType(double newType) {
		type = newType;
	}
}