package tests;
import .*;
import org.junit.jupiter.api.Test;

import src.Maze;
import src.RandomRobot;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class RandomRobotTest {

    private StringReader sr1 = new StringReader("S ****************\n" +
            "*             *\n" +
            "* ******** *****\n" +
            "*    *   *     G\n" +
            "**G**************\n");
    Maze m = new Maze(sr1);
    RandomRobot randBot = new RandomRobot(m);

    @Test
    void getPosition() {
        assertEquals(m.getStart(),randBot.getPosition());
        randBot.move();
        assertNotEquals(m.getStart(), randBot.getPosition());
    }

    @Test
    void hasReachedGoal() {
        assertFalse(randBot.hasReachedGoal());
        while(!m.isGoal(randBot.getPosition())){
            randBot.move();
        }
        assertTrue(randBot.hasReachedGoal());

    }
}