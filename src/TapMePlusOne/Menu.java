package TapMePlusOne;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Color;

import javafx.scene.Scene;

public class Menu {
	private Scene menuScene;
	private Pane menuPane;
	private Scene createHelpScene() {
	    Pane helpPane = new Pane();
	    Image backgroundImage = new Image("file:resources/help_background.png");
        ImageView help_backgroundImageView = new ImageView(backgroundImage);
        help_backgroundImageView.setFitWidth(600);
        help_backgroundImageView.setFitHeight(1024);
        helpPane.getChildren().add(help_backgroundImageView);
	    /*Text helpText = new Text("App Guide");
	    helpText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
	    helpText.setX(175);
	    helpText.setY(25);
	    helpPane.getChildren().add(helpText);
	    */
	    
	    Text guideText = new Text("1. 點擊任一方塊，方塊+1 \n2. 消除三塊以上相同數字的方塊");
	    guideText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
	    guideText.setFill(Paint.valueOf("WHITE"));   
	    guideText.setX(125);
	    guideText.setY(620);
	    helpPane.getChildren().add(guideText);
	    
	    
	    ImageView imageView = new ImageView(new Image("file:resources/guide.jpeg"));
	    imageView.setLayoutX(131);
	    imageView.setLayoutY(100);
	    imageView.setFitWidth(250);
	    imageView.setFitHeight(500);
	    helpPane.getChildren().add(imageView);
	    
	    Button backButton = new Button("Back");
	    backButton.setPrefSize(80, 40);
	    backButton.setLayoutX(216);
	    backButton.setLayoutY(660);
	    backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16pt;");
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

	public Menu(){
		menuPane = new Pane();
		// Background image
        Image backgroundImage = new Image("file:resources/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(1024);
        menuPane.getChildren().add(backgroundImageView);

		// Title
        /*
        Text title = new Text("Tap +1");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 68));
        title.setX(150);
        title.setY(250);
        title.setFill(Paint.valueOf("WHITE"));    
        menuPane.getChildren().add(title);
        */
        
        // Start button
        Button startBtn = new Button("Start");
        startBtn.setPrefSize(100, 50);
        startBtn.setLayoutX(250);
        startBtn.setLayoutY(450);
        startBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20pt;");
        menuPane.getChildren().add(startBtn);
        // Start button onClick event
        startBtn.setOnAction(event -> {
            Game game = new Game();
            Stage stage = (Stage) startBtn.getScene().getWindow();
            stage.setScene(game.getScene());
            stage.show();
        });
        
        // Help button
        Button helpBtn = new Button("Help");
        helpBtn.setPrefSize(100, 50);
        helpBtn.setLayoutX(250);
        helpBtn.setLayoutY(550);
        helpBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20pt;");
        menuPane.getChildren().add(helpBtn);
    	// Help button onClick event
        helpBtn.setOnAction(event -> {
            Stage stage = (Stage) helpBtn.getScene().getWindow();
            // Create a new scene for the help page and set it as the current scene
            Scene helpScene = createHelpScene();
            stage.setScene(helpScene);
            stage.show();
        });
        
        menuScene = new Scene(menuPane, 600, 1024);
    }
	public Scene getScene() {
		return this.menuScene;
	}
	
}
