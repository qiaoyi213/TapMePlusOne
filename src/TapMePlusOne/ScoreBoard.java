package TapMePlusOne;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ScoreBoard {
	private Scene oldScene;
	private Scene scene;
	public ScoreBoard(Scene oldScene) throws Exception {
		this.oldScene = oldScene;
		Pane pane = new Pane();
		pane.setStyle("-fx-background-image: url('file:resources/game_ver2.png'); -fx-background-size: contain;");
		Button exitBtn = new Button("");
		ImageView imageView = new ImageView(new Image("file:resources/close.png"));
		imageView.setFitWidth(50); 
        imageView.setPreserveRatio(true); 
        exitBtn.setGraphic(imageView);
        exitBtn.setStyle("-fx-background-color: transparent;");
	    exitBtn.setAlignment(Pos.TOP_RIGHT);
	    exitBtn.setOnAction(event -> {
	    	Stage stage = (Stage)this.getScene().getWindow();
	    	stage.setScene(oldScene);
	    	stage.show();
	    });
	    pane.getChildren().add(exitBtn);
		ArrayList<Pair<String, Integer>> board = ScoreWrapper.getScoreBoard();
		for(int i=0;i<board.size();i++) {
			Text name = new Text(board.get(i).getKey());
			name.setX(100);
			name.setY(50*i+200);
			name.setFill(Color.WHITE); // 設定文字顏色為白色
			name.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20)); // 設定字體和大小
			Text score = new Text(board.get(i).getValue().toString());
			score.setX(200);
			score.setY(50*i+200);
			score.setFill(Color.WHITE); // 設定文字顏色為白色
			score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20)); // 設定字體和大小
			pane.getChildren().add(name);
			pane.getChildren().add(score);
		}
		
		scene = new Scene(pane, 600, 1024);
	}
	public Scene getScene() {
		return this.scene;
	}
}
