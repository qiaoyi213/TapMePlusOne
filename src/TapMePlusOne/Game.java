package TapMePlusOne;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.animation.*;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Game {
	
	private TButton[][] pad;
	private boolean[][] vis;
	private Pane pane;
	private Scene mainScene;
	private Direction[][] e;
	Stack<Character>[][] moving;
	public Game(){
		this.pane = new Pane();
		vis = new boolean[7][7];
		
		// 新增 stopBtn
		Button stopBtn = new Button();
		Image stopImage = new Image("file:resources/stop.png");
		ImageView stopImageView = new ImageView(stopImage);
		stopImageView.setFitHeight(50);
		stopImageView.setFitWidth(50);
		stopBtn.setGraphic(stopImageView);
	    stopBtn.setOnAction(event -> {
	        // 創建 Alert 視窗
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Stop");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to continue or go back?");

	        // 新增兩個 ButtonType，分別對應 "continue" 與 "back"
	        ButtonType continueBtn = new ButtonType("continue");
	        ButtonType backBtn = new ButtonType("back");

	        // 將 ButtonType 加入 Alert 中
	        alert.getButtonTypes().setAll(continueBtn, backBtn);

	        // 顯示 Alert 視窗，並等待使用者選擇
	        Optional<ButtonType> result = alert.showAndWait();

	        // 根據使用者選擇的 ButtonType 來做不同的處理
	        if (result.isPresent() && result.get() == continueBtn) {
	            // 如果使用者選擇了 "continue"，則繼續執行
	            // ...
	        } else {
	            // 如果使用者選擇了 "back"，則回到上一頁
	            // ...
	        }
	    });

	    stopBtn.setTranslateX(30);
	    stopBtn.setTranslateY(30);
	    pane.getChildren().add(stopBtn);
	    
	    Text aboveText = new Text("TARGET         SCORE");
	    aboveText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
	    aboveText.setX(130);
	    aboveText.setY(30);
	    pane.getChildren().add(aboveText);
	    
	    Text score = new Text(Integer.toString(0));
	    score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
	    score.setX(430);
	    score.setY(80);
	    pane.getChildren().add(score);
	
		this.pad = new TButton[7][7];		
		
		for(int i=1;i<=5;i++) {
			for(int j=0;j<=5;j++) {
				this.pad[i][j] = null;
			}
		}
		
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				this.pad[i][j] = new TButton();
				this.pad[i][j].setText((Integer.toString((int)(Math.random()*6)+1)));
				final TButton nowBtn = this.pad[i][j];
				final int x = i;
				final int y = j;
				nowBtn.setPrefSize(100, 100);
				
				nowBtn.setStyle("-fx-background-color: #6666ff; -fx-border-radius: 50; -fx-font-size:40");
				nowBtn.setTextFill(Color.WHITE);
				nowBtn.setPos(x,y);
				nowBtn.setOnAction(event-> {
					nowBtn.setText(Integer.toString(Integer.parseInt(nowBtn.getText())+1));
					disable_pad(true);
				
					bfs(nowBtn.getI(),nowBtn.getJ());
					
					
				});
				nowBtn.setTranslateX(30+110*(j-1));
				nowBtn.setTranslateY(270+(i-1)*110);
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
		disable_pad(true);
		Queue<Pair<Integer,Integer>> q = new LinkedList<>();
		
		q.add(new Pair<Integer, Integer>(x,y));
		int target = getButtonNumber(this.pad[x][y]);
		System.out.println(target);
		System.out.print(x);
		System.out.print(" ");
		System.out.println(y);
		//showPad();
		vis = new boolean[7][7];
		e = new Direction[7][7];
		while(q.size() > 0) {
			Pair<Integer, Integer> now = q.poll();
			int nowX = now.getKey();
			int nowY = now.getValue();
			/*
			System.out.print(nowX);
			System.out.print(" ");
			System.out.println(nowY);
			*/
			if(nowX+1 <= 5 && nowX+1 >= 1 && nowY >= 1 && nowY <= 5 && vis[nowX+1][nowY] == false && getButtonNumber(this.pad[nowX+1][nowY]) == target) {
				vis[nowX+1][nowY] = true;
				q.add(new Pair<Integer, Integer>(nowX+1, nowY));
				e[nowX+1][nowY] = new Direction(nowX, nowY, 'U');
			}
			if(nowX-1 <= 5 && nowX-1 >= 1 && nowY >= 1 && nowY <= 5 && vis[nowX-1][nowY] == false && getButtonNumber(this.pad[nowX-1][nowY]) == target) {
				vis[nowX-1][nowY] = true;
				q.add(new Pair<Integer, Integer>(nowX-1, nowY));
				e[nowX-1][nowY] = new Direction(nowX, nowY, 'D');
			}
			if(nowX <= 5 && nowX >= 1 && nowY+1 >= 1 && nowY+1 <= 5 && vis[nowX][nowY+1] == false && getButtonNumber(this.pad[nowX][nowY+1]) == target) {
				vis[nowX][nowY+1] = true;
				q.add(new Pair<Integer, Integer>(nowX, nowY+1));
				e[nowX][nowY+1] = new Direction(nowX, nowY, 'L');
			}
			if(nowX <= 5 && nowX >= 1 && nowY-1 >= 1 && nowY-1 <= 5 && vis[nowX][nowY-1] == false && getButtonNumber(this.pad[nowX][nowY-1]) == target) {
				vis[nowX][nowY-1] = true;
				q.add(new Pair<Integer, Integer>(nowX, nowY-1));
				e[nowX][nowY-1] = new Direction(nowX, nowY, 'R');
			}
		}
		moving = new Stack[7][7];
		for(int i=0;i<=6;i++) {
			for(int j=0;j<=6;j++) {
				moving[i][j] = new Stack<Character>();
			}
		}
		int counter = 0;
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[i][j] == true) {
					counter++;
				}
			}
		}
		if(counter < 3) {
			disable_pad(false);
			return;
		}
		
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[i][j]) {
					//System.out.print(i);
					//System.out.print(" ");
					//System.out.print(j);
					
					print(i,j,x,y,moving[i][j]);
					System.out.println("");
				}
			}
			
		}
		vis[x][y] = false;
		
		SequentialTransition sq = new SequentialTransition();
		boolean flag = false;
		do {
			flag = false;
			Timeline t = new Timeline();
			t.setCycleCount(10);
			for(int i=1;i<=5;i++) {
				for(int j=1;j<=5;j++) {
					if(moving[i][j].size() != 0) {
						if(moving[i][j].size() != 0)flag = true;
						char dd = moving[i][j].pop();
						t.getKeyFrames().add(move(this.pad[i][j], dd));
					}
				}
			}
			sq.getChildren().add(t);
		}while(flag);
		sq.setOnFinished(e -> {
			padding();
		});
		sq.play();
		
	
	}
	private void print(int a,int b, int tx, int ty, Stack<Character> st) {
		if(a == tx && b == ty)return;
		if(e[a][b] == null)return;
		print(e[a][b].x,e[a][b].y,tx,ty,st);
		st.add(e[a][b].dir);
		//System.out.print(e[a][b].dir);
	}
	
	public void padding() {
		//remove 
		
		//fill the blanks.
		int[][] b = new int[7][7];
		

		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				int bubble = 0;
				if(vis[i][j] == true) continue;
				for(int k=i;k<=5;k++) {
					if(vis[k][j] == true) {
						bubble++;
					}
				}
				b[i][j] = bubble;
			}
		}
		/*
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				System.out.print(b[j][i]);
				
			}
			System.out.println("");
		}
		*/
		Timeline t = new Timeline();
		t.setCycleCount(10);
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				//this.pad[i][j+b[i][j]] = this.pad[i][j];
				for(int k=0;k<b[i][j];k++) {
					t.getKeyFrames().add(move(this.pad[i][j], 'D'));
				}
			}
		}
		SequentialTransition st = new SequentialTransition();
		st.getChildren().add(t);
		st.setOnFinished(e->{
			process_block(b);
		});
		st.play();
		//adding new blacks.
		 
		 
	}
	private void process_block(int[][] b) {
		

		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[i][j]) {
					this.pad[i][j].setText("0");
					this.pad[i][j].setVisible(false);
				}
			}
		}
		
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=5;j++) {
				this.pad[i+b[i][j]][j].setPos(this.pad[i][j].getI(),this.pad[i][j].getJ());
				this.pad[i][j].setPos(this.pad[i][j].getI()+b[i][j], this.pad[i][j].getJ());
				TButton tmp = this.pad[i+b[i][j]][j];
				this.pad[i+b[i][j]][j] = this.pad[i][j];
				this.pad[i][j] = tmp;
			}	
		}
		//showPad();
		SequentialTransition st = new SequentialTransition();
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=5;j++) {
				if(getButtonNumber(this.pad[i][j]) == 0) {
					this.pad[i][j].setText(Integer.toString((int)(Math.random()*6+1)));
					add_new_block(i,j);
					for(int k=0;k<i;k++) {						
						Timeline t = new Timeline();
						t.setCycleCount(10);
						t.getKeyFrames().add(move(this.pad[i][j],'D'));
						st.getChildren().add(t);
					}
					
				}
			}
		}
		st.setOnFinished(e -> {
			scan_pad();
			disable_pad(false);
		});
		//showPad();
		st.play();
	}
	private void scan_pad() {

	}
	private void add_new_block(int x,int y) {
		//this.pad[x][y].setText(Integer.toString((int)(Math.random()*6)+1));
		this.pad[x][y].setTranslateY(160);
		this.pad[x][y].setTranslateX(30+110*(y-1));
		this.pad[x][y].setVisible(true);
	}
	private void disable_pad(boolean flag) {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				this.pad[i][j].setDisable(flag);
			}
		}
	}
	private KeyFrame move(TButton btn, char dir) {
		if(btn == null) {
			System.out.println("!!!NULL BTN");
		}
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
		System.out.println("");
	}
	public Scene getScene() {
		return this.mainScene;
	}
}
