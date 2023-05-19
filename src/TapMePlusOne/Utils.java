package TapMePlusOne;

public class Utils {
	public static boolean isArrayAllFalse(boolean[][] b, int x, int y) {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(b[i][j] == true) {
					return false;
				}
			}
		}
		return true;
	}
}
