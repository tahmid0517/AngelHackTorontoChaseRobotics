package robotics.chase.util;

public class Command {
	public static final int DRIVE = 0;
	public static final int TURN = 1;
	public double value;
	public double type;
	
	public Command() {
		
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