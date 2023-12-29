package org.advent.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class CalibrationReader {
    
    public static void main(String args[]) {
         ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("calibration.txt");

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

       Stream<String> linesStream=reader.lines();

        CalibrationReader cr= new CalibrationReader();

       int result = cr.processLines(linesStream);

      System.out.println("Answer is result: " + result);

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
            String rightCharString = line.substring(line.length()-1-count, line.length()-count);
            if(StringUtils.isNumeric(leftCharString) && firstNumber==null) {
                firstNumber = leftCharString;
            }

            if(StringUtils.isNumeric(rightCharString) && lastNumber==null) {
                lastNumber = rightCharString;
            }

            if(firstNumber!=null && lastNumber!=null) {
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
