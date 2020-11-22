package Scenes;

import Characters.PlayerClass;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelpScene extends MyScene{
	
	private MyScene previousScene;
	private Button bCloseHelp;
	private Label lHelp, lInventory, lDeath, lInn;
	private GridPane gPane;
	
	public HelpScene (Stage window, MyScene previousScene) {
		super(window, new PlayerClass());
		this.previousScene = previousScene;
	}
	
	@Override
	public void loadScene() {
		
		lDeath = new Label("When you die, your inventory and armor will disappear. Anything in your bank will be saved");
		lHelp = new Label("Pressing \"H\" will open or close the help page at any time");
		lInventory = new Label("Pressing \"I\" will open or close the inventory at any time");
		bCloseHelp = new Button("Close Help");
		lInn = new Label("Entering the inn will refresh your character and save the game");
		
		gPane = new GridPane();
		gPane.add(lHelp, 0, 0);
		gPane.add(lInventory, 0, 1);
		gPane.add(lDeath, 0, 2);
		gPane.add(lInn, 0, 3);
		
		setRoot(new BorderPane());
		getRoot().setLeft(gPane);
		getRoot().setRight(bCloseHelp);
		
		setScene(new Scene(getRoot(), 1000, 500));
		setFormatting("Help", null);
		getWindow().setScene(getScene());
	}

	@Override
	protected void formatControls() {
		setFormat(lHelp, "gold");
		setFormat(lInventory, "gold");
		setFormat(bCloseHelp);
		setFormat(lDeath, "gold");
	}

	@Override
	protected void formatContainers() {
		gPane.setVgap(20);
		
	}

	@Override
	protected void buttonAddAction() {
		bCloseHelp.setOnAction(e -> {
			previousScene.loadScene();
		});
		getRoot().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				getRoot().setOnKeyPressed(i -> {
					if (i.getCode() == KeyCode.ESCAPE) {
						System.exit(0);
					}
				});
			}
			else if (e.getCode() == KeyCode.H) {
				previousScene.loadScene();
			}
			
		});
	}
	
	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
