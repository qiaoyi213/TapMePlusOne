package TapMePlusOne;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
public class TapMePlusOne extends Application{
	@Override
	public void start(Stage primaryStage) throws MalformedURLException {
		// Create the animation pane
	    Pane animationPane = new Pane();
	    Scene animationScene = new Scene(animationPane, 600, 1024);
	    primaryStage.setScene(animationScene);
	    primaryStage.show();

	    // Create the media player
	    File mediaFile = new File("resources/startanimation.mp4");
	    Media media = new Media(mediaFile.toURI().toURL().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(media);
	    MediaView mediaView = new MediaView(mediaPlayer);
	    mediaView.setFitWidth(600);
	    mediaView.setFitHeight(1024);
	    animationPane.getChildren().add(mediaView);

	    // Play the video
	    mediaPlayer.play();

	    // Switch to Menu after the video duration
	    mediaPlayer.setOnEndOfMedia(() -> {
	        primaryStage.setScene(new Menu().getScene());
	    });
	    primaryStage.setMaxWidth(600);
	    primaryStage.setMinWidth(600);
	    primaryStage.setMaxHeight(1024);
	    primaryStage.setMinHeight(1024);
		primaryStage.setTitle("Tap Me +1");
		
		
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
