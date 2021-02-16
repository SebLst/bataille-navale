package ensta.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ensta.ships.*;

public class TestBoard {
    @Test
    public void constructorTooSmallTest() {
        Board testBoard = new Board("Test Board", 5);
        assertEquals(10, testBoard.boardSize);
    }

    @Test
    public void constructorTooBigTest() {
        Board testBoard = new Board("Test Board", 30);
        assertEquals(26, testBoard.boardSize);
    }

    @Test
    public void constructorSizeTest() {
        Board testBoard = new Board("Test Board", 15);
        assertEquals(15, testBoard.boardSize);
    }

    // @Test(expected = Exception.class)
    // public void putShipTest() {
    // Board testBoard = new Board("Test Board", 15);
    // Submarine mySubmarine = new Submarine(Orientation.NORTH);
    // testBoard.putShip(mySubmarine, 5, 5);
    // }
}
