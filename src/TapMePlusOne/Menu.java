package TapMePlusOne;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;

public class Menu {
	private Scene menuScene;
	private Pane menuPane;
	private Scene createHelpScene() {
	    Pane helpPane = new Pane();
	    Text helpText = new Text("App Guide");
	    helpText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
	    helpText.setX(175);
	    helpText.setY(25);
	    helpPane.getChildren().add(helpText);
	    
	    
	    Text guideText = new Text("1. 點擊任一方塊，方塊+1 \n2. 消除三塊以上相同數字的方塊");
	    guideText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
	    guideText.setX(125);
	    guideText.setY(50);
	    helpPane.getChildren().add(guideText);
	    
	    
	    ImageView imageView = new ImageView(new Image("file:resources/guide.jpeg"));
	    imageView.setLayoutX(150);
	    imageView.setLayoutY(100);
	    imageView.setFitWidth(250);
	    imageView.setFitHeight(500);
	    helpPane.getChildren().add(imageView);
	    
	    Button backButton = new Button("Back");
	    backButton.setPrefSize(100, 50);
	    backButton.setLayoutX(200);
	    backButton.setLayoutY(600);
	    backButton.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-font-size: 20pt;");
	    helpPane.getChildren().add(backButton);

	    // Back button onClick event
	    backButton.setOnAction(event -> {
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        // Set the menu scene as the current scene
	        stage.setScene(menuScene);
	        stage.show();
	    });

	    Scene helpScene = new Scene(helpPane, 512, 720);
	    return helpScene;
	}

	public Menu(){
		menuPane = new Pane();
		// Title
        Text title = new Text("Tap +1");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 68));
        title.setX(150);
        title.setY(250);
        menuPane.getChildren().add(title);
        
        // Start button
        Button startBtn = new Button("Start");
        startBtn.setPrefSize(100, 50);
        startBtn.setLayoutX(200);
        startBtn.setLayoutY(400);
        startBtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-font-size: 20pt;");
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
        helpBtn.setLayoutX(200);
        helpBtn.setLayoutY(500);
        helpBtn.setStyle("-fx-background-color: lightgray; -fx-text-fill: black; -fx-font-size: 20pt;");
        menuPane.getChildren().add(helpBtn);
    	// Help button onClick event
        helpBtn.setOnAction(event -> {
            Stage stage = (Stage) helpBtn.getScene().getWindow();
            // Create a new scene for the help page and set it as the current scene
            Scene helpScene = createHelpScene();
            stage.setScene(helpScene);
            stage.show();
        });
        
        menuScene = new Scene(menuPane, 512, 720);
    }
	public Scene getScene() {
		return this.menuScene;
	}
	
}
