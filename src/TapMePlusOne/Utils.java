package TapMePlusOne;

public class Utils {
	public static boolean isArrayAllFalse(boolean[][] b, int x, int y) {
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				if(b[i][j] == true) {
					return false;
				}
			}
		}
		return true;
	}
}
