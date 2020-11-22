package DriverPackage;
	
import Scenes.WelcomeScene;
import javafx.application.Application;
import javafx.scene.media.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Driver extends Application {
	@Override
	public void start(Stage window) {
		
		try {
			
			Media stuff = new Media(this.getClass().getResource("/AudioFiles/Songs.wav").toString());
			MediaPlayer background = new MediaPlayer(stuff);
			background.setVolume(0.2);
			background.setOnEndOfMedia(new Runnable() {
			       @Override
				public void run() {
			         background.seek(Duration.ZERO);
			         background.play();
			       }
			   });
			background.play();
			window.setTitle("Paige's Game!");
			WelcomeScene scene = new WelcomeScene(window);
			scene.loadScene();
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
