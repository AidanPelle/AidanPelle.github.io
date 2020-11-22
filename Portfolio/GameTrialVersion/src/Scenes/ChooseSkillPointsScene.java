package Scenes;

import Characters.PlayerClass;
import Characters.PlayerClass.ClassType;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChooseSkillPointsScene extends MyScene{

	private int remainingPoints;
	private final int maxPoints = 20;
	private Label lRaceClass, lRaceStats, lStrength, lDexterity, lIntelligence, lAgility, lDefense, lRemainingPoints, lNotif;
	private Button bAddStrength, bAddDexterity, bAddIntelligence, bAddAgility, bAddDefense, bBegin;
	private Button bSubtractStrength, bSubtractDexterity, bSubtractIntelligence, bSubtractAgility, bSubtractDefense;
	private GridPane gPane;
	private Image iRace, iClass;
	private ImageView iVRace, iVClass;
	private Group root;
	private int baseStrength, baseDexterity, baseIntelligence, baseAgility, baseDefense;
	private boolean newCharacter;
	private MyScene previousScene = null;
	
	public ChooseSkillPointsScene(Stage window, PlayerClass myClass, int remainingPoints) {
		super(window, myClass);
		this.remainingPoints = remainingPoints;
	}
	
	public ChooseSkillPointsScene(Stage window, PlayerClass myClass, int remainingPoints, MyScene previousScene) {
		super(window, myClass);
		this.remainingPoints = remainingPoints;
		this.previousScene = previousScene;
	}

	@Override
	public void loadScene() {
		
		
		raceClassLabel();
		lRaceStats = new Label("");
		
		if (previousScene == null) racialStats();
		
		lStrength = new Label("Strength: " + getMyClass().getStrength());
		lDexterity = new Label("Dexterity: " + getMyClass().getDexterity());
		lIntelligence = new Label("Intelligence: " + getMyClass().getIntelligence());
		lAgility = new Label("Agility: " + getMyClass().getAgility());
		lDefense = new Label("Defense: " + getMyClass().getDefense());
		bAddStrength = new Button("+1");
		bAddDexterity = new Button("+1");
		bAddIntelligence = new Button("+1");
		bAddAgility = new Button("+1");
		bAddDefense = new Button("+1");
		bSubtractStrength = new Button("-1");
		bSubtractDexterity = new Button("-1");
		bSubtractIntelligence = new Button("-1");
		bSubtractAgility = new Button("-1");
		bSubtractDefense = new Button("-1");
		lRemainingPoints = new Label(remainingPoints + " points left");
		lNotif = new Label("");
		bBegin = new Button("Adventure!");
		
		setBasePoints();
		
		//creating and formatting gpane
		gPane = new GridPane();
		
		gPane.add(lRaceClass, 0, 0, 4, 1);
		gPane.add(lRaceStats, 0, 1, 4, 1);
		gPane.add(lStrength, 0, 2, 2, 1);
		gPane.add(bAddStrength, 2, 2);
		gPane.add(bSubtractStrength, 3, 2);
		gPane.add(lDexterity, 0, 3, 2, 1);
		gPane.add(bAddDexterity, 2, 3);
		gPane.add(bSubtractDexterity, 3, 3);
		gPane.add(lIntelligence, 0, 4, 2, 1);
		gPane.add(bAddIntelligence, 2, 4);
		gPane.add(bSubtractIntelligence, 3, 4);
		gPane.add(lAgility, 0, 5, 2, 1);
		gPane.add(bAddAgility, 2, 5);
		gPane.add(bSubtractAgility, 3, 5);
		gPane.add(lDefense, 0, 6, 2, 1);
		gPane.add(bAddDefense, 2, 6);
		gPane.add(bSubtractDefense, 3, 6);
		gPane.add(lRemainingPoints, 0, 7, 2, 1);
		gPane.add(lNotif, 2, 7, 2, 1);
		
		
		iRace = new Image("PlayerImage/" + getMyClass().getsRace() + ".jpg");
		iClass = new Image("PlayerImage/" + getMyClass().getClassType() + ".jpg");
		iVRace = new ImageView(iRace);
		iVClass = new ImageView(iClass);
		
		
		
		root = new Group(iVRace, iVClass, gPane, bBegin);
		
		setScene(new Scene(root, 1000, 450));
		setFormatting(null, null);
		getWindow().setScene(getScene());
	}
	
	private boolean isVowel(char c) {
		c = Character.toLowerCase(c);
		if (c == 'a' || c == 'i' || c == 'o' || c == 'u') 
			return true;
		else return false;
	}
	
	private void raceClassLabel() {
		if (isVowel(getMyClass().getsRace().charAt(0)))
			lRaceClass = new Label("You picked an: " + getMyClass().getsRace() + " " + getMyClass().getClassType() + "!");
		
		else
			lRaceClass = new Label("You picked a: " + getMyClass().getsRace() + " " + getMyClass().getClassType() + "!");
	}
	
	private void setBasePoints() {
		baseStrength = getMyClass().getStrength();
		baseDexterity = getMyClass().getDexterity();
		baseIntelligence = getMyClass().getIntelligence();
		baseAgility = getMyClass().getAgility();
		baseDefense = getMyClass().getDefense();
	}
	
	@Override
	protected void formatContainers() {
		gPane.setVgap(10);
		gPane.setHgap(10);
		gPane.setLayoutX(320);
		gPane.setAlignment(Pos.CENTER);
		gPane.setMaxWidth(380);
		
		iVRace.setX(000);
		iVRace.setY(0);
		iVRace.setFitHeight(450);
		iVRace.setFitWidth(300);
		iVClass.setX(700);
		iVClass.setY(0);
		iVClass.setFitHeight(450);
		iVClass.setFitWidth(300);
		
		bBegin.setLayoutX(425);
		bBegin.setLayoutY(400);
		
	}
	
	@Override
	protected void buttonAddAction() {
		bAddStrength.setOnAction(e -> { 
			if (remainingPoints == 0) {
				createNotif("Out of points!");
			}
			else if (getMyClass().getStrength() == 20) {
				createNotif("Max reached!");
			}
			else {
				getMyClass().setStrength(getMyClass().getStrength() + 1);
				lStrength.setText("Strength: " + getMyClass().getStrength());
				lRemainingPoints.setText(--remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bAddDexterity.setOnAction(e -> { 
			if (remainingPoints == 0) {
				createNotif("Out of points!");
			}
			else if (getMyClass().getDexterity() == 20) {
				createNotif("Max reached!");
			}
			else {
				getMyClass().setDexterity(getMyClass().getDexterity() + 1);
				lDexterity.setText("Dexterity: " + getMyClass().getDexterity());
				lRemainingPoints.setText(--remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bAddIntelligence.setOnAction(e -> { 
			if (remainingPoints == 0) {
				createNotif("Out of points!");
			}
			else if (getMyClass().getIntelligence() == 20) {
				createNotif("Max reached!");
			}
			else {
				getMyClass().setIntelligence(getMyClass().getIntelligence() + 1);
				lIntelligence.setText("Intelligence: " + getMyClass().getIntelligence());
				lRemainingPoints.setText(--remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bAddAgility.setOnAction(e -> { 
			if (remainingPoints == 0) {
				createNotif("Out of points!");
			}
			else if (getMyClass().getAgility() == 20) {
				createNotif("Max reached!");
			}
			else {
				getMyClass().setAgility(getMyClass().getAgility() + 1);
				lAgility.setText("Agility: " + getMyClass().getAgility());
				lRemainingPoints.setText(--remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bAddDefense.setOnAction(e -> { 
			if (remainingPoints == 0) {
				createNotif("Out of points!");
			}
			else if (getMyClass().getDefense() == 20) {
				createNotif("Max reached!");
			}
			else {
				getMyClass().setDefense(getMyClass().getDefense() + 1);
				lDefense.setText("Defense: " + getMyClass().getDefense());
				lRemainingPoints.setText(--remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bSubtractStrength.setOnAction(e -> {
			if (getMyClass().getStrength() == baseStrength) {
				createNotif("Min reached!");
			}
			else {
				getMyClass().setStrength(getMyClass().getStrength() - 1);
				lStrength.setText("Strength: " + getMyClass().getStrength());
				lRemainingPoints.setText(++remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bSubtractDexterity.setOnAction(e -> {
			if (getMyClass().getDexterity() == baseDexterity) {
				createNotif("Min reached!");
			}
			else {
				getMyClass().setDexterity(getMyClass().getDexterity() - 1);
				lDexterity.setText("Dexterity: " + getMyClass().getDexterity());
				lRemainingPoints.setText(++remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bSubtractIntelligence.setOnAction(e -> {
			if (getMyClass().getIntelligence() == baseIntelligence) {
				createNotif("Min reached!");
			}
			else {
				getMyClass().setIntelligence(getMyClass().getIntelligence() - 1);
				lIntelligence.setText("Intelligence: " + getMyClass().getIntelligence());
				lRemainingPoints.setText(++remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bSubtractAgility.setOnAction(e -> {
			if (getMyClass().getAgility() == baseAgility) {
				createNotif("Min reached!");
			}
			else {
				getMyClass().setAgility(getMyClass().getAgility() - 1);
				lAgility.setText("Agility: " + getMyClass().getAgility());
				lRemainingPoints.setText(++remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bSubtractDefense.setOnAction(e -> {
			if (getMyClass().getDefense() == baseDefense) {
				createNotif("Min reached!");
			}
			else {
				getMyClass().setDefense(getMyClass().getDefense() - 1);
				lDefense.setText("Defense: " + getMyClass().getDefense());
				lRemainingPoints.setText(++remainingPoints + " points left");
				playSound("Ding");
			}
		});
		bBegin.setOnAction(e -> {
			int maxMana = (int) ((100 + getMyClass().getLevel() * 3) * (1 + (getMyClass().getIntelligence() - 10) / 20.0));
			if (getMyClass().getClassType() == ClassType.Mage) getMyClass().setMaxMana((int) (maxMana * 1.5)); 
			else getMyClass().setMaxMana(maxMana);
			getMyClass().setCurrentMana(getMyClass().getMaxMana());

			if (previousScene == null) new BeginGameScene(getWindow(), getMyClass()).loadScene();
			
			else new GainSpellsScene(getWindow(), getMyClass(), previousScene).loadScene();
		});
	}
	
	
	@Override
	protected void formatControls() {
		setFormat(lRaceClass);
		setFormat(lRaceStats);
		setFormat(bAddStrength);
		setFormat(bAddDexterity);
		setFormat(bAddIntelligence);
		setFormat(bAddAgility);
		setFormat(bAddDefense);
		setFormat(lNotif);
		setFormat(bBegin);
		setFormat(bSubtractStrength);
		setFormat(bSubtractDexterity);
		setFormat(bSubtractIntelligence);
		setFormat(bSubtractAgility);
		setFormat(bSubtractDefense);
		
		bAddStrength.setPrefWidth(70);
		bAddDexterity.setPrefWidth(70);
		bAddIntelligence.setPrefWidth(70);
		bAddAgility.setPrefWidth(70);
		bAddDefense.setPrefWidth(70);
		bSubtractStrength.setPrefWidth(70);
		bSubtractDexterity.setPrefWidth(70);
		bSubtractIntelligence.setPrefWidth(70);
		bSubtractAgility.setPrefWidth(70);
		bSubtractDefense.setPrefWidth(70);
		
		lStrength.setAlignment(Pos.CENTER);
		lDexterity.setAlignment(Pos.CENTER);
		lIntelligence.setAlignment(Pos.CENTER);
		lAgility.setAlignment(Pos.CENTER);
		lDefense.setAlignment(Pos.CENTER);
		
		
		lStrength.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		lDexterity.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		lIntelligence.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		lAgility.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		lDefense.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		lRemainingPoints.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20));

		lRaceStats.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
		lRaceStats.setWrapText(true);
		lRaceClass.setWrapText(true);
		
		GridPane.setHalignment(lRaceClass, HPos.CENTER);
		GridPane.setHalignment(lRaceStats, HPos.CENTER);
		GridPane.setHalignment(bBegin, HPos.CENTER);
		
	}
	
	
	private void racialStats() {
		switch (getMyClass().getsRace()) {
		case("Aasimar"): {
			lRaceStats.setText("You gained: 1 Strength and 2 Intelligence");
			getMyClass().setStrength(getMyClass().getStrength() + 1);
			getMyClass().setIntelligence(getMyClass().getIntelligence() + 2);
			break;
		}case("Dragonborn"): {
			lRaceStats.setText("You gained: 2 Strength and 1 Defense");
			getMyClass().setStrength(getMyClass().getStrength() + 2);
			getMyClass().setDefense(getMyClass().getDefense() + 1);
			break;
		}case("Dwarf"): {
			lRaceStats.setText("You gained: 1 Intelligence and 2 Defense");
			getMyClass().setIntelligence(getMyClass().getIntelligence() + 1);
			getMyClass().setDefense(getMyClass().getDefense() + 2);
			break;
		}case("Elf"): {
			lRaceStats.setText("You gained: 2 Dexterity and 1 Agility");
			getMyClass().setDexterity(getMyClass().getDexterity() + 2);
			getMyClass().setIntelligence(getMyClass().getIntelligence() + 1);
			break;
		}case("Halfling"): {
			lRaceStats.setText("You gained: 2 Agility and 1 Intelligence");
			getMyClass().setDexterity(getMyClass().getDexterity() + 1);
			getMyClass().setAgility(getMyClass().getAgility() + 2);
			break;
		}case("Human"): {
			lRaceStats.setText("You gained: 1 to all stats");
			getMyClass().setStrength(getMyClass().getStrength() + 1);
			getMyClass().setDexterity(getMyClass().getDexterity() + 1);
			getMyClass().setIntelligence(getMyClass().getIntelligence() + 1);
			getMyClass().setAgility(getMyClass().getAgility() + 1);
			getMyClass().setDefense(getMyClass().getDefense() + 1);
			break;
		}case("Naga"): {
			lRaceStats.setText("You gained: 2 Dexterity and 1 Defense");
			getMyClass().setDexterity(getMyClass().getDexterity() + 2);
			getMyClass().setDefense(getMyClass().getDefense() + 1);
			break;
		}case("Orc"): {
			lRaceStats.setText("You gained: 3 Strength");
			getMyClass().setStrength(getMyClass().getStrength() + 3);
			break;
		}
		}
	}

	public int getRemainingPoints() {
		return remainingPoints;
	}

	public void setRemainingPoints(int remainingPoints) {
		this.remainingPoints = remainingPoints;
	}

	public int getMaxpoints() {
		return maxPoints;
	}

	public boolean isNewCharacter() {
		return newCharacter;
	}

	public void setNewCharacter(boolean newCharacter) {
		this.newCharacter = newCharacter;
	}

	@Override
	public void addTooltips() {
		bAddStrength.setTooltip(new Tooltip("Max 20 points"));
		bAddDexterity.setTooltip(new Tooltip("Max 20 points"));
		bAddIntelligence.setTooltip(new Tooltip("Max 20 points"));
		bAddAgility.setTooltip(new Tooltip("Max 20 points"));
		bAddDefense.setTooltip(new Tooltip("Max 20 points"));
	}
	
	public void createNotif(String s) {
		lNotif.setText(s);
		PauseTransition visiblePause = new PauseTransition(
		        Duration.seconds(1)
		);
		visiblePause.setOnFinished(
		        event -> lNotif.setText("")
		);
		visiblePause.play();
	}
	
	
}
