package TapMePlusOne;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Help {
	private Pane pane;
	public Help(Scene menuScene) {
		pane = new Pane();
	    Image backgroundImage = new Image("file:resources/guideback.png");
        ImageView help_backgroundImageView = new ImageView(backgroundImage);
        help_backgroundImageView.setFitWidth(600);
        help_backgroundImageView.setFitHeight(1024);
        //help_backgroundImageView.setPreserveRatio(true);
        pane.getChildren().add(help_backgroundImageView);
	    
	    
	    Button backButton = new Button("");
	    backButton.setPrefSize(180, 40);
	    backButton.setLayoutX(200);
	    backButton.setLayoutY(720);
	    //backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16pt;");
	    backButton.setStyle("-fx-background-color: transparent;");
	    pane.getChildren().add(backButton);

	    // Back button onClick event
	    backButton.setOnAction(event -> {
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        // Set the menu scene as the current scene
	        stage.setScene(menuScene);
	        stage.show();
	    });

	}
	public Scene getScene() {
		return new Scene(this.pane, 600, 1024);
	}
}
