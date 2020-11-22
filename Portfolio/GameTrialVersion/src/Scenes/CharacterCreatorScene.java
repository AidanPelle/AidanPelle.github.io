package Scenes;

import Characters.PlayerClass;
import Characters.PlayerClass.ClassType;
import Characters.Quest;
import Items.Weapon;
import Items.Armor;
import Items.Item;
import Items.Map;
import Items.Spell;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CharacterCreatorScene extends MyScene{
	
	private TextField tName;
	private Label lRace, lClass, fluff1, fluff2;
	private Button bAasimar, bDragonborn, bDwarf, bElf, bHalfling, bHuman;
	private Button bNaga, bOrc, bArcher, bAssassin, bMage, bWarrior, bCreateCharacter;
	private String sName;
	private ClassType classType;
	private String sRace;
	private GridPane gPane;
	private VBox vBox;
	private Weapon weapon;
	private Armor armor;
	
	public CharacterCreatorScene(Stage window) {
		super(window, new PlayerClass());
	}
	
	@Override
	public void loadScene() {
		
		//Declare Controls
		tName = new TextField("Enter Name");
		lRace = new Label("Select a Race");
		lClass = new Label("Select a Class");
		fluff1 = new Label("");
		fluff2 = new Label("");
		bAasimar = new Button("Aasimar");
		bDragonborn = new Button("Dragonborn");
		bDwarf = new Button("Dwarf");
		bElf = new Button("Elf");
		bHalfling = new Button("Halfling");
		bHuman = new Button("Human");
		bNaga = new Button("Naga");
		bOrc = new Button("Orc");
		bArcher = new Button("Archer");
		bAssassin = new Button("Assassin");
		bMage = new Button("Mage");
		bWarrior = new Button("Warrior");
		bCreateCharacter = new Button("Create Character");
		
		gPane = new GridPane();
		gPane.add(tName, 0, 0, 2, 1);
		gPane.add(fluff1, 0, 1);
		gPane.add(fluff2, 1, 1);
		gPane.add(lClass, 1, 2);
		gPane.add(lRace, 0, 2);
		gPane.add(bArcher, 1, 3);
		gPane.add(bAssassin, 1, 4);
		gPane.add(bMage, 1, 5);
		gPane.add(bWarrior, 1, 6);
		gPane.add(bAasimar, 0, 3);
		gPane.add(bDragonborn, 0, 4);
		gPane.add(bDwarf, 0, 5);
		gPane.add(bElf, 0, 6);
		gPane.add(bHalfling, 0, 7);
		gPane.add(bHuman, 0, 8);
		gPane.add(bNaga, 0, 9);
		gPane.add(bOrc, 0, 10);
		
		vBox = new VBox();
		vBox.getChildren().addAll(bCreateCharacter);
		
		setRoot(new BorderPane());
		getRoot().setLeft(gPane);
		getRoot().setRight(vBox);
		setScene(new Scene(getRoot(), 1000, 550));

		setFormatting("CharacterCreator", null);
		getWindow().setScene(getScene());
	}
	
	@Override
	protected void formatContainers() {
		gPane.setAlignment(Pos.TOP_CENTER);
		gPane.setHgap(20);
		gPane.setVgap(10);
		
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setSpacing(450);
		
		
	}
	
	@Override
	protected void buttonAddAction() {
		bAasimar.setOnAction(e -> {
			playSound("Ding");
			sRace = "Aasimar";
			disableRaces();
			bAasimar.setStyle("-fx-background-color: lightgrey");
					});
		bDragonborn.setOnAction(e -> {
			playSound("Ding");
			sRace = "Dragonborn";
			disableRaces();
			bDragonborn.setStyle("-fx-background-color: lightgrey");
		});
		bDwarf.setOnAction(e -> {
			playSound("Ding");
			sRace = "Dwarf";
			disableRaces();
			bDwarf.setStyle("-fx-background-color: lightgrey");
		});
		bElf.setOnAction(e -> {
			playSound("Ding");
			sRace = "Elf";
			disableRaces();
			bElf.setStyle("-fx-background-color: lightgrey");
		});
		bHalfling.setOnAction(e -> {
			playSound("Ding");
			sRace = "Halfling";
			disableRaces();
			bHalfling.setStyle("-fx-background-color: lightgrey");
		});
		bHuman.setOnAction(e -> {
			playSound("Ding");
			sRace = "Human";
			disableRaces();
			bHuman.setStyle("-fx-background-color: lightgrey");
		});
		bNaga.setOnAction(e -> {
			playSound("Ding");
			sRace = "Naga";
			disableRaces();
			bNaga.setStyle("-fx-background-color: lightgrey");
		});
		bOrc.setOnAction(e -> {
			playSound("Ding");
			sRace = "Orc";
			disableRaces();
			bOrc.setStyle("-fx-background-color: lightgrey");
		});
		bArcher.setOnAction(e -> {
			playSound("Ding");
			classType = ClassType.Archer;
			disableClasses();
			bArcher.setStyle("-fx-background-color: lightgrey");
		});
		bAssassin.setOnAction(e -> {
			playSound("Ding");
			classType = ClassType.Assassin;
			disableClasses();
			bAssassin.setStyle("-fx-background-color: lightgrey");
		});
		bMage.setOnAction(e -> {
			playSound("Ding");
			classType = ClassType.Mage;
			disableClasses();
			bMage.setStyle("-fx-background-color: lightgrey");
		});
		bWarrior.setOnAction(e -> {
			playSound("Ding");
			classType = ClassType.Warrior;
			disableClasses();
			bWarrior.setStyle("-fx-background-color: lightgrey");
		});
		bCreateCharacter.setOnAction(e -> {
			sName = tName.getText();
			weapon = new Weapon("Fists");
			armor = new Armor("None");
			setMyClass(new PlayerClass(classType, sRace, sName, new Map(), weapon, armor));
			if (classType == ClassType.Mage)
				getMyClass().getSpells().add(new Spell("Fireball"));
			addStartingItems();
			getMyClass().setQuests(loadQuests(0));
			new ChooseSkillPointsScene(getWindow(), getMyClass(), 10).loadScene();
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
	
	private Quest[] loadQuests(int saveFile) {
		Quest[] quests = new Quest[8];
		quests[0] = new Quest("A Boarish Task", saveFile);
		quests[1] = new Quest("Bleeding Ears", saveFile);
		quests[2] = new Quest("Going Batty", saveFile);
		quests[3] = new Quest("V of V", saveFile);
		quests[4] = new Quest("Fe Fi Fo Fum", saveFile);
		quests[5] = new Quest("Climate Change", saveFile);
		quests[7] = new Quest("Magic Stones", saveFile);
		quests[6] = new Quest("A Wizard's Hat", saveFile);
		
		return quests;
	}
	
	private void addStartingItems() {
		getMyClass().getInventory().addItem(new Item("Gold", 25));
		getMyClass().getInventory().addItem(new Item("Water Bottle - Full", 1));
	}
	
	private void disableRaces() {
		bAasimar.setDisable(true);
		bDragonborn.setDisable(true);
		bDwarf.setDisable(true);
		bElf.setDisable(true);
		bHalfling.setDisable(true);
		bHuman.setDisable(true);
		bNaga.setDisable(true);
		bOrc.setDisable(true);
		
		bAasimar.setStyle("-fx-opacity: 1");
		bDragonborn.setStyle("-fx-opacity: 1");
		bDwarf.setStyle("-fx-opacity: 1");
		bElf.setStyle("-fx-opacity: 1");
		bHalfling.setStyle("-fx-opacity: 1");
		bHuman.setStyle("-fx-opacity: 1");
		bNaga.setStyle("-fx-opacity: 1");
		bOrc.setStyle("-fx-opacity: 1");
	}
	
	private void disableClasses() {
		bArcher.setDisable(true);
		bAssassin.setDisable(true);
		bMage.setDisable(true);
		bWarrior.setDisable(true);
		
		bArcher.setStyle("-fx-opacity: 1");
		bAssassin.setStyle("-fx-opacity: 1");
		bMage.setStyle("-fx-opacity: 1");
		bWarrior.setStyle("-fx-opacity: 1");
	}
	
	@Override
	protected void formatControls() {
		setFormat(tName);
		setFormat(lRace);
		setFormat(lClass);
		setFormat(fluff1);
		setFormat(fluff2);
		setFormat(bAasimar);
		setFormat(bDragonborn);
		setFormat(bDwarf);
		setFormat(bElf);
		setFormat(bHalfling);
		setFormat(bHuman);
		setFormat(bNaga);
		setFormat(bOrc);
		setFormat(bArcher);
		setFormat(bAssassin);
		setFormat(bMage);
		setFormat(bWarrior);
		setFormat(bCreateCharacter);
		
		GridPane.setHalignment(tName, HPos.CENTER);
		tName.setPrefWidth(320);
		tName.setPrefHeight(40);
		tName.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		bCreateCharacter.setPrefWidth(200);
		lRace.setPrefWidth(150);
		lRace.setStyle("-fx-background-color: white;");
		lRace.setPrefHeight(30);
		lClass.setPrefWidth(150);
		lClass.setStyle("-fx-background-color: white;");
		lClass.setPrefHeight(30);
		bCreateCharacter.setPrefWidth(200);
	}

	@Override
	protected void addTooltips() {
		bAasimar.setTooltip(new Tooltip("1 Str 2 Int"));
		bDragonborn.setTooltip(new Tooltip("2 Str 1 Def"));
		bDwarf.setTooltip(new Tooltip("1 Int 2 Def"));
		bElf.setTooltip(new Tooltip("2 Dex 1 Agi"));
		bHalfling.setTooltip(new Tooltip("2 Agi 1 Int"));
		bHuman.setTooltip(new Tooltip("1 Str 1 Dex 1 Int 1 Agi 1 Def"));
		bNaga.setTooltip(new Tooltip("2 Dex 1 Def"));
		bOrc.setTooltip(new Tooltip("3 Str"));
	}

}
