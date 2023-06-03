package TapMePlusOne;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
		Pane pane = new Pane();
		pane.setStyle("-fx-background-image: url(\"file:resources/game_ver2.png\")");
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
        pane.getChildren().add(exitBtn);
        
		
		TableView board = new TableView();
		board.setStyle("-fx-font-size: 20px;-fx-alignment: center;");
		
		board.setLayoutY(50);
		board.setPrefSize(600, 900);
		ArrayList<Pair<String, Integer>> scoreList = ScoreWrapper.getScoreBoard();
        for (int i = 0; i < scoreList.size(); i++) {
        	board.getItems().add(scoreList.get(i));
        }
        
		TableColumn<Pair<String,Integer>, String> keyCol = new TableColumn<>("暱稱");
		keyCol.setStyle("-fx-alignment: center;");
		keyCol.setPrefWidth(300);
		keyCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));

		TableColumn<Pair<String, Integer>, Integer> valCol = new TableColumn<>("分數");
		valCol.setStyle("-fx-alignment: center;");
		valCol.setPrefWidth(300);
		valCol.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue()));
		
		board.getColumns().addAll(keyCol,valCol);
		pane.getChildren().add(board);
		
		scene = new Scene(pane, 600, 1024);


	}
	public Scene getScene() {
		return this.scene;
	}
}
