package com.groupv.puzzles;

import com.groupv.puzzles.Parser.InkiesParser;
import com.groupv.puzzles.Parser.SuguruParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertEquals;


public class ParserTests {

    // Partitions: SuguruParser parse valid format
    // Subdomain:  SuguruParser parse valid format
    @Test
    void test_suguru_parse() throws IOException, URISyntaxException {
        String expected = "5\n a d e2b3c f \n a e e e \n a h e i \n a h i i \n h h h i3g ";
        SuguruParser suguruParser = new SuguruParser();
        String actual = suguruParser.parse("suguru.txt").toString();
        assertEquals(expected, actual);
    }

    // Partitions: InkiesParser parse valid format
    // Subdomain:  InkiesParser parse valid format
    @Test
    void test_inkies_parse() throws IOException, URISyntaxException {
       String expected = "3\nX 3 X \n1 X 3 \n3 X 2 ";
        InkiesParser inkiesParser = new InkiesParser();
        String actual = inkiesParser.parse("inkies.txt").toString();
        assertEquals(expected, actual);
    }
}
