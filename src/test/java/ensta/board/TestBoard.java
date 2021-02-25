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

    @Test
    public void sendHitStrikeTest() {
        Board testBoard = new Board("Test Board", 15);
        AbstractShip destroyer = new Destroyer();
        destroyer.setOrientation(Orientation.EAST);
        ShipState ship = new ShipState(destroyer);
        try {
            testBoard.putShip(ship, 2, 2);
        } catch (Exception e) {
            System.out.println(e);
        }
        Hit hit = testBoard.sendHit(2, 2);
        assertEquals(Hit.STRIKE, hit);
    }

    @Test
    public void sendHitMissTest() {
        Board testBoard = new Board("Test Board", 15);
        AbstractShip destroyer = new Destroyer();
        destroyer.setOrientation(Orientation.EAST);
        ShipState ship = new ShipState(destroyer);
        try {
            testBoard.putShip(ship, 2, 2);
        } catch (Exception e) {
            System.out.println(e);
        }
        Hit hit = testBoard.sendHit(3, 3);
        assertEquals(Hit.MISS, hit);
    }

    @Test
    public void sendHitSinkTest() {
        Board testBoard = new Board("Test Board", 15);
        AbstractShip destroyer = new Destroyer();
        destroyer.setOrientation(Orientation.EAST);
        ShipState ship = new ShipState(destroyer);
        try {
            testBoard.putShip(ship, 2, 2);
        } catch (Exception e) {
            System.out.println(e);
        }
        testBoard.sendHit(2, 2);
        Hit hit = testBoard.sendHit(3, 2);
        assertEquals(Hit.DESTROYER, hit);
    }
}
