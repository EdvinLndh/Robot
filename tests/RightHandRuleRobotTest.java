package tests;
import .*;
import org.junit.jupiter.api.Test;

import src.Maze;
import src.RightHandRuleRobot;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class RightHandRuleRobotTest {

    private StringReader sr1 = new StringReader("S ****************\n" +
            "*             *\n" +
            "* ******** *****\n" +
            "*    *   *     G\n" +
            "**G**************\n");
    Maze m = new Maze(sr1);
    RightHandRuleRobot rHandRruleBot = new RightHandRuleRobot(m);
    
    @Test
    void getPosition() {
        assertEquals(m.getStart(),rHandRruleBot.getPosition());
        rHandRruleBot.move();
        assertNotEquals(m.getStart(), rHandRruleBot.getPosition());
    }

    @Test
    void hasReachedGoal() {
        while(!m.isGoal(rHandRruleBot.getPosition())){
            rHandRruleBot.move();
        }
        assertTrue(rHandRruleBot.hasReachedGoal());
    }
}