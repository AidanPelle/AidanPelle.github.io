package Scenes;

import Characters.PlayerClass;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MarketScene extends MyScene{
	
	private Label lMarket;
	private Button bCombat, bMisc;
	private MediaPlayer background;
	
	public MarketScene(Stage window, PlayerClass myClass, MediaPlayer background) {
		super(window, myClass);
		this.background = background;
	}
	
	@Override
	public void loadScene() {
		
		lMarket = new Label("You enter the bustling market. To the left is a zone of trading stalls, selling various goods and supplies. "
				+ "To the right is a cluttered smithing station, with weapons and armor on display.");
		bMisc = new Button("Go Left");
		bCombat = new Button("Go Right");
		
		setRoot(new BorderPane());
		getRoot().setTop(lMarket);
		getRoot().setLeft(bMisc);
		getRoot().setRight(bCombat);
		setScene(new Scene(getRoot(), 1000, 550));
		
		setFormatting("Market", this);
		getWindow().setScene(getScene());
	}

	@Override
	protected void formatControls() {
		
		setFormat(lMarket);
		setFormat(bMisc);
		setFormat(bCombat);
		
		lMarket.setWrapText(true);
		lMarket.setPrefWidth(500);
		lMarket.setPrefHeight(100);
		lMarket.setStyle("-fx-text-fill: black;");
		
		BorderPane.setAlignment(lMarket, Pos.TOP_CENTER);
		BorderPane.setAlignment(bMisc, Pos.CENTER);
		BorderPane.setAlignment(bCombat, Pos.CENTER);
	}

	@Override
	protected void formatContainers() {
	}

	@Override
	protected void buttonAddAction() {
		bMisc.setOnAction(e -> {
			new MiscBuyScene(getWindow(), getMyClass()).loadScene();
		});
		bCombat.setOnAction(e -> {
			background.stop();
			new SmithingScene(getWindow(), getMyClass()).loadScene();
		});
	}

	@Override
	protected void addTooltips() {
		bMisc.setTooltip(new Tooltip("Buy and sell supplies and loot"));
		bCombat.setTooltip(new Tooltip("Buy weapons and armor"));
	}

}
