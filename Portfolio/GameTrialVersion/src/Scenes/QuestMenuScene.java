package Scenes;


import Characters.PlayerClass;
import Items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class QuestMenuScene extends MyScene{
	
	private GridPane gPane;
	private Button b1, b2, b3, b4, b5, b6, b7, b8, bExit;
	private Label lOutput;
	private MyScene previousScene;
	private String sOutput;

	public QuestMenuScene(Stage window, PlayerClass myClass, MyScene previousScene) {
		super(window, myClass);
		this.previousScene = previousScene;
	}

	@Override
	public void loadScene() {
		setRoot(new BorderPane());
		
		b1 = new Button(getMyClass().getQuests()[0].getName());
		b2 = new Button(getMyClass().getQuests()[1].getName());
		b3 = new Button(getMyClass().getQuests()[2].getName());
		b4 = new Button(getMyClass().getQuests()[3].getName());
		b5 = new Button(getMyClass().getQuests()[4].getName());
		b6 = new Button(getMyClass().getQuests()[5].getName());
		b7 = new Button(getMyClass().getQuests()[6].getName());
		b8 = new Button(getMyClass().getQuests()[7].getName());
		bExit = new Button("Exit");
		
		gPane = new GridPane();
		if (!getMyClass().getQuests()[0].isCompleted())
			gPane.add(b1, 0, 0);
		if (!getMyClass().getQuests()[1].isCompleted())
			gPane.add(b2, 1, 0);
		if (!getMyClass().getQuests()[2].isCompleted())
			gPane.add(b3, 2, 0);
		if (!getMyClass().getQuests()[3].isCompleted())
			gPane.add(b4, 3, 0);
		if (!getMyClass().getQuests()[4].isCompleted())
			gPane.add(b5, 0, 1);
		if (!getMyClass().getQuests()[5].isCompleted())
			gPane.add(b6, 1, 1);
		if (!getMyClass().getQuests()[6].isCompleted())
			gPane.add(b7, 2, 1);
		if (!getMyClass().getQuests()[7].isCompleted())
			gPane.add(b8, 3, 1);
		
		getRoot().setCenter(gPane);
		getRoot().setBottom(bExit);
		
		setScene(new Scene(getRoot(), 1000, 550));
		setFormatting("Quest", this);
		getWindow().setScene(getScene());
	}

	@Override
	protected void formatControls() {
		setFormat(b1);
		setFormat(b2);
		setFormat(b3);
		setFormat(b4);
		setFormat(b5);
		setFormat(b6);
		setFormat(b7);
		setFormat(b8);
		setFormat(bExit);
		
	}

	@Override
	protected void formatContainers() {
		gPane.setVgap(30);
		gPane.setHgap(30);
		gPane.setAlignment(Pos.CENTER);
		
		getRoot().setPadding(new Insets(40));
	}

	@Override
	protected void buttonAddAction() {
		b1.setOnAction(e -> {genericButton(0);});
		b2.setOnAction(e -> {genericButton(1);});
		b3.setOnAction(e -> {genericButton(2);});
		b4.setOnAction(e -> {genericButton(3);});
		b5.setOnAction(e -> {genericButton(4);});
		b6.setOnAction(e -> {genericButton(5);});
		b7.setOnAction(e -> {genericButton(6);});
		b8.setOnAction(e -> {genericButton(7);});
		bExit.setOnAction(e -> {
			previousScene.loadScene();
		});
	}
	
	private void genericButton(int questNumber) {
		playSound("Page Flip");
		gPane.getChildren().clear();
		if (getMyClass().getInventory().findItem(getMyClass().getQuests()[questNumber].getRequiredItem().getName()) != null
				&& getMyClass().getInventory().findItem(getMyClass().getQuests()[questNumber].getRequiredItem().getName()).getAmount() 
				> getMyClass().getQuests()[questNumber].getRequiredItem().getAmount()) {
			sOutput = getMyClass().getQuests()[questNumber].getResponse();
			sOutput += "\nYou gained: " + getMyClass().getQuests()[questNumber].getXpReward() + " experience!";
			sOutput += "\nYou gained: " + getMyClass().getQuests()[questNumber].getGoldReward() + " gold!";
			getMyClass().setCurrentExperience(getMyClass().getCurrentExperience() + getMyClass().getQuests()[questNumber].getXpReward());
			if (getMyClass().getCurrentExperience() >= getMyClass().getMaxExperience() && getMyClass().getLevel() < 20) {
				sOutput += "\nYou levelled up!";
				getMyClass().setCurrentExperience(getMyClass().getCurrentExperience() - getMyClass().getMaxExperience());
				getMyClass().setLevel(getMyClass().getLevel() + 1);
				getMyClass().setMaxExperience(getMyClass().getLevel() * 100);
				
				Button bLevelUp = new Button("Level Up");
				setFormat(bLevelUp);
				bLevelUp.setOnAction(i -> {
					new ChooseSkillPointsScene(getWindow(), getMyClass(), 2, previousScene).loadScene();
				});
				getRoot().setBottom(bLevelUp);
			}
			lOutput = new Label(sOutput);
			getMyClass().getQuests()[questNumber].setCompleted(true);
			getMyClass().getInventory().addItem(new Item("Gold", getMyClass().getQuests()[questNumber].getGoldReward()));
			getMyClass().getInventory().removeItem(getMyClass().getQuests()[questNumber].getRequiredItem());
		}
		else {
			lOutput = new Label(getMyClass().getQuests()[questNumber].getRequest());
		}
		setFormat(lOutput);
		lOutput.setPrefWidth(600);
		lOutput.setWrapText(true);
		gPane.add(lOutput, 0, 0);
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
