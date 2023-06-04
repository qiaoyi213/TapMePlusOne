package TapMePlusOne;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ScoreBoard {
    private Scene oldScene;
    private Scene scene;

    public ScoreBoard(Scene oldScene) throws Exception {
        this.oldScene = oldScene;
        Pane pane = new Pane();
        pane.getStyleClass().add("root");

        Button exitBtn = new Button("");
        ImageView imageView = new ImageView(new Image("file:resources/close.png"));
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        exitBtn.setGraphic(imageView);
        exitBtn.getStyleClass().add("exit-button");
        exitBtn.setLayoutX(0);
        exitBtn.setLayoutY(0);
        exitBtn.setOnAction(event -> {
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(oldScene);
            stage.show();
        });
        pane.getChildren().add(exitBtn);

        TableView<Pair<String, Integer>> board = new TableView<>();
        ScrollPane scrollPane = new ScrollPane(board);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setFitToHeight(true);
        scrollPane.setLayoutY(50);
        board.getStyleClass().add("table-view");
        board.setLayoutY(50);
        board.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        board.setPrefSize(600, 800);

        ArrayList<Pair<String, Integer>> scoreList = ScoreWrapper.getScoreBoard();
        ObservableList<Pair<String, Integer>> items = FXCollections.observableArrayList(scoreList);
        board.setItems(items);

        TableColumn<Pair<String, Integer>, String> keyCol = new TableColumn<>("暱稱");
        keyCol.getStyleClass().add("table-column");
        keyCol.setPrefWidth(300);
        keyCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));

        TableColumn<Pair<String, Integer>, Integer> valCol = new TableColumn<>("分數");
        valCol.getStyleClass().add("table-column");
        valCol.setPrefWidth(300);
        valCol.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue()));

        board.getColumns().addAll(keyCol, valCol);
        pane.getChildren().add(scrollPane);

        scene = new Scene(pane, 600, 1024);
        scene.getStylesheets().add("file:resources/scoreboard.css");
    }

    public Scene getScene() {
        return this.scene;
    }
}

