package Scenes;

import Characters.Enemy;
import Characters.PlayerClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CaveScene extends MyScene{
	
	private Button bLeft, bForwards, bRight;
	private Label lCave;
	private HBox hBox;
	private int correctPath;

	public CaveScene(Stage window, PlayerClass myClass, int correctPath) {
		super(window, myClass);
		this.correctPath = correctPath;
	}

	@Override
	public void loadScene() {
		
		bLeft = new Button("Left");
		bForwards = new Button("Fowards");
		bRight = new Button("Right");
		lCave = new Label(caveString());
		
		hBox = new HBox();
		hBox.getChildren().addAll(bLeft, bForwards, bRight);
		
		setRoot(new BorderPane());
		
		getRoot().setTop(lCave);
		getRoot().setBottom(hBox);
		setScene(new Scene(getRoot(), 950, 600));
		setFormatting("Cave", this);
		getWindow().setScene(getScene());
	}
	
	private String caveString() {
		String s = "You enter a dark cave. Looking back, you can see the light of dusk behind you.";
		s += "\nAhead of you are three paths, to the left, forwards and to the right.";
		s += "\nOne of the three contains treasure, will you be lucky or unlucky?";
				
		return s;
	}

	@Override
	protected void formatControls() {
		setFormat(lCave, "white");
		lCave.setAlignment(Pos.TOP_CENTER);
		BorderPane.setAlignment(lCave, Pos.TOP_CENTER);
		
		setFormat(bLeft);
		setFormat(bRight);
		setFormat(bForwards);
	}

	@Override
	protected void formatContainers() {
		// TODO Auto-generated method stub
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(50);
		getRoot().setPadding(new Insets(20));
	}

	@Override
	protected void buttonAddAction() {
		bLeft.setOnAction(e -> {
			if (correctPath == 1) 
				new TreasureScene(getWindow(), getMyClass()).loadScene();
			else 
				loadNotTreasure();
		});
		bForwards.setOnAction(e -> {
			if (correctPath == 2) 
				new TreasureScene(getWindow(), getMyClass()).loadScene();
			else 
				loadNotTreasure();
		});
		bRight.setOnAction(e -> {
			if (correctPath == 3) 
				new TreasureScene(getWindow(), getMyClass()).loadScene();
			else 
				loadNotTreasure();
		});
	}
	
	private void loadNotTreasure() {
		if ((int) (Math.random() * 2) == 0) {
			getMyClass().getEnemies().add(new Enemy("Spider"));
			getMyClass().getEnemies().add(new Enemy("Spider"));
			getMyClass().getEnemies().add(new Enemy("Spider"));
			getMyClass().getEnemies().add(new Enemy("Spider"));
			getMyClass().getEnemies().add(new Enemy("Spider"));
			getMyClass().getEnemies().add(new Enemy("Brood Mother"));
			new FightScene(getWindow(), getMyClass(), this).loadScene();
		}
		else {
			getMyClass().getEnemies().add(new Enemy("Bat"));
			getMyClass().getEnemies().add(new Enemy("Bat"));
			getMyClass().getEnemies().add(new Enemy("Bat"));
			getMyClass().getEnemies().add(new Enemy("Bat"));
			getMyClass().getEnemies().add(new Enemy("Bat"));
			new FightScene(getWindow(), getMyClass(), this).loadScene();
		}
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
