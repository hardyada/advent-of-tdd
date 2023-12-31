package org.advent.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class CalibrationReader {

    private Map<String,String> numberMapB = Map.of(
    "one", "1", 
    "two", "2", 
    "three", "3",
     "four", "4", 
    "five", "5",
     "six", "6",
    "seven", "7", 
    "eight", "8",
     "nine", "9");

     private Map<String,String> digit3 = Map.of("one", "1", 
    "two", "2", 
    "six", "6");

    private Map<String, String> digit4 = Map.of( "four", "4", 
    "five", "5",
    "nine", "9");

    private Map<String, String> digit5 = Map.of("three", "3",
     "seven", "7", 
    "eight", "8"
    );

     private Map<String,String> numberMapA = Map.of(
        "threeight","3ight",
        "twone", "2ne",
        "nineight", "9ight",
        "fiveight", "5ight",
        "eighthree", "8hree",
        "eightwo", "8wo",
        "oneight", "1ight",
        "sevenine", "7ine");


    
    public static void main(String args[]) {
         ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("calibration.txt");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

       Stream<String> linesStream=reader.lines();

        CalibrationReader cr= new CalibrationReader();

       int result = cr.processLines(linesStream);

      System.out.println("Answer is: " + result);

    }

    public CalibrationReader() {

    }

    public int processLines(Stream<String> lines) {
        int result = 0;

        result = lines.map(this::processLine).
        mapToInt(ld -> Integer.valueOf(ld.firstNumber + ld.lastNumber)).sum();

        return result;
    }

    public LineDetails processLine(String line) {
         String firstNumber=null, lastNumber=null;
        


        for(int count=0; count < line.length(); count++) {
            String leftCharString = line.substring(count, count+1);
            String rightCharString = line.substring(line.length()-count-1,line.length()-count);
            if(StringUtils.isNumeric(leftCharString) && firstNumber==null) {
                firstNumber = leftCharString;
            } else if(firstNumber==null){
                //expand the window for number words
                Iterator<Entry<String,String>> i = numberMapB.entrySet().iterator();
                while(i.hasNext()) {
                    Entry<String,String> entry = i.next();
                    if(!(entry.getKey().length()+count>line.length())) {
                        if(line.substring(count, count+entry.getKey().length()).equals(entry.getKey())) {
                            firstNumber = entry.getValue();
                        }
                    }
                }
            }

            if(StringUtils.isNumeric(rightCharString) && lastNumber==null) {
                lastNumber = rightCharString;
            } else if(lastNumber==null) {
                  //expand the window for number words
                Iterator<Entry<String,String>> i = numberMapB.entrySet().iterator();
                while(i.hasNext()) {
                    Entry<String,String> entry = i.next();
                    if(!(line.length()-1-count-entry.getKey().length()<0)) {
                        if(line.substring(line.length()-count-entry.getKey().length(),  line.length()-count).equals(entry.getKey())) {
                            lastNumber = entry.getValue();
                        }
                    }
                }
            }

            if(firstNumber!=null && lastNumber!=null) {
                System.out.println("line: " + line);
                System.out.println("line details: " + firstNumber + " " +lastNumber);
                return new LineDetails(firstNumber, lastNumber);
            }

        }

        if(firstNumber==null) {
            firstNumber="0";
        }

        if(lastNumber==null) {
            lastNumber="0";
        }

        return new LineDetails(firstNumber, lastNumber);
    }

    public record LineDetails(String firstNumber, String lastNumber){}

}
