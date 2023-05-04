package TapMePlusOne;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Scene;

public class Menu {
	private Scene menuScene;
	private Pane menuPane;
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
        
        // Help button
        Button helpBtn = new Button("Help");
        helpBtn.setPrefSize(100, 50);
        helpBtn.setLayoutX(200);
        helpBtn.setLayoutY(500);
        helpBtn.setStyle("-fx-background-color: lightgray; -fx-text-fill: black; -fx-font-size: 20pt;");
        menuPane.getChildren().add(helpBtn);
        
        menuScene = new Scene(menuPane, 512, 720);
    }
	public Scene getScene() {
		return this.menuScene;
	}
	
}
