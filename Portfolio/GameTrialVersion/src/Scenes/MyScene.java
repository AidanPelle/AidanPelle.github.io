package Scenes;

import Characters.Enemy;
import Characters.PlayerClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public abstract class MyScene {
	
	public MyScene(Stage window, PlayerClass myClass) {
		this.window = window;
		this.myClass = myClass;
	}
	
	private Stage window;
	private Scene scene;
	private PlayerClass myClass;
	private BorderPane root;
	
	public abstract void loadScene();
	protected abstract void formatControls();
	protected abstract void formatContainers();
	protected abstract void buttonAddAction();
	protected abstract void addTooltips();

	public void setFormat(TextField textfield) {
		textfield.setAlignment(Pos.CENTER);
		textfield.setPrefWidth(150);
	}
	
	public void setFormat(Button button){
		button.setAlignment(Pos.CENTER);
		button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
		button.setPrefWidth(150);
		button.setStyle("-fx-text-fill: green;");
	}
	
	public void setFormat(Label label) {
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
	}
	
	public void setFormat(Label label, int font) {
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, font));
	}
	
	public void setFormat(Label label, String color) {
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
		label.setStyle("-fx-text-fill: " + color + ";");
	}
	public void setFormatting(String imageSource, MyScene currentScene) {
		formatControls();
		formatContainers();
		buttonAddAction();
		addTooltips();
		scene.getStylesheets().add("DriverPackage/application.css");
		
		if (imageSource != null) {
			root.setBackground(new Background(new BackgroundImage(new Image("BackgroundFiles/" + imageSource 
				+ "Background.jpg", 1000, 1000, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			root.setPadding(new Insets(20));
		}
		
		if (currentScene != null)
		rootButtons(window, myClass, root, currentScene);
	}
	
	public void rootButtons(Stage window, PlayerClass myClass, Node root, MyScene previousScene) {
		root.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				root.setOnKeyPressed(i -> {
					if (i.getCode() == KeyCode.ESCAPE) {
						System.exit(0);
					}
				});
			}
			else if (e.getCode() == KeyCode.I) {
				new InventoryScene(window, myClass, previousScene).loadScene();
			}
			else if (e.getCode() == KeyCode.H) {
				new HelpScene(window, previousScene).loadScene();
			}
			
		});
	}
	
	public ProgressBar getHealthBar() {
		ProgressBar healthBar = new ProgressBar();
		healthBar.setStyle("-fx-accent: red");
		healthBar.setProgress(((double) myClass.getCurrentHealth())/myClass.getMaxHealth());
		healthBar.setPrefSize(100, 20);
		return healthBar;
	}
	
	public ProgressBar getHealthBar(Enemy e) {
		ProgressBar healthBar = new ProgressBar();
		healthBar.setStyle("-fx-accent: red");
		healthBar.setProgress(((double) e.getCurrentHP())/e.getMaxHP());
		healthBar.setPrefSize(100, 20);
		return healthBar;
	}
	
	public ProgressBar getManaBar() {
		ProgressBar manaBar = new ProgressBar();
		manaBar.setStyle("-fx-accent: blue");
		manaBar.setProgress(((double)myClass.getCurrentMana())/myClass.getMaxMana());
		manaBar.setPrefSize(100, 20);
		return manaBar;
	}
	
	public ProgressBar getExperienceBar() {
		ProgressBar xpBar = new ProgressBar();
		xpBar.setStyle("-fx-accent: yellow");
		xpBar.setProgress(((double)myClass.getCurrentExperience())/myClass.getMaxExperience());
		xpBar.setPrefSize(100, 20);
		return xpBar;
	}
	
	public ProgressBar getWaterBar() {
		ProgressBar waterBar = new ProgressBar();
		waterBar.setStyle("-fx-accent: lightblue");
		waterBar.setProgress(((double)myClass.getCurrentWater())/myClass.getMaxWater());
		waterBar.setPrefSize(100, 20);
		return waterBar;
	}
	
	public void deathCheck() {
		if (myClass.getCurrentHealth() <= 0) {
			new DeathScene(window, myClass, "You have 0 health points left!").loadScene();
		}
		else if (myClass.getCurrentWater() <= 0) {
			new DeathScene(window, myClass, "You ran out of water!").loadScene();
		}
		else if (myClass.getInventory().findItem("Potato") == null) {
			new DeathScene(window, myClass, "You ran out of food!").loadScene();
		}
	}
	
	public void playSound(String string) {
		AudioClip test = new AudioClip(this.getClass().getResource("/AudioFiles/" + string + ".wav").toString());
		test.play();
	}
	
	public void playSound(String string, double volume) {
		AudioClip test = new AudioClip(this.getClass().getResource("/AudioFiles/" + string + ".wav").toString());
		test.play(volume);
	}
	
	public void playLongSound(String string) {
		Media stuff = new Media(this.getClass().getResource("/AudioFiles/" + string + ".wav").toString());
		MediaPlayer test = new MediaPlayer(stuff);
		test.play();
	}
	
	public void playLongSound(String string, double volume) {
		Media stuff = new Media(this.getClass().getResource("/AudioFiles/" + string + ".wav").toString());
		MediaPlayer test = new MediaPlayer(stuff);
		test.setVolume(volume);
		test.play();
	}
	
	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public PlayerClass getMyClass() {
		return myClass;
	}

	public void setMyClass(PlayerClass myClass) {
		this.myClass = myClass;
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

}
