package org.advent.day2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class GameParser {

    private final int red,green,blue;

    public GameParser(int red, int green, int blue) {
        this.red=red;
        this.green=green;
        this.blue=blue;
    }



    
    public static void main(String args[]) {
         ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("cube.txt");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

       Stream<String> linesStream=reader.lines();

        GameParser  gp= new GameParser(12,13,14);


      int powerSum = gp.processMinSumPower(linesStream);

      System.out.println("Sum power: " + powerSum);

    }

  

    public int processLines(Stream<String> lines) {
       int result = 0;

        List<GameDetails> gameDetails = lines.map(this::processLine).collect(Collectors.toList());
        
        //iterate games and see if they are valid
        result = gameDetails.stream().filter(this::isGameValid).collect(Collectors.summingInt(GameDetails::gameId));
        

        return result;
    }

    public int processMinSumPower(Stream<String> lines) {
        int result = 0;

        List<GameDetails> gameDetails = lines.map(this::processLine).collect(Collectors.toList());

        result = gameDetails.stream().collect(Collectors.summingInt(this::gameMinSetPower));

        return result;
    }

    public boolean isGameValid(GameDetails gd) {
        boolean result = true;

        Turn invalidTurn = gd.turns.stream().filter(t ->  (t.red>this.red || t.blue > this.blue || t.green > this.green)).findFirst().orElse(null);
        if(invalidTurn!=null) {
            return false;
        }

        return result;
    }

    public int gameMinSetPower(GameDetails game) {
        int result =0;

        //iterate the turns and find the max of red,green and blue, then return the multiplication of those
        int maxRed = 0, maxGreen = 0, maxBlue = 0;

        for(Turn turn: game.turns) {
            if(turn.red > maxRed) {
                maxRed=turn.red;
            }

            if(turn.green > maxGreen) {
                maxGreen=turn.green;
            }

            if(turn.blue > maxBlue) {
                maxBlue = turn.blue;
            }
        }

        result = maxRed * maxBlue * maxGreen;

        return result;
    }

    public GameDetails processLine(String line) {
       String[] firstParts = line.split(":");

       int gameId = Integer.valueOf(firstParts[0].replaceAll("Game ", ""));

       String[] secondParts = firstParts[1].split(";");

       List<Turn> turns= new ArrayList<Turn>();

       for(int count=0; count<secondParts.length; count++) {
        String[] colourParts = secondParts[count].split(",");
            int red=0,green=0,blue=0;

            for(int count2=0; count2<colourParts.length; count2++) {
                String[] turnParts = colourParts[count2].trim().split(" ");
                
                int cubeNumber = Integer.valueOf(turnParts[0]);

                switch(turnParts[1]) {
                    case "red": red = cubeNumber; break;
                    case "green": green = cubeNumber; break;
                    case "blue": blue = cubeNumber; break;
                }
            }

            turns.add(new Turn(red, green, blue));
       }

       return new GameDetails(gameId, turns);
    }

    public record Turn(int red, int green, int blue){}

    public record GameDetails(int gameId, List<Turn> turns){}

}
