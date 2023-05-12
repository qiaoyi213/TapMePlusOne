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
		
		Menu menu = new Menu();
		primaryStage.setScene(menu.getScene());
		/*
		File mediaFile = new File("resources/ads.mp4");
		Media media = new Media(mediaFile.toURI().toURL().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);
		mediaPlayer.play();
		Pane pane = new Pane();
		pane.getChildren().add(mediaView);
		primaryStage.setScene(new Scene(pane, 512, 720));
		*/
		primaryStage.setTitle("Tap Me +1");
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
