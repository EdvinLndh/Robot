package tests;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
import Ou1.*;
import src.Maze;
import src.MemoryRobot;

class MemoryRobotTest {

    private StringReader sr1 = new StringReader("S ****************\n" +
            "*             *\n" +
            "* ******** *****\n" +
            "*    *   *     G\n" +
            "**G**************\n");
    Maze m = new Maze(sr1);
    MemoryRobot memBot = new MemoryRobot(m);

    @Test
    void getPosition() {
        assertEquals(m.getStart(),memBot.getPosition());
        memBot.move();
        assertNotEquals(m.getStart(), memBot.getPosition());
    }

    @Test
    void hasReachedGoal() {
        while(!m.isGoal(memBot.getPosition())){
            memBot.move();
        }
        assertTrue(memBot.hasReachedGoal());

    }
}