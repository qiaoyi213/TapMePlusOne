package TapMePlusOne;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.animation.*;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
	Text score;
	int life;
	private ArrayList<Rectangle> lifeBar;
	private boolean playing;
	private Scene mainScene;
	private Direction[][] e;
	Stack<Character>[][] moving;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Button closeButton;
	private boolean closeButtonPressed;
    
	public Game(){
		this.pane = new Pane();
		vis = new boolean[7][7];
		this.playing = false;
		life = 5;
		this.lifeBar = new ArrayList<Rectangle>(5);
		for(int i=0;i<5;i++) {
			this.lifeBar.add(new Rectangle(90, 20, Color.LIGHTCORAL));
			this.lifeBar.get(i).setTranslateX(50 + 100*i);
			this.lifeBar.get(i).setTranslateY(200);
			pane.getChildren().add(this.lifeBar.get(i));
		}
        
		
		Button stopBtn = new Button();
		Image stopImage = new Image("file:resources/stop.png");
		ImageView stopImageView = new ImageView(stopImage);
		stopImageView.setFitHeight(50);
		stopImageView.setFitWidth(50);
		stopBtn.setGraphic(stopImageView);
	    stopBtn.setOnAction(event -> {
	        
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Stop");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to continue or go back?");

	        ButtonType continueBtn = new ButtonType("continue");
	        ButtonType backBtn = new ButtonType("back");

	        alert.getButtonTypes().setAll(continueBtn, backBtn);

	        Optional<ButtonType> result = alert.showAndWait();

	        if (result.isPresent() && result.get() != continueBtn) {
	            exit();
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
	
	    
		this.pad = new TButton[7][7];		
		
		for(int i=1;i<=5;i++) {
			for(int j=0;j<=5;j++) {
				this.pad[i][j] = new TButton();
			}
		}
		shufflePad();
		
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				final TButton nowBtn = this.pad[i][j];
				final int x = i;
				final int y = j;
				nowBtn.setPrefSize(100, 100);
				nowBtn.setStyle("-fx-border-radius: 50; -fx-font-size:40");
				nowBtn.setPos(x,y);
				nowBtn.setOnAction(event-> {
					nowBtn.setVal(Integer.parseInt(nowBtn.getText())+1);
					disable_pad(true);
					while(true) {
						if(playing == false) {
							bfs(nowBtn.getI(),nowBtn.getJ(), false);
							break;
						}
					}
				});
				nowBtn.setTranslateX(30+110*(j-1));
				nowBtn.setTranslateY(270+(i-1)*110);
			}
		}
	    score = new Text(Integer.toString(0));
	    score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
	    score.setX(430);
	    score.setY(80);
	    pane.getChildren().add(score);
	    
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

	private void bfs(int x, int y, boolean isScan) {
		playing = true;
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
			playing = false;
			if(!isScan) {
				decreaseLife();
				if(this.life <= 0) {
					System.out.println("GAME OVER");
					showGameOver();
				}
			}
			System.out.print("LIFE: ");
			System.out.println(this.life);
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
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[i][j]) {
					this.score.setText(Integer.toString(Integer.parseInt(this.score.getText())+Integer.parseInt(this.pad[i][j].getText())));
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
			increaseLife();
			this.pad[x][y].setVal(Integer.parseInt(this.pad[x][y].getText())+1);
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
		SequentialTransition st = new SequentialTransition();

		Timeline t = new Timeline();
		t.setCycleCount(10);
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				for(int k=0;k<b[i][j];k++) {
					t.getKeyFrames().add(move(this.pad[i][j], 'D'));	
				}
			}
		}

		st.getChildren().add(t);
		
		st.setOnFinished(e->{
			process_block(b);
		});
		st.play();
		 
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
					this.pad[i][j].setVal((int)(Math.random()*6+1));
					
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
			playing=false;
			disable_pad(false);
			Scanner s = new Scanner(System.in);
			System.out.println("SCAN");

			//dev(x,y);
			scan_pad();
			
		});
		st.play();
	}
	private void dev(int i, int j) {
		boolean flag = true;
		while(flag) {
			if(playing == false) {
				bfs(i,j,true);
				flag = false;
			}
		}
	}
	
	private void scan_pad() {
	
		Thread thread = new Thread(() -> {
			for(int i=5;i>=1;i--) {
				for(int j=1;j<=5;j++) {
					boolean flag = true;
					System.out.print("scan the point: ");
					System.out.print(i);
					System.out.print(" ");
					System.out.println(j);
					System.out.println(playing);
					while(flag) {
						if(playing == false) {
							System.out.println("RUNBFS");
							bfs(i,j,true);
							flag = false;
						}
					}
				}
			}
		});
				
		thread.start();

	}
	private void add_new_block(int x,int y) {
		this.pad[x][y].setVal((int)(Math.random()*5)+1);
		this.pad[x][y].setTranslateY(160);
		this.pad[x][y].setTranslateX(30+110*(y-1));
		this.pad[x][y].setVisible(true);
	}
	private void disable_pad(boolean flag) {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				this.pad[i][j].setDisable(flag);
				this.pad[i][j].setOpacity(1);
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
	private void decreaseLife() {
        this.life--;
        this.lifeBar.get(this.life).setVisible(false);
    }
	private void increaseLife() {
		if(this.life != 5) {
			this.life++;
			
			this.lifeBar.get(this.life-1).setVisible(true);
		}
	}
	private void shufflePad() {
		int[][] tmp = RandomMatrix.generate();
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				this.pad[i][j].setVal(tmp[i][j]);
			}
		}
	}
	private void exit() {
		System.out.println("Exit");
		Stage stage = (Stage)this.getScene().getWindow();
		Menu menu = new Menu();
		stage.setScene(menu.getScene());
	}
	public void showGameOver() {
	    // 創建 Game Over 文字
	    Text gameOverText = new Text("GAME OVER");
	    gameOverText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 60));
	    gameOverText.setFill(Color.RED);
	    gameOverText.setStroke(Color.BLACK);
	    gameOverText.setStrokeWidth(2);
	    gameOverText.setX(120);
	    gameOverText.setY(400);
	    gameOverText.setId("gameOverText"); // 設定 ID 以便識別

	    // 創建重新開始按鈕
	    Button restartBtn = new Button("Restart");
	    restartBtn.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
	    restartBtn.setLayoutX(220);
	    restartBtn.setLayoutY(500);
	    restartBtn.setId("restartBtn"); // 設定 ID 以便識別
	    restartBtn.setOnAction(event -> {
	    	String videoUrl = "file:resources/ads.mp4";
	    	Video v;
			try {
				v = new Video(videoUrl,(Stage)this.getScene().getWindow(), this.getScene());
		    	v.play();
		    	
		    	Stage stage = (Stage)this.getScene().getWindow();
		    	stage.setScene(v.getScene());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    //showCloseButton();
	        resetGame();
	    });

	    // 加入遊戲結束文字和重新開始按鈕到畫面
	    pane.getChildren().addAll(gameOverText, restartBtn);
	    
	}
	
	
	private void resetGame() {
	    // 重新初始化遊戲相關資料
	    for (int i = 1; i <= 5; i++) {
	        for (int j = 1; j <= 5; j++) {
	            this.pad[i][j].setVal((int) (Math.random() * 6) + 1);
	        }
	    }
	    this.score.setText("0");
	    this.life = 5;
	    for(int i=0;i<5;i++) {
	    	this.lifeBar.get(i).setVisible(true);
	    }

	    // 移除遊戲結束文字和重新開始按鈕	
	    Node gameOverText = pane.lookup("#gameOverText");
	    Node restartBtn = pane.lookup("#restartBtn");
	    pane.getChildren().removeAll(gameOverText, restartBtn);

	    // 啟用按鈕
	    disable_pad(false);
	    playing = false;
	}


	public Scene getScene() {
		return this.mainScene;
	}
}
