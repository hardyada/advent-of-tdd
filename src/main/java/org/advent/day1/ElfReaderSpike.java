package org.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


public class ElfReaderSpike {

    public static void main(String[] args) {
        List<Elf> elves = new ArrayList<>();


    

        Stream<String> lineStream=null;
        Path path = FileSystems.getDefault().getPath("src/main/resources", "day1-elf-calories.txt");
        try {
             lineStream = Files.lines(path);
            Iterator<String> i= lineStream.iterator();

            int runningTotal = 0;
            
            while(i.hasNext()) {
                String next = i.next();
                if(next.equals("")) {
                    elves.add(new Elf(runningTotal));
                    runningTotal=0;
                } else {
                   runningTotal += Integer.parseInt(next);
                }
            }

            //don't miss the last one
            elves.add(new Elf(runningTotal));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(lineStream!=null) {
                lineStream.close();
            }
        }
    }

}
