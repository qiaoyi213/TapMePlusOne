package TapMePlusOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.util.Pair;

public class ScoreWrapper {
	public static void addScore(String name, int score) throws Exception {
		
		Pair<String, String> token = TokenWrapper.getToken();
		String privateToken = token.getKey();
		String publicToken = token.getValue();
		if(token == null) {
			return;
		}
		URL url = new URL("http://dreamlo.com/lb/"+ privateToken +"/add/" + name + "/" + Integer.toString(score));
		URLConnection connection = url.openConnection();
		try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			String line;
			while((line = in.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
	public static ArrayList<Pair<String, Integer>>  getScoreBoard() throws Exception{

		Pair<String, String> token = TokenWrapper.getToken();
		
		String publicToken = token.getValue();
		URL url = new URL("http://dreamlo.com/lb/"+ publicToken + "/json-asc");
		URLConnection connection = url.openConnection();
		String s = "";
		try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			String line;
			while((line = in.readLine()) != null) {
				s += line;
			}
		}
		System.out.println(s);

        ArrayList<Pair<String, Integer>> scoreboard = new ArrayList<>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(s);
			JSONObject dreamloObject = (JSONObject) jsonObject.get("dreamlo");
            JSONObject leaderboardObject = (JSONObject) dreamloObject.get("leaderboard");

            System.out.println("A");
            try {
            	JSONArray entryArray = (JSONArray) leaderboardObject.get("entry");
                for (Object entryObj : entryArray) {
                    JSONObject entry = (JSONObject) entryObj;
                    String name = (String) entry.get("name");
                    String score = (String) entry.get("score");
                    scoreboard.add(new Pair<String, Integer>(name, Integer.parseInt(score)));
                }
            } catch (Exception e) {
            	JSONObject one = (JSONObject) leaderboardObject.get("entry");
            	scoreboard.add(new Pair<String, Integer>((String) one.get("name"), Integer.parseInt((String) one.get("score"))));
            }
            
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		Collections.reverse(scoreboard);
        return scoreboard;	
    }
	
	public static void isExist(String username) throws Exception {

		Pair<String, String> token = TokenWrapper.getToken();
		String privateToken = token.getKey();
		String publicToken = token.getValue();
		URL url = new URL("http://dreamlo.com/lb/" + publicToken +"/pipe-get/" + username);
		URLConnection connection = url.openConnection();
		String s = "";

		try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			String line;
			while((line = in.readLine()) != null) {
				s += line;
			}
		} catch(Exception e) {
			throw e;
		}
		if(!s.equals("")) {
			System.out.println("Exist");
			throw new Exception();
		}
	}
}
