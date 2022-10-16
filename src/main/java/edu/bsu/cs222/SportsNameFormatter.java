package edu.bsu.cs222;

public class SportsNameFormatter {

    //Reformats the selected sport name to be able to work through the API url and returns as String
    public String NameReformatted(String userChoice) {
        if (userChoice == "American Football") {
            return "americanfootball";
        }
        else if (userChoice == "Baseball"){
            return "baseball_mlb";
        }
        else if (userChoice == "Ice Hockey") {
            return "icehockey";
        }
        else if (userChoice == "Mixed Martial Arts") {
            return "mma_mixed_martial_arts";
        }
        else {
            return userChoice;
        }
    }
}
