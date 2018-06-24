package robotics.chase.ui;

import java.io.File;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import robotics.chase.engine.ClientCommunication;
import robotics.chase.engine.CommandBuilder;
import robotics.chase.util.Command;

public class PathEditor 
{
	private static PathEditor instance;
	public static PathEditor getInstance()
	{
		if(instance == null)
		{
			instance = new PathEditor();
		}
		return instance;
	}
	
	public Pane canvas;
	Button uploadBtn;
	ImageView imageView;
	Pane imagePane;
	Image image;
	double mapWidth,mapHeight,scale;
	public ClientCommunication client;
	public PathEditor()
	{
		canvas = new Pane();
		canvas.setStyle("-fx-border-style:solid;-fx-border-width:1");
		canvas.setBackground(new Background(new BackgroundFill(Color.web(UI_Constants.PathEditor.BACKGROUND_COLOUR),null,null)));
		canvas.setPrefSize(UI_Constants.PathEditor.WIDTH, UI_Constants.PathEditor.HEIGHT);
		canvas.setLayoutX(UI_Constants.MainMenu.ROBOT_BAR_WIDTH);
		addUploadButton();
	}
	
	public void addUploadButton()
	{
		uploadBtn = new Button();
		uploadBtn.setText("UPLOAD MAP");
		double uploadBtnWidth = 160;
		double uploadBtnHeight = 30;
		uploadBtn.setPrefSize(uploadBtnWidth,uploadBtnHeight);
		uploadBtn.setLayoutX(UI_Constants.PathEditor.WIDTH / 2 - (uploadBtnWidth / 2));
		uploadBtn.setLayoutY(UI_Constants.PathEditor.HEIGHT / 2 - (uploadBtnHeight / 2));
		uploadBtn.setOnAction(e -> uploadImage());
		canvas.getChildren().add(uploadBtn);
	}
	
	public void uploadImage()
	{
		canvas.getChildren().remove(uploadBtn);
		File file = getFile();
		image = new Image(file.toURI().toString(),900,900,true,true);
		imageView = new ImageView();
		imageView.setImage(image);
		imagePane = new Pane();
		imagePane.setPrefSize(image.getWidth(),image.getHeight());
		imagePane.getChildren().add(imageView);
		canvas.getChildren().add(imagePane);
		imagePane.setLayoutX(UI_Constants.PathEditor.WIDTH / 2 - (image.getWidth() / 2));
		imagePane.setLayoutY(UI_Constants.PathEditor.HEIGHT / 2 - (image.getHeight() / 2));
		addDrawFunctionality();
	}
	
	public File getFile()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Pick an image to use as a map");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*jpeg","*png","*jpg","*bmp"));
		File file = fileChooser.showOpenDialog(new Stage());
		return file;
	}
	
	public void addNode(MouseEvent event)
	{
		Circle node = new Circle();
		node.setLayoutX(event.getX());
		node.setLayoutY(event.getY());
		node.setRadius(10);
		node.setFill(Color.web("aa2b1d"));
		nodes.add(node);
		imagePane.getChildren().add(node);
		if(nodes.size() > 1)
		{
			drawLine(node,nodes.get(nodes.size() - 2));
		}
	}
	
	public void drawLine(Circle currentNode,Circle prevNode)
	{
		Line line = new Line();
		line.setStartX(prevNode.getLayoutX());
		line.setEndX(currentNode.getLayoutX());
		line.setStartY(prevNode.getLayoutY());
		line.setEndY(currentNode.getLayoutY());
		line.setStroke(Color.web("e83C29"));
		line.setStrokeWidth(5);
		imagePane.getChildren().add(line);
		lines.add(line);
		currentNode.toFront();
		prevNode.toFront();
	}
	
	public void deleteLastNode()
	{
		int numberOfNodes = nodes.size();
		System.out.println(numberOfNodes);
		if(numberOfNodes == 0)
			return;
		imagePane.getChildren().remove(nodes.get(numberOfNodes - 1));
		nodes.remove(numberOfNodes - 1);
		if(numberOfNodes > 1)
		{
			imagePane.getChildren().remove(lines.get(lines.size() - 1));
			lines.remove(lines.size() - 1);
		}
	}
	
	public void clearMap()
	{
		for(int i = 0;i < nodes.size();i++)
		{
			imagePane.getChildren().remove(nodes.get(i));
		}
		for(int i = 0;i < lines.size();i++)
		{
			imagePane.getChildren().remove(lines.get(i));
		}
		nodes = new ArrayList<Circle>();
		lines = new ArrayList<Line>();
		completedNodes = 0;
		completedLines = 0;
	}
	
	public void playPath()
	{
		CommandBuilder builder = new CommandBuilder(lines);
		String ip_address = "192.168.2.21";
		int port = 6852;
		if(client == null)
			client = new ClientCommunication(ip_address,port);
		setNodeCompleted();
		for(int i = 0;i < builder.allCommands.size();i++)
		{
			String mssg = "";
			double commandType = builder.allCommands.get(i).type;
			if(commandType == Command.DRIVE)
			{
				mssg += "D";
			}
			else if(commandType == Command.TURN)
			{
				mssg += "T";
			}
			int value = (int)builder.allCommands.get(i).value;
			mssg += String.valueOf(value);
			client.writeMessage(mssg);
			String response = client.waitForMessage();
			if(response.equals("DONE"))
			{
				if(commandType == Command.DRIVE)
					setLineCompleted();
				else if(commandType == Command.TURN)
					setNodeCompleted();
			}
		}
		setNodeCompleted();
	}
	
	ArrayList<Circle> nodes;
	ArrayList<Line> lines;
	int completedNodes;
	int completedLines;
	public void addDrawFunctionality()
	{
		nodes = new ArrayList<Circle>();
		lines = new ArrayList<Line>();
		completedNodes = 0;
		completedLines = 0;
		imagePane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				addNode(event);
			}
		});
	}
	
	public void setNodeCompleted()
	{
		int index = completedNodes;
		nodes.get(index).setFill(Color.web(UI_Constants.PathEditor.COMPLETED_COLOUR));
		completedNodes++;
	}
	
	public void setLineCompleted()
	{
		int index = completedLines;
		lines.get(index).setStroke(Color.web(UI_Constants.PathEditor.COMPLETED_COLOUR));
		completedLines++;
	}
}
