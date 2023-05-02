package TapMePlusOne;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.animation.*;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.shape.*;
import javafx.scene.control.Button;
public class Game {
	
	private Button[][] pad;
	private int score;
	private Pane pane;
	private Scene mainScene;
	public Game(){
		this.pane = new Pane();
		this.pad = new Button[7][7];		
		this.score = 0;
		for(int i=1;i<=5;i++) {
			for(int j=0;j<=5;j++) {
				this.pad[i][j] = null;
			}
		}
		
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				this.pad[i][j] = new Button(Integer.toString((int)(Math.random()*6)+1));
				final Button nowBtn = this.pad[i][j];
				nowBtn.setPrefSize(100, 100);
				
				nowBtn.setStyle("-fx-background-color: #6666ff; -fx-border-radius: 50;");
				nowBtn.setOnAction(event->{
					nowBtn.setText(Integer.toString(Integer.parseInt(nowBtn.getText())+1));
				});
				nowBtn.setTranslateX(30+110*(i-1));
				nowBtn.setTranslateY(270+(j-1)*110);
			}
		}
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				this.pane.getChildren().add(this.pad[i][j]);
			}
		}
		this.mainScene = new Scene(this.pane,600, 1024);
		
	}
	
	private void dfs() { 
		
	}
	public boolean scan() {
		
		return false;
	}
	
	public void padding() {
		
	}
	public void move() {
		
	}
	public Scene getScene() {
		
		return this.mainScene;
	}
}
