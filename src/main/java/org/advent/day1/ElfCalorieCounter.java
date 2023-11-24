package org.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ElfCalorieCounter {
    private final List<Elf> elves = new ArrayList<>();

    public ElfCalorieCounter(BufferedReader bufferedReader) {
        try {
            Elf elf = new Elf();
            boolean firstRead = true;
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                if(line.isBlank() || firstRead) {
                    elf = new Elf();
                    elves.add(elf);
                    if(firstRead) {
                        elf.addCalories(Integer.parseInt(line));
                        firstRead = false;
                    }
                } else {
                    elf.addCalories(Integer.parseInt(line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int maxCalories() {
        return elves.stream().max(Elf::compareTo).get().getTotalCalories();
    }

    public int top3Total() {
        return elves.stream().sorted(Collections.reverseOrder()).limit(3).collect(Collectors.summingInt(Elf::getTotalCalories));

    }
}
