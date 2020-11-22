package Scenes;

import Characters.PlayerClass;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BeginGameScene extends MyScene{
	
	private Label lStory;
	private Button bNext, bPrevious, bContinueGame;
	private int currentPage;
	private GridPane gPane;
	private String[] sPage;
	
	public BeginGameScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}
	
	@Override
	public void loadScene() {
		currentPage = 1;
		populatePaiges();
		playSound("Rolling Cart");
		lStory = new Label(sPage[currentPage - 1]);
		bNext = new Button("Next");
		bPrevious = new Button("Previous");
		bContinueGame = new Button("Continue Journey");
		
		gPane = new GridPane();
		gPane.add(lStory, 0, 0, 2, 1);
		gPane.add(bPrevious, 0, 1);
		gPane.add(bNext, 1, 1);
		gPane.add(bContinueGame, 0, 2, 2, 1);
		
		setRoot(new BorderPane());
		getRoot().setLeft(gPane);
		
		setScene(new Scene(getRoot(), 1000, 550));
		setFormatting("BeginGame", null);
		getWindow().setScene(getScene());
	}
	
	private void populatePaiges() {
		sPage = new String[]{
				("Ralof: Hey, you. You're finally awake. You were trying to cross the border, right? Walked right into that Imperial ambush, same as us, and that thief over there. "
						+ "\nLokir: Damn you Stormcloaks. Skyrim was fine until you came along. Empire was nice and lazy. "
						+ "\nIf they hadn't been looking for you, I could've stolen that horse and been half way to Hammerfell. "
						+ "\nYou there. You and me -- we should be here. It's these Stormcloaks the Empire wants. "
						+ "\nRalof: We're all brothers and sisters in binds now, thief."),
				("Soldier: Shut up back there! [Lokir looks at the gagged man.] Lokir: And what's wrong with him? "
						+ "\nRalof: Watch your tongue! You're speaking to Ulfric Stormcloak, the true High King. "
						+ "\nRalof: I don't know where we're going, but Sovngarde awaits. "
						+ "\nLokir: No, this can't be happening. This isn't happening. "
						+ "\nRalof: Hey, what village are you from, horse thief? "
						+ "\nRalof: A Nord's last thoughts should be of home."),
				("[A Soldier calls out from the lead wagon]"
						+ "\nImperial Soldier: Let the thief go! Were not here for petty criminals, and we need space for the rebels"
						+ "\nLokir: Lucky bastard."
						+ "\nOne of the soliders uncuffs you, and shoves you off the wagon with only the clothes on your back."
						+ "\nAs the line of wagons rolls off into the distance, you come about and collect yourself. All you have are your muddy clothes and the 20 gold you hid."
						+ "\nYou start heading towards the nearby town, hoping to score some food and gear."),
				("Heya babe! Welcome to the second iteration of your video game! Hopefully with some more experience, I can make this an actually solid game!"
						+ "\nJust so you know, you'll consume 1 piece of food and 1 water for every space you travel on the map. Keep track of your supplies!"
						+ "\nAlso, max of 20 skillpoints in any category. I'll try to make this game easier, so that it's more fun on the first few playthroughs, and then change stats accordingly."
						+ "\nLet me know if there's anything I missed! Enjoy!")
		};
	}

	@Override
	protected void buttonAddAction() {
		bNext.setOnAction(e -> {
			if (currentPage != 4) {
				lStory.setText(sPage[++currentPage - 1]);
				playSound("Page Flip");
			}
			});
		
		bPrevious.setOnAction(e -> {
			if (currentPage != 1) {
				lStory.setText(sPage[--currentPage - 1]);
				playSound("Page Flip");
			}
			});
		
		bContinueGame.setOnAction(e -> {
			new TownScene(getWindow(), getMyClass()).loadScene();
		});
		getRoot().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				getRoot().setOnKeyPressed(i -> {
					if (i.getCode() == KeyCode.ESCAPE) {
						System.exit(0);
					}
				});
			}
		});
	}

	@Override
	protected void formatControls() {

		setFormat(lStory);
		setFormat(bNext);
		setFormat(bPrevious);
		setFormat(bContinueGame);
		
		lStory.setPrefWidth(350);
		lStory.setStyle("-fx-text-fill: white");
		lStory.setWrapText(true);
		lStory.setPrefHeight(400);
		bContinueGame.setPrefWidth(200);
		
		GridPane.setHalignment(bContinueGame, HPos.CENTER);
		GridPane.setHalignment(lStory, HPos.CENTER);
		GridPane.setHalignment(bNext, HPos.CENTER);
		GridPane.setHalignment(bPrevious, HPos.CENTER);
	}

	@Override
	protected void formatContainers() {
		gPane.setAlignment(Pos.CENTER);
		gPane.setVgap(10);
		gPane.setHgap(10);
		gPane.getColumnConstraints().add(new ColumnConstraints(170));
		gPane.getColumnConstraints().add(new ColumnConstraints(170));
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}
	
	

}
