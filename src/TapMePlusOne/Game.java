package TapMePlusOne;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.nio.charset.Charset;
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
	private boolean[][] vis;
	private int score;
	private Pane pane;
	private Scene mainScene;
	private Direction[][] e;
	Stack<Character>[][] moving;
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
				final int x = i;
				final int y = j;
				nowBtn.setPrefSize(100, 100);
				
				nowBtn.setStyle("-fx-background-color: #6666ff; -fx-border-radius: 50;");
				nowBtn.setOnAction(event-> {
					nowBtn.setText(Integer.toString(Integer.parseInt(nowBtn.getText())+1));
					bfs(x,y);
					
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
	private int getButtonNumber(Button btn) {
		return Integer.parseInt(btn.getText());
	}
	private void bfs(int x, int y) {
		Queue<Pair<Integer,Integer>> q = new LinkedList<>();
		q.add(new Pair<Integer, Integer>(x,y));
		int target = getButtonNumber(this.pad[x][y]);
		System.out.println(target);
		boolean[][] vis = new boolean[7][7];
		e = new Direction[7][7];
		while(q.size() > 0) {
			Pair<Integer, Integer> now = q.poll();
			int nowX = now.getKey();
			int nowY = now.getValue();
			if(nowX+1 <= 5 && nowX+1 >= 1 && nowY >= 1 && nowY <= 5 && vis[nowX+1][nowY] == false && getButtonNumber(this.pad[nowX+1][nowY]) == target) {
				vis[nowX+1][nowY] = true;
				q.add(new Pair<Integer, Integer>(nowX+1, nowY));
				e[nowX+1][nowY] = new Direction(nowX, nowY, 'L');
			}
			if(nowX-1 <= 5 && nowX-1 >= 1 && nowY >= 1 && nowY <= 5 && vis[nowX-1][nowY] == false && getButtonNumber(this.pad[nowX-1][nowY]) == target) {
				vis[nowX-1][nowY] = true;
				q.add(new Pair<Integer, Integer>(nowX-1, nowY));
				e[nowX-1][nowY] = new Direction(nowX, nowY, 'R');
			}
			if(nowX <= 5 && nowX >= 1 && nowY+1 >= 1 && nowY+1 <= 5 && vis[nowX][nowY+1] == false && getButtonNumber(this.pad[nowX][nowY+1]) == target) {
				vis[nowX][nowY+1] = true;
				q.add(new Pair<Integer, Integer>(nowX, nowY+1));
				e[nowX][nowY+1] = new Direction(nowX, nowY, 'U');
			}
			if(nowX <= 5 && nowX >= 1 && nowY-1 >= 1 && nowY-1 <= 5 && vis[nowX][nowY-1] == false && getButtonNumber(this.pad[nowX][nowY-1]) == target) {
				vis[nowX][nowY-1] = true;
				q.add(new Pair<Integer, Integer>(nowX, nowY-1));
				e[nowX][nowY-1] = new Direction(nowX, nowY, 'D');
			}
		}
		moving = new Stack[7][7];
		for(int i=0;i<=6;i++) {
			for(int j=0;j<=6;j++) {
				moving[i][j] = new Stack<Character>();
			}
		}
		
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[j][i]) {
					System.out.print(j);
					System.out.print(" ");
					System.out.print(i);
					
					print(j,i,x,y,moving[j][i]);
					System.out.println("");
				}
			}
		}
		SequentialTransition sq = new SequentialTransition();
		boolean flag = false;
		do {
			
			flag = false;
			Timeline t = new Timeline();
			t.setCycleCount(10);
			for(int i=1;i<=5;i++) {
				for(int j=1;j<=5;j++) {
					if(moving[j][i].size() != 0) {
						if(moving[j][i].size() != 0)flag = true;
						char dd = moving[j][i].pop();
						t.getKeyFrames().add(move(this.pad[j][i], dd));
					}
				}
			}
			sq.getChildren().add(t);
		}while(flag);
		sq.play();

		
	}
	private void print(int a,int b, int tx, int ty, Stack<Character> st) {
		if(a == tx && b == ty)return;
		if(e[a][b] == null)return;
		print(e[a][b].x,e[a][b].y,tx,ty,st);
		
		st.add(e[a][b].dir);
	}
	public void padding() {
		
	}
	private KeyFrame move(Button btn, char dir) {
		
		KeyFrame kf = new KeyFrame(Duration.millis(20), event -> {
			if(dir == 'U') {
				btn.setTranslateY(btn.getTranslateY() - 11);
			} else if(dir == 'D') {
				btn.setTranslateY(btn.getTranslateY() + 11);
			} else if(dir == 'L') {
				btn.setTranslateX(btn.getTranslateX() - 11);
			} else if(dir == 'R') {
				btn.setTranslateX(btn.getTranslateX() + 11);
			}
		});
		return kf;
	}
	public void showPad() {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				System.out.print(getButtonNumber(this.pad[i][j]));
			}
			System.out.println("");
		}
	}
	public Scene getScene() {
		return this.mainScene;
	}
}
