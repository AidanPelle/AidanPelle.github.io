package Scenes;

import Characters.PlayerClass;
import Items.Inventory;
import Items.Item;
import Items.Map;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeathScene extends MyScene{
	
	String causeOfDeath;

	public DeathScene(Stage window, PlayerClass myClass, String causeOfDeath) {
		super(window, myClass);
		this.causeOfDeath = causeOfDeath;
	}

	@Override
	public void loadScene() {
		
		getMyClass().setMap(new Map());
		getMyClass().setMaxWater(25);
		getMyClass().setInventory(new Inventory());
		getMyClass().getInventory().addItem(new Item("Gold", 0));
		getMyClass().getInventory().addItem(new Item("Water Bottle - Full", 1));
		getMyClass().getInventory().addItem(new Item("Potato", 50));
		
		Label l1 = new Label("Unfortunately, you died");
		Label l2 = new Label("Cause of Death: " + causeOfDeath);
		Label l3 = new Label("Your inventory has been cleared, save a water bottle and a sack of potatoes");
		Label l4 = new Label("You'll return to your most recent save point, and your bank will remain intact");
		Label l5 = new Label("If you'd like to pretend that didnt happen, close and re-open your game right now");
		Button b = new Button("Continue");
		
		setFormat(l1 , "gold");
		setFormat(l2, "gold");
		setFormat(l3, "gold");
		setFormat(l4, "gold");
		setFormat(l5, "gold");
		setFormat(b);
		
		b.setOnAction(e -> {
			new InnScene(getWindow(), getMyClass()).loadScene();
		});
		
		GridPane gPane = new GridPane();
		gPane.setVgap(5);
		gPane.add(l1, 0, 0);
		gPane.add(l2, 0, 1);
		gPane.add(l3, 0, 2);
		gPane.add(l4, 0, 3);
		gPane.add(l5, 0,  4);
		
		setRoot(new BorderPane());
		getRoot().setLeft(gPane);
		getRoot().setRight(b);
		
		setScene(new Scene(getRoot(), 1000, 500));
		setFormatting("Death", null);
		getWindow().setScene(getScene());
		
		/*setRoot(new BorderPane());
		setScene(new Scene(getRoot(), 1000, 1000));
		getWindow().setScene(getScene());*/
		
	}

	@Override
	protected void formatControls() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void formatContainers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buttonAddAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
