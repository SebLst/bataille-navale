package ensta;

import org.junit.Test;

import ensta.ships.*;

public class TestGame {

    /**
     * Pauses the execution
     * 
     * @param ms Pause time in ms
     */
    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aiGameTest() {
        Board board = new Board("Player Board");
        board.print();

        AbstractShip[] ships = { new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier() };

        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);
        int shipsDestroyed = 0;
        int[] coords = { 0, 0 };
        Hit hit;

        while (shipsDestroyed != ships.length) {
            hit = ai.sendHit(coords);
            System.out
                    .println("Strike at : " + Integer.toString(coords[0] + 1) + ", " + Integer.toString(coords[1] + 1));
            if (hit != Hit.MISS && hit != Hit.STRIKE) {
                System.out.println(hit.toString() + " coulé");
                shipsDestroyed++;
            } else {
                System.out.println(hit.toString());
            }

            board.print();
            // sleep(500);
        }

    }
}
