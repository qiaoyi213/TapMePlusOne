package TapMePlusOne;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
public class TapMePlusOne extends Application{
	@Override
	public void start(Stage primaryStage) {
		Menu menu = new Menu();
		Game game = new Game();
		primaryStage.setScene(game.getScene());
		primaryStage.setTitle("Tap Me +1");
		primaryStage.show();
		game.padding();
		//game.drawPad();
		
	}
	public static void main(String[] args) {
		/*
		Scanner s = new Scanner(System.in);
		Game game = new Game();
		game.showPad();
		System.out.print("Please input what position you click: ");
		int posI, posJ;
		posI = s.nextInt();
		posJ = s.nextInt();
		game.showPad();
		System.out.println("---");
		game.click(posI, posJ);
		game.showPad();
		System.out.println("---");
		game.scan();
		game.showPad();
		*/
		
		launch(args);
	}

}
