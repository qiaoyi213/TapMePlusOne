package TapMePlusOne;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.Scene;

public class Menu {
	private Scene menuScene;
	private Pane menuPane;
	public Menu(){
		menuPane = new Pane();
		//Start button
		Button startBtn = new Button("Start");
		menuPane.getChildren().add(startBtn);
		menuScene = new Scene(menuPane, 512, 720);
	}
	public Scene getScene() {
		return this.menuScene;
	}
	
}
