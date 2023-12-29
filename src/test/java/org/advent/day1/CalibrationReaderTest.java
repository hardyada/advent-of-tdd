package org.advent.day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;



public class CalibrationReaderTest {

@Test
public void testProcessLine() {
    CalibrationReader cr = new CalibrationReader();
    CalibrationReader.LineDetails ld = cr.processLine("1abc2");

    assertEquals("1", ld.firstNumber());
    assertEquals("2", ld.lastNumber());

    ld = cr.processLine("a1b2c3d4e5f");

    assertEquals("1", ld.firstNumber());
    assertEquals("5", ld.lastNumber());

    ld = cr.processLine("treb7uchet");

    assertEquals("7", ld.firstNumber());
    assertEquals("7", ld.lastNumber());

}

@Test
public void testProcessLines() {
    CalibrationReader cr = new CalibrationReader();

   List<String> sample = List.of("1abc2\n",
                                "pqr3stu8vwx\n",
                                "a1b2c3d4e5f\n",
                                "treb7uchet\n");

assertEquals(142, cr.processLines(sample.stream()));

}

}
