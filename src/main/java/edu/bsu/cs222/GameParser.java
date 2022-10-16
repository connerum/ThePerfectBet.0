package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GameParser {
    //Initialized API Json Response for all the games from the users selected sport, and returns as Inputstream
    public InputStream ParseGames(String Sport) {
        String BaseApiUrl = "https://api.the-odds-api.com/v4/sports/";
        String EndApiUrl = "/odds/?apiKey=2446fc3219989c3e2fe587845e6ed627&regions=us&markets=h2h&bookmakers=draftkings,spreads&oddsFormat=american";
        String CombinedUrl = BaseApiUrl + Sport + EndApiUrl;
        try {
            URL url = new URL(CombinedUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            return inputStream;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Parses the Inputstream and Returns all the Home Teams as an ArrayList
    public ArrayList<String> HomeTeams(InputStream inputStream) throws IOException {
        JSONArray homeTeamArray = JsonPath.read(inputStream, "$..home_team");
        int listLength = homeTeamArray.size();
        ArrayList<String> homeTeamList = new ArrayList<>();

        for (int i = 0; i < listLength; i++) {
            homeTeamList.add(homeTeamArray.get(i).toString());
        }
        return homeTeamList;
    }


    //Parses the Inputstream and Returns all the Away Teams as an ArrayList
    public ArrayList<String> AwayTeams(InputStream inputStream) throws IOException {
        JSONArray awayTeamArray = JsonPath.read(inputStream, "$..away_team");
        int listLength = awayTeamArray.size();
        ArrayList<String> awayTeamList = new ArrayList<>();

        for (int i = 0; i < listLength; i++) {
            awayTeamList.add(awayTeamArray.get(i).toString());
        }
        return awayTeamList;
    }


    //Parses the Inputstream and Returns all the Game IDs for the selected sport as an ArrayList
    public ArrayList<String> GameId(InputStream inputStream) throws IOException {
        JSONArray gameIdArray = JsonPath.read(inputStream, "$..id");
        int listLength = gameIdArray.size();
        ArrayList<String> gameIdList = new ArrayList<>();

        for (int i = 0; i < listLength; i++) {
            gameIdList.add(gameIdArray.get(i).toString());
        }
        return gameIdList;
    }
}
