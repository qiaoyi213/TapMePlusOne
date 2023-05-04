package TapMePlusOne;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
public class TapMePlusOne extends Application{
	@Override
	public void start(Stage primaryStage) {
		//Menu menu = new Menu();
		Game game = new Game();
		primaryStage.setScene(game.getScene());
		primaryStage.setTitle("Tap Me +1");
		primaryStage.show();
		game.padding();
		game.showPad();
		
		//game.drawPad();
		
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
