package TapMePlusOne;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;

public class Video {
	File mediaFile;
	Media media;
	MediaPlayer mediaPlayer;
	MediaView mediaView;
	Button closeButton;
	public Video(String videoURL, Stage oldStage,Scene oldScene) throws MalformedURLException {
		this.mediaFile = new File("resources/ads.mp4");
		this.media = new Media(mediaFile.toURI().toURL().toString());
		this.mediaPlayer = new MediaPlayer(media);
		this.mediaView = new MediaView(mediaPlayer);
		this.mediaPlayer.setOnEndOfMedia(() -> {
			oldStage.setScene(oldScene);
		});
		this.mediaPlayer.setOnStopped(() -> {
			oldStage.setScene(oldScene);
		});
		closeButton = new Button("✕");
		    closeButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
		    closeButton.setAlignment(Pos.TOP_RIGHT);
		    closeButton.setOnAction(event -> {
		    	stop();
		    });

	}
	public void play() {
		this.mediaPlayer.play();
	}
	public Scene getScene() {
		Pane pane = new Pane();
		pane.getChildren().add(mediaView);

		pane.getChildren().add(closeButton);
		return new Scene(pane, 512, 720); 
	}
	public void stop() {
		this.mediaPlayer.stop();
	}
}
