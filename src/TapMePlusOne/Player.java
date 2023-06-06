package TapMePlusOne;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	private String name;
	private int score;
	public Player() {
		this.name = "";
		this.score = 0;
	}
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}
	public void readPlayer() throws Exception {
		try {
			File f = new File("player.txt");
			Scanner s = new Scanner(f);
			this.name = s.nextLine();
			this.score = Integer.parseInt(s.nextLine());
		} catch(Exception e) {
			throw e;
		}
	}
	public Scene createPlayer() {
		Pane pane = new Pane();

        Image backgroundImage = new Image("file:resources/user.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(1024);
        pane.getChildren().add(backgroundImageView);
        //pane.setStyle("-fx-background-image: url('file:resources/user.png');-fx-background-size: cover;");

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double halfWidth = screenWidth / 7;

        TextField tf = new TextField();
        tf.setStyle("-fx-border-color: #FF999A; -fx-border-width: 2px; -fx-border-radius: 5px;");
        tf.setPrefWidth(halfWidth);
        tf.setLayoutX(200);
        tf.setLayoutY(400);
        pane.getChildren().add(tf);

        Button submitBtn = new Button("");
        ImageView imageView = new ImageView(new Image("file:resources/enter.png"));
        imageView.setFitWidth(75); // 設定圖片顯示寬度
        imageView.setPreserveRatio(true); // 保持圖片比例
        submitBtn.setGraphic(imageView); // 將圖片設為按鈕的圖形
        submitBtn.setStyle("-fx-background-color: transparent;");

		submitBtn.setOnAction(e -> {
			if(isVaildNewPlayer(tf.getText())) {
				this.name = tf.getText();
				this.score = 0;
				System.out.println(this.name);
				try {
					ScoreWrapper.addScore(name, 0);
					updatePlayer(name, 0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Stage stage = (Stage)submitBtn.getScene().getWindow();
		        stage.setScene(new Menu().getScene());
		        
		        
			} else {
				Alert al = new Alert(AlertType.WARNING);
				al.setHeaderText("該名稱已經有人使用過了！");
				al.show();
			}
		});
		submitBtn.setLayoutX(255);
        submitBtn.setLayoutY(450);
        pane.getChildren().add(submitBtn);

        return new Scene(pane, 600, 1024);
	}
	private boolean isVaildNewPlayer(String name) {
		
		try {
			ScoreWrapper.isExist(name);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	private void updatePlayer(String name, int score) {
		FileWriter fw;
		try {
			fw = new FileWriter("player.txt");
			fw.write(name + "\n");
			fw.write(Integer.toString(score)+"\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
	public void updateScore(int score) {
		if(score > this.score) {
			this.score = score;
			updatePlayer(this.name, this.score);
			try {
				//ScoreWrapper.addScore(this.name, this.score);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getName() {
		return this.name;
	}
	public int getScore() {
		return this.score;
	}
}
