package Scenes;

import Characters.Enemy;
import Characters.PlayerClass;
import Items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TravellingMenuScene extends MyScene{
	
	private Label lMap, lSupplies;
	private VBox root;
	private GridPane gPane;
	private Button bUp, bDown, bLeft, bRight, bEnter, bInventory;
	private HBox hBox;
	private final int LAVA_DAMAGE = 1;
	
	public TravellingMenuScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}
	
	@Override
	public void loadScene() {
		
		String sMap = loadMapString();
		
		lMap = new Label(sMap);
		bUp = new Button("North");
		bDown = new Button("South");
		bLeft = new Button("East");
		bRight = new Button("West");
		bEnter = new Button("Enter " + enter());
		lSupplies = new Label("Water: " + getMyClass().getCurrentWater() + "\nPotatoes: " + countPotatoes());
		bInventory = new Button("Inventory");
		
		gPane = new GridPane();
		gPane.add(bUp, 1, 0);
		gPane.add(bLeft, 0, 1);
		gPane.add(bRight, 2, 1);
		gPane.add(bDown, 1, 2);
		
		hBox = new HBox();
		hBox.getChildren().addAll(bEnter, lSupplies, bInventory);
		root = new VBox();
		root.getChildren().addAll(lMap, gPane, hBox);
		
		setScene(new Scene(root, 903, 1000));
		checkStartingPosition();
		
		setFormatting(null, null);
		getWindow().setScene(getScene());
	}
	
	private int countPotatoes() {
		if (getMyClass().getInventory().findItem("Potato") != null) {
			return getMyClass().getInventory().findItem("Potato").getAmount();
		} else return 0;
	}
	
	private void checkStartingPosition() {
		if (getMyClass().getMap().getPositionChar() == 'O' || getMyClass().getMap().getPositionChar() == 'T' 
				|| getMyClass().getMap().getPositionChar() == 'C') {
			bEnter.setVisible(true);
			}
		else {
			bEnter.setVisible(false);
		}
	}
	
	private String enter() {
		switch (getMyClass().getMap().getPositionChar()) {
		case 'O': {
			return "Outpost";
		}
		case 'T': {
			return "Town";
		}
		case 'C': {
			return "Cave";
		}
		}
		return "NullString";
		
	}
	
	private String loadMapString() {
		String s = "";
		for (int i = 0; i < getMyClass().getMap().getMap().length; i++) {
			for (int j = 0; j < getMyClass().getMap().getMap()[i].length; j++) {
				s += getMyClass().getMap().getMap()[i][j];
			}
		}
		return s;
	}

	@Override
	protected void formatControls() {
		setFormat(bUp);
		setFormat(bDown);
		setFormat(bLeft);
		setFormat(bRight);
		setFormat(bEnter);
		setFormat(lSupplies);
		setFormat(bInventory);
		
		bUp.setPrefWidth(100);
		bDown.setPrefWidth(100);
		bLeft.setPrefWidth(100);
		bRight.setPrefWidth(100);
		bInventory.setPrefWidth(160);
		bEnter.setPrefWidth(160);
		
		bEnter.setFocusTraversable(false);
		bUp.setFocusTraversable(false);
		bDown.setFocusTraversable(false);
		bLeft.setFocusTraversable(false);
		bRight.setFocusTraversable(false);
		bInventory.setFocusTraversable(false);
		
		lSupplies.setTextAlignment(TextAlignment.CENTER);
		lMap.setAlignment(Pos.CENTER);
		lMap.setFont(Font.font("Courier", FontWeight.NORMAL, FontPosture.REGULAR, 16));
		
		root.requestFocus();
	}

	@Override
	protected void formatContainers() {
		root.setAlignment(Pos.CENTER);
		root.setSpacing(40);
		
		gPane.setAlignment(Pos.CENTER);
		gPane.setVgap(10);
		gPane.setHgap(10);
		
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(200);
		hBox.setPadding(new Insets(20));
		}
	
	private void displayEntry(boolean previousEnter ) {
		if (previousEnter == true && enter().equalsIgnoreCase("NullString")) {
			bEnter.setVisible(false);
		}
		if (previousEnter == false && !enter().equalsIgnoreCase("NullString")) {
			bEnter.setText("Enter " + enter());
			bEnter.setVisible(true);
		}
		lMap.setText(loadMapString());
		
	}
	
	private void move(String direction) {
		boolean previousEnter;
		if (!enter().equalsIgnoreCase("NullString")) previousEnter = true;
		else previousEnter = false;
		
		if (getMyClass().getMap().move(direction) == 1) {
			checkPosition();
			if (getMyClass().getCurrentWater() == 0 || countPotatoes() == 0) {
				if ((int) (Math.random() * 10) == 0) {
					deathCheck();
				}
			}
			else {
				getMyClass().setCurrentWater(getMyClass().getCurrentWater() - 1);
				getMyClass().getInventory().removeItem(new Item("Potato", 1));
			}
		}
		displayEntry(previousEnter);
	}
	
	private void checkPosition() {
		if (getMyClass().getMap().getPositionChar() == '.')
			getMyClass().setCurrentHealth(getMyClass().getCurrentHealth() - LAVA_DAMAGE);
		else if (getMyClass().getMap().getPositionChar() == 'E') {
			getMyClass().getEnemies().add(new Enemy("Forest Elemental"));
			getMyClass().getEnemies().add(new Enemy("Forest Elemental"));
			getMyClass().getEnemies().add(new Enemy("Fire Elemental"));
			getMyClass().getEnemies().add(new Enemy("Fire Elemental"));
			getMyClass().getEnemies().add(new Enemy("Storm Elemental"));
			new FightScene(getWindow(), getMyClass(), this).loadScene();
		}
		else if (getMyClass().getMap().getPositionChar() == 'W') {
			getMyClass().getEnemies().add(new Enemy("Wizard"));
			new FightScene(getWindow(), getMyClass(), this).loadScene();
		}
		else if (getMyClass().getMap().getPositionChar() == ',' && (int) (Math.random() * 3) == 0) {
			getMyClass().getEnemies().add(new Enemy("Small Boar"));
			getMyClass().getEnemies().add(new Enemy("Small Boar"));
			getMyClass().getEnemies().add(new Enemy("Large Boar"));
			new FightScene(getWindow(), getMyClass(), this).loadScene();
		}
		else if (getMyClass().getMap().getPositionChar() == ' ' && (int) (Math.random() * 10) == 0) {
			levelAppropriateMonsters();
		}
		else if (getMyClass().getMap().getPositionChar() == 'M') {
			levelAppropriateMonsters();
		}
		else if (getMyClass().getMap().getPositionChar() == 'D') {
			getMyClass().getEnemies().add(new Enemy("Tiamat"));
			new FightScene(getWindow(), getMyClass(), this).loadScene();
		}
	}
	
	private void levelAppropriateMonsters() {
		if (getMyClass().getLevel() < 3) {
			getMyClass().getEnemies().add(new Enemy("Small Boar"));
			getMyClass().getEnemies().add(new Enemy("Small Boar"));
			getMyClass().getEnemies().add(new Enemy("Large Boar"));
		}
		else if (getMyClass().getLevel() < 6) {
			getMyClass().getEnemies().add(new Enemy("Goblin"));
			getMyClass().getEnemies().add(new Enemy("Goblin"));
			getMyClass().getEnemies().add(new Enemy("Goblin"));
			getMyClass().getEnemies().add(new Enemy("Mad Hatter"));
			getMyClass().getEnemies().add(new Enemy("The Walt"));
		}
		else if (getMyClass().getLevel() < 9) {
			getMyClass().getEnemies().add(new Enemy("Orc Soldier"));
			getMyClass().getEnemies().add(new Enemy("Orc Soldier"));
			getMyClass().getEnemies().add(new Enemy("Orc Leader"));
		}
		else if (getMyClass().getLevel() < 12) {
			getMyClass().getEnemies().add(new Enemy("Giant"));
			getMyClass().getEnemies().add(new Enemy("Elder Giant"));
		}
		else if (getMyClass().getLevel() < 15) {
			getMyClass().getEnemies().add(new Enemy("Lesser Ice Golem"));
			getMyClass().getEnemies().add(new Enemy("Lesser Ice Golem"));
			getMyClass().getEnemies().add(new Enemy("Greater Ice Golem"));
		}
		else if (getMyClass().getLevel() < 18) {
			getMyClass().getEnemies().add(new Enemy("Nature Elemental"));
			getMyClass().getEnemies().add(new Enemy("Fire Elemental"));
			getMyClass().getEnemies().add(new Enemy("Storm Elemental"));
		}
		else {
			getMyClass().getEnemies().add(new Enemy("Lesser Demon"));
			getMyClass().getEnemies().add(new Enemy("Lesser Demon"));
			getMyClass().getEnemies().add(new Enemy("Lesser Demon"));
			getMyClass().getEnemies().add(new Enemy("Chaos Demon"));
		}
		new FightScene(getWindow(), getMyClass(), this).loadScene();
	}

	@Override
	protected void buttonAddAction() {
		getScene().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				move("Up");
			} else if (e.getCode() == KeyCode.DOWN) {
				move("Down");
			} else if (e.getCode() == KeyCode.LEFT) {
				move("Left");
			} else if (e.getCode() == KeyCode.RIGHT) {
				move("Right");
			} 
			lSupplies.setText("Water: " + getMyClass().getCurrentWater() + "\nPotatoes: " + countPotatoes());
			
		});
		bUp.setOnAction(e -> {
			move("Up");
			lSupplies.setText("Water: " + getMyClass().getCurrentWater() + "\nPotatoes: " + countPotatoes());
		});
		bDown.setOnAction(e -> {
			move("Down");
			lSupplies.setText("Water: " + getMyClass().getCurrentWater() + "\nPotatoes: " + countPotatoes());
		});
		bLeft.setOnAction(e -> {
			move("Left");
			lSupplies.setText("Water: " + getMyClass().getCurrentWater() + "\nPotatoes: " + countPotatoes());
		});
		bRight.setOnAction(e -> {
			move("Right");
			lSupplies.setText("Water: " + getMyClass().getCurrentWater() + "\nPotatoes: " + countPotatoes());
		});
		
		bInventory.setOnAction(e ->{
			new InventoryScene(getWindow(), getMyClass(), new TravellingMenuScene(getWindow(), getMyClass())).loadScene();
		});
		
		bEnter.setOnAction(e -> {
			switch (bEnter.getText()) {
			case "Enter Town": {
				new TownScene(getWindow(), getMyClass()).loadScene();
				break;
			}
			case "Enter Outpost": {
				new OutpostScene(getWindow(), getMyClass()).loadScene();
				break;
			}
			case "Enter Cave": {
				int correctPath = (int) (Math.random() * 3 + 1);
				new CaveScene(getWindow(), getMyClass(), correctPath).loadScene();
				break;
			}
			}
		});
		rootButtons(getWindow(), getMyClass(), root, this);
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}
	
	

}
