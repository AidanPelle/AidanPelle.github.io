package Scenes;

import Characters.PlayerClass;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InnScene extends MyScene{
	
	private Label lInn;
	private Button bExit;
	private boolean outpost = false;
	
	public InnScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}
	
	public InnScene(Stage window, PlayerClass myClass, boolean outpost) {
		super(window, myClass);
		this.outpost = outpost;
	}
	
	@Override
	public void loadScene() {
		
		lInn = new Label("You enter the inn and are quickly directed to the nearest available room. "
				+ "After a lovely night's rest, you wake up feeling refreshed. "
				+ "\n-HP restored to full! \n-Mana restored to full!");
		bExit = new Button("Town");
		
		setRoot(new BorderPane());
		getRoot().setTop(lInn);
		getRoot().setBottom(bExit);
		
		setScene(new Scene(getRoot(), 1000, 600));
		
		getMyClass().setCurrentHealth(getMyClass().getMaxHealth());
		getMyClass().setCurrentMana(getMyClass().getMaxMana());
		setFormatting("Inn", this);
		getWindow().setScene(getScene());
	}

	@Override
	protected void formatControls() {
		setFormat(lInn);
		lInn.setWrapText(true);
		lInn.setStyle("-fx-text-fill: white;");
		setFormat(bExit);
	}

	@Override
	protected void formatContainers() {
	}

	@Override
	protected void buttonAddAction() {
		bExit.setOnAction(e -> {
			if (outpost)
				new OutpostScene(getWindow(), getMyClass()).loadScene();
			else
				new TownScene(getWindow(), getMyClass()).loadScene();
		});
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

}
