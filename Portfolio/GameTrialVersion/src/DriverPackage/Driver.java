package DriverPackage;
	
import java.nio.file.Paths;

import Scenes.WelcomeScene;
import javafx.application.Application;
import javafx.scene.media.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Driver extends Application {
	@Override
	public void start(Stage window) {
		
		try {
			music();
			window.setTitle("RPG Game!");
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
	
	MediaPlayer mediaPlayer;
	public void music() {
		String s = "Resources/AudioFiles/Songs.wav";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.setVolume(0.2);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       @Override
			public void run() {
		         mediaPlayer.seek(Duration.ZERO);
		         mediaPlayer.play();
		       }
		   });		
	}
}
