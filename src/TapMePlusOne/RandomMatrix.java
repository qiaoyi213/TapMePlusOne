package TapMePlusOne;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

public class RandomMatrix {
	public static int[][] generate() {
		int[][] matrix = new int[7][7];
		shuffle(matrix);
		
		
		while(bfs(matrix)) {
			/*
			for(int i=1;i<=5;i++) {
				for(int j=1;j<=5;j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println("");
			}
			System.out.println("");
			*/
		}
		
		return matrix;
	}
	private static void shuffle(int[][] matrix){
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				matrix[i][j] = (int)(Math.random()*5)+1;
			}
		}
		
	}
	private static boolean bfs(int[][] matrix) {
		boolean flag = false;
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=5;j++) {
				Queue<Pair<Integer, Integer>> q = new LinkedList<Pair<Integer, Integer>>();
				boolean[][] vis = new boolean[7][7];
				for(int a=1;a<=5;a++) {
					for(int b=1;b<=5;b++) {
						vis[a][b] = false;
					}
				}
				q.add(new Pair<Integer, Integer>(i,j));
				vis[i][j] = true;
				
				while(q.size() > 0) {
					Pair<Integer, Integer> now = q.poll();
					int x = now.getKey();
					int y = now.getValue();
					if( 1 <= x+1 && x+1 <= 5 && 1 <= y && y <= 5 && matrix[x+1][y] == matrix[i][j]&&vis[x+1][y] == false) {
						q.add(new Pair<Integer, Integer>(x+1,y));
						vis[x+1][y] = true;
					}
					if( 1 <= x-1 && x-1 <= 5 && 1 <= y && y <= 5 && matrix[x-1][y] == matrix[i][j] && vis[x-1][y] == false) {
						q.add(new Pair<Integer, Integer>(x-1,y));
						vis[x-1][y] = true;
					}
					if( 1 <= x && x <= 5 && 1 <= y+1 && y+1 <= 5 && matrix[x][y+1] == matrix[i][j] && vis[x][y+1] == false) {
						q.add(new Pair<Integer, Integer>(x,y+1));
						vis[x][y+1] = true;
					}
					if( 1 <= x && x <= 5 && 1 <= y-1 && y-1 <= 5 && matrix[x][y-1] == matrix[i][j] && vis[x][y-1] == false) {
						q.add(new Pair<Integer, Integer>(x,y-1));
						vis[x][y-1] = true;
					}
				}
				
				int counter = 0;
				for(int a=1;a<=5;a++) {
					for(int b=1;b<=5;b++) {
						if(vis[a][b]) {
							counter++;
						}
					}
				}
				if(counter >= 3){
					process(padding(vis), vis, matrix);
					flag = true;
				}
			}
		}
		return flag;
		
	}
	private static int[][] padding(boolean[][] vis) {

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
		return b;
	}
	private static void process(int[][] b, boolean[][] vis,int[][] matrix) {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(vis[i][j]) {
					matrix[i][j] = (int)(Math.random()*5)+1;
				}
			}
		}
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=5;j++) {
				int tmp = matrix[i+b[i][j]][j];
				matrix[i+b[i][j]][j] = matrix[i][j];
				matrix[i][j] = tmp;
			}	
		}
		
	}
	public static int maximumNumber(int score) {
		if(score > 10000) return 8;
		else if(score > 5000)return 7;
		else if(score > 1000) return 6;
		else return 5;
		
	}
	
}
