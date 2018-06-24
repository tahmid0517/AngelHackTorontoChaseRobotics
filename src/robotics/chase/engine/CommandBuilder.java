package robotics.chase.engine;

import java.util.ArrayList;

import javafx.scene.shape.Line;
import robotics.chase.util.Command;
import robotics.chase.util.MathUtils;
import robotics.chase.util.Vector;

public class CommandBuilder 
{
	ArrayList<Command> turns;
	ArrayList<Command> driveStraights;
	public ArrayList<Command> allCommands;
	ArrayList<Vector> vectors;
	public CommandBuilder(ArrayList<Line> lengths)
	{
		turns = new ArrayList<Command>();
		driveStraights = new ArrayList<Command>();
		vectors = new ArrayList<Vector>();
		for(int i = 0;i < lengths.size();i++)
		{
			Vector vector = new Vector(lengths.get(i));
			vectors.add(vector);
			Command drive = new Command();
			drive.setType(Command.DRIVE);
			drive.setValue(MathUtils.calculateVectorAbsoluteLength(vector)/8);
			driveStraights.add(drive);
		}
		for(int i = 0;i < driveStraights.size() - 1;i++)
		{
			Command turn = new Command();
			turn.setType(Command.TURN);
			double firstAngle = MathUtils.calculateVectorAngle(vectors.get(i));
			double secondAngle = MathUtils.calculateVectorAngle(vectors.get(i + 1));
			double turnAngle = firstAngle - secondAngle;
			if(turnAngle > 180)
				turnAngle -= 360;
			if(turnAngle < -180)
				turnAngle += 360;
			turn.setValue(turnAngle);
			turns.add(turn);
		}
		allCommands = new ArrayList<Command>();
		for(int i = 0;i < driveStraights.size() - 1;i++)
		{
			allCommands.add(driveStraights.get(i));
			allCommands.add(turns.get(i));
		}
		allCommands.add(driveStraights.get(driveStraights.size() - 1));
	}
	
	public void printOutAllCommands()
	{
		for(int i = 0;i < allCommands.size();i++)
		{
			if(allCommands.get(i).type == Command.DRIVE)
			{
				System.out.println("DRIVE: " + allCommands.get(i).value);
			}
			else
			{
				System.out.println("TURN: " + allCommands.get(i).value);
			}
		}
	}
}
