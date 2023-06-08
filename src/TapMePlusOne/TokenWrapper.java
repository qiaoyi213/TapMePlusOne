package TapMePlusOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TokenWrapper {
	public static String getToken() {
		try {
			File f = new File("token.txt");
			Scanner s = new Scanner(f);
			String token = s.nextLine();
			System.out.println(token);
			return token;
		} catch (Exception e) {
			System.out.println("You don't have the token.");
			return "";
		}
	}
}
