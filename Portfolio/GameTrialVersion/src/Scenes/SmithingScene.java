package Scenes;

import Characters.PlayerClass;
import Items.Armor;
import Items.Item;
import Items.Weapon;
import Items.Weapon.WeaponType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class SmithingScene extends MyScene{
	
	private HBox hBox;
	private GridPane gArmor, gWeapons;
	private Label lGold, lWeapons, lArmor, lw1a, lw1b, lw2a, lw2b, lw3a, lw3b, lw4a, lw4b,
	la1a, la1b, la2a, la2b, la3a, la3b;
	private Button bExit, bw1, bw2, bw3, bw4, ba1, ba2, ba3;
	private Weapon w1, w2, w3, w4;
	private Armor a1, a2, a3;
	private final int[] PRICES;
	private VBox vBox;
	private AudioClip background;
	
	public SmithingScene (Stage window, PlayerClass myClass) {
		super(window, myClass);
		PRICES = new int[]{10, 250, 500, 1000};
	}
	
	@Override
	public void loadScene() {
		background = new AudioClip(this.getClass().getResource("/AudioFiles/Blacksmith.wav").toString());
		background.play();
		loadGear();
		
		lWeapons = new Label("Weapons");
		lArmor = new Label("Armor");
		lGold = new Label("Gold: " + getMyClass().getInventory().findItem("Gold").getAmount());
		lGold.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, getMyClass().getInventory().findItem("Gold")).loadScene();});
		bExit = new Button("Leave Market");
		lw1a = new Label(w1.getName());
		lw1a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, w1).loadScene();});
		lw2a = new Label(w2.getName());
		lw2a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, w2).loadScene();});
		lw3a = new Label(w3.getName());
		lw3a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, w3).loadScene();});
		lw4a = new Label(w4.getName());
		lw4a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, w4).loadScene();});
		la1a = new Label(a1.getName());
		la1a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, a1).loadScene();});
		la1b = new Label("Defense: " + a1.getDefense());
		la2a = new Label(a2.getName());
		la2a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, a2).loadScene();});
		la2b = new Label("Defense: " + a2.getDefense());
		la3a = new Label(a3.getName());
		la3a.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, a3).loadScene();});
		la3b = new Label("Defense " + a3.getDefense());
		bw1 = new Button("Buy (" + PRICES[0] + ") Gold");
		bw2 = new Button("Buy (" + PRICES[1] + ") Gold");
		bw3 = new Button("Buy (" + PRICES[2] + ") Gold");
		bw4 = new Button("Buy (" + PRICES[3] + ") Gold");
		ba1 = new Button("Buy (" + PRICES[0] + ") Gold");
		ba2 = new Button("Buy (" + PRICES[1] + ") Gold");
		ba3 = new Button("Buy (" + PRICES[2] + ") Gold");
		
		if (w1.getWeaponType() != WeaponType.Staff) {
			lw1b = new Label(w1.getWeaponType().toString() + " Damage: " + (int) w1.getRelevantDamage());
			lw2b = new Label(w2.getWeaponType().toString() + " Damage: " + (int) w2.getRelevantDamage());
			lw3b = new Label(w3.getWeaponType().toString() + " Damage: " + (int) w3.getRelevantDamage());
			lw4b = new Label(w4.getWeaponType().toString() + " Damage: " + (int) w4.getRelevantDamage());
		}
		else {
			lw1b = new Label(w1.getWeaponType().toString() + " Damage: " + w1.getRelevantDamage());
			lw2b = new Label(w2.getWeaponType().toString() + " Damage: " + w2.getRelevantDamage());
			lw3b = new Label(w3.getWeaponType().toString() + " Damage: " + w3.getRelevantDamage());
			lw4b = new Label(w4.getWeaponType().toString() + " Damage: " + w4.getRelevantDamage());
		}
		hBox = new HBox();
		hBox.getChildren().addAll(lGold, bExit);
		
		gWeapons = new GridPane();
		
		if (getMyClass().getWeapon().getItemTier() < w1.getItemTier()) {
			gWeapons.add(lw1a, 0, 0);
			gWeapons.add(lw1b, 0, 1);
			gWeapons.add(bw1,  0,  2);
		}
		if (getMyClass().getWeapon().getItemTier() < w2.getItemTier() ) {
			gWeapons.add(lw2a, 1, 0);
			gWeapons.add(lw2b, 1, 1);
			gWeapons.add(bw2,  1,  2);
		}
		if (getMyClass().getWeapon().getItemTier() < w3.getItemTier()) {
			gWeapons.add(lw3a, 2, 0);
			gWeapons.add(lw3b, 2, 1);
			gWeapons.add(bw3,  2,  2);
		}
		if (getMyClass().getWeapon().getItemTier() < w4.getItemTier() ) {
			gWeapons.add(lw4a, 3, 0);
			gWeapons.add(lw4b, 3, 1);
			gWeapons.add(bw4,  3,  2);
		}
		
		
		gArmor = new GridPane();
		
		if (getMyClass().getArmor().getItemTier() < a1.getItemTier()) {
			gArmor.add(la1a, 0, 0);
			gArmor.add(la1b, 0, 1);
			gArmor.add(ba1,  0,  2);
		}
		
		placeArmor();
		
		vBox = new VBox();
		vBox.getChildren().addAll(lWeapons, gWeapons, lArmor, gArmor);
		
		setRoot(new BorderPane());
		getRoot().setBottom(hBox);
		getRoot().setTop(vBox);
		
		setScene(new Scene(getRoot(), 1000, 600));
		
		setFormatting("Blacksmith", this);
		getWindow().setScene(getScene());
	}
	
	private void placeArmor() {
		switch (getMyClass().getClassType()) {
		case Mage: {
			if (getMyClass().getArmor().getItemTier() < a2.getItemTier()) {
				gArmor.add(la2a, 1, 0);
				gArmor.add(la2b, 1, 1);
				gArmor.add(ba2, 1, 2);
			}
			break;
		}
		case Assassin: {
			if (getMyClass().getArmor().getItemTier() < a2.getItemTier()) {
				gArmor.add(la2a, 1, 0);
				gArmor.add(la2b, 1, 1);
				gArmor.add(ba2, 1, 2);
			}
			break;
		}
		case Warrior: {
			if (getMyClass().getArmor().getItemTier() < a2.getItemTier()) {
				gArmor.add(la2a, 1, 0);
				gArmor.add(la2b, 1, 1);
				gArmor.add(ba2, 1, 2);
			}
			if (getMyClass().getArmor().getItemTier() < a3.getItemTier()) {
				gArmor.add(la3a, 2, 0);
				gArmor.add(la3b, 2, 1);
				gArmor.add(ba3, 2, 2);
			}
			break;
		}
		default:
			break;
		}
	}
	
	private void loadGear() {
		a1 = new Armor("Tattered Leather");
		a2 = new Armor("Chain Shirt");
		a3 = new Armor("Plate Mail");
		switch (getMyClass().getClassType()) {
		case Archer: {
			w1 = new Weapon("Slingshot");
			w2 = new Weapon("Oak Bow");
			w3 = new Weapon("Ornate Bow");
			w4 = new Weapon("Masterwork Bow");
			break;
		}
		case Assassin: {
			w1 = new Weapon("Kitchen Knife");
			w2 = new Weapon("Short Blade");
			w3 = new Weapon("Steel Dagger");
			w4 = new Weapon("Masterwork Dagger");
			break;
		}
		case Mage: {
			w1 = new Weapon("Large Branch");
			w2 = new Weapon("Oak Staff");
			w3 = new Weapon("Runic Staff");
			w4 = new Weapon("Masterwork Staff");
			break;
		}
		case Warrior: {
			w1 = new Weapon("Rusty Shovel");
			w2 = new Weapon("Iron Sword");
			w3 = new Weapon("Steel Sword");
			w4 = new Weapon("Masterwork Blade");
			break;
		}
		}
	}

	@Override
	protected void formatControls() {
		setFormat(lw1a, "white");
		setFormat(lw1b, "white");
		setFormat(lw2a, "white");
		setFormat(lw2b, "white");
		setFormat(lw3a, "white");
		setFormat(lw3b, "white");
		setFormat(lw4a, "white");
		setFormat(lw4b, "white");
		setFormat(la1a, "white");
		setFormat(la1b, "white");
		setFormat(la2a, "white");
		setFormat(la2b, "white");
		setFormat(la3a, "white");
		setFormat(la3b, "white");

		setFormat(lWeapons, "white");
		setFormat(lArmor, "white");
		setFormat(lGold, "white");
		setFormat(bExit);
		
	}

	@Override
	protected void formatContainers() {
		gWeapons.setAlignment(Pos.CENTER);
		gWeapons.setHgap(40);
		gWeapons.setVgap(10);
		
		gArmor.setAlignment(Pos.CENTER);
		gArmor.setHgap(100);
		gArmor.setVgap(10);
		
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(40);
		
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(40);		
	}

	@Override
	protected void buttonAddAction() {
		bExit.setOnAction(e -> {
			if (background.isPlaying()) background.stop();
			new TownScene(getWindow(), getMyClass()).loadScene();
		});
		bw1.setOnAction(e -> {
			if (PRICES[0] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[0]));
				getMyClass().setWeapon(w1);
				loadScene();
			}
		});
		bw2.setOnAction(e -> {
			if (PRICES[1] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[1]));
				getMyClass().setWeapon(w2);
				loadScene();
			}
		});
		bw3.setOnAction(e -> {
			if (PRICES[2] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[2]));
				getMyClass().setWeapon(w3);
				loadScene();
			}
		});
		bw4.setOnAction(e -> {
			if (PRICES[3] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[3]));
				getMyClass().setWeapon(w4);
				loadScene();
			}
		});
		ba1.setOnAction(e -> {
			if (PRICES[0] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[0]));
				getMyClass().setArmor(a1);
				loadScene();
			}
		});
		ba2.setOnAction(e -> {
			if (PRICES[1] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[1]));
				getMyClass().setArmor(a2);
				loadScene();
			}
		});
		ba3.setOnAction(e -> {
			if (PRICES[2] <= getMyClass().getInventory().findItem("Gold").getAmount()) {
				getMyClass().getInventory().removeItem(new Item("Gold", PRICES[2]));
				getMyClass().setArmor(a3);
				loadScene();
			}
		});
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
