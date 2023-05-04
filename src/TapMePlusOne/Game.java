package TapMePlusOne;
import java.util.Queue;
import java.util.Scanner;
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
				nowBtn.setOnAction(event->{
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
			/*
			System.out.print(nowX);
			System.out.print(" ");
			System.out.println(nowY);
			System.out.println("___");
			*/
			if(nowX+1 <= 5 && nowX+1 >= 1 && nowY >= 1 && nowY <= 5 && vis[nowX+1][nowY] == false && getButtonNumber(this.pad[nowX+1][nowY]) == target) {
				vis[nowX+1][nowY] = true;
				q.add(new Pair<Integer, Integer>(nowX+1, nowY));
				e[nowX+1][nowY] = new Direction(nowX, nowY, 'L');
				
				/*
				System.out.print(nowX+1);
				System.out.print(" ");
				System.out.println(nowY);
				*/
			}
			if(nowX-1 <= 5 && nowX-1 >= 1 && nowY >= 1 && nowY <= 5 && vis[nowX-1][nowY] == false && getButtonNumber(this.pad[nowX-1][nowY]) == target) {
				vis[nowX-1][nowY] = true;
				q.add(new Pair<Integer, Integer>(nowX-1, nowY));
				e[nowX-1][nowY] = new Direction(nowX, nowY, 'R');
				
				/*
				System.out.print(nowX-1);
				System.out.print(" ");
				System.out.println(nowY);
				*/
			}
			if(nowX <= 5 && nowX >= 1 && nowY+1 >= 1 && nowY+1 <= 5 && vis[nowX][nowY+1] == false && getButtonNumber(this.pad[nowX][nowY+1]) == target) {
				vis[nowX][nowY+1] = true;
				q.add(new Pair<Integer, Integer>(nowX, nowY+1));
				e[nowX][nowY+1] = new Direction(nowX, nowY, 'U');
				/*
				System.out.print(nowX);
				System.out.print(" ");
				System.out.println(nowY+1);
				*/
			}
			if(nowX <= 5 && nowX >= 1 && nowY-1 >= 1 && nowY-1 <= 5 && vis[nowX][nowY-1] == false && getButtonNumber(this.pad[nowX][nowY-1]) == target) {
				vis[nowX][nowY-1] = true;
				q.add(new Pair<Integer, Integer>(nowX, nowY-1));
				e[nowX][nowY-1] = new Direction(nowX, nowY, 'D');

				/*
				System.out.print(nowX);
				System.out.print(" ");
				System.out.println(nowY-1);
				*/
			}
		}
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[j][i]) {
					System.out.print(j);
					System.out.print(" ");
					System.out.print(i);
					print(j,i,x,y);
					System.out.println("");
				}
			}
		}
		
	}
	private void print(int a,int b, int tx, int ty) {
		if(a == tx && b == ty)return;
		if(e[a][b] == null)return;
		print(e[a][b].x,e[a][b].y,tx,ty);
		System.out.print(e[a][b].dir);
	}
	public void padding() {
		
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
