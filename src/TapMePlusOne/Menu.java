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

        Image backgroundImage = new Image("file:resources/menuback.png");
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
        Button startBtn = new Button("開始");
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
        Button continueBtn = new Button("繼續");
        continueBtn.setPrefSize(100, 50);
        continueBtn.setLayoutX(250);
        continueBtn.setLayoutY(350);
        continueBtn.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 20pt;");
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
        Button helpBtn = new Button("教學");
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
        Button boardBtn = new Button("計分板");
        boardBtn.setPrefSize(100, 50);
        boardBtn.setLayoutX(250);
        boardBtn.setLayoutY(750);
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
	    Image backgroundImage = new Image("file:resources/game_ver2.png");
        ImageView help_backgroundImageView = new ImageView(backgroundImage);
        help_backgroundImageView.setFitWidth(600);
        help_backgroundImageView.setFitHeight(1024);
        //help_backgroundImageView.setPreserveRatio(true);
        helpPane.getChildren().add(help_backgroundImageView);
	    Text helpText = new Text("App Guide");
	    helpText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 35));
	    helpText.setFill(Paint.valueOf("WHITE"));  
	    helpText.setX(220);
	    helpText.setY(40);
	    helpPane.getChildren().add(helpText);
	    
	    
	    Text guideText = new Text("1. 點擊任一方塊，方塊+1 \n2. 消除三塊以上相同數字的方塊");
	    guideText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
	    guideText.setFill(Paint.valueOf("WHITE"));   
	    guideText.setX(155);
	    guideText.setY(680);
	    helpPane.getChildren().add(guideText);
	    
	    
	    ImageView imageView = new ImageView(new Image("file:resources/guide.png"));
	    imageView.setLayoutX(150);
	    imageView.setLayoutY(100);
	    imageView.setFitWidth(300);
	    imageView.setFitHeight(550);
	    helpPane.getChildren().add(imageView);
	    
	    Button backButton = new Button("Back");
	    backButton.setPrefSize(80, 40);
	    backButton.setLayoutX(260);
	    backButton.setLayoutY(750);
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
	
	public Scene getScene() {
		return this.menuScene;
	}
	
}
