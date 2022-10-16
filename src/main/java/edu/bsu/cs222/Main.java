package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        //Parses the Names of all in-season Sports
        SportsParser sportsParser = new SportsParser();
        InputStream inputStream = sportsParser.apiParser();
        ArrayList<String> SportsList =  sportsParser.sports(inputStream);


        //Example String of the Users Input for testing (Can be changed to any of the Sport names from the "SportsList" above)
        String userChoice = "American Football";


        //Formats the users Sports choice to prepare for passing through the API url
        SportsNameFormatter sportsNameFormatter = new SportsNameFormatter();
        userChoice = sportsNameFormatter.NameReformatted(userChoice);


        //Based on the users sports choice, parses Home-Teams, Away-Teams, and their ID from the API
        GameParser gameParser = new GameParser();

        InputStream inputStream1 = gameParser.ParseGames(userChoice);
        ArrayList<String> HomeTeams = gameParser.HomeTeams(inputStream1);

        InputStream inputStream2 = gameParser.ParseGames(userChoice);
        ArrayList<String> AwayTeams = gameParser.AwayTeams(inputStream2);

        InputStream inputStream3 = gameParser.ParseGames(userChoice);
        ArrayList<String> GameIds = gameParser.GameId(inputStream3);


        //Uses the users sports choice and Selected Game ID to parse the Odds of said game
        Odds odds = new Odds();
        InputStream inputStream4 = odds.OddsParserApi(userChoice, GameIds.get(3));
        ArrayList<String> OddsList = odds.oddsParser(inputStream4);


        System.out.println(SportsList);
        System.out.println(HomeTeams.get(3));
        System.out.println(AwayTeams.get(3));
        System.out.println(GameIds.get(3));
        System.out.println(OddsList);
    }
}