package tests;
import .*;
import org.junit.jupiter.api.Test;

import src.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    double rand = Math.random();
    Position p = new Position((int) rand,(int)rand);
    @Test
    void getX() {
        assertEquals((int)rand, p.getX());
    }

    @Test
    void getY() {
        assertEquals((int)rand, p.getY());
    }

    @Test
    void getPosToSouth() {
        assertEquals((int)rand -1, p.getY()-1);
    }

    @Test
    void getPosToNorth() {
        assertEquals((int)rand + 1, p.getY() + 1);
    }

    @Test
    void getPosToWest() {
        assertEquals((int)rand - 1, p.getX() - 1);
    }

    @Test
    void getPosToEast() {
        assertEquals((int)rand + 1, p.getX() + 1);
    }
}