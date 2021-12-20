package tests;
import src.Maze;
import src.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {


    // Okay maze
    private StringReader sr1 = new StringReader("S ****************\n" +
                                                    "*             *\n" +
                                                    "* ******** *****\n" +
                                                    "*    *   *     G\n" +
                                                    "**G**************\n");
    // No goal
    private StringReader sr2 = new StringReader("S ***************\n" +
                                            "*             *\n" +
                                            "* ******** *****\n" +
                                            "*    *   *     \n" +
                                            "*****************\n");

    // Several Starts
    private StringReader sr3 = new StringReader("S ***************\n" +
                                                "*             *\n" +
                                                "* *****S** *****\n" +
                                                "*    *   *     G\n" +
                                                "********S********\n");
    //Non movable maze
    private StringReader sr4 = new StringReader("*\n" +
                                            "*S*\n" +
                                            "*");
    //No start, goal at 0,0
    private StringReader sr5 = new StringReader("\"G ***************\\n\" +\n" +
                                            "\"*             *\\n\" +\n" +
                                            "\"* ******** *****\\n\" +\n" +
                                            "\"*    *   *     \\n\" +\n" +
                                            "\"*****************\\n\");");
    private StringReader sr6 = new StringReader("G ***************\\n\" +\n" +
                                                    "\"*             *\\n\" +\n" +
                                                    "\"* ******** *****\\n\" +\n" +
                                                    "\"*    *   *     \\n\" +\n" +
                                                    "\"***************S*\\n\");");

    @Test
    void Maze(){

        assertThrows(IllegalArgumentException.class, () -> {
            Maze m = new Maze(sr3);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Maze m = new Maze(sr5);
        });

    }


    @Test
    void isMovable() {

        Maze m = new Maze (sr4);

        //Test all directions.
        assertFalse(m.isMovable(m.getStart().getPosToNorth()));
        assertFalse(m.isMovable(m.getStart().getPosToSouth()));
        assertFalse(m.isMovable(m.getStart().getPosToWest()));
        assertFalse(m.isMovable(m.getStart().getPosToEast()));


        Maze maze = new Maze (sr1);
        assertTrue(maze.isMovable(maze.getStart().getPosToEast()));

    }

    @Test
    void isGoal() {

        Maze m = new Maze(sr6);
        double rand = Math.random() * 100 + 1;
        Position p = new Position(0, 0);
        Position p1 = new Position((int) rand, (int) rand);
        assertTrue(m.isGoal(p));

        assertFalse(m.isGoal(p1));


    }

    @Test
    void getStart() {

        Maze m = new Maze(sr1);
        double rand = Math.random() * 100 + 1;

        Position p = new Position(0,0);
        Position p1 = new Position((int) rand, (int) rand);

        assertEquals(m.getStart(),p);

        assertNotEquals(p1, m.getStart());

        assertEquals(0, m.getStart().getX());
        assertEquals(0, m.getStart().getY());


    }

    @Test
    void getNumColumns() {

        Maze m = new Maze(sr1);
        assertEquals(18,m.getNumColumns());
    }

    @Test

    void getNumRows() {

        Maze m = new Maze(sr1);
        assertEquals(5,m.getNumRows());
    }
}