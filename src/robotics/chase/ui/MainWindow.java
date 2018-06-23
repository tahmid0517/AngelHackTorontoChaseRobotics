package robotics.chase.ui;

import javafx.stage.Stage;

public class MainWindow 
{
	private static Stage window;
	public static void init(Stage w)
	{
		window = w;
		window.setResizable(false);
		window.setTitle(UI_Constants.APP_TITLE);
	}
	
	public static Stage get()
	{
		return window;
	}
}
