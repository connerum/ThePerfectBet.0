package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SportsParser {
    //Initialized API Json Response for available sports names and returns as Inputstream
    public InputStream apiParser() {
        String ApiUrl = "https://api.the-odds-api.com/v4/sports?apiKey=2446fc3219989c3e2fe587845e6ed627";
        try {
            URL url = new URL(ApiUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            return inputStream;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Parses the Inputstream and Returns the available in-season sports(excluding golf) as an ArrayList
    public ArrayList<String> sports(InputStream inputStream) throws IOException {
        JSONArray sportsArray = JsonPath.read(inputStream, "$..group");
        int listLength = sportsArray.size();
        ArrayList<String> sportsList = new ArrayList<>();

        for(int i=0; i < listLength; i++){
            if( !sportsList.contains(sportsArray.get(i)) ){
                sportsList.add(sportsArray.get(i).toString());
            }
        }
        sportsList.remove("Golf");
        return sportsList;
    }
}