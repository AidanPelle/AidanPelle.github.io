package Scenes;

import Characters.PlayerClass;
import Items.Item;
import Items.Spell;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class InventoryScene extends MyScene{
	
	private Button bCloseInventory;
	private GridPane gItems, gStats, gStatus, gName;
	private Image iBackpack;
	private ImageView iVBackpack;
	private MyScene previousScene;
	private Label lStrength1, lStrength2, lDexterity1, lDexterity2,
	lIntelligence1, lIntelligence2, lAgility1, lAgility2, lDefense1, lDefense2,
	lHealth, lMana, lExperience, lLevel1, lLevel2, lWater, lWeapon1, lArmor1,
	lFluff1, lFluff2, lFluff3, lDamage1, lDamage2, lResistances1, lResistances2, lName, lRace, lClass, lSpells;
	private ProgressBar pHealth, pMana, pExperience, pWater;
	private VBox vBox;
	private HBox hSpells;
	
	public InventoryScene(Stage window, PlayerClass myClass, MyScene previousScene) {
		super(window, myClass);
		this.previousScene = previousScene;
	}
	
	@Override
	public void loadScene() {
		
		bCloseInventory = new Button("Close Inventory");
		lStrength1 = new Label("Strength:");
		lStrength2 = new Label("" + getMyClass().getStrength());
		lDexterity1 = new Label("Dexterity:");
		lDexterity2 = new Label("" + getMyClass().getDexterity());
		lIntelligence1 = new Label("Intelligence:");
		lIntelligence2 = new Label("" + getMyClass().getIntelligence());
		lAgility1 = new Label("Agility:");
		lAgility2 = new Label("" + getMyClass().getAgility());
		lDefense1 = new Label("Defense:");
		lDefense2 = new Label("" + getMyClass().getDefense());
		lHealth = new Label("Health");
		lMana = new Label("Mana");
		lExperience = new Label("Experience");
		lLevel1 = new Label("Level");
		lLevel2 = new Label("" + getMyClass().getLevel());
		lWeapon1 = new Label(getMyClass().getWeapon().getName());
		lArmor1 = new Label(getMyClass().getArmor().getName());
		lDamage1 = new Label("Damage: ");
		lDamage2 = new Label("" + getMyClass().getWeapon().getRelevantDamage());
		lResistances1 = new Label("Resistance:");
		lResistances2 = new Label("" + getMyClass().getArmor().getDefense());
		lFluff1 = new Label("");
		lFluff2 = new Label("");
		lFluff3 = new Label("");
		lWater = new Label("Water");
		lName = new Label(getMyClass().getsName());
		lRace = new Label(getMyClass().getsRace());
		lClass = new Label(getMyClass().getClassType().toString());
		
		pHealth = getHealthBar();
		pMana = getManaBar();
		pExperience = getExperienceBar();
		pWater = getWaterBar();
		
		lWeapon1.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, getMyClass().getWeapon()).loadScene();});
		lArmor1.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, getMyClass().getArmor()).loadScene();});
		
		gStats = new GridPane();
		gStats.add(lWeapon1, 0, 0, 2, 1);
		gStats.add(lDamage1, 0, 1);
		gStats.add(lDamage2, 1, 1);
		gStats.add(lArmor1, 0, 2, 2, 1);
		gStats.add(lResistances1, 0, 3);
		gStats.add(lResistances2, 1, 3);
		gStats.add(lFluff1, 0, 4);
		gStats.add(lFluff2, 1, 4);
		gStats.add(lStrength1, 0, 5);
		gStats.add(lStrength2, 1, 5);
		gStats.add(lDexterity1, 0, 6);
		gStats.add(lDexterity2, 1, 6);
		gStats.add(lIntelligence1, 0, 7);
		gStats.add(lIntelligence2, 1, 7);
		gStats.add(lAgility1, 0, 8);
		gStats.add(lAgility2, 1, 8);
		gStats.add(lDefense1, 0, 9);
		gStats.add(lDefense2, 1, 9);
		
		hSpells = new HBox();
		lSpells = new Label("Spells:");
		hSpells.getChildren().add(lSpells);
		
		for (Spell spell : getMyClass().getSpells()) {
			Label lSpell = new Label(spell.getName());
			String tooltip = spell.getDescription();
			tooltip += "\n";
			if (spell.getDamage() != 0) {
				tooltip += "Damage: " + spell.getDamage();
			} else if (spell.getDamageBuff() != 0) {
				tooltip += "Damage Buff: " + spell.getDamageBuff();
			} else if (spell.getDefenseBuff() != 0) {
				tooltip += "Defense Buff: " + spell.getDefenseBuff();
			} else if (spell.getHp() != 0) {
				tooltip += "HP Bonus: " + spell.getHp();
			} else if (spell.getImmunity()) {
				tooltip += "Immunity: " + spell.getImmunity();
			}
			tooltip += "\nMana Cost: " + spell.getManaCost();
			lSpell.setTooltip(new Tooltip(tooltip));
			setFormat(lSpell);
			lSpell.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
					this, spell).loadScene();});
			hSpells.getChildren().add(lSpell);
		}
		
		gStatus = new GridPane();
		
		gStatus.add(lHealth, 0, 0);
		gStatus.add(pHealth, 0, 1);
		gStatus.add(lMana, 1, 0);
		gStatus.add(pMana, 1, 1);
		gStatus.add(lExperience, 2, 0);
		gStatus.add(pExperience, 2, 1);
		gStatus.add(lWater, 3, 0);
		gStatus.add(pWater, 3, 1);
		gStatus.add(lLevel1, 4, 0);
		gStatus.add(lLevel2, 4, 1);
		gStatus.add(lFluff3, 5, 0);
		gStatus.add(bCloseInventory, 6, 0, 1, 2);
		
		vBox = new VBox();
		vBox.getChildren().addAll(hSpells, gStatus);
		
		gName = new GridPane();
		gName.add(lName, 0, 0, 2, 1);
		gName.add(lRace, 0, 1);
		gName.add(lClass, 1, 1);
		
		iBackpack = new Image("BackgroundFiles/Backpack.jpg");
		iVBackpack = new ImageView(iBackpack);
		
		setRoot(new BorderPane());
		getRoot().setBottom(vBox);
		getRoot().setCenter(iVBackpack);
		getRoot().setRight(gStats);
		getRoot().setTop(gName);
		setScene(new Scene(getRoot(), 1000, 1000));
		
		gItems = new GridPane();
		populateInventory();
		setFormatting(null, null);
		getWindow().setScene(getScene());
	}
	
	private void populateInventory() {
		gItems = new GridPane();
		
		for (int i = 0; i < getMyClass().getInventory().getLength(); i++) {
			Item item = getMyClass().getInventory().get(i);
			
			Label lItemName = new Label(item.getName() + ":");
			lItemName.setAlignment(Pos.CENTER);
			lItemName.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16));
			lItemName.setTooltip(new Tooltip(item.getDescription()));
			lItemName.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
					this, item).loadScene();});
			
			Button bUseItem = new Button("Use " + item.getName());
			bUseItem.setAlignment(Pos.CENTER);
			bUseItem.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16));
			bUseItem.setStyle("-fx-text-fill: green;");
			
			Label lItemAmount = new Label("" + item.getAmount());
			lItemAmount.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16));

			bUseItem.setOnAction(e -> {
				switch (item.getName()) {
				case "Water Bottle - Full": {
					playSound("Drink");
					item.setName(item.getName().replace("Full", "Empty"));
					lItemName.setText(item.getName());
					gItems.getChildren().remove(bUseItem);
					getMyClass().setCurrentWater(getMyClass().getMaxWater());
					pWater.setProgress(((double)getMyClass().getCurrentWater())/getMyClass().getMaxWater());
					break;
				}
				case "Canteen - Full": {
					playSound("Drink");
					item.setName(item.getName().replace("Full", "Empty"));
					lItemName.setText(item.getName());
					gItems.getChildren().remove(bUseItem);
					getMyClass().setCurrentWater(getMyClass().getMaxWater());
					pWater.setProgress(((double)getMyClass().getCurrentWater())/getMyClass().getMaxWater());
					break;
				}
				case "Potato": {
					if (getMyClass().getCurrentHealth() + 5 >= getMyClass().getMaxHealth()) {
						getMyClass().setCurrentHealth(getMyClass().getMaxHealth());
						pHealth.setProgress(((double) getMyClass().getCurrentHealth())/getMyClass().getMaxHealth());
					} 
					else {
						getMyClass().setCurrentHealth(getMyClass().getCurrentHealth() + 5);
						pHealth.setProgress(((double) getMyClass().getCurrentHealth())/getMyClass().getMaxHealth());
					}
					getMyClass().getInventory().removeItem(new Item("Potato", 1));
					populateInventory();
					break;
				}
				case "Health Potion": {
					playSound("Drink");
					int healthGained = (int) (30 + getMyClass().getMaxHealth() * 0.25);
					if (getMyClass().getCurrentHealth() + healthGained >= getMyClass().getMaxHealth()) {
						getMyClass().setCurrentHealth(getMyClass().getMaxHealth());
						pHealth.setProgress(((double) getMyClass().getCurrentHealth())/getMyClass().getMaxHealth());
					} 
					else {
						getMyClass().setCurrentHealth(getMyClass().getCurrentHealth() + healthGained);
						pHealth.setProgress(((double) getMyClass().getCurrentHealth())/getMyClass().getMaxHealth());
					}
					getMyClass().getInventory().removeItem(new Item("Health Potion", 1));
					populateInventory();
					break;
				}
				case "Mana Potion": {
					playSound("Drink", 0.5);
					int manaGained = (int) (50 + getMyClass().getMaxMana() * 0.25);
					if (getMyClass().getCurrentMana() + manaGained >= getMyClass().getMaxMana()) {
						getMyClass().setCurrentMana(getMyClass().getMaxMana());
						pMana.setProgress(((double) getMyClass().getCurrentMana())/getMyClass().getMaxMana());
					} 
					else {
						getMyClass().setCurrentMana(getMyClass().getCurrentMana() + manaGained);
						pMana.setProgress(((double) getMyClass().getCurrentMana())/getMyClass().getMaxMana());
					}
					getMyClass().getInventory().removeItem(new Item("Mana Potion", 1));
					populateInventory();
					break;
				}
				}
			});
			
			gItems.add(lItemName, 0, i);
			gItems.add(lItemAmount, 1, i);
			if (isConsumable(item)) {
				gItems.add(bUseItem, 2, i);
			}
		}
		gItems.setAlignment(Pos.CENTER_LEFT);
		gItems.setVgap(20);
		gItems.setHgap(10);
		getRoot().setLeft(gItems);
	}
	
	private boolean isConsumable(Item item) {
		if (item.getName().equalsIgnoreCase("Potato") || item.getName().equalsIgnoreCase("Water Bottle - Full") 
				|| item.getName().equalsIgnoreCase("Health Potion") || item.getName().equalsIgnoreCase("Canteen - Full") 
				|| item.getName().equalsIgnoreCase("Mana Potion")) {
			return true;
		}
		else return false;
	}

	@Override
	protected void formatControls() {
		setFormat(bCloseInventory);
		bCloseInventory.setPrefWidth(200);
		
		setFormat(lStrength1, 16);
		setFormat(lStrength2, 16);
		setFormat(lDexterity1, 16);
		setFormat(lDexterity2, 16);
		setFormat(lIntelligence1, 16);
		setFormat(lIntelligence2, 16);
		setFormat(lAgility1, 16);
		setFormat(lAgility2, 16);
		setFormat(lDefense1, 16);
		setFormat(lDefense2, 16);
		setFormat(lLevel1, 16);
		setFormat(lLevel2, 16);
		setFormat(lWeapon1, 16);
		setFormat(lArmor1, 16);
		setFormat(lHealth, 16);
		setFormat(lMana, 16);
		setFormat(lExperience, 16);
		setFormat(lWater, 16);
		setFormat(lDamage1, 16);
		setFormat(lDamage2, 16);
		setFormat(lResistances1, 16);
		setFormat(lResistances2, 16);
		setFormat(lName);
		setFormat(lRace);
		setFormat(lClass);
		setFormat(lSpells);
		
		GridPane.setHalignment(lHealth, HPos.CENTER);
		GridPane.setHalignment(lMana, HPos.CENTER);
		GridPane.setHalignment(lExperience, HPos.CENTER);
		GridPane.setHalignment(lLevel1, HPos.CENTER);
		GridPane.setHalignment(lWater, HPos.CENTER);
		GridPane.setHalignment(lName, HPos.CENTER);
		GridPane.setHalignment(lRace, HPos.CENTER);
		GridPane.setHalignment(lClass, HPos.CENTER);
		
		lName.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR, 40));
		lRace.setFont(Font.font("Copperplate", FontWeight.NORMAL, FontPosture.REGULAR, 30));
		lClass.setFont(Font.font("Copperplate", FontWeight.NORMAL, FontPosture.REGULAR, 30));
		lFluff3.setPrefWidth(150);

	}

	@Override
	protected void formatContainers() {
		
		iVBackpack.setFitHeight(400);
		iVBackpack.setFitWidth(400);
		
		gStats.setAlignment(Pos.CENTER_RIGHT);
		gStats.setVgap(20);
		gStats.setHgap(10);
		
		gStatus.setAlignment(Pos.CENTER_LEFT);
		gStatus.setVgap(10);
		gStatus.setHgap(20);
		
		gName.setAlignment(Pos.TOP_CENTER);
		gName.setVgap(20);
		gName.setHgap(10);
		
		hSpells.setPadding(new Insets(20));
		hSpells.setSpacing(30);
		hSpells.setAlignment(Pos.CENTER_LEFT);
		
		vBox.setPadding(new Insets(20));
		vBox.setSpacing(40);
		vBox.setAlignment(Pos.CENTER);
		
		getRoot().setPadding(new Insets(20));
	}

	@Override
	protected void buttonAddAction() {
		bCloseInventory.setOnAction(e -> {
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
			else if (e.getCode() == KeyCode.I) {
				previousScene.loadScene();
			}
			
		});
	}

	@Override
	protected void addTooltips() {
		lSpells.setTooltip(new Tooltip("These are the spells you currently have access to"));
		lWeapon1.setTooltip(new Tooltip(getMyClass().getWeapon().getDescription()));
		lArmor1.setTooltip(new Tooltip(getMyClass().getArmor().getDescription()));
	}

}
