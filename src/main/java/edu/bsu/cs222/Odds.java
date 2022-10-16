package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Odds {

    //Initialized API Json Response for the odds of the selected game and returns as Inputstream
    public InputStream OddsParserApi(String Sport, String GameId) {
        String BaseApiUrl = "https://api.the-odds-api.com/v4/sports/";
        String MidApiUrl = "/odds/?apiKey=2446fc3219989c3e2fe587845e6ed627&regions=us&markets=h2h&bookmakers=draftkings,spreads&eventIds=";
        String EndApiUrl = "&oddsFormat=american";
        String CombinedUrl = BaseApiUrl + Sport + MidApiUrl + GameId + EndApiUrl;
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


    //Parses the Inputstream and Returns the Odds for both teams in an ArrayList
    public ArrayList<String> oddsParser(InputStream inputStream) throws IOException {
        JSONArray oddsArray = JsonPath.read(inputStream, "$..price");
        int listLength = oddsArray.size();
        ArrayList<String> oddsList = new ArrayList<>();

        for (int i = 0; i < listLength; i++) {
            oddsList.add(oddsArray.get(i).toString());
        }
        return oddsList;
    }
}