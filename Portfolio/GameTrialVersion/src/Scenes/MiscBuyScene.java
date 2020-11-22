package Scenes;

import Characters.PlayerClass;
import Items.Item;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MiscBuyScene extends MyScene{
	
	private GridPane gInventory, gMisc, gBottom;
	private Button bExit;
	private Label lHealthPot, lManaPot, lPotato, lHat, lUpgradeWater, lGold, lArrow;
	private Button bHealthPot, bManaPot, bPotato, bHat, bUpgradeWater, bArrow;
	
	public MiscBuyScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}
	
	@Override
	public void loadScene() {
		lHealthPot = new Label("Health Potion");
		lManaPot = new Label("Mana Potion");
		lPotato = new Label("Potato");
		lHat = new Label("Hat");
		lUpgradeWater = new Label("Water Canteen");
		lGold = new Label("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
		lArrow = new Label("Arrow");
		bHealthPot = new Button("Buy (" + new Item("Health Potion").getPrice() + " Gold)");
		bManaPot = new Button("Buy (" + new Item("Mana Potion").getPrice() + " Gold)");
		bPotato = new Button("Buy 5 (1 Gold)");
		bHat = new Button("Buy (" + new Item("Hat").getPrice() + " Gold)");
		bArrow = new Button("Buy 5 (1 Gold)");
		bUpgradeWater = new Button("Buy (" + new Item("Canteen - Full").getPrice() + " Gold)");
		
		bExit = new Button("Leave Market");
		
		lHealthPot.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Health Potion")).loadScene();});
		lManaPot.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Mana Potion")).loadScene();});
		lPotato.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Potato")).loadScene();});
		lHat.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Hat")).loadScene();});
		lUpgradeWater.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Canteen - Full")).loadScene();});
		lUpgradeWater.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Arrow")).loadScene();});
		lGold.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, new Item("Gold")).loadScene();});
		
		gMisc = new GridPane();
		gMisc.add(bHealthPot, 0, 0);
		gMisc.add(lHealthPot, 1, 0);
		gMisc.add(bManaPot, 0, 1);
		gMisc.add(lManaPot, 1, 1);
		gMisc.add(bPotato, 0, 2);
		gMisc.add(lPotato, 1, 2);
		gMisc.add(bArrow, 0, 3);
		gMisc.add(lArrow, 1, 3);
		gMisc.add(bHat, 0, 4);
		gMisc.add(lHat, 1, 4);
		
		if (getMyClass().getInventory().findItem("Canteen - Full") == null 
				&& getMyClass().getBank().findItem("Canteen - Full") == null) {
			gMisc.add(bUpgradeWater, 0, 5);
			gMisc.add(lUpgradeWater, 1, 5);
		}
		
		gBottom = new GridPane();
		gBottom.add(lGold, 0, 0);
		gBottom.add(bExit, 1, 0);
		
		setRoot(new BorderPane());
		getRoot().setRight(gMisc);
		getRoot().setBottom(gBottom);
		
		setScene(new Scene(getRoot(), 1000, 550));
		
		populateInventory();
		setFormatting("MiscBuy", this);
		getWindow().setScene(getScene());
	}
	
	protected void populateInventory() {
		gInventory = new GridPane();
		gInventory.setAlignment(Pos.CENTER_LEFT);
		gInventory.setVgap(20);
		gInventory.setHgap(10);
		
		int skipGold = 0;
		for (int i = 0; i < getMyClass().getInventory().getLength(); i++) {
			
			Item item = getMyClass().getInventory().get(i);
			if (!item.getName().equalsIgnoreCase("Gold")) {
				
			
			Label itemName = new Label(item.getName() + ":");
			Label lItemAmount = new Label("" + item.getAmount());
			
			Button bSell = new Button("Sell (" + item.getPrice() + " Gold)");
			
			setFormat(itemName);
			setFormat(bSell);
			itemName.setStyle("-fx-text-fill: white;");
			lItemAmount.setStyle("-fx-text-fill: white;");
			lItemAmount.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
			itemName.setTooltip(new Tooltip(item.getDescription()));
			itemName.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
					this, item).loadScene();});
			gInventory.add(itemName, 0, (i - skipGold));
			gInventory.add(lItemAmount, 1, (i - skipGold));
			
			if (item.getPrice() > 0 && !item.getName().equalsIgnoreCase("Canteen - Full")) {
				gInventory.add(bSell, 2, (i - skipGold));
			}
			
			bSell.setOnAction(e -> {
				if (item.getPrice() > 0 && item.getAmount() > 0) {
					getMyClass().getInventory().addItem(new Item("Gold", item.getPrice()));
					getMyClass().getInventory().removeItem(new Item(item.getName(), 1));
					lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
					playSound("BuyOrSell");
					populateInventory();
				}
			});
			}
			else {
				skipGold = 1;
			}
		}
		getRoot().setLeft(gInventory);
	}

	@Override
	protected void formatControls() {
		setFormat(lHealthPot);
		setFormat(lManaPot);
		setFormat(lPotato);
		setFormat(lHat);
		setFormat(lUpgradeWater);
		setFormat(lGold);
		setFormat(lArrow);
		
		setFormat(bHealthPot);
		setFormat(bManaPot);
		setFormat(bPotato);
		setFormat(bHat);
		setFormat(bUpgradeWater);
		setFormat(bArrow);
		setFormat(bExit);
		
		lHealthPot.setStyle("-fx-text-fill: white;");
		lManaPot.setStyle("-fx-text-fill: white;");
		lPotato.setStyle("-fx-text-fill: white;");
		lHat.setStyle("-fx-text-fill: white;");
		lUpgradeWater.setStyle("-fx-text-fill: white;");
		lArrow.setStyle("-fx-text-fill: white;");
		lGold.setStyle("-fx-text-fill: white;");
		
		BorderPane.setAlignment(bExit, Pos.CENTER);
		GridPane.setHalignment(lGold, HPos.CENTER);
		GridPane.setValignment(lGold, VPos.CENTER);

		
	}

	@Override
	protected void formatContainers() {
		gMisc.setHgap(10);
		gMisc.setVgap(20);
		gMisc.setAlignment(Pos.CENTER_RIGHT);
		
		gBottom.setAlignment(Pos.BOTTOM_CENTER);
		gBottom.setHgap(50);
	}

	@Override
	protected void buttonAddAction() {
		bHealthPot.setOnAction(e -> {
			Item healthPot = new Item("Health Potion", 1);
			if (getMyClass().getInventory().findItem("Gold").getAmount() >= healthPot.getPrice()) {
				getMyClass().getInventory().addItem(healthPot);
				getMyClass().getInventory().removeItem(new Item("Gold", healthPot.getPrice()));
				lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
				playSound("BuyOrSell");
				populateInventory();
			}
		});
		bManaPot.setOnAction(e -> {
			Item manaPot = new Item("Mana Potion", 1);
			if (getMyClass().getInventory().findItem("Gold").getAmount() >= manaPot.getPrice()) {
				getMyClass().getInventory().addItem(manaPot);
				getMyClass().getInventory().removeItem(new Item("Gold", manaPot.getPrice()));
				lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
				playSound("BuyOrSell");
				populateInventory();
			}
		});
		bPotato.setOnAction(e -> {
			Item potato = new Item("Potato", 5);
			if (getMyClass().getInventory().findItem("Gold").getAmount() >= 1) {
				getMyClass().getInventory().addItem(potato);
				getMyClass().getInventory().removeItem(new Item("Gold", 1));
				lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
				playSound("BuyOrSell");
				populateInventory();
				}
		});
		bHat.setOnAction(e -> {
			Item hat = new Item("Hat", 1);
			if (getMyClass().getInventory().findItem("Gold").getAmount() >= hat.getPrice()) {
				getMyClass().getInventory().addItem(hat);
				getMyClass().getInventory().removeItem(new Item("Gold", hat.getPrice()));
				lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
				playSound("BuyOrSell");
				populateInventory();
			}
		});
		bArrow.setOnAction(e -> {
			Item potato = new Item("Arrow", 5);
			if (getMyClass().getInventory().findItem("Gold").getAmount() >= 1) {
				getMyClass().getInventory().addItem(potato);
				getMyClass().getInventory().removeItem(new Item("Gold", 1));
				lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
				playSound("BuyOrSell");
				populateInventory();
				}
		});
		bUpgradeWater.setOnAction(e -> {
			Item canteen = new Item("Canteen - Full", 1);
			if (getMyClass().getInventory().findItem("Gold").getAmount() >= canteen.getPrice()) {
				getMyClass().getInventory().addItem(canteen);
				getMyClass().getInventory().removeItem(new Item("Water Bottle - Full", 1));
				getMyClass().getInventory().removeItem(new Item("Gold", canteen.getPrice()));
				lGold.setText("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
				playSound("BuyOrSell");
				getMyClass().setMaxWater(50);
				getMyClass().setCurrentWater(50);
				loadScene();
			}
		});
		bExit.setOnAction(e -> {
			new TownScene(getWindow(), getMyClass()).loadScene();
		});
		
		
	}

	@Override
	protected void addTooltips() {
		lHealthPot.setTooltip(new Tooltip(new Item("Health Potion").getDescription()));
		lManaPot.setTooltip(new Tooltip(new Item("Mana Potion").getDescription()));
		lPotato.setTooltip(new Tooltip(new Item("Potato").getDescription()));
		lHat.setTooltip(new Tooltip(new Item("Hat").getDescription()));
		lUpgradeWater.setTooltip(new Tooltip(new Item("Canteen - Full").getDescription()));
		lGold.setTooltip(new Tooltip(new Item("Gold").getDescription()));
	}

}
