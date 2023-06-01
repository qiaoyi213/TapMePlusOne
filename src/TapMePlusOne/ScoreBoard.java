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
import javafx.scene.layout.StackPane;
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
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        StackPane contentPane = new StackPane(); // 使用 StackPane 作為 ScrollPane 的內容

        ImageView background = new ImageView(new Image("file:resources/game_ver2.png"));
        background.setFitWidth(600);
        background.setFitHeight(1024);

        Button exitBtn = new Button("");
        ImageView imageView = new ImageView(new Image("file:resources/close.png"));
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        exitBtn.setGraphic(imageView);
        exitBtn.setStyle("-fx-background-color: transparent;");
        exitBtn.setLayoutX(0);
        exitBtn.setLayoutY(0);
        exitBtn.setOnAction(event -> {
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(oldScene);
            stage.show();
        });

        ArrayList<Pair<String, Integer>> board = ScoreWrapper.getScoreBoard();
        VBox vb = new VBox();
        vb.setPrefWidth(600);
        for (int i = 0; i < board.size(); i++) {
            HBox hb = new HBox();
            Text name = new Text(board.get(i).getKey());
            name.setX(50);
            name.setY(50 * i + 100);
            name.setFill(Color.WHITE);
            name.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20)); 
            Text score = new Text(board.get(i).getValue().toString());
            score.setX(100);
            score.setY(50 * i + 100);
            score.setFill(Color.WHITE); 
            score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            hb.getChildren().add(score);
            hb.getChildren().add(name);

            vb.getChildren().add(hb);
        }

        contentPane.getChildren().addAll(background, vb);
        scrollPane.setContent(contentPane);
        scrollPane.setLayoutY(0);
        g.getChildren().addAll(scrollPane, exitBtn);

        scene = new Scene(g, 600, 1024);
	}
	public Scene getScene() {
		return this.scene;
	}
}
