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
		window.setOnCloseRequest(e -> PathEditor.getInstance().client.closeSocket());
	}
	
	public static Stage get()
	{
		return window;
	}
}
