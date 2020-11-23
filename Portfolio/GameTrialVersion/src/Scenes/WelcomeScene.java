package Scenes;


import Characters.PlayerClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WelcomeScene extends MyScene{
	
	private Label lWelcome;
	private Button bStartGame, bExitGame;
	private VBox vButtons;
	
	public WelcomeScene(Stage window) {
		super(window, new PlayerClass());
	}
	
	@Override
	public void loadScene() {
		playSound("Trumpet");
		//Adding buttons and content
		lWelcome = new Label("Welcome to A Generic RPG Game!");
		bStartGame = new Button("Start Game");
		bExitGame = new Button("Exit Game");
		//Organizing buttons for left hand side
		vButtons = new VBox();
		vButtons.getChildren().add(bStartGame);
		vButtons.getChildren().add(bExitGame);
		
		
		//Setting up BorderPane
		setRoot(new BorderPane());
		getRoot().setLeft(vButtons);
		getRoot().setTop(lWelcome);

		setScene(new Scene(getRoot(), 1000, 600));
		
		setFormatting("OpeningScene", null);
		getWindow().setScene(getScene());
	}
	
	@Override
	protected void buttonAddAction() {
		bStartGame.setOnAction(e -> new CharacterCreatorScene(getWindow()).loadScene());
		bExitGame.setOnAction(e -> System.exit(0));
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
	
	@Override
	protected void formatContainers() {
		vButtons.setAlignment(Pos.CENTER_LEFT);
		vButtons.setSpacing(20);
		BorderPane.setAlignment(lWelcome, Pos.TOP_CENTER);
		BorderPane.setMargin(vButtons, new Insets(30));
		BorderPane.setMargin(lWelcome, new Insets(10));
	}
	
	@Override
	protected void formatControls() {
		lWelcome.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
		setFormat(bStartGame);
		setFormat(bExitGame);
	}

	@Override
	protected void addTooltips() {
		
	}
	
	

}
