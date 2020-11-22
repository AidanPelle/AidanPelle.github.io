package Scenes;

import Characters.PlayerClass;

import Characters.Enemy;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TownScene extends MyScene{
	
	private Label lDesc;
	private GridPane gPane;
	private Button bMarket, bBank, bInn, bQuest, bTravel, bTestDPS;
	private VBox vLeft, vRight;
	private HBox hBox;
	private MediaPlayer background;
	
	public TownScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}
	
	@Override
	public void loadScene() {
		background = new MediaPlayer(new Media(this.getClass().getResource("/AudioFiles/Town.wav").toString()));
		background.play();
		
		bMarket = new Button("Market");
		bBank = new Button("Bank");
		bInn = new Button("Inn");
		bQuest = new Button("Quests");
		bTravel = new Button("Travel");
		bTestDPS = new Button("Training");
		lDesc = new Label(populateLabel());
		
		vLeft = new VBox();
		vLeft.getChildren().addAll(bMarket, bBank, bTravel);
		
		vRight = new VBox();
		vRight.getChildren().addAll(bInn, bQuest, bTestDPS);
		
		hBox = new HBox();
		hBox.getChildren().addAll(vLeft, vRight);
		
		gPane = new GridPane();
		gPane.add(lDesc, 0, 0);
		gPane.add(hBox, 0, 1);
		
		setRoot(new BorderPane());
		getRoot().setTop(gPane);
		
		setScene(new Scene(getRoot(), 1000, 550));
		setFormatting("Town", this);
		getWindow().setScene(getScene());
	}
	
	private String populateLabel() {
		String s = "You find yourself in a modest, but bustling town. "
				+ "You hear merchants calling out their wares, "
				+ "banks advertising their low interest, "
				+ "inns hoping to fill a room, "
				+ "the clang of metal at a training ground, "
				+ "and villagers hoping to hire an adventurer. You quench your thirst at a nearby well.";
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
	protected void buttonAddAction() {
			bMarket.setOnAction(e -> {
				new MarketScene(getWindow(), getMyClass(), background).loadScene();
			});
			
			bBank.setOnAction(e -> {
				background.stop();
				new BankScene(getWindow(), getMyClass()).loadScene();
			});
			
			bInn.setOnAction(e -> {
				background.stop();
				new InnScene(getWindow(), getMyClass()).loadScene();
			});
			
			bQuest.setOnAction(e -> {
				background.stop();
				playSound("Page Flip");
				new QuestMenuScene(getWindow(), getMyClass(), this).loadScene();
			});
			
			bTravel.setOnAction(e -> {
				background.stop();
				new TravellingMenuScene(getWindow(), getMyClass()).loadScene();
			});
			bTestDPS.setOnAction(e -> {
				background.stop();
				getMyClass().getEnemies().add(new Enemy("Target Dummy"));
				new FightScene(getWindow(), getMyClass(), this).loadScene();
			});
			
		
	}

	@Override
	protected void formatControls() {
		lDesc.setWrapText(true);
		lDesc.setTextAlignment(TextAlignment.CENTER);
		
		setFormat(bMarket);
		setFormat(bBank);
		setFormat(bInn);
		setFormat(bQuest);
		setFormat(bTravel);
		setFormat(bTestDPS);
		setFormat(lDesc);
		
		GridPane.setHalignment(lDesc, HPos.CENTER);
	}

	@Override
	protected void formatContainers() {
		vLeft.setSpacing(20);
		vRight.setSpacing(20);
		
		hBox.setSpacing(660);
		gPane.setVgap(50);
		gPane.setAlignment(Pos.TOP_CENTER);
	}

	@Override
	protected void addTooltips() {
		bMarket.setTooltip(new Tooltip("Buy and sell miscellaneous goods"));
		bBank.setTooltip(new Tooltip("Store items in case you die"));
		bTravel.setTooltip(new Tooltip("Travel the world (bring plenty of food and water)"));
		bInn.setTooltip(new Tooltip("Click here to save game"));
		bQuest.setTooltip(new Tooltip("Available jobs from villagers"));
		bTestDPS.setTooltip(new Tooltip("Practice your spells and abilities here!"));
	}
}
