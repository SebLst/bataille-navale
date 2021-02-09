package ensta;

import ensta.board.*;
import ensta.ships.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Board MyBoard = new Board("My Board name", 16);

        Destroyer MyDestroyer = new Destroyer(Orientation.NORTH);
        Submarine MySubmarine = new Submarine(Orientation.SOUTH);
        BattleShip MyBattleShip = new BattleShip(Orientation.EAST);
        AircraftCarrier MyAircraftCarrier = new AircraftCarrier(Orientation.WEST);
        try {
            MyBoard.putShip(MyDestroyer, 5, 8);
            MyBoard.putShip(MySubmarine, 2, 2);
            MyBoard.putShip(MyBattleShip, 10, 0);
            MyBoard.putShip(MyAircraftCarrier, 3, 12);
        } catch (Exception e) {
            System.out.println(e);
        }

        MyBoard.setHit(false, 3, 7);
        MyBoard.setHit(false, 12, 12);
        MyBoard.setHit(false, 2, 2);
        MyBoard.print();
    }
}
