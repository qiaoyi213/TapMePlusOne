package TapMePlusOne;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

		Group g = new Group();
		ScrollPane scrollPane = new ScrollPane();
		//pane.setStyle("-fx-background-image: url('file:resources/game_ver2.png'); -fx-background-size: contain;");
		
		Button exitBtn = new Button("");
		ImageView imageView = new ImageView(new Image("file:resources/close.png"));
		imageView.setFitWidth(50); 
        imageView.setPreserveRatio(true); 
        exitBtn.setGraphic(imageView);
        exitBtn.setStyle("-fx-background-color: transparent;");
	    exitBtn.setLayoutX(0);
	    exitBtn.setLayoutY(0);
	    exitBtn.setOnAction(event -> {
	    	Stage stage = (Stage)this.getScene().getWindow();
	    	stage.setScene(oldScene);
	    	stage.show();
	    });
	    
	    g.getChildren().add(exitBtn);
	    
		ArrayList<Pair<String, Integer>> board = ScoreWrapper.getScoreBoard();
		VBox vb = new VBox();
		vb.setPrefWidth(600);
		for(int i=0;i<board.size();i++) {
			HBox hb = new HBox();
			Text name = new Text(board.get(i).getKey());
			name.setX(50);
			name.setY(50*i+100);
			name.setFill(Color.BLACK); // 設定文字顏色為白色
			name.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20)); // 設定字體和大小
			Text score = new Text(board.get(i).getValue().toString());
			score.setX(100);
			score.setY(50*i+100);
			score.setFill(Color.BLACK); // 設定文字顏色為白色
			score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20)); // 設定字體和大小
			hb.getChildren().add(score);
			hb.getChildren().add(name);
			
			vb.getChildren().add(hb);
		}
		
		scrollPane.setContent(vb);
		scrollPane.setLayoutY(50);
		g.getChildren().add(scrollPane);
		scene = new Scene(g, 600, 1024);
	}
	public Scene getScene() {
		return this.scene;
	}
}
