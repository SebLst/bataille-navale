package ensta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ensta.ships.*;
import ensta.ships.AbstractShip.Orientation;

/**
 * Instead of having a main function, I implemented a series of tests.
 */
public class TestBoard {

    @Test
    public void boardFirstConstructorTest() {
        Board myBoard = new Board("Parameterized constructor board (12)", 12);
        myBoard.print();
    }

    @Test
    public void boardSecondConstructorTest() {
        Board myBoard = new Board("Default constructor coard");
        myBoard.print();
    }

    @Test
    public void boardPutShipTest() {
        Board testBoard = new Board("Put ship test");
        Submarine sub1 = new Submarine(Orientation.NORTH);
        Submarine sub2 = new Submarine(Orientation.EAST);
        Submarine sub3 = new Submarine(Orientation.SOUTH);
        Submarine sub4 = new Submarine(Orientation.WEST);

        // should make a cross if everything is ok
        testBoard.putShip(sub1, 5, 4); // goes to the north
        testBoard.putShip(sub2, 6, 5); // goes to the east
        testBoard.putShip(sub3, 5, 6); // goes to the south
        testBoard.putShip(sub4, 4, 5); // goes to the west
        testBoard.print();
    }

    @Test
    public void boardHasShipTest() {
        Board testBoard = new Board("Put ship test");
        Submarine sub1 = new Submarine(Orientation.NORTH);
        testBoard.putShip(sub1, 5, 4);
        assertEquals(true, testBoard.hasShip(5, 4));
        assertEquals(true, testBoard.hasShip(5, 3));
        assertEquals(true, testBoard.hasShip(5, 2));
        assertEquals(false, testBoard.hasShip(5, 1));
    }

    @Test
    public void boardSendHitTest() {
        Board testBoard = new Board("Send hit test");
        Submarine sub1 = new Submarine(Orientation.NORTH);
        testBoard.putShip(sub1, 5, 4);
        Hit hit1 = testBoard.sendHit(5, 4);
        Hit hit2 = testBoard.sendHit(5, 3);
        Hit hit3 = testBoard.sendHit(5, 2);
        Hit hit4 = testBoard.sendHit(5, 1);
        assertEquals(Hit.STRIKE, hit1);
        assertEquals(Hit.STRIKE, hit2);
        assertEquals(Hit.SUBMARINE, hit3);
        assertEquals(Hit.MISS, hit4);
        assertEquals(true, testBoard.ships[5][2].getShip().isSunk());
        testBoard.printShips();
    }

}
