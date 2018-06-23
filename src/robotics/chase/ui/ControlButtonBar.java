package robotics.chase.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class ControlButtonBar 
{
	private static ControlButtonBar instance;
	public static ControlButtonBar getInstance()
	{
		if(instance == null)
		{
			instance = new ControlButtonBar();
		}
		return instance;
	}
	
	public HBox canvas;
	public ControlButtonBar()
	{
		canvas = new HBox();
		canvas.setPrefSize(UI_Constants.PathEditor.WIDTH,UI_Constants.AddBotAndHelp.HEIGHT);
		canvas.setLayoutX(UI_Constants.MainMenu.ROBOT_BAR_WIDTH);
		canvas.setLayoutY(UI_Constants.MainMenu.ROBOT_BAR_HEIGHT);
		canvas.setBackground(new Background(new BackgroundFill(Color.web(UI_Constants.ControlButtonBar.BACKGROUND_COLOUR),null,null)));
		canvas.setPadding(new Insets(UI_Constants.ControlButtonBar.PADDING_TOP,UI_Constants.ControlButtonBar.PADDING_RIGHT,UI_Constants.ControlButtonBar.PADDING_BOTTOM,UI_Constants.ControlButtonBar.PADDING_LEFT));
		canvas.setSpacing(30);
		addButtons();
		addButtonMethods();
	}
	
	Button save,undo,clear,play;
	public void addButtons()
	{
		save = new Button("",new ImageView(new Image("res/download.png",60,60,true,true)));
		save.setStyle("-fx-background-radius: 16.4, 15;");
		undo = new Button("",new ImageView(new Image("res/undo.png",60,60,true,true)));
		undo.setStyle("-fx-background-radius: 16.4, 15;");
		clear = new Button("",new ImageView(new Image("res/garbage.png",60,60,true,true)));
		clear.setStyle("-fx-background-radius: 16.4, 15;");
		play = new Button("",new ImageView(new Image("res/play.png",60,60,true,true)));
		play.setStyle("-fx-background-radius: 16.4, 15;");
		canvas.getChildren().addAll(save,undo,clear,play);
	}
	
	public void addButtonMethods()
	{
		undo.setOnAction(e -> PathEditor.getInstance().deleteLastNode());
		clear.setOnAction(e -> PathEditor.getInstance().clearMap());
	}
}
