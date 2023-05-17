package TapMePlusOne;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persistence {
	
	public static void saveGame(TButton[][] pad, int score, int life) {
		try {
			FileWriter f = new FileWriter("gameData.txt");
			f.write(score + "\n");
			f.write(life + "\n");
			for(int i=1;i<=5;i++) {
				for(int j=1;j<=5;j++) {
					f.write(pad[i][j].getText());
					f.write(" ");
				}
				f.write("\n");
			}
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block

		    System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	public static List<Object> readPad() {
		int[][] pad = new int[7][7];
		int score;
		int life;
		List<Object> result = new ArrayList<>();
		try {
			
			File f = new File("gameData.txt");
			Scanner s = new Scanner(f);
			score = s.nextInt();
			life = s.nextInt();
			for(int i=1;i<=5;i++) {
				for(int j=1;j<=5;j++) {
					pad[i][j] = s.nextInt();
				}
			}
			result.add(pad);
			result.add(score);
			result.add(life);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
}
