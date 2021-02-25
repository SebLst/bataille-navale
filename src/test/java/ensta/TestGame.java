package ensta;

import java.util.*;
import org.junit.Test;

import ensta.ships.*;
import ensta.board.*;

public class TestGame {

    @Test
    public void gameTest() {
        int boardSize = 10;
        Board board = new Board("Player Board", boardSize);
        board.print();

        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new AircraftCarrier());

        BattleShipsAI ai = new BattleShipsAI(board, board);
        int destroyedShipsByAI = 0;
        ai.putShips(ships);
        Hit hit = null;

        while (destroyedShipsByAI != 5) {
            Random rnd = new Random();
            int[] coords = new int[2];
            coords[0] = rnd.nextInt(boardSize);
            coords[1] = rnd.nextInt(boardSize);
            hit = ai.sendHit(coords);
            System.out.println("Tir en " + Integer.toString(coords[0]) + " ," + Integer.toString(coords[0])
                    + " résultats : " + hit.toString());
            if (hit == Hit.DESTROYER || hit == Hit.SUBMARINE || hit == Hit.BATTLESHIP || hit == Hit.CARRIER) {
                destroyedShipsByAI++;
                System.out.println("Nombre de bateaux détruits :" + Integer.toString(destroyedShipsByAI));
            }
            board.print();
            // try {
            // Thread.sleep(500);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }

    }

}
