package TapMePlusOne;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
public class TapMePlusOne extends Application{
	@Override
	public void start(Stage primaryStage) {
		Menu menu = new Menu();
		
		primaryStage.setScene(menu.getScene());
		primaryStage.setTitle("Tap Me +1");
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
