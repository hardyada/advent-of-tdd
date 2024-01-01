package org.advent.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;



public class GameParserTest {

@Test
public void testProcessLine() {
    GameParser gp = new GameParser(12,13,14);
    GameParser.GameDetails gd = gp.processLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

    assertEquals(1, gd.gameId());
    assertEquals(3,gd.turns().size());



}

@Test
public void testProcessLines() {
  
    List<String> input= List.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");

    GameParser gp = new GameParser(12,13,14);

    assertEquals(8,gp.processLines(input.stream()));

}

@Test
public void testPowerSum() {
  
    List<String> input= List.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");

    GameParser gp = new GameParser(12,13,14);

    assertEquals(2286,gp.processMinSumPower(input.stream()));

}

@Test
public void testGamePower() {
    GameParser gp = new GameParser(12,13,14);
    GameParser.GameDetails gd = gp.processLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

    assertEquals(48, gp.gameMinSetPower(gd));
}

}
