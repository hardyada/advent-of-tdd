package org.advent.day1;

import java.util.Comparator;

/**
 * Elf that is assigned calories
 */
public class Elf implements Comparable<Elf> {

    private int totalCalories = 0;

    /**
     * Calories carried by the Elf
     * @return total calories
     */
    public int getTotalCalories() {
        return totalCalories;
    }

    public void addCalories(int calories) {
        this.totalCalories+=calories;
    }

    @Override
    public int compareTo(Elf other) {
        return Comparator.comparing(Elf::getTotalCalories).compare(this, other);
    }

}
