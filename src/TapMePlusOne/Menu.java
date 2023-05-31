package TapMePlusOne;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Color;
import java.io.File;
import java.util.List;

import javafx.scene.Scene;

public class Menu {
	private Scene menuScene;
	private Pane menuPane;

	public Menu(){
		menuPane = new Pane();
		// Background image
		
        Image backgroundImage = new Image("file:resources/menu_ver3.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(1024);
        menuPane.getChildren().add(backgroundImageView);
        // Start button
        Button startBtn = new Button("");
        startBtn.setPrefSize(200, 50);
        startBtn.setLayoutX(250);
        startBtn.setLayoutY(400);
        //startBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20pt;");
        startBtn.setStyle("-fx-background-color: transparent;");
        menuPane.getChildren().add(startBtn);
        // Start button onClick event
        
        startBtn.setOnAction(event -> {

            Game game = new Game();
            Stage stage = (Stage) startBtn.getScene().getWindow();
            stage.setScene(game.getScene());
            stage.show();
        });
        Button continueBtn = new Button("");
        continueBtn.setPrefSize(200, 50);
        continueBtn.setLayoutX(200);
        continueBtn.setLayoutY(325);
        //continueBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20pt;");
        continueBtn.setStyle("-fx-background-color: transparent;");
        menuPane.getChildren().add(continueBtn);
        continueBtn.setOnAction(event -> {
        	Game game = new Game();
            List<Object> record = Persistence.readPad();  
            game.loadGame((int[][])record.get(0), (int)record.get(1), (int)record.get(2));
            Stage stage = (Stage) startBtn.getScene().getWindow();
            stage.setScene(game.getScene());
            stage.show();
        });
        // Help button
        Button helpBtn = new Button("");
        helpBtn.setPrefSize(200, 50);
        helpBtn.setLayoutX(200);
        helpBtn.setLayoutY(475);
        //helpBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20pt;");
        helpBtn.setStyle("-fx-background-color: transparent;");
        menuPane.getChildren().add(helpBtn);
    	// Help button onClick event
        helpBtn.setOnAction(event -> {
            Stage stage = (Stage) helpBtn.getScene().getWindow();
            // Create a new scene for the help page and set it as the current scene
            Scene helpScene = createHelpScene();
            stage.setScene(helpScene);
            stage.show();
        });
        Button boardBtn = new Button("");
        //boardBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14pt;");
        boardBtn.setStyle("-fx-background-color: transparent;");
        boardBtn.setPrefSize(200, 50);
        boardBtn.setLayoutX(200);
        boardBtn.setLayoutY(550);
        menuPane.getChildren().add(boardBtn);
        boardBtn.setOnAction(event -> {
        	try {
				ScoreBoard sb = new ScoreBoard((Scene)this.getScene());
				Stage stage = (Stage)this.getScene().getWindow();
				Scene sbScene = sb.getScene();
				stage.setScene(sbScene);
				stage.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        menuScene = new Scene(menuPane, 600, 1024);
    }
	
	private Scene createHelpScene() {
		Pane helpPane = new Pane();
	    Image backgroundImage = new Image("file:resources/guideback.png");
        ImageView help_backgroundImageView = new ImageView(backgroundImage);
        help_backgroundImageView.setFitWidth(600);
        help_backgroundImageView.setFitHeight(1024);
        //help_backgroundImageView.setPreserveRatio(true);
        helpPane.getChildren().add(help_backgroundImageView);
	    
	    
	    Button backButton = new Button("");
	    backButton.setPrefSize(180, 40);
	    backButton.setLayoutX(200);
	    backButton.setLayoutY(720);
	    //backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16pt;");
	    backButton.setStyle("-fx-background-color: transparent;");
	    helpPane.getChildren().add(backButton);

	    // Back button onClick event
	    backButton.setOnAction(event -> {
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        // Set the menu scene as the current scene
	        stage.setScene(menuScene);
	        stage.show();
	    });

	    Scene helpScene = new Scene(helpPane, 600, 1024);
	    return helpScene;
	}
	
	public Scene getScene() {
		return this.menuScene;
	}
	
}
