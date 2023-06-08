package TapMePlusOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.util.Pair;

public class TokenWrapper {
	public static Pair<String, String> getToken() {
		try {
			File f = new File("token.txt");
			Scanner s = new Scanner(f);
			String PrivateToken = s.nextLine();
			String PublicToken = s.nextLine();
			return new Pair<>(PrivateToken, PublicToken);
		} catch (Exception e) {
			System.out.println("You don't have the token.");
			return null;
		}
	}
}
