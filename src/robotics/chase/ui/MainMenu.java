package robotics.chase.ui;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu 
{
	private static MainMenu instance;
	public static MainMenu getInstance()
	{
		if(instance == null)
		{
			instance = new MainMenu();
		}
		return instance;
	}
	
	Scene scene;
	Group rootNode;
	public void initScene()
	{
		rootNode = new Group();
		scene = new Scene(rootNode,UI_Constants.MainMenu.WINDOW_WIDTH,UI_Constants.MainMenu.WINDOW_HEIGHT);
	}
	
	VBox robotBar;
	ScrollPane scroller;
	Hyperlink realLink;
	public void createRobotBar()
	{
		Font linkFont = new Font(20);
		robotBar = new VBox();
		robotBar.setPrefSize(UI_Constants.MainMenu.ROBOT_BAR_WIDTH,UI_Constants.MainMenu.ROBOT_BAR_HEIGHT);
		robotBar.setBackground(new Background(new BackgroundFill(Color.web(UI_Constants.BLUISH_GREY),null,null)));
		scroller = new ScrollPane(robotBar);
		scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		rootNode.getChildren().add(scroller);
		realLink = new Hyperlink();
		realLink.setText(UI_Constants.ROBOT_NAME);
		realLink.setTextFill(Color.BLACK);
		realLink.setFont(linkFont);
		realLink.setBorder(Border.EMPTY);
		realLink.setOnAction(e -> openRobot());
		robotBar.getChildren().add(realLink);
		for(int i = 1;i <= 25;i++)
		{
			Hyperlink fakeLink = new Hyperlink();
			fakeLink.setText("DemoBot " + i);
			fakeLink.setFont(linkFont);
			fakeLink.setBorder(Border.EMPTY);
			fakeLink.setTextFill(Color.BLACK);
			robotBar.getChildren().add(fakeLink);
		}
	}
	
	VBox addBotAndHelpButtonBar;
	Button addRobot,help;
	public void addHelpAndAddRobotBtns()
	{
		addBotAndHelpButtonBar = new VBox();
		addBotAndHelpButtonBar.setBackground(new Background(new BackgroundFill(Color.web(UI_Constants.BLUISH_GREY),null,null)));
		addBotAndHelpButtonBar.setPadding(new Insets(UI_Constants.AddBotAndHelp.PADDING_TOP,UI_Constants.AddBotAndHelp.PADDING_RIGHT,UI_Constants.AddBotAndHelp.PADDING_BOTTOM,UI_Constants.AddBotAndHelp.PADDING_LEFT));
		addBotAndHelpButtonBar.setSpacing(20);
		addBotAndHelpButtonBar.setStyle("-fx-border-style:solid;-fx-border-width:1;-fx-border-color:grey");
		Hyperlink addRobot = new Hyperlink();
		addRobot.setText("+ Add Robot");
		addRobot.setTextFill(Color.BLACK);
		addRobot.setPadding(new Insets(0,0,0,8));
		addRobot.setFont(new Font(30));
		help = new Button();
		help.setText("HELP");
		help.setPrefSize(UI_Constants.AddBotAndHelp.BUTTON_WIDTH,30);
		addBotAndHelpButtonBar.getChildren().addAll(addRobot,help);
		addBotAndHelpButtonBar.setLayoutX(0);
		addBotAndHelpButtonBar.setLayoutY(UI_Constants.MainMenu.ROBOT_BAR_HEIGHT);
		rootNode.getChildren().add(addBotAndHelpButtonBar);
	}

	public void openRobot()
	{
		addPathEditor();
		addControlButtonBar();
	}
	
	public void addPathEditor()
	{
		rootNode.getChildren().add(PathEditor.getInstance().canvas);
	}
	
	public void addControlButtonBar()
	{
		rootNode.getChildren().add(ControlButtonBar.getInstance().canvas);
		ControlButtonBar.getInstance().canvas.toFront();
	}
	
	public void setSceneToWindow(Stage window)
	{
		if(scene == null)
		{
			initScene();
		}
		window.setScene(scene);
		createRobotBar();
		addHelpAndAddRobotBtns();
		window.show();
	}
}
