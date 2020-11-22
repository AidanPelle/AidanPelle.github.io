package Scenes;

import java.io.File;

import Characters.PlayerClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OutpostScene extends MyScene{
	
	private Button bBank, bInn, bTravel;
	private Label lOutpost;
	private HBox hBox;
	private MediaPlayer background;

	public OutpostScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}

	@Override
	public void loadScene() {
		background = new MediaPlayer(new Media(new File("src/AudioFiles/Town.wav").toURI().toString()));
		background.play();
		
		lOutpost = new Label(loadLabel());
		bBank = new Button("Bank");
		bTravel = new Button("Travel");
		bInn = new Button("Inn");
		
		hBox = new HBox();
		hBox.getChildren().addAll(bBank, bTravel, bInn);
		
		setRoot(new BorderPane());
		getRoot().setBottom(hBox);
		getRoot().setTop(lOutpost);
		setScene(new Scene(getRoot(), 1000, 450));
		setFormatting("Outpost", this);
		getWindow().setScene(getScene());
	}
	
	private String loadLabel() {
		String s = "You find yourself in a small outpost on the outskirts of civilization, where the only establishments" 
				+ "\nstill open seem to be the bank and the inn.";
		s += " You quench your thirst at a nearby well";
		
		getMyClass().setCurrentWater(getMyClass().getMaxWater());
		
		if (getMyClass().getInventory().findItem("Water Bottle - Empty") != null) {
			getMyClass().getInventory().findItem("Water Bottle - Empty").setName("Water Bottle - Full");
		}
		if (getMyClass().getInventory().findItem("Canteen - Empty") != null) {
			getMyClass().getInventory().findItem("Canteen - Empty").setName("Canteen - Full");
		}
		
		return s;
	}

	@Override
	protected void formatControls() {
		setFormat(bBank);
		setFormat(bInn);
		setFormat(bTravel);
		
		lOutpost.setAlignment(Pos.TOP_CENTER);
		lOutpost.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
		lOutpost.setStyle("-fx-text-fill: black;");
		BorderPane.setAlignment(lOutpost, Pos.TOP_CENTER);
	}

	@Override
	protected void formatContainers() {
		hBox.setSpacing(50);
		hBox.setAlignment(Pos.CENTER);
		
		getRoot().setPadding(new Insets(20));
	}

	@Override
	protected void buttonAddAction() {
		bInn.setOnAction(e -> {
			background.stop();
			new InnScene(getWindow(), getMyClass(), true).loadScene();
		});
		bBank.setOnAction(e -> {
			background.stop();
			new BankScene(getWindow(), getMyClass(), true).loadScene();
		});
		bTravel.setOnAction(e -> {
			background.stop();
			new TravellingMenuScene(getWindow(), getMyClass()).loadScene();
		});
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
