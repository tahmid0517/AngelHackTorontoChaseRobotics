package robotics.chase.ui;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application
{
	public void start(Stage window)
	{
		MainWindow.init(window);
		displaySplashScreenAndOpenMainMenu();
	}

	public static void main(String args[])
	{
		launch(args);
	}
	
	public void displaySplashScreenAndOpenMainMenu()
	{
		Stage splashWindow = new Stage();
		splashWindow.initStyle(StageStyle.UNDECORATED);
		ImageView imageView = new ImageView();
		Image logo = new Image("res/logo.png",UI_Constants.SplashScreen.WIDTH,UI_Constants.SplashScreen.HEIGHT,true,true);
		imageView.setImage(logo);
		Group rootNode = new Group();
		Scene scene = new Scene(rootNode);
		splashWindow.setScene(scene);
		rootNode.getChildren().add(imageView);
		splashWindow.show();
		PauseTransition pause = new PauseTransition(Duration.seconds(UI_Constants.SplashScreen.DURATION));
		pause.setOnFinished(e -> 
		{
			splashWindow.close();
			Stage window = MainWindow.get();
			MainMenu.getInstance().setSceneToWindow(window);
		});
		pause.play();
	}
}
